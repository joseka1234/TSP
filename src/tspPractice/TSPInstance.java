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
	private int coste;

	/**
	 * Constructor por defecto para la instancia de TSP
	 * 
	 * @throws FileNotFoundException
	 * @throws JDOMException
	 * @throws IOException
	 */
	public TSPInstance() throws FileNotFoundException, JDOMException, IOException {
		setCoste(Integer.MAX_VALUE);
		getMatrizDistancia().rellenaMatriz(FILE);
		setVisitados(new TreeSet<Integer>());
		setCamino(new ArrayList<Step>());
	}

	/**
	 * Método que calcula el tour basandose en el vecino más próximo
	 */
	public void calculaCamino() {
		Step dummyStep = null;
		while (getVisitados().size() < getMatrizDistancia().getFilas()) {
			dummyStep = vecinoCercano(dummyStep);
			getCamino().add(dummyStep);

			getVisitados().add(dummyStep.getI());
			getVisitados().add(dummyStep.getJ());

		}
		int auxJ = getCamino().get(0).getI();
		int auxI = getCamino().get(getCamino().size() - 1).getJ();
		dummyStep = new Step(auxI, auxJ, getMatrizDistancia().getItem(auxI, auxJ));
		getCamino().add(dummyStep);
		calculaCosteActual();
	}

	/**
	 * Método que aplica 2Opt
	 */
	public void aplica2Opt() {
		boolean first = true;
		double bestGain = Double.MAX_VALUE;
		int bestI = Integer.MAX_VALUE;
		int bestJ = Integer.MAX_VALUE;

		while (bestGain >= 0) {
			bestGain = 0;
			for (int i = 0; i < getCamino().size(); i++) {
				for (int j = 0; j < getCamino().size(); j++) {
					if (i != j) {
						double gain = computeGain(i, j);
						if (gain < bestGain) {
							bestGain = gain;
							bestI = i;
							bestJ = j;
							if(first) {
								break;
							}
						}
					}
				}
				if(bestGain < 0 && first) {
					break;
				}
			}
		}
		
		if(first) {
			exchange(bestI, bestJ);
		}
		
		calculaCosteActual();
	}

	/**
	 * Método que computa la ganancia de realizar un paso
	 * 
	 * @param i
	 *            ciudad de partida
	 * @param j
	 *            ciudad de llegada
	 * @return valor de ganancia que se obtiene al moverse entre ciudades
	 */
	private double computeGain(int i, int j) {
		Step stepI = getCamino().get(i);
		Step stepJ = getCamino().get(j);
		double auxValue1 = getMatrizDistancia().getItem(stepI.getJ(), stepJ.getJ())
				+ getMatrizDistancia().getItem(stepI.getI(), stepJ.getI());
		double auxValue2 = stepI.getCost() + stepJ.getCost();
		
		return auxValue1 - auxValue2;
	}
	
	/**
	 * Método que intercambia dos nodos del camino
	 * @param i elemento a cambiar
	 * @param j elemento a cambiar
	 */
	private void exchange(int indexI, int indexJ) {
		ArrayList<Step> caminoNuevo = new ArrayList<Step>();
		int i = 0;
		
		while(i < indexI) {
			if(caminoNuevo.contains(getCamino().get(i)) == false)
				caminoNuevo.add(getCamino().get(i));
			i++;
		}
		
		i = indexJ;
		while(i >= getCamino().get(indexJ).getJ()) {
			if(caminoNuevo.contains(getCamino().get(i)) == false)
				caminoNuevo.add(getCamino().get(i));
			i--;
		}
		i = getCamino().get(indexJ).getJ();
		while(i < getCamino().size()) {
			if(caminoNuevo.contains(getCamino().get(i)) == false)
				caminoNuevo.add(getCamino().get(i));
			i++;
		}
		
		for(int j = 0; j < caminoNuevo.size(); j++) {
			getCamino().set(j, caminoNuevo.get(j));
		}
	}

	/**
	 * Método que calcula el vecino más cercano basandose en el paso anterior
	 * que se realizo en la trayectoria
	 * 
	 * @param lastStep
	 *            último paso dado en la trayectoria
	 * @return siguiente paso a dar en la trayectoria
	 */
	private Step vecinoCercano(Step lastStep) {
		int auxI = -1;
		int auxJ = -1;
		double aux;
		double costeMinimo = Double.MAX_VALUE;

		if (lastStep == null) {
			auxI = 0;
			auxJ = 0;
			for (int i = 0; i < getMatrizDistancia().getFilas(); i++) {
				for (int j = 0; j < getMatrizDistancia().getColumnas(); j++) {
					aux = getMatrizDistancia().getItem(i, j);
					if (aux < costeMinimo) {
						costeMinimo = aux;
						auxI = i;
						auxJ = j;
					}
				}
			}
		} else {
			auxI = lastStep.getJ();
			for (int i = 0; i < getMatrizDistancia().getColumnas(); i++) {
				aux = getMatrizDistancia().getItem(auxI, i);
				if (aux < costeMinimo) {
					if (!getVisitados().contains(i)) {
						costeMinimo = aux;
						auxJ = i;
					}
				}
			}
		}

		return new Step(auxI, auxJ, costeMinimo);
	}

	/**
	 * Método que muestra la matriz de distancias. Solo para testeo.
	 */
	public void muestraMatriz() {
		for (int i = 0; i < getMatrizDistancia().getFilas(); i++) {
			for (int j = 0; j < getMatrizDistancia().getColumnas(); j++) {
				System.out.print(getMatrizDistancia().getItem(i, j) + " ");
			}
			System.out.println();
		}
	}

	/**
	 * Método que muestra el camíno de la trayectoria actual Solo para testeo
	 */
	public void muestraCamino() {
		for (Step paso : getCamino()) {
			System.out.println(paso);
		}
		System.out.println("Coste total = " + getCoste());
	}

	/**
	 * Método que calcula el coste del caino actual
	 */
	private void calculaCosteActual() {
		int coste = 0;
		for (Step paso : getCamino()) {
			coste += paso.getCost();
		}
		setCoste(coste);
	}

	// Getters y setters
	public Matriz getMatrizDistancia() {
		return matrizDistancia;
	}

	public void setMatrizDistancia(Matriz matrizDistancia) {
		this.matrizDistancia = matrizDistancia;
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

	public int getCoste() {
		return coste;
	}

	public void setCoste(int coste) {
		this.coste = coste;
	}
}
