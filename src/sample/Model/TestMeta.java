package sample.Model;

import java.io.File;
import java.util.ArrayList;

public class TestMeta {
	
	ArrayList<Statistic> statsPopSize=new ArrayList<Statistic>();
	ArrayList<Statistic> statsMutRate=new ArrayList<Statistic>();
	ArrayList<Statistic> statsCrossRate=new ArrayList<Statistic>();
	ArrayList<Statistic> statsMaxIter=new ArrayList<Statistic>();
	ArrayList<Statistic> statsAllIter=new ArrayList<Statistic>();
	
	
	
	public void testGenetic(String param, int minVal, int maxVal, String instance, String bench) {
		
		
		
		cnfReader c = new cnfReader(0,0);
		int k = 01;
		String name = bench+"-"+instance+".cnf";
		String fileName = "C:\\Users\\ASUS X541U\\Desktop\\keh\\"+name;
		File f = new File(fileName);
		int [][] matClauses = c.cnfRead(f);
		int nbrLitteraux = c.getNumVar();
		int mutationRate = 0;
		int crossoverRate = 0;
		int popSize = 0;
		int lastfit = 0;
		int maxIter = 3000;
		
		
		switch (param) {
		case "PopSize":
			mutationRate = 10;
			crossoverRate = 70;
			
			for (int val=minVal; val<=maxVal; val+=maxVal/minVal) {
					for (int i=1; i<=10; i++) {
					PopulationGenetic pop = new PopulationGenetic(nbrLitteraux, matClauses, val, mutationRate);
					SolutionGenetic best = pop.geneticAlgorithm(matClauses, val, crossoverRate, mutationRate, maxIter, 60, nbrLitteraux);
					if(lastfit<best.solutionFitnessValue) lastfit = best.solutionFitnessValue;
				}
					Statistic stat = new Statistic(lastfit,val,"PopSize");
					statsPopSize.add(stat);
			}
			break;
		case "CrossRate":
			mutationRate = 10;
			popSize=5000;
			for (int val=minVal; val<=maxVal; val+=maxVal/minVal) {
				for (int i=1; i<=10; i++) {
				PopulationGenetic pop = new PopulationGenetic(nbrLitteraux, matClauses, popSize, mutationRate);
				SolutionGenetic best = pop.geneticAlgorithm(matClauses, popSize, val, mutationRate,maxIter, 60, nbrLitteraux);
				if(lastfit<best.solutionFitnessValue) lastfit = best.solutionFitnessValue;
			}
				Statistic stat = new Statistic(lastfit,val,"CrossRate");
				statsCrossRate.add(stat);
		}
			break;
		
		case "MutationRate":
			mutationRate = 10;
			popSize=5000;
			for (int val=minVal; val<=maxVal; val+=maxVal/minVal) {
				for (int i=1; i<=10; i++) {
				PopulationGenetic pop = new PopulationGenetic(nbrLitteraux, matClauses, popSize, mutationRate);
				SolutionGenetic best = pop.geneticAlgorithm(matClauses, popSize, crossoverRate, val,maxIter, 60, nbrLitteraux);
				if(lastfit<best.solutionFitnessValue) lastfit = best.solutionFitnessValue;
			}
				Statistic stat = new Statistic(lastfit,val,"MutationRate");
				statsMutRate.add(stat);
		}
			break;

		case "MaxIter":
			mutationRate = 10;
			popSize=5000;
			for (int val=minVal; val<=maxVal; val+=maxVal/minVal) {
				for (int i=1; i<=10; i++) {
				PopulationGenetic pop = new PopulationGenetic(nbrLitteraux, matClauses, popSize, mutationRate);
				SolutionGenetic best = pop.geneticAlgorithm(matClauses, popSize, crossoverRate, mutationRate,val, 60, nbrLitteraux);
				if(lastfit<best.solutionFitnessValue) lastfit = best.solutionFitnessValue;
				
			}
				Statistic stat = new Statistic(lastfit,val,"MutationRate");
				statsMaxIter.add(stat);
		}
			break;

		case "All":
			mutationRate = 10;
			popSize=5000;
			crossoverRate=70;
			maxIter=30000;
			
				for (int i=1; i<=10; i++) {
				PopulationGenetic pop = new PopulationGenetic(nbrLitteraux, matClauses, popSize, mutationRate);
				SolutionGenetic best = pop.geneticAlgorithm(matClauses, popSize, crossoverRate, mutationRate, maxIter, 60, nbrLitteraux);
				if(lastfit<best.solutionFitnessValue) lastfit = best.solutionFitnessValue;
				
			}
				int inst = Integer.parseInt(instance);
				Statistic stat = new Statistic(lastfit,inst);
				statsAllIter.add(stat);
		
			
			break;
		}
		
	}
	
	
	public void testGeneticInstances(String bench) {
	
		int lastfit = 0;
		
		
		cnfReader c = new cnfReader(0,0);
		int k = 01;
		String name = bench+"-01.cnf";
		String fileName = "C:\\Users\\ASUS X541U\\Desktop\\keh\\"+name;
		File f = new File(fileName);
		int [][] matClauses = c.cnfRead(f);
	
		
		for (int j=0; j<c.getNumClauses();j++) {
			c = new cnfReader(0,0);
			String instance = "0"+Integer.toString(j);
			name = bench+"-"+instance+".cnf";
			fileName = "C:\\Users\\ASUS X541U\\Desktop\\keh\\"+name;
			f = new File(fileName);
		
			
			for (int i=0; i<10; i++) {
				testGenetic("All", 0, 0, instance, bench);
			}
	}
	
	}
	}
