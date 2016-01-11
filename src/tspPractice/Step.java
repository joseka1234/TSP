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
	
	@Override
	public String toString() {
		return getI() + ", " + getJ() + ", Cost: " + getCost();
	}
	
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
