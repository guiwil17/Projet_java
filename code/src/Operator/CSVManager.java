package Operator;

import java.util.Vector;

public class CSVManager 
	{
	Vector<LigneTableau> vector = new Vector<LigneTableau>();
	
	public static void main(String[] args) 
		{
		// TODO Auto-generated method stub

		}
	
	public void supprimer_doublons()
		{
		Vector<LigneTableau> vector2 = new Vector<LigneTableau>();
		int decallage=1;
		for(int indice=0; indice<vector.size(); indice+=decallage)
			{
			LigneTableau debut = vector.get(indice);
				int offset=1;
				LigneTableau fin = new LigneTableau();
				boolean doublons=false;
			while((indice+offset)<vector.size()&&vector.elementAt(indice).is_equal_to(vector.elementAt(indice+offset)))
				{
				doublons=true;
				fin = vector.elementAt(indice+offset);
				offset+=1;
				decallage=offset;
				}
			
			vector2.add(debut);
			if(doublons)
				{
				vector2.add(fin);	
				}
			}
		vector=vector2;
		for(int indice=0; indice<vector.size(); indice+=1)
			{
			System.out.print(vector.get(indice).to_string());
			}
		}

	}
