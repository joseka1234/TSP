// TODO: Mirar problemas

package tspPractice;

import java.awt.Point;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.jdom2.JDOMException;

public class TSPInstance {
	
	public static final String FILE = "bayg29.xml";
	private Matriz matrizDistancia = new Matriz();
	private ArrayList<Step> camino;
	private ArrayList<Point> visitados;
	
	public TSPInstance() throws FileNotFoundException, JDOMException, IOException {
		getMatrizDistancia().rellenaMatriz(FILE);
		setVisitados(new ArrayList<Point>());
		setCamino(new ArrayList<Step>());
	}
	
	
	public Matriz getMatrizDistancia() {
		return matrizDistancia;
	}
	public void setMatrizDistancia(Matriz matrizDistancia) {
		this.matrizDistancia = matrizDistancia;
	}
	
	public void calculaCamino() {
		while(visitados.size() < getMatrizDistancia().getFilas() - 1) {
			getCamino().add(vecinoCercano());
		}
	}
	
	public Step vecinoCercano() {
		int auxI = 0;
		int auxJ = 0;
		Point auxPoint = new Point();
		double costeMinimo = Double.MAX_VALUE;
		double aux;
		for(int i = 0; i < getMatrizDistancia().getFilas(); i++) {
			for(int j = 0; j < getMatrizDistancia().getColumnas(); j++) {
				aux = getMatrizDistancia().getItem(i, j);
				if(aux < costeMinimo) {
					auxPoint.x = i;
					auxPoint.y = j;
					if(!getVisitados().contains(auxPoint)) {
						costeMinimo = aux;
						auxI = i;
						auxJ = j;
					}
				}
			}
		}
		return new Step(auxI, auxJ, costeMinimo);
	}
	
	public void muestraMatriz() {
		for(int i = 0; i < getMatrizDistancia().getFilas(); i++) {
			for(int j = 0; j < getMatrizDistancia().getColumnas(); j++) {
				System.out.print(getMatrizDistancia().getItem(i, j) + " ");
			}
			System.out.println();
		}
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


	public ArrayList<Point> getVisitados() {
		return visitados;
	}


	public void setVisitados(ArrayList<Point> visitados) {
		this.visitados = visitados;
	}
	
	
}
