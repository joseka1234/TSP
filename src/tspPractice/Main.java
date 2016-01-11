package tspPractice;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.jdom2.JDOMException;

public class Main {

	public static void main(String[] args) throws FileNotFoundException, JDOMException, IOException {
		TSPInstance tsp = new TSPInstance();
		tsp.calculaCamino();
		tsp.muestraCamino();
	}
}
