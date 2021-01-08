package Operator;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.sql.Time;	

public class test {

	
	
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
	
	public boolean estUnEntier(String chaine) {
		try {
			Integer.parseInt(chaine);
		} catch (NumberFormatException e){
			return false;
		}
 
		return true;
	}
	
	public static void main(String[] args) {
		String csvFile = "C:/Users/guigu/Desktop/CNAM/Java/Projet_java/input_file.csv";
		char cvsSplitBy = ';';
        BufferedReader r = null;
        String data[][];
        int x;
        int y;
        String annee = "";
        String day = "";
        char month = ' ';
        String min = "";
        String hours = "";
        String s = "";
        String ms = "";
        String[] li = null;
        LigneTableau L = new LigneTableau();

        try {
        	
        	String[][] tableau1 = readCSV(csvFile, cvsSplitBy, StandardCharsets.UTF_8);
        	int[] nbr = {14,15,16,17,18,19,20};
        	for(String[] ligne : tableau1) {
        		L = new LigneTableau();
				for(int colonne=0; colonne<ligne.length; colonne++) {
					if(!existe(nbr,colonne)) {
						switch(colonne) {
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
								annee = String.valueOf(ligne[colonne].charAt(19))+ String.valueOf(ligne[colonne].charAt(20)) + String.valueOf(ligne[colonne].charAt(21)) + String.valueOf(ligne[colonne].charAt(22));	
								month = ligne[colonne].charAt(25);
								day = String.valueOf(ligne[colonne].charAt(28)) + String.valueOf(ligne[colonne].charAt((29)));
								hours = String.valueOf(ligne[colonne].charAt(32)) + String.valueOf(ligne[colonne].charAt((33)));
								min = String.valueOf(ligne[colonne].charAt(36)) + String.valueOf(ligne[colonne].charAt((37)));								
								
								s = String.valueOf(ligne[colonne].charAt(39)) + String.valueOf(ligne[colonne].charAt(40));
								s = s.replaceAll(" ", "");
								
								 
								if(min.charAt(1) == ',' && s.charAt(0) == ' ') {
									ms = String.valueOf(ligne[colonne].charAt(41)) + String.valueOf(ligne[colonne].charAt(42)) + String.valueOf(ligne[colonne].charAt(43)) +String.valueOf(ligne[colonne].charAt(44)) + String.valueOf(ligne[colonne].charAt(45)) + String.valueOf(ligne[colonne].charAt(46))  ;
								}
								else if(min.charAt(1) == ',' || s.charAt(0) == ' ') {
									ms = String.valueOf(ligne[colonne].charAt(42)) + String.valueOf(ligne[colonne].charAt(43)) + String.valueOf(ligne[colonne].charAt(44)) +String.valueOf(ligne[colonne].charAt(45)) + String.valueOf(ligne[colonne].charAt(46)) + String.valueOf(ligne[colonne].charAt(47))  ;
								}
								else {
									ms = String.valueOf(ligne[colonne].charAt(43)) + String.valueOf(ligne[colonne].charAt(44)) + String.valueOf(ligne[colonne].charAt(45)) +String.valueOf(ligne[colonne].charAt(46)) + String.valueOf(ligne[colonne].charAt(47)) + String.valueOf(ligne[colonne].charAt(48))  ;
								}
								
								
								min = min.replaceAll(",", "");
								System.out.println(ms);
							         
							}
							
					}					
					
				}
				
			}
        	/*
        	r = new BufferedReader(new FileReader(csvFile));
        	String line = r.readLine();
            while (line != null) {


                String[] country = line.split(cvsSplitBy);

                System.out.println(line);
                line = r.readLine();

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (r != null) {
                try {
                    r.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }*/
        }
        catch(IOException e) {
        	e.printStackTrace();
        }

    }

	}


