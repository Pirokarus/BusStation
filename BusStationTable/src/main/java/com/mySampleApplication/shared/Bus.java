package com.mySampleApplication.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Bus implements IsSerializable {
    String number;
    String firstStation;
    String lastStation;
    Time time;

    public String getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bus bus = (Bus) o;

        if (getNumber() != null ? !getNumber().equals(bus.getNumber()) : bus.getNumber() != null) return false;
        if (getFirstStation() != null ? !getFirstStation().equals(bus.getFirstStation()) : bus.getFirstStation() != null)
            return false;
        if (getLastStation() != null ? !getLastStation().equals(bus.getLastStation()) : bus.getLastStation() != null)
            return false;
        return getTime() != null ? getTime().equals(bus.getTime()) : bus.getTime() == null;
    }

    @Override
    public int hashCode() {
        int result = getNumber() != null ? getNumber().hashCode() : 0;
        result = 31 * result + (getFirstStation() != null ? getFirstStation().hashCode() : 0);
        result = 31 * result + (getLastStation() != null ? getLastStation().hashCode() : 0);
        result = 31 * result + (getTime() != null ? getTime().hashCode() : 0);
        return result;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getFirstStation() {
        return firstStation;
    }

    public void setFirstStation(String firstStation) {
        this.firstStation = firstStation;
    }

    public String getLastStation() {
        return lastStation;
    }

    public void setLastStation(String lastStation) {
        this.lastStation = lastStation;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Bus() {
    }

    public Bus(String number, String firstStation, String lastStation, int h, int m) {
        this.number = number;
        this.firstStation = firstStation;
        this.lastStation = lastStation;
        this.time = new Time(h,m);
    }

    public Bus(String number, String firstStation, String lastStation, Time time) {
        this.number = number;
        this.firstStation = firstStation;
        this.lastStation = lastStation;
        this.time = time;
    }

    @Override
    public String toString() {
        return "Bus{" +
                "number='" + number + '\'' +
                ", firstStation='" + firstStation + '\'' +
                ", lastStation='" + lastStation + '\'' +
                ", time=" + time +
                '}';
    }
}
