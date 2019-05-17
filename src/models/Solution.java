package models;

import java.util.*;

public class Solution implements Comparable<Solution> {

    private BitSet solution;
    private int nb_bits;

    public Solution(int nb_bits) {
        this.nb_bits = nb_bits;
        generateRandomSolution();
    }

    public Solution(BitSet solution, int nb_bits) {
        this.solution = solution;
        this.nb_bits = nb_bits;
    }

    

    public BitSet getSolution() {
        return solution;
    }

    //une méthode qui genere une solution aléatoire
    private void generateRandomSolution() {

        solution = new BitSet(nb_bits);
        List<Integer> list = new ArrayList<>();  // une liste qui contient les nombre de variabls de 1 à nb_vars
        for (int i = 0; i < nb_bits; i++)
            list.add(i);
        Collections.shuffle(list);                  //réordonner la list alétoirement

        Random random = new Random();               //prendre un nombre random entre 0 et nb_vars (exclus)
        int nb_shift = random.nextInt(nb_bits);

        for (int i = 0; i <= nb_shift; i++)           //inverser des bit set pour une initialisation aléatoire
            solution.flip(list.get(i));
    }

    //transforme un bitset en LinkedList pour réutiliser la methode d'evaluation dans la classe insatance
    public LinkedList<Integer> solutionToEtat() {
        LinkedList<Integer> etat = new LinkedList<>();
        for (int i = 0; i < nb_bits; i++) {
            //+1 car l'lement à la postion i designe i+1, les litteraux commence de 1 pas de 0, donc le litteral 1 se  trouve à la position 0
            if (solution.get(i) == true)
                etat.add(i + 1);
            else
                etat.add(-(i + 1));
        }
        return etat;
    }

   

    public int getNb_bits() {
        return nb_bits;
    }

   

    //redefinir les methodes de comparaiosn des solutions pour le tri des listes
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Solution solution1 = (Solution) o;
        return Objects.equals(solution, solution1.solution);
    }

    @Override
    public int compareTo(Solution o) {
        return 1-1;
    }
}