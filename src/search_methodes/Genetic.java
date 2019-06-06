package search_methodes;

import models.Instance;
import java.util.*;

public class Genetic {
	
	private Instance instance;
	private LinkedList<Integer> solution;
	
	public Genetic(Instance instance) {
		this.instance=instance;
	}

	public void solve() {
		
		/* créer un comparateur pour ordonner la population le plus meilleur vers le moins meilleur */
        Comparator<LinkedList<Integer>> comparator = new Comparator<LinkedList<Integer>>() {
            @Override
            public int compare(LinkedList<Integer> o1, LinkedList<Integer> o2) {
                /*Utuliser g pour comparer entre 2 solutions */
            	return g(o2) - g(o1);
            }
        };
		
		/* Création d'une population initiale */
		int pop_init_number=10;
		for (int i=0;i<pop_init_number;i++) {
			
		}
	
	}
	
	private int g(LinkedList<Integer> configuration) {
        return instance.get_nb_sat(configuration);
    }
	
}
