/**
@author José Carlos Rodríguez Cortés
Class: Main.java
@version 1.0.0
Description: Clase principal del programa del TSP
Date: 11 de ene. de 2016
Contacto: alu0100766950@ull.edu.es
Contacto2: joseka1234@gmail.com
*/
package tspPractice;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.jdom2.JDOMException;

public class Main {

	public static void main(String[] args) throws FileNotFoundException, JDOMException, IOException {
		TSPInstance tsp = new TSPInstance();
		tsp.calculaCamino();
		tsp.muestraCamino();
		System.out.println("");
		tsp.aplica2Opt();
		tsp.muestraCamino();
	}
}
