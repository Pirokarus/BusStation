package com.mySampleApplication.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

import java.util.List;

public class Responce implements IsSerializable {
    private List<Bus> busList;
    private boolean isEnd = false;

    public List<Bus> getBusList() {
        return busList;
    }

    public void setBusList(List<Bus> busList) {
        this.busList = busList;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public void setEnd(boolean end) {
        isEnd = end;
    }

    public void add(Bus bus){
        this.busList.add(bus);
    }

    public  void remove(Bus bus){
        this.busList.remove(bus);
    }

}
