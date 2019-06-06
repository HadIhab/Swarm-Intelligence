package search_methodes;

import models.Instance;
import models.Solution;

import java.util.*;

public class Genetic {
	
	private Instance instance;
	private LinkedList<Integer> solution;
	
	public Genetic(Instance instance) {
		this.instance=instance;
	}

	public void solve(int pop_init_number,int Rc,int taux_croisement,int Rm,int taux_mutation) {
		
		/* créer un comparateur pour ordonner la population le plus meilleur vers le moins meilleur */
        Comparator<LinkedList<Integer>> comparator = new Comparator<LinkedList<Integer>>() {
            @Override
            public int compare(LinkedList<Integer> o1, LinkedList<Integer> o2) {
                /*Utuliser g pour comparer entre 2 solutions */
            	return g(o2) - g(o1);
            }
        };
		
		/* Création d'une population initiale */
        
		int clauses_number = instance.getNb_clauses();
        int vars_number = instance.getNb_literaux();
        LinkedList<Integer> temp_etat;
        
        PriorityQueue<LinkedList<Integer>> population = new PriorityQueue<>(100, comparator);
		ArrayList<LinkedList<Integer>> selected_individus = new ArrayList<LinkedList<Integer>>();
        
		for ( int i=0;i<pop_init_number;i++) {
			
			Solution sol = new Solution(vars_number);
			temp_etat = sol.solutionToEtat();
			/* L'évaluation des individus (Tris) */
			population.add(temp_etat);
		}
		
		while(true) {
			/* Sélectionner n individus */
			
			/*Pour l'instant je fais que 2selections*/
			selected_individus.add(population.poll());
			selected_individus.add(population.poll());
			
			/***		 Croisement		 ***/
			if ( Rc>taux_croisement) {
				
			}
			if ( Rm>taux_mutation) {
				
			}
			
		} 
	}
	
	private int g(LinkedList<Integer> configuration) {
        return instance.get_nb_sat(configuration);
    }
	
}
