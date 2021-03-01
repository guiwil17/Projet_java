package Operator;

import java.util.Vector;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import static java.nio.file.StandardOpenOption.APPEND;
import java.io.*;


public class CSVManager {
	
	Vector<LigneTableau> vector = new Vector<LigneTableau>();
	
	public static void main(String[] args) {
	
	}
	
	public static String[][] readCSV(String nomFichier, char c, Charset charset) throws IOException {
		return Files.lines(Paths.get(nomFichier), charset)
					.skip(1)
			     .map(ligne-> ligne.split(String.valueOf(c)))
			     .toArray(String[][]::new);
	}
	
	static boolean existe(int T[], int val){
		  for(int i = 0 ; i<T.length;i++){
		   if(val==T[i])		     
		     return true;
		  }		  
		  return false;
	}

	public void elimine_number() { 
		
		// Declaration des variables
		
		String csvFile = ".././input_file.csv";	
		
		// Permet de creer et de remplir le nouveau csv
		String SEPARATOR = "%n";
		String HEADER = "Box_conveyor;Part_conveyor;Grab;C_plus;Auto;Manual;Emergency_stop;Reset_button;Start;Stop;Part_at_place;Box_at_place;Detected;C_limit;Temps_occurrence" + SEPARATOR;
		String DELIMITER = ";";
		String ajout = "";
		FileWriter file = null;
		
		char cvsSplitBy = ';';
        String annee = "";
        String day = "";
        String month = "";
        String min = "";
        String h = "";
        String s = "";
        String ms = "0";       
        long last_time = 0;
        int debut_s = 43;
        LigneTableau L = new LigneTableau();

      
        try {
        	  // Cree un nouveau fichier a la racine du projet qui se nomme result
        	  file = new FileWriter(".././result.csv");        	
        }
        catch(Exception e)
	      {
	        e.printStackTrace();
	      }	
        
        // Recupere le chemin du nouveau csv
        Path orderPath = Paths.get(".././result.csv");
        try {      	 
      	  // Ajoute l'entete du CSV
      	  Files.write(orderPath, String.format(HEADER).getBytes(), APPEND);
      }
      catch(Exception e)
	      {
	        e.printStackTrace();
	      }	
       
       
        try {
        	
        	String[][] tableau1 = readCSV(csvFile, cvsSplitBy, StandardCharsets.UTF_8);
        	int[] nbr = {14,15,16,17,18,19,20};
        	for(String[] ligne : tableau1) {
        		L = new LigneTableau();
				for(int colonne=0; colonne<ligne.length; colonne++) {
					if(!existe(nbr,colonne)) {
						switch(colonne) {
						//On stock dans notre objet toutes les valeurs booleennes
							case 0 : 
								L.set_box_conveyor(Boolean.parseBoolean(ligne[colonne]));
								break;
							case 1:
								L.set_part_conveyor(Boolean.parseBoolean(ligne[colonne]));
								break;
							case 2:
								L.set_grab(Boolean.parseBoolean(ligne[colonne]));
								break;
							case 3:
								L.set_c_plus(Boolean.parseBoolean(ligne[colonne]));
								break;
							case 4:
								L.set_auto(Boolean.parseBoolean(ligne[colonne]));
								break;
							case 5 : 
								L.set_manual(Boolean.parseBoolean(ligne[colonne]));
								break;
							case 6 :
								L.set_emergency_stop(Boolean.parseBoolean(ligne[colonne]));
								break;
							case 7 :
								L.set_reset_button(Boolean.parseBoolean(ligne[colonne]));
								break;
							case 8 :
								L.set_start(Boolean.parseBoolean(ligne[colonne]));
								break;
							case 9 :
								L.set_stop(Boolean.parseBoolean(ligne[colonne]));
								break;
							case 10 :
								L.set_part_at_place(Boolean.parseBoolean(ligne[colonne]));
								break;
							case 11 :
								L.set_box_at_place(Boolean.parseBoolean(ligne[colonne]));
								break;
							case 12 :
								L.set_detected(Boolean.parseBoolean(ligne[colonne]));
								break;
							case 13 :
								L.set_c_limit(Boolean.parseBoolean(ligne[colonne]));
								break;
							case 21:	
								
								//On va ici decomposer la String de la date pour pouvoir l'exploiter
								
								annee = String.valueOf(ligne[colonne].charAt(19))+ String.valueOf(ligne[colonne].charAt(20)) + String.valueOf(ligne[colonne].charAt(21)) + String.valueOf(ligne[colonne].charAt(22));	
								month = "0" + String.valueOf(ligne[colonne].charAt(25)) ;
								day = String.valueOf(ligne[colonne].charAt(28)) + String.valueOf(ligne[colonne].charAt((29)));
								h = String.valueOf(ligne[colonne].charAt(32)) + String.valueOf(ligne[colonne].charAt((33)));
								min = String.valueOf(ligne[colonne].charAt(36)) + String.valueOf(ligne[colonne].charAt(37));								
								//Permet de remettre a 0 la String
								ms = "";
								
								s = String.valueOf(ligne[colonne].charAt(39)) + String.valueOf(ligne[colonne].charAt(40)) + String.valueOf(ligne[colonne].charAt(41));
								s = s.replaceAll(" ", "");
								s = s.replaceAll(",", "");
								min = min.replaceAll(",", "");
								
								
								//On cree une nouvelle chaine de caractere afin de recuperer les millisecondes car la String ne fait pas tout le temps la meme taille
								String ne = ligne[colonne].substring(19,ligne[colonne].indexOf(')'));
								
								if(String.valueOf(ligne[colonne].charAt(40)) == "," ) 
								{
									debut_s = 23;
								}
								else 
								{
									 debut_s = 24;
								}
								
								//On ajoute chacune des valeurs
								while(debut_s != ne.length()) 
								{
									ms += String.valueOf(ne.charAt(debut_s));
									debut_s ++;
								}
								
																
								min = min.replaceAll(",", "");
								
								ms = ms.replaceAll(" ", "");
								
								
								//On convertit notre date afin de l'obtenir en milliseconde pour pouvoir ajouter les millisecondes recupere precedemment
									
								
									long seconds = Long.parseLong(s) * 1000000;
									long minutes = Long.parseLong(min) * (60 * 1000000);
									long hours = Long.parseLong(h)* 60 *60 * 1000000;
									long days = Long.parseLong(day) * (24 * 60 * 60 * 1000000);
									long months = Long.parseLong(month) * (2629800000L);
									long years = Long.parseLong(annee) * (31557600000L);
																
									long time = Long.parseLong(ms) + seconds + minutes + hours + days + months + years;									
									
									// On verifie que le temps precedent est inferieur au temps actuel 
									if (last_time != 0 && last_time > time) {
										time += last_time - time + 00000000001;
									}
									last_time = time;


									//On ajoute la date a notre Ligne
									L.set_date(time);	
								
														        
							}												
					}										
				}
				
				//On ajoute notre ligne au vecteur
				vector.addElement(L);
				
				try
			      {
					  // Ajoute une nouvelle ligne au csv
			        ajout = "";
			        ajout +=String.valueOf(L.get_box_conveyor());
			        ajout += DELIMITER;
			        ajout += String.valueOf(L.get_part_conveyor());
			        ajout += DELIMITER;
			        ajout += String.valueOf(L.get_grab());
			        ajout += DELIMITER;
			        ajout += String.valueOf(L.get_c_plus());
			        ajout += DELIMITER;
			        ajout += String.valueOf(L.get_auto());
			        ajout += DELIMITER;
			        ajout += String.valueOf(L.get_manual());
			        ajout += DELIMITER;
			        ajout += String.valueOf(L.get_emergency_stop());
			        ajout += DELIMITER;
			        ajout += String.valueOf(L.get_reset_button());
			        ajout +=  DELIMITER;
			        ajout += String.valueOf(L.get_start());
			        ajout += DELIMITER;
			        ajout += String.valueOf(L.get_stop());
			        ajout += DELIMITER;
			        ajout += String.valueOf(L.get_part_at_place());
			        ajout += DELIMITER;
			        ajout += String.valueOf( L.get_box_at_place());
			        ajout += DELIMITER;
			        ajout += String.valueOf(L.get_detected());
			        ajout += DELIMITER;
			        ajout += String.valueOf(L.get_c_limit());
			        ajout += DELIMITER;
			        ajout += String.valueOf(L.get_date());
			        ajout += SEPARATOR;			             
			        Files.write(orderPath, String.format(ajout).getBytes(), APPEND);
			       
			      }
			      catch(Exception e)
			      {
			        e.printStackTrace();
			      }			    
			}
        	
        	
        }
        catch(IOException e) {
        	e.printStackTrace();
        }
	}

	public void supprimer_doublons() //Supprime les doublons sauf la premiere et derniere occurence
		{
		Vector<LigneTableau> vector2 = new Vector<LigneTableau>(); //Vecteur temporaire pour stocker les LignesTableaux sans doublons
		int decallage=1;
		for(int indice=0; indice<vector.size(); indice+=decallage) //On parcours le vecteur
			{
			LigneTableau debut = vector.get(indice); //debut contient la LigneTableau que l'on est en train de regarder
			int offset=1;
			LigneTableau fin = new LigneTableau();
			boolean doublons=false;
			//Tant qu'on a un doublon et qu'on ne dépasse pas la taille du tableau on incrémente offset et on met le dernier élément en cours dans fin
			while((indice+offset)<vector.size()&&vector.elementAt(indice).is_equal_to(vector.elementAt(indice+offset)))
				{
				doublons=true;
				fin = vector.elementAt(indice+offset);
				offset+=1;
				decallage=offset;
				}
			vector2.add(debut); //On met début dans le vecteur, et si il y a des doublons, on met aussi fin, sinon pas besoin car on a rien supprimé
			if(doublons)
				{
				vector2.add(fin);	
				}
			}
		vector=vector2; //vector prend la valeur du vecteur temporaire vector2
		}
	
	public Vector<Vector<String>> ListEvent() //Renvoie un vector de string sous la forme suivante : evenement; contraite_temporelle µs;
	{
		Vector<Vector<String>> str = new Vector<Vector<String>>();
		Vector<Boolean> ligneComp = new Vector<Boolean>();
		Vector<Boolean> preLigneComp = new Vector<Boolean>();
		Vector<String> compName = new Vector<String>();
		long TimeConstraint = vector.elementAt(1).get_date() - vector.elementAt(0).get_date();
		//System.out.println(vector.size());
		for(int i = 1; i < vector.size(); i++)
		{
			
			LigneTableau ligne = vector.elementAt(i); //obj[i];
			LigneTableau preLigne = vector.elementAt(i-1); //obj[i-1];
			if(!ligne.is_equal_to(preLigne))
			{
				TimeConstraint = (long)ligne.get_date() - (long)preLigne.get_date();
				ligneComp.clear();
				preLigneComp.clear();
				compName.clear();
				//Comp1 box_conveyor
				ligneComp.add(ligne.get_box_conveyor());
				preLigneComp.add(preLigne.get_box_conveyor());
				compName.add("box_conveyor");
				//Comp2 part_conveyor
				ligneComp.add(ligne.get_part_conveyor());
				preLigneComp.add(preLigne.get_part_conveyor());
				compName.add("part_conveyor");
				//Comp3 grab
				ligneComp.add(ligne.get_grab());
				preLigneComp.add(preLigne.get_grab());
				compName.add("grab");
				//Comp4 c_plus
				ligneComp.add(ligne.get_c_plus());
				preLigneComp.add(preLigne.get_c_plus());
				compName.add("c_plus");
				//Comp5 auto
				ligneComp.add(ligne.get_auto());
				preLigneComp.add(preLigne.get_auto());
				compName.add("auto");
				//Comp6 manual
				ligneComp.add(ligne.get_manual());
				preLigneComp.add(preLigne.get_manual());
				compName.add("manual");
				//Comp7 emergency_stop
				ligneComp.add(ligne.get_emergency_stop());
				preLigneComp.add(preLigne.get_emergency_stop());
				compName.add("emergency_stop");
				//Comp8 reset_button
				ligneComp.add(ligne.get_reset_button());
				preLigneComp.add(preLigne.get_reset_button());
				compName.add("reset_button");
				//Comp9 start
				ligneComp.add(ligne.get_start());
				preLigneComp.add(preLigne.get_start());
				compName.add("start");
				//Comp10 stop
				ligneComp.add(ligne.get_stop());
				preLigneComp.add(preLigne.get_stop());
				compName.add("stop");
				//Comp11 part_at_place
				ligneComp.add(ligne.get_part_at_place());
				preLigneComp.add(preLigne.get_part_at_place());
				compName.add("part_at_place");
				//Comp12 box_at_place
				ligneComp.add(ligne.get_box_at_place());
				preLigneComp.add(preLigne.get_box_at_place());
				compName.add("box_at_place");
				//Comp13 detected
				ligneComp.add(ligne.get_detected());
				preLigneComp.add(preLigne.get_detected());
				compName.add("detected");
				//Comp14 c_limit
				ligneComp.add(ligne.get_c_limit());
				preLigneComp.add(preLigne.get_c_limit());
				compName.add("c_limit");
				//System.out.println(ligneComp.size());
				Vector<String> ligneEvent = new Vector<String>();
				for(int j = 0; j < ligneComp.size(); j++)
				{
					if(ligneComp.elementAt(j) != preLigneComp.elementAt(j))
					{
						String event = new String();
						if(preLigneComp.elementAt(j) == true)
						{
							event = "FE_";
						}
						else
						{
							event ="RE_";
						}
						event+=compName.elementAt(j);
						event+=";";
						event+=TimeConstraint;
						event+="µs;";
						ligneEvent.add(event);
					}
				}
				str.add(ligneEvent);
			}
		}
		return str;
	}
}
