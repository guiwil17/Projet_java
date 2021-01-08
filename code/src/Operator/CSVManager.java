package Operator;
import java.util.*; 

public class CSVManager {
	
	
	
	public void supprimer_doublons()
		{
		Vector<LigneTableau> vector2 = new Vector<LigneTableau>();
		int decallage=1;
		for(int indice=0; indice<vector.size(); indice+=decallage)
			{
			System.out.print("For indice : "+indice);
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
			System.out.println("Add debut : "+debut);
			vector2.add(debut);
			if(doublons)
				{
				System.out.println("Add fin : "+fin);
				vector2.add(fin);	
				}
			}
		vector=vector2;
		for(int indice=0; indice<vector.size(); indice+=1)
			{
			System.out.print(vector.get(indice));
			}
		}
	
	CSVManager()
		{
		
		}
	
	public Vector<LigneTableau> vector = new Vector<LigneTableau>();  
	/*public void supprimer_int()
		{
		Vector<Integer> vector2 = new Vector<Integer>();
		int decallage=1;
		for(int indice=0; indice<vector.size(); indice+=decallage)
			{
			System.out.print("For indice : "+indice);
			int debut = vector.get(indice);
 			int offset=1;
 			int fin = -1;
 			boolean doublons=false;
			while((indice+offset)<vector.size()&&vector.elementAt(indice)==vector.elementAt(indice+offset))
				{
				doublons=true;
				fin = vector.get(indice+offset);
				offset+=1;
				decallage=offset;
				}
			System.out.println("Add debut : "+debut);
			vector2.add(debut);
			if(doublons)
				{
				System.out.println("Add fin : "+fin);
				vector2.add(fin);	
				}
			}
		vector=vector2;
		
		for(int indice=0; indice<vector.size(); indice+=1)
			{
			System.out.print(vector.get(indice));
			}
		
		}*/
	

}
