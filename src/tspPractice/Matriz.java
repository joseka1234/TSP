package tspPractice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class Matriz {
	
	public static final String NOMBRE_RAIZ = "graph";
	public static final String NOMBRE_COSTE = "cost";
	private double [][] matriz;
	private int columnas;
	private int filas;
	
	public Matriz() {
		setMatriz(null);
		setColumnas(0);
	}
	
	public void rellenaMatriz(String file) throws FileNotFoundException, JDOMException, IOException {
		inicializaMatriz(file);
		int i = 0;
		int j = 0;
		
		SAXBuilder builder = new SAXBuilder();
		Document documentoJDOM = builder.build(new FileInputStream(file));
		Element raiz = documentoJDOM.getRootElement();
		List<Element> hijosRaiz = raiz.getChildren();
		hijosRaiz = hijosRaiz.get(hijosRaiz.size() - 1).getChildren();
		List<Element> listaAuxiliar;
		
		for(Element hijo : hijosRaiz) {
			listaAuxiliar = hijo.getChildren();
			for(Element coste : listaAuxiliar) {
				if(i != j) {
					setItem(i, j, Double.parseDouble(coste.getAttributeValue(NOMBRE_COSTE)));
					j++;
				}
				else {
					if (j >= getColumnas() - 1) {
						setItem(i, j, Double.MAX_VALUE);
						j++;
					}
					else {
						setItem(i, j, Double.MAX_VALUE);
						j++;
						setItem(i, j, Double.parseDouble(coste.getAttributeValue(NOMBRE_COSTE)));
						j++;
					}
				} 
			}
			j = 0;
			i++;
		}
		setItem(getFilas() - 1, getColumnas() - 1, Double.MAX_VALUE);
	}
	
	public void inicializaMatriz(String file) throws FileNotFoundException, JDOMException, IOException {
		int i = 0;
		int j = 0;
		
		SAXBuilder builder = new SAXBuilder();
		Document documentoJDOM = builder.build(new FileInputStream(file));
		Element raiz = documentoJDOM.getRootElement();
		List<Element> hijosRaiz = raiz.getChildren();
		hijosRaiz = hijosRaiz.get(hijosRaiz.size() - 1).getChildren();
		List<Element> listaAuxiliar;
		
		for(Element hijo : hijosRaiz) {
			listaAuxiliar = hijo.getChildren();
			for(Element coste : listaAuxiliar) {
				if(i == 0)
					j++;
			}
			i++;
		}
		setColumnas(j + 1);
		setFilas(i);
		
		setMatriz(new double[getFilas()][getColumnas()]);
	}
	
	public Double getItem(int i, int j) {
		return getMatriz()[i][j];
	}
	
	public void setItem(int i, int j, double valor) {
		getMatriz()[i][j] = valor;
	}

	public int getColumnas() {
		return columnas;
	}

	public void setColumnas(int ancho) {
		this.columnas = ancho;
	}

	public int getFilas() {
		return filas;
	}

	public void setFilas(int largo) {
		this.filas = largo;
	}

	public double [][] getMatriz() {
		return matriz;
	}

	public void setMatriz(double [][] matriz) {
		this.matriz = matriz;
	}
}
