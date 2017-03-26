package com.mySampleApplication.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Reqest implements IsSerializable {
    private String sortField = "name";
    private String numFiltr;
    private String startStationFiltr;
    private String lastStationFiltr;
    private Time startTime;
    private Time endTime;
    private boolean isRead = true;
    private Bus bus;
    private int stationsNum = 10;

    public Reqest() {
    }

    public Reqest(String sortField, String numFiltr, String startStationFiltr, String lastStationFiltr, Time startTime, Time endTime, boolean isRead, Bus bus, int stationsNum) {
        this.sortField = sortField;
        this.numFiltr = numFiltr;
        this.startStationFiltr = startStationFiltr;
        this.lastStationFiltr = lastStationFiltr;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isRead = isRead;
        this.bus = bus;
        this.stationsNum = stationsNum;
    }

    public String getSortField() {
        return sortField;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

    public String getNumFiltr() {
        return numFiltr;
    }

    public void setNumFiltr(String numFiltr) {
        this.numFiltr = numFiltr;
    }

    public String getStartStationFiltr() {
        return startStationFiltr;
    }

    public void setStartStationFiltr(String startStationFiltr) {
        this.startStationFiltr = startStationFiltr;
    }

    public String getLastStationFiltr() {
        return lastStationFiltr;
    }

    public void setLastStationFiltr(String lastStationFiltr) {
        this.lastStationFiltr = lastStationFiltr;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public int getStationsNum() {
        return stationsNum;
    }

    public void setStationsNum(int stationsNum) {
        this.stationsNum = stationsNum;
    }
}
