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
import java.util.Vector;

public class CSVManager {
	
	Vector<LigneTableau> vector = new Vector<LigneTableau>();
	
	public static void main(String[] args) {
		Vector<LigneTableau> res = elimine_number();
		LigneTableau t = res.get(0);
		System.out.println(t.get_box_conveyor());
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

	public static Vector<LigneTableau> elimine_number() { 
		
		// Déclaration des variables
		
		String csvFile = "C:/Users/guigu/Desktop/CNAM/Java/Projet_java/input_file.csv";
		char cvsSplitBy = ';';
        BufferedReader r = null;        
        String annee = "";
        String day = "";
        String month = "";
        String min = "";
        String hours = "";
        String s = "";
        String ms = "";
        Vector<LigneTableau> vector = new Vector<LigneTableau>();
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
						//On stock dans notre objet toutes les valeurs booléennes
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
								
								//On va ici décomposer la String de la date pour pouvoir l'exploiter
								
								annee = String.valueOf(ligne[colonne].charAt(19))+ String.valueOf(ligne[colonne].charAt(20)) + String.valueOf(ligne[colonne].charAt(21)) + String.valueOf(ligne[colonne].charAt(22));	
								month = "0" + String.valueOf(ligne[colonne].charAt(25)) ;
								day = String.valueOf(ligne[colonne].charAt(28)) + String.valueOf(ligne[colonne].charAt((29)));
								hours = String.valueOf(ligne[colonne].charAt(32)) + String.valueOf(ligne[colonne].charAt((33)));
								min = String.valueOf(ligne[colonne].charAt(36));								
								//Permet de remettre à 0 la String
								ms = "";
								
								s = String.valueOf(ligne[colonne].charAt(39)) + String.valueOf(ligne[colonne].charAt(40));
								s = s.replaceAll(" ", "");
								s = s.replaceAll(",", "");
								
								// Afin d'avoir une date correcte on rajoute un 0 devant si la valeure est inférieure à 10
								if(Integer.parseInt(s) < 10) 
								{
									s = "0" + s;
								}
								
								if(Integer.parseInt(min) < 10) 
								{
									min = "0" + min;
								}
								
								//On créé une nouvelle chaine de caratère afin de récupérer les millisecondes car la String ne fait pas tout le temps la même taille
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
								
								String MyDate = annee + "/" + month + "/" + day + " " + hours + ":" + min + ":" + s;
								ms = ms.replaceAll(" ", "");
								
								
								//On convertit notre date afin de l'obtenir en milliseconde pour pouvoir ajouter les millisecondes récupéré précedemment
								try {
									SimpleDateFormat da = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
									Date d = da.parse(MyDate);
									long time = d.getTime() + Long.parseLong(ms);
									Date dd = new Date(time);
									
									//On ajoute la date à notre Ligne
									L.set_date(dd);								
								}
								catch(ParseException e) {
									System.out.println(e);
								}							        
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
