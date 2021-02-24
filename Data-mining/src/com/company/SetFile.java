package com.company;

import java.util.*;
public class SetFile<T1, T2> {

    //Variables
    public T1 o1,o2;

    //Constructor
    public SetFile(T1 o1, T1 o2) {
        this.o1 = o1;
        this.o2 = o2;
    }

    //Setter
    public void setPair(T1 o1, T1 o2) {
        this.o1 = o1;
        this.o2 = o2;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SetFile)) {
            return false;
        }

        return (o1 == ((SetFile) o).o1) && (o2 == ((SetFile) o).o2);
    }

    /*This hash function which is used for 2 numbers of type integer*/
    public int hashCode() {
        int hashVariable = 321;
        hashVariable = 20 * hashVariable + Objects.hashCode(this.o1);
        hashVariable = 50 * hashVariable + Objects.hashCode(this.o2);
        return hashVariable;
    }

    public String toString() {
        return "(" + o1 + ", " + o2 + ")";
    }

}