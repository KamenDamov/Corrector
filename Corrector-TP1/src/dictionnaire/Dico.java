package dictionnaire;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

public class Dico {

    public static ArrayList<String> dico = new ArrayList<>();

    public Dico(){}

    //Méthode qui produit un dictionnaire en appelant la méthode vectorize
    public ArrayList<String>readDico(ArrayList<String> incomingDico){
        dico.clear();
        this.dico = vectorize(incomingDico);
        return incomingDico;
    }

    //Méthode qui nettoie le texte et sort un vecteur de texte sans espaces et ponctuations.
    public ArrayList<String> vectorize(ArrayList<String> textInput) {
        ArrayList<String> modifiedDict = new ArrayList<String>();
        for (int i = 0; i < textInput.size(); i++) {
            String[] strSplit = textInput.get(i).split(" ");
            for (int j = 0; j < strSplit.length; j++) {
                strSplit[j] = strSplit[j].replaceAll("\\p{Punct}","");
                modifiedDict.add(strSplit[j].toLowerCase());
            }
        }
        return modifiedDict;
    }

    //Méthode pour trier les distances
    public static HashMap<String, Integer> sortByValue(HashMap<String, Integer> hm)
    {
        // Créer une liste avec les éléments de la hashmap
        List<Map.Entry<String, Integer> > list =
                new LinkedList<Map.Entry<String, Integer> >(hm.entrySet());

        // Trier la liste
        Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2)
            {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        // put data from sorted list to hashmap
        HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }

    public static HashMap<String, HashMap> check(ArrayList<String> texteAVerif) throws IOException {

        //On créer un hashset qui icnlut uniquement les mots qui ne figurent pas dans le dictionnaire.
        HashSet<String> wordSet = new HashSet<String>();
        for (int i = 0; i < texteAVerif.size(); i++) {
            if (!dico.contains(texteAVerif.get(i))){
                wordSet.add(texteAVerif.get(i));
            }
        }

        //On calcule la distance de Levenshtein entre un mot dans le hashset et les mots du dictionnaire
        //On ajoute dans une hashmap le mot du dictionnaire comme clé et un Integer étant la distance comme valeur.
        HashMap<String, HashMap> wordAndDistance2 = new HashMap<String, HashMap>();
        for(String key: wordSet){
            HashMap<String, Integer> distances = new HashMap<String, Integer>();
            for (int i = 0; i < dico.size(); i++) {
                distances.put(dico.get(i), distanceDeLeven(key, dico.get(i)));
            }

            //On tri en ordre ascendant les distances
            Map<String, Integer> hm12 = sortByValue(distances);
            HashMap<String, Integer> fin = new HashMap<String, Integer>();
            int n = 0;

            //On garde uniquement les top 5 distances car c'est celles-ci qu'ont veut présenter à l'utilisateur.
            for (Map.Entry<String, Integer> en : hm12.entrySet()) {
                if (n == 5) {
                    break;
                }

                //On place la hashmap de top 5 mots les proches comme valeur dans une hashmap ayant comme clé les mots
                //qui ne figurent pas dans le dictionnaire.
                fin.put(en.getKey(), en.getValue());
                n++;
                wordAndDistance2.put(key, fin);
            }
        }
        return wordAndDistance2;
    }

    //Méthode pour calculer la distance en deux en utilisant l'algorithme de Levenshtein
    //Cet algorithme a été implémenté avec programmation dynamique.
    //Ressource: https://www.geeksforgeeks.org/java-program-to-implement-levenshtein-distance-computing-algorithm/
    public static int distanceDeLeven(String str1,
                                              String str2)
    {

        // Matrice 2D qui garde les reponses calculées

        int[][] dp = new int[str1.length() + 1][str2.length() + 1];

        for (int i = 0; i <= str1.length(); i++)
        {
            for (int j = 0; j <= str2.length(); j++) {

                if (i == 0) {
                    dp[i][j] = j;
                }

                else if (j == 0) {
                    dp[i][j] = i;
                }

                else {
                    dp[i][j] = minm_edits(dp[i - 1][j - 1]
                                    + NumOfReplacement(str1.charAt(i - 1),str2.charAt(j - 1)), // remplacer
                            dp[i - 1][j] + 1, // supprimer
                            dp[i][j - 1] + 1); // insérer
                }
            }
        }

        return dp[str1.length()][str2.length()];
    }

    static int NumOfReplacement(char c1, char c2)
    {
        return c1 == c2 ? 0 : 1;
    }

    static int minm_edits(int... nums)
    {

        return Arrays.stream(nums).min().orElse(
                Integer.MAX_VALUE);
    }


}
