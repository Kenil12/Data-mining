package com.company;

import java.util.*;
/*This class is used to override the hash code used by java*/
class SetFile2<T1, T2> extends SetFile<T1, T2> {

    public SetFile2(T1 o1, T1 o2) {
        super(o1, o2);
    }

    @Override
    public int hashCode() {
        int hashVar = 20;
        hashVar = 150 * hashVar + Objects.hashCode(this.o1);
        hashVar = 50 * hashVar + Objects.hashCode(this.o2);
        return hashVar;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof SetFile)) {
            return false;
        }
        if (this == o) {
            return true;
        }
        return (o1 == ((SetFile) o).o1) && (o2 == ((SetFile) o).o2);
    }

}