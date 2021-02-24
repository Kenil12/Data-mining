package com.company;

import java.util.*;
/* class used for holding entries*/
public class Bucket {

    //Variables
    private Begin list[];
    private int mSize = 131072;

    //Constructor
    public Bucket(int num) {
        mSize = num;
        list = new Begin[mSize];
    }



    //Getters
    public int getmaxSize() {
        return mSize;
    }

    public Integer get(SetFile k) {
        int hashVar = Math.abs(k.hashCode() % mSize);
        Begin begin = list[hashVar];
        return begin.getValue();

    }

    /*This method is used to convert into  BitSet*/
    public BitSet toBit(Integer thresh) {
        BitSet vector = new BitSet(mSize);

        for (int i = 0; i < mSize; i++) {
            if (list[i] != null && list[i].getValue() >= thresh) {
                vector.set(i);
            }
        }
        return vector;
    }

    /*To convert to a bitvector */
    public void input(SetFile k, Integer val) {
        int hashVar = Math.abs(k.hashCode() % mSize);
        Begin begin = list[hashVar];

        if (begin == null) {
            Begin newBegin = new Begin(k, val);
            list[hashVar] = newBegin;
        } else {
            begin.val = val;
        }
    }

    /*to checks if hash location has a k entry*/
    public boolean haveKey(SetFile k) {
        int hash = Math.abs(k.hashCode() % mSize);
        return (list[hash] != null);
    }

    //to string method to display output on the console
    public String toString() {
        String s;
        s = "{";
        for (Begin array : list) {
            if (array != null) {
                s += array;
            }
        }
        s = s+"}";
        return s;
    }

}