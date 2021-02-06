package Operator;

import java.util.Vector;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;


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
		
		// DÃƒÂ©claration des variables
		
		String csvFile = "C:\\Users\\Arthur\\Desktop\\projet\\Projet_Java\\input_file.csv";
		char cvsSplitBy = ';';
        BufferedReader r = null;        
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
        	
        	String[][] tableau1 = readCSV(csvFile, cvsSplitBy, StandardCharsets.UTF_8);
        	int[] nbr = {14,15,16,17,18,19,20};
        	for(String[] ligne : tableau1) {
        		L = new LigneTableau();
				for(int colonne=0; colonne<ligne.length; colonne++) {
					if(!existe(nbr,colonne)) {
						switch(colonne) {
						//On stock dans notre objet toutes les valeurs boolÃƒÂ©ennes
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
								
								//On va ici dÃƒÂ©composer la String de la date pour pouvoir l'exploiter
								
								annee = String.valueOf(ligne[colonne].charAt(19))+ String.valueOf(ligne[colonne].charAt(20)) + String.valueOf(ligne[colonne].charAt(21)) + String.valueOf(ligne[colonne].charAt(22));	
								month = "0" + String.valueOf(ligne[colonne].charAt(25)) ;
								day = String.valueOf(ligne[colonne].charAt(28)) + String.valueOf(ligne[colonne].charAt((29)));
								h = String.valueOf(ligne[colonne].charAt(32)) + String.valueOf(ligne[colonne].charAt((33)));
								min = String.valueOf(ligne[colonne].charAt(36)) + String.valueOf(ligne[colonne].charAt(37));								
								//Permet de remettre ÃƒÂ  0 la String
								ms = "";
								
								s = String.valueOf(ligne[colonne].charAt(39)) + String.valueOf(ligne[colonne].charAt(40)) + String.valueOf(ligne[colonne].charAt(41));
								s = s.replaceAll(" ", "");
								s = s.replaceAll(",", "");
								min = min.replaceAll(",", "");
								
								
								//On crÃƒÂ©ÃƒÂ© une nouvelle chaine de caratÃƒÂ¨re afin de rÃƒÂ©cupÃƒÂ©rer les millisecondes car la String ne fait pas tout le temps la mÃƒÂªme taille
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
								
								String MyDate = annee + "/" + month + "/" + day + " " + h + ":" + min + ":" + s;
								ms = ms.replaceAll(" ", "");
								
								
								//On convertit notre date afin de l'obtenir en milliseconde pour pouvoir ajouter les millisecondes rÃ©cupÃ©rÃ© precedemment
							
									long seconds = Long.parseLong(s) * 1000000;
									long minutes = Long.parseLong(min) * (60 * 1000000);
									long hours = Long.parseLong(h)* 60 *60 * 1000000;
									long days = Long.parseLong(day) * (24 * 60 * 60 * 1000000);
																
									long time = Long.parseLong(ms) + seconds + minutes + hours + days;									
									
									// On vÃ©rifie que le temps prÃ©cÃ©dent est infÃ©rieur au temps actuel 
									if (last_time != 0 && last_time > time) {
										time += last_time - time + 00000000001;
									}
									last_time = time;

									//System.out.println(time);	

									//On ajoute la date ÃƒÂ  notre Ligne
									L.set_date(time);	
								
														        
							}												
					}										
				}
				
				//On ajoute notre ligne au vecteur
				vector.addElement(L);				
			}
        	
        	
        }
        catch(IOException e) {
        	e.printStackTrace();
        }
	}

	public void supprimer_doublons() //Supprime les doublons sauf la premiere et derniere occurence
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
		}
	
	public Vector<String> ListEvent() //Renvoie un vector de string sous la forme suivante : evenement; contraite_temporelle µs;
	{
		Vector<String> str = new Vector<String>();
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
				for(int j = 0; j < ligneComp.size(); j++)
				{
					if(ligneComp.elementAt(j) != preLigneComp.elementAt(j))
					{
						String event = new String();
						if(preLigneComp.elementAt(j) == true)
						{
							event+="FE_";
						}
						else
						{
							event+="RE_";
						}
						event+=compName.elementAt(j);
						event+=";";
						event+=TimeConstraint;
						event+="µs;";
						str.add(event);
					}
				}
				
			}
		}
		return str;
	}
}
