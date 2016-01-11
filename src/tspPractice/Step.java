/**
@author José Carlos Rodríguez Cortés
Class: Step.java
@version 1.0.0
Description: Clase que representa un paso en la trayectoria a seguir para resolver el TSP
Date: 11 de ene. de 2016
Contacto: alu0100766950@ull.edu.es
Contacto2: joseka1234@gmail.com
*/
package tspPractice;

public class Step {
	private int i;
	private int j;
	private double cost;
	
	public Step(int i, int j, double cost) {
		setI(i);
		setJ(j);
		setCost(cost);
	}
	
	/**
	 * Método toString sobrecargado para facilitar el muestreo de los pasos a seguir en la trayectoria
	 */
	@Override
	public String toString() {
		return getI() + ", " + getJ() + ", Cost: " + getCost();
	}
	
	//Getters y setters
	public int getI() {
		return i;
	}
	public void setI(int i) {
		this.i = i;
	}
	public int getJ() {
		return j;
	}
	public void setJ(int j) {
		this.j = j;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
}
