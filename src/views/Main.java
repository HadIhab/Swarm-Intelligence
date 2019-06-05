package views;  

import java.util.LinkedList;

import models.*;

public class Main {  	
	public static void main(String[] args) { 
		// TODO Auto-generated method stub 		
		DataExtractor De = new DataExtractor("/home/hads/Documents/JavaWS/Sat/src/views/data_set/sets/uuf75-01.cnf");
		Instance I=De.getData();
		//System.out.println(I.list);
		Solution sol=new Solution(75);
		LinkedList<Integer> Etat=sol.solutionToEtat();
		System.out.println(Etat);
		int nb_clause_sat=I.get_nb_sat(Etat);
		System.out.println("\n"+nb_clause_sat);
		nb_clause_sat=I.get_list_clausesNot(Etat).size();
		System.out.println("\n"+nb_clause_sat);
	}
}