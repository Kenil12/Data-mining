package com.company;

public class Begin {

    final SetFile k;
    Integer val;

    public Begin(SetFile k, Integer val) {
        this.k = k;
        this.val = val;
    }

    public Integer getValue() {
        return val;
    }

    public SetFile getKey() {
        return k;
    }

    public void setValue(Integer val) {
        this.val = val;
    }

    public String toString() {

        return "[" + k + "=>" + val + "]";
    }
}