package sample.Model;

public class Statistic {
	
	int fitness;
	int mutRate;
	int instance;
	int crossRate;
	int maxIter;
	int minPopSize;
	int maxPopSize;
	int currentPop;
	
	
	
	public Statistic(int fitness, int instance) {
		super();
		this.fitness = fitness;
		this.instance = instance;
	}


	public Statistic(int fitness, int par, String param) {
		super();
		switch (param) {
		case "PopSize":
			this.currentPop = par;
			break;
		case "CrossRate":
			this.crossRate=par;
			break;
		case "MutationRate":
			this.mutRate=par;
			break;
		case "maxIter":
			this.maxIter=par;
			break;
		default:
			break;
		}
		this.fitness = fitness;
		
	}


	public int getFitness() {
		return fitness;
	}


	public void setFitness(int fitness) {
		this.fitness = fitness;
	}


	public int getMutRate() {
		return mutRate;
	}


	public void setMutRate(int mutRate) {
		this.mutRate = mutRate;
	}


	public int getCrossRate() {
		return crossRate;
	}


	public void setCrossRate(int crossRate) {
		this.crossRate = crossRate;
	}


	public int getMaxIter() {
		return maxIter;
	}


	public void setMaxIter(int maxIter) {
		this.maxIter = maxIter;
	}


	public int getMinPopSize() {
		return minPopSize;
	}


	public void setMinPopSize(int minPopSize) {
		this.minPopSize = minPopSize;
	}


	public int getMaxPopSize() {
		return maxPopSize;
	}


	public void setMaxPopSize(int maxPopSize) {
		this.maxPopSize = maxPopSize;
	}


	public int getCurrentPop() {
		return currentPop;
	}


	public void setCurrentPop(int currentPop) {
		this.currentPop = currentPop;
	}
	
	




}
