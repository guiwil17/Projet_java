package Operator;

import java.util.Vector;

public class Main {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CSVManager m = new CSVManager();
		m.supprimer_doublons();
        Vector<LigneTableau> res =  m.elimine_number();
        //LigneTableau t = res.get(0);
        //System.out.println(t.get_box_conveyor());
        
        //System.out.println(res.size());
        System.out.println(m.ListEvent(res).elementAt(2));
	}
}
