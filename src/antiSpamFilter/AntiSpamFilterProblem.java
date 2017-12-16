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

	    //cria��o de vetores para o intervalo de cada das 300 regras (limite superior e inferior))
	    List<Double> lowerLimit = new ArrayList<>(getNumberOfVariables()) ;
	    List<Double> upperLimit = new ArrayList<>(getNumberOfVariables()) ;

	    for (int i = 0; i < getNumberOfVariables(); i++) {
	      lowerLimit.add(-5.0);
	      upperLimit.add(5.0);
	      //preenchimento dos valores m�ximos para 300 das regras
	    }
	    //defini��o desses valores no programa
	    setLowerLimit(lowerLimit);
	    setUpperLimit(upperLimit);
	  }

	  //este metodo est� feito para dar coisas aleat�rias
	  
	  //recebe um problema e este m�todo � usado para gravar no ficheiro  
	  public void evaluate(DoubleSolution solution){
		  
	    double aux, xi, xj;
	    
	    //vetor de objetivos -->FP & FN com 2 posi��es neste caso
	    double[] fx = new double[getNumberOfObjectives()];
	    //vetor com 300 posi��es
	    double[] x = new double[getNumberOfVariables()];
	    
	    for (int i = 0; i < solution.getNumberOfVariables(); i++) {
	      x[i] = solution.getVariableValue(i) ;
	    }
	    //meter no valor de vari�veis o que est� em cada das 300 posi��es da solu��o j� computada

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
