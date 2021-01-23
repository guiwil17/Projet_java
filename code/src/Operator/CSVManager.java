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

	public Vector<LigneTableau> elimine_number() { 
		
		// DÃ©claration des variables
		
		String csvFile = "C:/Users/guigu/Desktop/CNAM/Java/Projet_java/input_file.csv";
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
						//On stock dans notre objet toutes les valeurs boolÃ©ennes
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
								
								//On va ici dÃ©composer la String de la date pour pouvoir l'exploiter
								
								annee = String.valueOf(ligne[colonne].charAt(19))+ String.valueOf(ligne[colonne].charAt(20)) + String.valueOf(ligne[colonne].charAt(21)) + String.valueOf(ligne[colonne].charAt(22));	
								month = "0" + String.valueOf(ligne[colonne].charAt(25)) ;
								day = String.valueOf(ligne[colonne].charAt(28)) + String.valueOf(ligne[colonne].charAt((29)));
								h = String.valueOf(ligne[colonne].charAt(32)) + String.valueOf(ligne[colonne].charAt((33)));
								min = String.valueOf(ligne[colonne].charAt(36)) + String.valueOf(ligne[colonne].charAt(37));								
								//Permet de remettre Ã  0 la String
								ms = "";
								
								s = String.valueOf(ligne[colonne].charAt(39)) + String.valueOf(ligne[colonne].charAt(40)) + String.valueOf(ligne[colonne].charAt(41));
								s = s.replaceAll(" ", "");
								s = s.replaceAll(",", "");
								min = min.replaceAll(",", "");
								
								
								//On crÃ©Ã© une nouvelle chaine de caratÃ¨re afin de rÃ©cupÃ©rer les millisecondes car la String ne fait pas tout le temps la mÃªme taille
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
								
								
								//On convertit notre date afin de l'obtenir en milliseconde pour pouvoir ajouter les millisecondes récupéré precedemment
							
									long seconds = Long.parseLong(s) * 1000000;
									long minutes = Long.parseLong(min) * (60 * 1000000);
									long hours = Long.parseLong(h)* 60 *60 * 1000000;
									long days = Long.parseLong(day) * (24 * 60 * 60 * 1000000);
																
									long time = Long.parseLong(ms) + seconds + minutes + hours + days;									
									
									// On vérifie que le temps précédent est inférieur au temps actuel 
									if (last_time != 0 && last_time > time) {
										time += last_time - time + 00000000001;
									}
									last_time = time;

									System.out.println(time);	

									//On ajoute la date Ã  notre Ligne
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
        
        return vector;

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
