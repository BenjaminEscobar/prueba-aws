package com.prueba.prueba.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.prueba.model.Pais;
import com.prueba.prueba.service.PruebaService;



@CrossOrigin
@RestController
@RequestMapping("/ivr")
public class PruebaController {
	
	@Autowired
	private PruebaService pruebaService;
	
	
	@GetMapping("/obtieneinfopoliza/{solicitud}/{contacto}")
	public ResponseEntity<List<Pais>> listadoPais() {

		List<Pais> list = pruebaService.listadoPais();

		if (list == null)
			return new ResponseEntity<>(list, HttpStatus.INTERNAL_SERVER_ERROR);

		return new ResponseEntity<>(list, HttpStatus.OK);

	}

}
