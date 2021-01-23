package Operator;

import java.util.Vector;

public class Main {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CSVManager m = new CSVManager();
        Vector<LigneTableau> res =  m.elimine_number();
        //LigneTableau t = res.get(0);
        //System.out.println(t.get_box_conveyor());
        //m.supprimer_doublons();
        System.out.println(m.ListEvent(res).elementAt(55));
	}
}
