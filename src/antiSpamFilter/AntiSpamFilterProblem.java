package antiSpamFilter;

import java.util.ArrayList;
import java.util.List;

import org.uma.jmetal.problem.impl.AbstractDoubleProblem;
import org.uma.jmetal.solution.DoubleSolution;


public class AntiSpamFilterProblem extends AbstractDoubleProblem {
	
	public AntiSpamFilterProblem() {
		this(10);
	}

	
	  public AntiSpamFilterProblem(Integer numberOfVariables) {
	    //numero de regras
		setNumberOfVariables(numberOfVariables);
	    //numero de objetivos (2-->FP & FN)
	    setNumberOfObjectives(2);
	    //Vai para esta pasta
	    setName("AntiSpamFilterProblem");

	    //criação de vetores para o intervalo de cada das 300 regras (limite superior e inferior))
	    List<Double> lowerLimit = new ArrayList<>(getNumberOfVariables()) ;
	    List<Double> upperLimit = new ArrayList<>(getNumberOfVariables()) ;

	    for (int i = 0; i < getNumberOfVariables(); i++) {
	      lowerLimit.add(-5.0);
	      upperLimit.add(5.0);
	      //preenchimento dos valores máximos para 300 das regras
	    }
	    //definição desses valores no programa
	    setLowerLimit(lowerLimit);
	    setUpperLimit(upperLimit);
	  }

	  //este metodo está feito para dar coisas aleatórias
	  
	  //recebe um problema e este método é usado para gravar no ficheiro  
	  public void evaluate(DoubleSolution solution){
		  
	    double aux, xi, xj;
	    
	    //vetor de objetivos -->FP & FN com 2 posições neste caso
	    double[] fx = new double[getNumberOfObjectives()];
	    //vetor com 300 posições
	    double[] x = new double[getNumberOfVariables()];
	    
	    for (int i = 0; i < solution.getNumberOfVariables(); i++) {
	      x[i] = solution.getVariableValue(i) ;
	    }
	    //meter no valor de variáveis o que está em cada das 300 posições da solução já computada

	    fx[0] = 0.0;
	    for (int var = 0; var < solution.getNumberOfVariables() - 1; var++) {
		  fx[0] += Math.abs(x[0]); // Example for testing
	    }
	    
	    fx[1] = 0.0;
	    for (int var = 0; var < solution.getNumberOfVariables(); var++) {
	    	fx[1] += Math.abs(x[1]); // Example for testing
	    }
	    
	   

	    solution.setObjective(0, fx[0]);
	    solution.setObjective(1, fx[1]);
	  }
	}
