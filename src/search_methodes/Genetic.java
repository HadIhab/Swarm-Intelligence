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
			/* Sélectionner n individus (for loop) */
			
			/*Pour l'instant je fais que 2selections*/
			selected_individus.add(population.poll());
			selected_individus.add(population.poll());
			/* La tête de la file coresponds a la meilleure solution faut la sauvegrader pour répondre au problème de la stagnation  */
			LinkedList<Integer> clone_solution = selected_individus.get(0);
			/***		 Croisement		 ***/
			
			LinkedList<Integer> new_idv1 = selected_individus.get(0);
			LinkedList<Integer> new_idv2 = selected_individus.get(1);
			
			if ( Rc > taux_croisement) {
				
				Random random = new Random();
				int nb_shift = random.nextInt(vars_number);
				/*premier croisement pour crée un nv individu*/
				
				for ( int i=0;i < nb_shift; i++) {
					new_idv1.set(i,selected_individus.get(1).get(i) );
				}
				
				for ( int i=nb_shift;i < vars_number; i++) {
					new_idv1.set(i,selected_individus.get(0).get(i) );
				}
				
				for ( int i=0;i < (vars_number-nb_shift); i++) {
					new_idv2.set(i,selected_individus.get(0).get(i) );
				}
				
				for ( int i=(vars_number-nb_shift);i < vars_number; i++) {
					new_idv2.set(i,selected_individus.get(1).get(i) );
				}
				
				
				
			}
			
			if ( Rm > taux_mutation) {
				
				Random random = new Random();
				int mutation1 = random.nextInt(vars_number);
				int mutation2 = random.nextInt(vars_number);
				
				int val1 = (selected_individus.get(0)).get(mutation1);
				(selected_individus.get(0)).set( mutation1, val1*(-1) );
				int val2 = (selected_individus.get(0)).get(mutation2);
				(selected_individus.get(1)).set( mutation1, val2*(-1) );
				
			}
			/*  Évaluation des individus  */
			
			
		} 
	}
	
	private int g(LinkedList<Integer> configuration) {
        return instance.get_nb_sat(configuration);
    }
	
}
