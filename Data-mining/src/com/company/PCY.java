package com.company;
import java.io.*;
import java.util.*;
public final class PCY {

    //Variables
    private static int lim,mSize = 131072;// set the size to 2^17
    private static BitSet bit;

    //Setters
    public static void setBucketSize(int bSize) {
        mSize = bSize;
    }

    public static void setLimit(int p ) {
        lim = p ;
    }

    //Link the hash maps
    public static LinkedHashMap<SetFile, Integer> toFindPair(String retail) throws NumberFormatException, IOException {
        HashMap<Integer, Integer> ItemFrequent = PCYPassOne(retail);
        LinkedHashMap<SetFile, Integer> canBeSet = new LinkedHashMap<>();
        String _line,nol[];
        Integer key;
        ArrayList<Integer> myList = new ArrayList<>();

        FileReader toReadFile = new FileReader(retail);
        BufferedReader reader = new BufferedReader(toReadFile);
        while ((_line = reader.readLine()) != null) {
            nol = _line.split("\\s+");
            for (String toNum : nol) {
                /*convert to integer*/
                key = Integer.parseInt(toNum);
                if (ItemFrequent.containsKey(key) && !myList.contains(key)) {
                    myList.add(key);
                }
            }
            /*sort myList*/
            Collections.sort(myList);
            for (int n = 0; n < myList.size(); n++) {
                /*create pairs*/
                for (int k = n + 1; k < myList.size(); k++) {
                    SetFile<Integer, Integer> val = new SetFile<>(myList.get(n), myList.get(k));
                    if (!bit.get(Math.abs(val.hashCode()) % mSize)) {
                        continue;
                    }
                    if (canBeSet.containsKey(val)) {
                        canBeSet.put(val, canBeSet.get(val) + 1);
                    } else {
                        canBeSet.put(val, 1);
                    }
                }
            }
            myList.clear();

        }
        reader.close();

        removeNotFrequent(canBeSet);
        /*return frequent pairs and number of support*/
        return canBeSet;
    }

    /* first pass -- to find freq Item Counts */
    private static HashMap<Integer,Integer> PCYPassOne(String fileName) throws NumberFormatException, IOException {
        //Decelerations
        HashMap<Integer, Integer> pass1 = new HashMap<>();
        Bucket firstBucket = new Bucket(mSize);
        String totalLine[], _lin = "";
        Integer value;
        ArrayList<Integer> myList = new ArrayList<>();
        FileReader readFile = new FileReader(fileName);
        BufferedReader reader = new BufferedReader(readFile);
        while ((_lin = reader.readLine()) != null) {
            totalLine = _lin.split("\\s+");
            for (String num : totalLine) {
                value = Integer.parseInt(num);
                if (pass1.containsKey(value)) {
                    pass1.put(value, pass1.get(value) + 1);
                } else {
                    pass1.put(value, 1);
                }
                if (!myList.contains(value)) {
                    myList.add(value);
                }
            }
            Collections.sort(myList);
            for (int n = 0; n < myList.size(); n++) {
                for (int k = n + 1; k < myList.size(); k++) {
                    SetFile<Integer, Integer> val = new SetFile< >(myList.get(n), myList.get(k));

                    if (firstBucket.haveKey(val)) {
                        firstBucket.input(val, firstBucket.get(val) + 1);
                    } else {
                        firstBucket.input(val, 1);
                    }
                }
            }
            myList.clear();
        }
        reader.close();
        removeNotFrequent(pass1);
        bit = firstBucket.toBit(lim);
        firstBucket = null;
        return pass1;
    }
    private static boolean checkFrequancy(int num) {
        return num >= lim;
    }
    /*To remove none freq. Pair*/
    private static void removeNotFrequent(HashMap<Integer, Integer> map) {

        Iterator<Map.Entry<Integer, Integer>> check = map.entrySet().iterator();
        while (check.hasNext()) {
            Map.Entry<Integer, Integer> entry = check.next();
            if (!checkFrequancy(entry.getValue())) {
                check.remove();
            }
        }
    }
    /*To remove none freq. Pair*/
    private static void removeNotFrequent(LinkedHashMap<SetFile, Integer> map) {

        Iterator<Map.Entry<SetFile, Integer>> check = map.entrySet().iterator();
        while (check.hasNext()) {
            Map.Entry<SetFile,Integer> entry = check.next();
            if (!checkFrequancy(entry.getValue())) {
                check.remove();
            }
        }
    }
}