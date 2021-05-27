package com.prueba.prueba.service.imp;

import java.util.ArrayList;
import java.util.List;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.lsmp.djep.djep.DJep;
import org.nfunk.jep.Node;
import org.springframework.stereotype.Service;

import com.prueba.prueba.model.Resultado;
import com.prueba.prueba.service.Metodos;

@Service
public class MetodosImp implements Metodos {

	@Override
	public List<Resultado> newtownRaphson(String funcion, double valorX, int iteraciones) {

		// obtiene deribada
		String derivada = derivar(funcion);

		return formula(valorX, funcion, derivada, valorX, iteraciones);
	}

	@Override
	public List<Resultado> formula(double valorX, String funcion, String primerDerivada, double e, int ciclos) {

		List<Resultado> lista = new ArrayList<>();

		ScriptEngineManager mgr = new ScriptEngineManager();
		ScriptEngine engine = mgr.getEngineByName("JavaScript");
		

		try {
			

			double funcionValorX =  Double.parseDouble(engine.eval(fixEquation(funcion, valorX)).toString());
			double funcionValorXDerivada = Double.parseDouble(engine.eval(fixEquation(primerDerivada.replace("*", ""), valorX)).toString());

			for (int i = 0; i <= ciclos; i++) {
				Resultado res = new Resultado();
				double valorAnterior = valorX; 
				
				res.setX(valorX);
				res.setIteracion(i);
				valorX = valorX - funcionValorX / funcionValorXDerivada;				

				if(i!=0) {
					res.setPorcentaje(Math.abs((valorX-valorAnterior)/valorX));
				}
				
				res.setDerivada(primerDerivada.replace("*", ""));
				
				lista.add(res);
			}

		} catch (ScriptException e1) {
			e1.printStackTrace();
		}

		return lista;
	}

	public String fixEquation(String equation, double x) {
		StringBuilder sb = new StringBuilder(equation.replaceAll(" ", ""));
		StringBuilder fixed = new StringBuilder("(");
		StringBuilder temp = new StringBuilder("");

		for (int i = 0; i < sb.length(); i++) {

			if (sb.charAt(i) == '^') {
				for (int j = i + 1; j < sb.length(); j++) {
					if (j + 1 == sb.length()) {
						temp.append(sb.charAt(j));
						for (int k = 0; k < Double.valueOf(temp.toString()); k++)
							fixed.append("*" + x);
						temp.replace(0, temp.length(), "");
						i = sb.length();
						break;
					}
					if (sb.charAt(j) == '+' || sb.charAt(j) == '-') {
						for (int k = 0; k < Double.valueOf(temp.toString()); k++)
							fixed.append("*" + x);
						temp.replace(0, temp.length(), "");
						i = j - 1;
						break;
					}
					temp.append(sb.charAt(j));

				}
			} else if (sb.charAt(i) == 'x' && (i + 1 == sb.length() || sb.charAt(i + 1) != '^')) {
				fixed.append("*" + x);
			} else if (sb.charAt(i) == 'x') {
				fixed.append("");
			} else if (sb.charAt(i) == '+' || sb.charAt(i) == '-') {
				fixed.append(")" + sb.charAt(i) + "(");
			} else
				fixed.append(sb.charAt(i));
		}

		fixed.append(")");
		return fixed.toString();
	}

	private String derivar(String funcion) {
		String derivada = "";
		DJep derivar = new DJep();
		derivar.addStandardFunctions();
		derivar.addStandardConstants();
		derivar.addComplex();
		derivar.setAllowUndeclared(true);
		derivar.setAllowAssignment(true);
		derivar.setImplicitMul(true);
		derivar.addStandardDiffRules();
		

		try {
			Node node = derivar.parse(funcion);
			Node diff = derivar.differentiate(node, "x");
			Node sim = derivar.simplify(diff);
			derivada = derivar.toString(sim);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return derivada;

	}

}
