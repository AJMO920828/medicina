package com.kosmos.medicina.dao;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.kosmos.medicina.dto.CitasDto;
import com.kosmos.medicina.entity.Citas;

@Repository
public class CitasDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	private NamedParameterJdbcTemplate namedJdbcTemplate;
	private SimpleDateFormat formatter =  new SimpleDateFormat("yyyy-MM-dd");
	
	@SuppressWarnings("deprecation")
	public Integer doctorRecibeCita(Citas citas){
		String sql = "select count(*) as totalCitas from doctor d join citas c on d.id_doctor = c.id_doctor and c.fecha_consulta = ? and d.id_doctor = ?";
		List<Integer> count = jdbcTemplate.query(sql, new Object[] { 
				citas.getFechaConsulta(),
				citas.getDoctor().getIdDoctor()
				},(rs, rowNum) ->
		{
			return rs.getInt("totalCitas");
			
		});
		return count.get(0);
	}
	
	@SuppressWarnings("deprecation")
	public List<Citas> existeCitaDoctor(Citas citas){
		String sql = "select * from citas c "
				+ "where "
				+ "c.id_doctor = ? "
				+ "and c.horario_consulta = ? "
				+ "and c.fecha_consulta = ?";
		return jdbcTemplate.query(sql, new Object[] { 
				citas.getDoctor().getIdDoctor(),
				citas.getHorarioConsulta(),
				citas.getFechaConsulta()
				},(rs, rowNum) ->
		{
			Citas c = new Citas();
			c.setIdCita(rs.getLong("id_cita"));
			return c;
		});
	}
	
	@SuppressWarnings("deprecation")
	public List<Citas> existeCitaEnConsultorio(Citas citas){
		String sql = "select * from citas c "
				+ "where "
				+ "c.id_consultorio = ? "
				+ "and c.horario_consulta = ? "
				+ "and c.fecha_consulta = ?";
		return jdbcTemplate.query(sql, new Object[] { 
				citas.getConsultorio().getIdConsultorio(),
				citas.getHorarioConsulta(),
				citas.getFechaConsulta()
				},(rs, rowNum) ->
		{
			Citas c = new Citas();
			c.setIdCita(rs.getLong("id_cita"));
			return c;
		});
	}
	
	
	
	public List<CitasDto> consultarCita(Citas filtros){
		Map<String, Object> params = new HashMap<>();
		String sql = "select concat(d.nombre,' ',d.apellido_paterno,' ',ifnull(d.apellido_materno,'')) nombre_doctor,\r\n"
				+ "c.nombre_paciente as paciente, c.horario_consulta, c.fecha_consulta as fecha_consulta,\r\n"
				+ "c2.numero_consultorio, c2.piso \r\n"
				+ "from doctor d join citas c on d.id_doctor = c.id_doctor \r\n"
				+ "join consultorios c2 on c2.id_consultorio =c.id_consultorio \r\n"
				+ "where 1 = 1 ";
		if(filtros.getFechaConsulta()!=null) {
			String fecha = formatter.format(filtros.getFechaConsulta());
			params.put("fechaConsulta", fecha);
			sql = sql + " and c.fecha_consulta = :fechaConsulta";
		}
		if(filtros.getDoctor() != null && filtros.getDoctor().getIdDoctor() != null) {
			params.put("idDoctor", filtros.getDoctor().getIdDoctor());
			sql = sql + " and c.id_doctor = :idDoctor";
		}
		if(filtros.getConsultorio() != null && filtros.getConsultorio().getIdConsultorio() != null) {
			params.put("idConsultorio", filtros.getConsultorio().getIdConsultorio());
			sql = sql + " and c.id_consultorio = :idConsultorio";
		}
		SqlParameterSource parameters = new MapSqlParameterSource(params);

		return namedJdbcTemplate.query(sql, parameters, (rs, rowNum) -> {
			CitasDto dto = new CitasDto();
			dto.setNombreDoctor(rs.getString("nombre_doctor"));
			dto.setPaciente(rs.getString("paciente"));
			dto.setHorario(rs.getString("horario_consulta"));
			dto.setFechaConsulta(rs.getString("fecha_consulta"));
			dto.setNumeroConsultorio(rs.getInt("numero_consultorio"));
			dto.setPiso(rs.getInt("piso"));
			return dto;
			
		});
	}
	
}
