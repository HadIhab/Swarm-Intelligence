package search_methodes;

import models.Instance;
import java.util.*;

public class Heuristic {

    private Instance instance;
    private int max_sat;
    private LinkedList<Integer> solution;

    public Heuristic(Instance instance) {
        this.instance = instance;
    }

    public void solve() {

        // créer un comparateur pour ordonner open
        Comparator<LinkedList<Integer>> comparator = new Comparator<LinkedList<Integer>>() {
            @Override
            public int compare(LinkedList<Integer> o1, LinkedList<Integer> o2) {
                /*Utuliser g pour comparer entre 2 solutions */
            	return g(o2) - g(o1);
            }
        };

        //créer la liste des variable ordonnée du plus fréquent au moins fréquent

        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 1; i <= instance.getNb_literaux(); i++) {
            list.add(new Integer(i));
        }
        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        int nb_sat, configuration_size, cuerent_var;
        int clauses_number = instance.getNb_clauses();
        int vars_number = instance.getNb_literaux();

        PriorityQueue<LinkedList<Integer>> open = new PriorityQueue<>(100, comparator);
        LinkedList<Integer> configuration, g_configuration, d_configuration;
        configuration = new LinkedList<>();

        open.add(configuration);

        
        while (!open.isEmpty()) {
            //récuperer le premier element
            configuration = open.poll();

            // tester si la solution à étét trouvé
            nb_sat = instance.get_nb_sat(configuration);
            max_sat = Math.max(max_sat, nb_sat);
            if (nb_sat == clauses_number) {
                solution = configuration;
                return;
            }

            // si la configuration ne peut etre etendu
            configuration_size = configuration.size();
            if (configuration_size == vars_number)
                continue;
            //choisir une variable
            cuerent_var = list.get(configuration_size);

            //créer le filss gauche
            g_configuration = (LinkedList<Integer>) configuration.clone();
            g_configuration.add(cuerent_var);
            open.add(g_configuration);

            //créer le fils droit
            d_configuration = (LinkedList<Integer>) configuration.clone();
            d_configuration.add(cuerent_var * (-1));
            open.add(d_configuration);

        }
        System.out.println(max_sat);
        open.clear();
    }

    public int getMax_sat() {

        return max_sat;
    }

    //la fonction g de l'heuristique
    private int g(LinkedList<Integer> configuration) {
        return instance.get_nb_sat(configuration);
    }

}