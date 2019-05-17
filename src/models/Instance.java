package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class Instance {

    private LinkedList<Clause> clauses; // l'ensemble des clauses
    private int nb_clauses;             //le nombre de clause
    private int nb_literaux;            // le nombre de litteraux
    private HashMap<Integer, ArrayList<Integer>> list;// pour chaque litteral on garde l'index des clause ou il apparait

    public Instance(int a) {
        clauses = new LinkedList<Clause>();
        nb_clauses = 0;
        setNb_literaux(a);
        getList();
    }

    public LinkedList<Clause> getClauses() {
        return clauses;
    }

    public void addClause(Clause c) {
        clauses.addLast(c);
        nb_clauses++;
    }

    public int getNb_clauses() {
        return nb_clauses;
    }
    /*
     * Méthodes pour construire un array contenant les literaux de la configuration, jprends l'array "clauses" et je retourne
     * les littéraux de chaque clause (itérer toute les clauses) 
     * */

    public ArrayList<Integer> getLiteraux() {
        ArrayList<Integer> literaux = new ArrayList<Integer>();
        Clause clause;
        int i, l;
        Integer f = new Integer(0);
        Iterator it = clauses.iterator();
        while (it.hasNext()) {
            clause = (Clause) it.next();
            for (i = 1; i <= 3; i++) {
                l = clause.getLiteral(i);
                if (!literaux.contains(l)) {
                    literaux.add(l);
                }
            }
        }
        return literaux;
    }

    public int getNb_literaux() {
        return nb_literaux;
    }

    public void setNb_literaux(int nb_literaux) {
        this.nb_literaux = nb_literaux;
    }

    //construire pour chaque litteral la list de ses clauses ou il apparait
    public void getList() {
        list = new HashMap<>();
        Integer key;
        // je vais numurauté les clauses 
        Integer c = new Integer(0);
        ArrayList<Integer> temp;
        //pour chaque clause
        for (Clause clause : clauses) {
            //pour chaque litteral
            for (int l = 1; l <= 3; l++) {
                key = clause.getLiteral(l);
                if (list.containsKey(key)) {
                    list.get(key).add(c);
                } else {
                    temp = new ArrayList<>();
                    temp.add(c);
                    list.put(key, temp);
                }
            }
            c++;
        }
    }
    
    
    /* get nombre clauses satisfaites par une certaine solution " etat 
     * list.get(i) -> retourne (array avec la Numurautation des clauses) liste des clauses où le littéral i apparait et donc :
     * S'il est positive c a dire il est vrais donc toutes les clauses où il apparait sont sat par ETAT sinon 
     * on execute pas le if 
     * removeAll clauses où il apparait ce littéral courant qui est vrais pour ne pas les re insérer 
     * */
    
    
    public int get_nb_sat(LinkedList<Integer> etat) {
        ArrayList<Integer> temp = new ArrayList<>();
        for (Integer i : etat) {
            if (list.get(i) != null) {
                temp.removeAll(list.get(i));
                temp.addAll(list.get(i));
            }
        }
        return temp.size();
    }
    /*
     * La même méthode mais je retourne l'array lui même contenant les num des clauses sat par ETAT
     * donc N°2 corresponds à la 3ème clause qui est dans l'array " clauses"
     * */
    public ArrayList<Integer> get_list_clauses(LinkedList<Integer> etat) {
        ArrayList<Integer> temp = new ArrayList<>();
        for (Integer i : etat) {
            if (list.get(i) != null) {
                temp.removeAll(list.get(i));
                temp.addAll(list.get(i));
            }
        }
        return temp;
    }
    /*
     * Le contraire de la méthode précedente 
     * */
    public ArrayList<Integer> get_list_clausesNot(LinkedList<Integer> etat) {
        ArrayList<Integer> temp1 = get_list_clauses(etat);
        ArrayList<Integer> temp2 = new ArrayList<Integer>();
        for (int i = 0; i < nb_clauses; i++) {
            if (!temp1.contains(i))
                temp2.add(i);
        }
        return temp2;
    }
    
    
    /* CALCULE DES FREQUENCES ON A 2 METHODES POUR SA:
     * Construire un HashMap: littéral ==> nombre d'apparition qu'il soit en (L) ou (nonL) on le compte
     * 
     * Construire un HashMap: littéral ==> nombre d'apparition de ce littéral ( on aura donc une clé pour L et une pour nonL
     * 
     * */
    
    public HashMap<Integer, Integer> getFrequence() {
        HashMap<Integer, Integer> frequence = new HashMap();
        Clause clause;
        int i, l;
        Integer f = new Integer(0);
        Iterator it = clauses.iterator();
        while (it.hasNext()) {
            clause = (Clause) it.next();
            for (i = 1; i <= 3; i++) {
                l = Math.abs(clause.getLiteral(i));
                if (frequence.containsKey(l)) {
                    f = frequence.get(l);
                    frequence.put(l, ++f);
                } else {
                    frequence.put(l, 1);
                }
            }
        }
        return frequence;
    }

    public HashMap<Integer, Integer> getFrequence2() {
        HashMap<Integer, Integer> frequence = new HashMap();
        Clause clause;
        int i, l;
        Integer f = new Integer(0);
        Iterator it = clauses.iterator();
        while (it.hasNext()) {
            clause = (Clause) it.next();
            for (i = 1; i <= 3; i++) {
                l = clause.getLiteral(i);
                if (frequence.containsKey(l)) {
                    f = frequence.get(l);
                    frequence.put(l, ++f);
                } else {
                    frequence.put(l, 1);
                }
            }
        }
        return frequence;
    }
    
    
    @Override
    public String toString() {
        StringBuffer s = new StringBuffer();
        s.append("Nombre de littéraux = " + nb_literaux + "\n");
        s.append("Nombre de clause = " + nb_clauses + "\n");
        Iterator it = clauses.iterator();
        while (it.hasNext())
            s.append(it.next() + "\n");
        return s.toString();
    }

}
