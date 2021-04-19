package com.prueba.prueba.repos.imp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.prueba.prueba.model.Pais;
import com.prueba.prueba.repos.PruebaRepo;


@Repository("pruebaRepo")
public class PruebaRepoImp implements PruebaRepo {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	private static final String CONSULTA_PAIS = "select country_id idPais, country_name nombrePais, region_id idRegion from countries";

	@Override
	public List<Pais> listadoPais() {
		RowMapper<Pais> rowMapper = new BeanPropertyRowMapper<>(Pais.class);
		return jdbcTemplate.query(CONSULTA_PAIS, rowMapper);	
	}

}
