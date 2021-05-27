package com.prueba.prueba.service;

import java.util.List;

import com.prueba.prueba.model.Resultado;

public interface Metodos {

	List<Resultado> newtownRaphson(String funcion, double valorX, int iteraciones);
	List<Resultado> formula(double valorX, String funcion, String primerDerivada, double e, int ciclos);
}
