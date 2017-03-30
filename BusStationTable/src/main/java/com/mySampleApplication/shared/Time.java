package com.mySampleApplication.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Time implements IsSerializable {
    int h;
    int m;

    public Time() {
    }

    public Time(int h, int m) {
        this.h = h;
        this.m = m;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Time time = (Time) o;

        if (getH() != time.getH()) return false;
        return getM() == time.getM();
    }

    @Override
    public int hashCode() {
        int result = getH();
        result = 31 * result + getM();
        return result;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    public boolean before(Time t){
        boolean res = false;
        if (this.h<t.getH()){
            res = true;
        }
        if (this.h==t.getH()&&this.m<t.getM()){
            res = true;
        }
        return res;
    }

    public boolean after(Time t){
        boolean res = false;
        if (this.h>t.getH()){
            res = true;
        }
        if (this.h==t.getH()&&this.m>t.getM()){
            res = true;
        }
        return res;
    }

    public int compareTo(Time t){
        return 60*t.getH() + t.getM() - 60 * this.h - this.m;
    }

    @Override
    public String toString() {
        return "" + h +
                "ч. " + m +
                "м.";
    }
}
