/**
@author José Carlos Rodríguez Cortés
Class: TSPInstance.java
@version 1.0.0
Description: Clase que representa una instancia del TSP
Date: 11 de ene. de 2016
Contacto: alu0100766950@ull.edu.es
Contacto2: joseka1234@gmail.com
*/
package tspPractice;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

import org.jdom2.JDOMException;

public class TSPInstance {
	
	public static final String FILE = "bayg29.xml";
	private Matriz matrizDistancia = new Matriz();
	private ArrayList<Step> camino;
	private Set<Integer> visitados;
	
	/**
	 * Constructor por defecto para la instancia de TSP
	 * @throws FileNotFoundException
	 * @throws JDOMException
	 * @throws IOException
	 */
	public TSPInstance() throws FileNotFoundException, JDOMException, IOException {
		getMatrizDistancia().rellenaMatriz(FILE);
		setVisitados(new TreeSet<Integer>());
		setCamino(new ArrayList<Step>());
	}
	
	/**
	 * Método que calcula el tour basandose en el vecino más próximo
	 */
	public void calculaCamino() {
		Step dummyStep = null;
		while(getVisitados().size() < getMatrizDistancia().getFilas()) {
			dummyStep = vecinoCercano(dummyStep);
			getCamino().add(dummyStep);
			getVisitados().add(dummyStep.getI());
			getVisitados().add(dummyStep.getJ());
			
		}
	}
	
	/**
	 * Método que calcula el vecino más cercano basandose en el paso anterior que se realizo en la trayectoria
	 * @param lastStep último paso dado en la trayectoria
	 * @return siguiente paso a dar en la trayectoria
	 */
	public Step vecinoCercano(Step lastStep) {
		int auxI = -1;
		int auxJ = -1;
		double aux;
		double costeMinimo = Double.MAX_VALUE;
		
		if(lastStep == null) {
			auxI = 0;
			auxJ = 0;
			for(int i = 0; i < getMatrizDistancia().getFilas(); i++) {
				for(int j = 0; j < getMatrizDistancia().getColumnas(); j++) {
					aux = getMatrizDistancia().getItem(i, j);
					if(aux < costeMinimo) {
						costeMinimo = aux;
						auxI = i;
						auxJ = j;
					}
				}
			}
		}
		else {
			auxI = lastStep.getJ();
			for(int i = 0; i < getMatrizDistancia().getColumnas(); i++) {
				aux = getMatrizDistancia().getItem(auxI, i);
				if(aux < costeMinimo) {
					if(!getVisitados().contains(i)) {
						costeMinimo = aux;
						auxJ = i;
					}
				}
			}
		}
		
		return new Step(auxI, auxJ, costeMinimo);
	}
	
	/**
	 * Método que muestra la matriz de distancias.
	 * Solo para testeo.
	 */
	public void muestraMatriz() {
		for(int i = 0; i < getMatrizDistancia().getFilas(); i++) {
			for(int j = 0; j < getMatrizDistancia().getColumnas(); j++) {
				System.out.print(getMatrizDistancia().getItem(i, j) + " ");
			}
			System.out.println();
		}
	}
	
	// Getters y setters
	public Matriz getMatrizDistancia() {
		return matrizDistancia;
	}
	
	public void setMatrizDistancia(Matriz matrizDistancia) {
		this.matrizDistancia = matrizDistancia;
	}
	
	public void muestraCamino() {
		for(Step paso : getCamino()) {
			System.out.println(paso);
		}
	}

	public ArrayList<Step> getCamino() {
		return camino;
	}

	public void setCamino(ArrayList<Step> camino) {
		this.camino = camino;
	}


	public Set<Integer> getVisitados() {
		return visitados;
	}


	public void setVisitados(Set<Integer> visitados) {
		this.visitados = visitados;
	}
}
