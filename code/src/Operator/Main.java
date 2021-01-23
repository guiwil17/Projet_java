package Operator;

import java.util.Vector;

public class Main {
	public static void main(String[] args) {
		
		CSVManager m = new CSVManager();
		Vector<LigneTableau> res =  m.elimine_number();
		LigneTableau t = res.get(58);
		//System.out.println(t.get_box_conveyor());
		//m.supprimer_doublons();		
	}
}
