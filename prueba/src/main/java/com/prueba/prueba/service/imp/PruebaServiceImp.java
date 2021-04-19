package com.prueba.prueba.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.prueba.prueba.model.Pais;
import com.prueba.prueba.repos.PruebaRepo;
import com.prueba.prueba.service.PruebaService;

@Service
public class PruebaServiceImp implements PruebaService {
	
	@Autowired
	private PruebaRepo pruebaRepo;

	@Override
	public List<Pais> listadoPais() {
		return pruebaRepo.listadoPais();
	}

}
