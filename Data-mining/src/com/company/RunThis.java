package com.company;
import java.io.*;
public class RunThis {

    public static void main(String[] args) throws IOException {

        long firstvar,lastvar,diff;
        int[] percent = {1, 5, 10};

        for (int per : percent) {

            for (int i = 1; i <= 12; i++) {

                String retailIn = "retail.txt";
                int memSize = buckets(retailIn);
                System.out.println("\nInput file "+i +": "+retailIn);
                System.out.println("Bucket " + memSize + " read in at " + per + "% support");

                int threshold = calculation(memSize, per);
                firstvar = System.currentTimeMillis();
                A_Priori.setThreshold(threshold);
                A_Priori.toFindPair(retailIn);
                lastvar = System.currentTimeMillis();
                diff = lastvar - firstvar;
                System.out.println("Time for APriori: " + diff + "ms.");

                firstvar = System.currentTimeMillis();
                PCY.setLimit(threshold);
                PCY.toFindPair(retailIn);
                lastvar = System.currentTimeMillis();
                diff = lastvar - firstvar;
                System.out.println("Time for PCY: " + diff + "ms.");
                System.out.println("Frequent Pairs are: " );
                System.out.print( A_Priori.toFindPair(retailIn));
            }
        }
    }
    /*method to count the buckets */
    public static int buckets(String retail) throws IOException {
        InputStream inp = new BufferedInputStream(new FileInputStream(retail));
        byte[] i = new byte[1024];
        int reads,n = 0;
        boolean empty = true;
        while ((reads = inp.read(i)) != -1) {
            empty = false;
            for (int k = 0; k < reads; ++k) {
                //new line
                if (i[k] == '\n') {
                    ++n;
                }
            }
        }
        //Check if the line is not empty return 1 otherwise return n
        return (n == 0 && !empty) ? 1 : n;
    }
    /*calculate the amount of support for the required percentage*/
    public static int calculation(int sum, double per) {
        if (per < 0) {
            return 0;
        } else if (per > 100) {
            return sum;
        } else {
            //cast to integer
            return (int) Math.round((per / 100) * sum);
        }
    }

}