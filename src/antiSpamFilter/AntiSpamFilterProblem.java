package antiSpamFilter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.uma.jmetal.problem.impl.AbstractDoubleProblem;
import org.uma.jmetal.solution.DoubleSolution;

import GUI.GUI;


@SuppressWarnings("serial")
public class AntiSpamFilterProblem extends AbstractDoubleProblem {
	
	private Map<String, Double> RulesMap;
	private Map<Integer, ArrayList<String>> SpamMap;
	private Map<Integer, ArrayList<String>> HamMap;

	public AntiSpamFilterProblem(Map<String, Double> RulesMap, Map<Integer, ArrayList<String>> SpamMap, Map<Integer, ArrayList<String>> HamMap) {

		this.RulesMap = RulesMap; 
		this.SpamMap = SpamMap; 
		this.HamMap = HamMap; 

		setNumberOfVariables(RulesMap.size());
		setNumberOfObjectives(2);
		setName("AntiSpamFilterProblem");

		List<Double> lowerLimit = new ArrayList<>(getNumberOfVariables()) ;
		List<Double> upperLimit = new ArrayList<>(getNumberOfVariables()) ;

		for (int i = 0; i < getNumberOfVariables(); i++) {
			lowerLimit.add(-5.0);
			upperLimit.add(5.0);
		}
		setLowerLimit(lowerLimit);
		setUpperLimit(upperLimit);
	}

	public void evaluate(DoubleSolution solution){

		int[] falseX= new int[getNumberOfObjectives()];

		for (int i = 0; i < GUI.getAutomaticRulesWeightList().getRowCount(); i++) {
			RulesMap.put((String) (GUI.getAutomaticRulesWeightList().getValueAt(i, 0)),solution.getVariableValue(i));
		}

		int computedFalsePositive = avaluateFalsePositive();
		int computedFalseNegative = avaluateFalseNegative();

		falseX[0]=computedFalsePositive;
		falseX[1]=computedFalseNegative;

		solution.setObjective(0,falseX[0]);
		solution.setObjective(1,falseX[1]);
	}

	private int avaluateFalseNegative() {
		int falseNegativeCount = 0;
		for (int i = 0; i < SpamMap.size(); i++) {

			ArrayList<String> aux =SpamMap.get(i);
			double veredict=0.0;
			for (int j = 0; j <aux.size() ; j++) {				
				if(RulesMap.get(aux.get(j))!=null)
					veredict+=RulesMap.get(aux.get(j));
			}
			if(veredict<-5.0)falseNegativeCount++;
		}
		return falseNegativeCount;
	}

	private int avaluateFalsePositive() {
		int falsePositiveCount = 0;
		for (int i = 0; i < HamMap.size(); i++) {
			ArrayList<String> aux =HamMap.get(i);
			double veredict=0.0;
			for (int j = 0; j <aux.size() ; j++) {
				if(RulesMap.get(aux.get(j))!=null)
					veredict+=RulesMap.get(aux.get(j));
			}
			if(veredict>5.0)falsePositiveCount++;
		}
		return falsePositiveCount;
	}

	public Map<String, Double> getRulesMap() {
		return RulesMap;
	}

	public Map<Integer, ArrayList<String>> getSpamMap() {
		return SpamMap;
	}

	public Map<Integer, ArrayList<String>> getHamMap() {
		return HamMap;
	}
}
