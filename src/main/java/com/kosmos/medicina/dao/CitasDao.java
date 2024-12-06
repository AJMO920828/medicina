package com.kosmos.medicina.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kosmos.medicina.entity.Citas;

@Repository
public class CitasDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
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
	
	
}
