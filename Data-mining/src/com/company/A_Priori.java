package com.company;
import java.io.*;
import java.util.*;
public final class A_Priori {

    private static int lim;

    //constructor
    private A_Priori() {
        lim = 0;
    }

    /*sets the threshold*/
    public static void setThreshold(int max) {
        lim = max;
    }

    /*finds the frequent pair*/
    public static LinkedHashMap<SetFile, Integer> toFindPair(String retail) throws NumberFormatException, IOException {

        HashMap<Integer, Integer> ItemFrequent = AprioriPassOne(retail);
        LinkedHashMap<SetFile,Integer> canBeSet = new LinkedHashMap<>();
        String numsLine[],_line = "";
        Integer value;
        ArrayList<Integer> myList = new ArrayList<>();
        FileReader fileReader = new FileReader(retail);
        BufferedReader reader = new BufferedReader(fileReader);
        while ((_line = reader.readLine()) != null) {
            numsLine = _line.split("\\s+");
            for (String num : numsLine) {
                value = Integer.parseInt(num);
                if (ItemFrequent.containsKey(value) && !myList.contains(value)) {
                    myList.add(value);
                }
            }
            //use collection class through "java.util.Collections" for sorting
            Collections.sort(myList);
            for (int n = 0; n < myList.size(); n++) {
                for (int k = n + 1; k< myList.size(); k++) {
                    SetFile<Integer, Integer> val = new SetFile<>(myList.get(n), myList.get(k));
                    if (canBeSet.containsKey(val)) {
                        canBeSet.put(val,
                                canBeSet.get(val) + 1);
                    } else {
                        canBeSet.put(val, 1);
                    }
                }
            }
            myList.clear();
        }
        reader.close();
        removeNotFrequent(canBeSet);
        return canBeSet;
    }

    /* to find freq Item Counts*/
    private static HashMap<Integer, Integer> AprioriPassOne(String fileName) throws NumberFormatException, IOException {
        HashMap<Integer, Integer> pass1 = new HashMap<>();

        String numsLine[],_line = "";
        Integer key;
        FileReader readFile = new FileReader(fileName);
        BufferedReader toRead = new BufferedReader(readFile);
        while ((_line = toRead.readLine()) != null) {
            numsLine = _line.split("\\s+");
            for (String num : numsLine) {
                key = Integer.parseInt(num);
                if (pass1.containsKey(key)) {
                    pass1.put(key, pass1.get(key) + 1);
                } else {
                    pass1.put(key, 1);
                }
            }
        }
        toRead.close();

        removeNotFrequent(pass1);
        return pass1;
    }

    /*method to check if item is frequent*/
    private static boolean checkFrequancy(int item) {
        return item >= lim;
    }
    /*remove none freq item*/
    private static void removeNotFrequent(HashMap<Integer, Integer> map) {

        Iterator<Map.Entry<Integer, Integer>> check = map.entrySet().iterator();
        while (check.hasNext()) {
            Map.Entry<Integer, Integer> entry = check.next();
            if (!(checkFrequancy(entry.getValue()))) {
                check.remove();
            }
        }
    }
    /*remove none freq pair*/
    private static void removeNotFrequent(LinkedHashMap<SetFile, Integer> map) {
        Iterator<Map.Entry<SetFile, Integer>> check = map.entrySet().iterator();
        while (check.hasNext()) {
            Map.Entry<SetFile, Integer> entry = check.next();
            if (!checkFrequancy(entry.getValue())) {
                check.remove();
            }
        }
    }


}