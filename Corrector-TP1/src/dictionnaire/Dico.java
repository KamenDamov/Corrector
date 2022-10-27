package dictionnaire;

import java.io.IOException;
import java.util.*;

public class Dico {

    public static ArrayList<String> dict;

    public Dico(ArrayList<String> dict) {
        this.dict = dict;
        System.out.println(dict.toString());
    }
    public static HashMap<String, Integer> sortByValue(HashMap<String, Integer> hm)
    {
        // Create a list from elements of HashMap
        List<Map.Entry<String, Integer> > list =
                new LinkedList<Map.Entry<String, Integer> >(hm.entrySet());

        // Sort the list
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
        //TODO
        // Naive algo: 2 for loops equating to O(n*m) algo
        // For words that are not in dico
        // Add in a hashmap KEY == DicoWord and VAL == Levenshtein Distance
        // Sort on Val and keep only top 5

        //TODO
        // Algo part2
        // Create a hashset and check if word in it.
        // if not find 5 closest
        HashSet<String> wordSet = new HashSet<String>();
        for (int i = 0; i < texteAVerif.size(); i++) {
            if (!dict.contains(texteAVerif.get(i))){
                wordSet.add(texteAVerif.get(i));
            }
        }

        //Compute levenDist with words in hashset
        HashMap<String, HashMap> wordAndDistance2 = new HashMap<String, HashMap>();
        for(String key: wordSet){
            HashMap<String, Integer> distances = new HashMap<String, Integer>();
            for (int i = 0; i < dict.size(); i++) {
                distances.put(dict.get(i), compute_Levenshtein_distanceDP(key, dict.get(i)));
            }
            Map<String, Integer> hm12 = sortByValue(distances);
            HashMap<String, Integer> fin = new HashMap<String, Integer>();
            int n = 0;
            for (Map.Entry<String, Integer> en : hm12.entrySet()) {
                if (n == 5) {
                    break;
                }
                fin.put(en.getKey(), en.getValue());
                n++;
                wordAndDistance2.put(key, fin);
            }
        }
        return wordAndDistance2;
    }

    public static int compute_Levenshtein_distanceDP(String str1,
                                              String str2)
    {

        // A 2-D matrix to store previously calculated
        // answers of subproblems in order
        // to obtain the final

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
                                    + NumOfReplacement(str1.charAt(i - 1),str2.charAt(j - 1)), // replace
                            dp[i - 1][j] + 1, // delete
                            dp[i][j - 1] + 1); // insert
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
