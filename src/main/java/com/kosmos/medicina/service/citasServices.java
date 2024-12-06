package com.kosmos.medicina.service;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kosmos.medicina.dao.CitasDao;
import com.kosmos.medicina.dto.CitasDto;
import com.kosmos.medicina.entity.Citas;
import com.kosmos.medicina.entity.Consultorios;
import com.kosmos.medicina.entity.Doctor;
import com.kosmos.medicina.repository.CitasRepository;
import com.kosmos.medicina.repository.ConsultorioRepository;
import com.kosmos.medicina.repository.DoctoresRepository;
import jakarta.transaction.Transactional;

@Service
public class citasServices {

	@Autowired
	private CitasRepository citasRepository;
	@Autowired
	private DoctoresRepository doctoresRepository;
	@Autowired
	private ConsultorioRepository consultorioRepository;
	@Autowired
    private CitasDao citasDao;
	
	public static final int HORA_PREVIA_CITA = 2;
	public static final int MAXIMO_CITAS = 8;
	
	@Transactional
	public void guardar(Citas cita) throws Exception {
		if (cita.getDoctor() == null || cita.getDoctor().getIdDoctor() == null) {
			throw new Exception("Seleccione un doctor valido"); 
		}
		if (cita.getConsultorio() == null || cita.getConsultorio().getIdConsultorio() == null) {
			throw new Exception("Seleccione un consultorio valido"); 
		}
		Optional<Doctor> doc = doctoresRepository.findById(cita.getDoctor().getIdDoctor());
		Optional<Consultorios> con = consultorioRepository.findById(cita.getConsultorio().getIdConsultorio());
		if (!doc.isPresent() || !con.isPresent()) {
			throw new Exception("los datos enviados son invalidos");
		}

		Date hoy = new Date();
		if (!cita.getFechaConsulta().after(hoy)) {
			String[] horario = cita.getHorarioConsulta().split(":");
			int horas = Integer.parseInt(horario[0]);
			int minutos = Integer.parseInt(horario[1]);
			LocalTime horaConsulta = LocalTime.of(horas, minutos);
			LocalTime horaActual = LocalTime.now();
			Duration tiempoHastaCita = Duration.between(horaActual, horaConsulta);

			if (tiempoHastaCita.toHours() < HORA_PREVIA_CITA) {
				throw new Exception("Para solicitar una cita, debe ser con al menos dos horas de anticipación.");
			}
		}
		if (citasDao.existeCitaEnConsultorio(cita).isEmpty()) {
			if (citasDao.existeCitaDoctor(cita).isEmpty()) {
				if (citasDao.doctorRecibeCita(cita) < MAXIMO_CITAS) {
					citasRepository.save(cita);
				} else {
					throw new Exception("El doctor ya cuenta con su máximo de citas.");
				}
			} else {
				throw new Exception("No se puede generar la cita ya que el doctor se encuentra ocupado.");
			}
		} else {
			throw new Exception("No se puede generar la cita ya que el consultorio se encuentra ocupado.");
		}
	}
	
	public List<CitasDto> consultarCita(Citas filtro){
		return citasDao.consultarCita(filtro);
	}
}
