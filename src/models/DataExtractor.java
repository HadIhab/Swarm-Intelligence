package models;
import java.io.*;
//une classe pour extraire les données à partir d'une instances
public class DataExtractor {
	private Instance instance;
    private Clause clause;

    public DataExtractor(String file_path) {
        File file = new File(file_path);
        String filePath = file.getPath();
        // si on a le droit de lecture
        file.setReadable(true);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            // chercher l'entete ,il est de la forme p pnf vars_number clauses_number
            while ((line = reader.readLine()) != null) {
                line = clearInput(line);
                if (line.charAt(0) == 'p') break;
            }

            // recuperer vars_number et clauses_number
            String[] stringBuffer = line.split("\\s");
            int vars_number = Integer.parseInt(stringBuffer[2]);
            instance = new Instance(vars_number);

            //lire les clauses
            while (((line = reader.readLine()) != null)) {
                line = clearInput(line);
                if (line.matches("(-?\\d|\\s)+")) {
                    stringBuffer = line.split(" ");
                    if (stringBuffer.length == 4) {
                        clause = new Clause(Integer.parseInt(stringBuffer[0]), Integer.parseInt(stringBuffer[1]), Integer.parseInt(stringBuffer[2]));
                        instance.addClause(clause);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    //on passe à cette methode chaque ligne du fichier d'insance pour la nettoyer des caracteres en plus
    private String clearInput(String input) {
        input = input.trim();
        input = input.replaceAll("\\s+", " ");
        return input;
    }
    
    public Instance getData() {
        return instance;
    }
}
