/**
@author José Carlos Rodríguez Cortés
Class: Matriz.java
@version 1.0.0
Description: Clase que representa la matriz de distancias del TSP
Date: 11 de ene. de 2016
Contacto: alu0100766950@ull.edu.es
Contacto2: joseka1234@gmail.com
*/
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
	
	/**
	 * Constructor por defecto
	 */
	public Matriz() {
		setMatriz(null);
		setColumnas(0);
	}
	
	/**
	 * Método que rellena la matriz de distancias basandose en la información extraida de un fichero XML
	 * @param file nombre del fichero a analizar
	 * @throws FileNotFoundException
	 * @throws JDOMException
	 * @throws IOException
	 */
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
	
	/**
	 * Método que incializa la matriz de distancias basandose en la información extraída de un fichero XML
	 * @param file nombre del fichero XML del que se extraerá la información
	 * @throws FileNotFoundException
	 * @throws JDOMException
	 * @throws IOException
	 */
	private void inicializaMatriz(String file) throws FileNotFoundException, JDOMException, IOException {
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
			for(int x = 0; x < listaAuxiliar.size(); x++) {
				if(i == 0)
					j++;
			}
			i++;
		}
		setColumnas(j + 1);
		setFilas(i);
		
		setMatriz(new double[getFilas()][getColumnas()]);
	}
	
	//Getters y setters
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
