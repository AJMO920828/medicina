package com.kosmos.medicina;

import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.kosmos.medicina.entity.Consultorios;
import com.kosmos.medicina.entity.Doctor;
import com.kosmos.medicina.entity.Especialidad;
import com.kosmos.medicina.repository.ConsultorioRepository;
import com.kosmos.medicina.repository.DoctoresRepository;
import com.kosmos.medicina.repository.EspecialidadRepository;

@Component
public class DataInitializer implements ApplicationRunner {

	private final EspecialidadRepository especialidadRepository;
	private final DoctoresRepository doctoresRepository;
	private final ConsultorioRepository consultorioRepository;

    public DataInitializer(EspecialidadRepository especialidadRepository, DoctoresRepository doctoresRepository,ConsultorioRepository consultorioRepository) {
        this.especialidadRepository = especialidadRepository;
        this.doctoresRepository = doctoresRepository;
        this.consultorioRepository = consultorioRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
    	List<Especialidad> list = new ArrayList<>();
    	list.add(new Especialidad(1L, "Cardiología"));
    	list.add(new Especialidad(2L, "Pediatría"));
    	list.add(new Especialidad(3L, "Neurología"));
        if (especialidadRepository.count() < list.size()) {
        	list.forEach(item->{
        		item = especialidadRepository.findById(item.getIdEspecialidad()).orElse(new Especialidad(item.getNombre()));
        		especialidadRepository.save(item);
        	});
        }
        
        List<Doctor> listDoctores = new ArrayList<>();
        listDoctores.add(new Doctor(1L, "Abel","Mendoza","Ortiz",list.get(0)));
        listDoctores.add(new Doctor(2L, "Libia","Gomez","Maya",list.get(1)));
        listDoctores.add(new Doctor(3L, "Sharon","Mendoza","Corona",list.get(2)));
        listDoctores.add(new Doctor(4L, "Ian","Blanco","Gomez",list.get(2)));
        if (doctoresRepository.count() < listDoctores.size()) {
        	listDoctores.forEach(item->{
        		item = doctoresRepository.findById(item.getIdDoctor()).orElse(new Doctor(item));
        		doctoresRepository.save(item);
        	});
        }
        
        List<Consultorios> listConsultorio = new ArrayList<>();
        listConsultorio.add(new Consultorios(1L, 1, 1));
        listConsultorio.add(new Consultorios(2L, 2, 1));
        listConsultorio.add(new Consultorios(3L, 3, 2));
        listConsultorio.add(new Consultorios(4L, 4, 2));
        if (consultorioRepository.count() < listConsultorio.size()) {
        	listConsultorio.forEach(item->{
        		item = consultorioRepository.findById(item.getIdConsultorio()).orElse(new Consultorios(item));
        		consultorioRepository.save(item);
        	});
        }
    }
}
