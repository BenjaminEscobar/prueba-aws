package com.prueba.prueba.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.prueba.model.Resultado;
import com.prueba.prueba.model.Valores;
import com.prueba.prueba.service.Metodos;

@CrossOrigin
@RestController
@RequestMapping("/metodos")
public class PruebaController {

	@Autowired
	private Metodos metodos;

	@PostMapping("/newtonraphson")
	public ResponseEntity<List<Resultado>> newtonRaphson(@RequestBody Valores valores) {

		List<Resultado> list = metodos.newtownRaphson(valores.getFuncion(), valores.getValorX(), valores.getIteraciones());

		if (list == null)
			return new ResponseEntity<>(list, HttpStatus.INTERNAL_SERVER_ERROR);

		return new ResponseEntity<>(list, HttpStatus.OK);

	}

}
