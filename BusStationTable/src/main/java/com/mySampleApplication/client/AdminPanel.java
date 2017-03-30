package com.mySampleApplication.client;

import com.google.gwt.user.client.ui.*;
import com.mySampleApplication.shared.Bus;
import com.mySampleApplication.shared.Time;


public class AdminPanel extends VerticalPanel {

    TextBox numTextBox;
    TextBox firstStationTextBox;
    TextBox lastStationTextBox;
    ListBox hours;
    ListBox minuts;


    public AdminPanel() {
        Label numLabel = new Label("Введите номер:");
        this.numTextBox = new TextBox();
        Label firstStationLabel = new Label("Введите начальную \n остановку:");
        this.firstStationTextBox = new TextBox();
        Label lastStationLabel = new Label("Введите конечную \n остановку:");
        this.lastStationTextBox = new TextBox();
        Label timeLabel = new Label("Введите время \n проезда:");
        this.hours = new ListBox();
        for(Integer i = 0; i<24; i++){
            hours.addItem(i.toString());
        }
        Label h = new Label("ч.");
        this.minuts = new ListBox();
        for(Integer i = 0; i<60; i++){
            minuts.addItem(i.toString());
        }
        Label m = new Label("м.");

        HorizontalPanel horizontalPanel1 = new HorizontalPanel();
        HorizontalPanel horizontalPanel2 = new HorizontalPanel();

        horizontalPanel1.add(numLabel);
        horizontalPanel1.add(numTextBox);
        horizontalPanel2.add(firstStationLabel);
        horizontalPanel2.add(firstStationTextBox);
        horizontalPanel2.add(lastStationLabel);
        horizontalPanel2.add(lastStationTextBox);
        horizontalPanel1.add(timeLabel);
        horizontalPanel1.add(hours);
        horizontalPanel1.add(h);
        horizontalPanel1.add(minuts);
        horizontalPanel1.add(m);
        add(horizontalPanel1);
        add(horizontalPanel2);

        numLabel.setStyleName("myTextBox");
        numTextBox.setStyleName("myTextBox");
        firstStationLabel.setStyleName("myTextBox");
        firstStationTextBox.setStyleName("myTextBox");
        lastStationLabel.setStyleName("myTextBox");
        lastStationTextBox.setStyleName("myTextBox");
        timeLabel.setStyleName("myTextBox");
        hours.setStyleName("myClickBox");
        h.setStyleName("myTextBox");
        minuts.setStyleName("myClickBox");
        m.setStyleName("myTextBox");
    }

    public Bus getBus(){
        Bus bus = new Bus();
        bus.setNumber(numTextBox.getValue());
        bus.setFirstStation(firstStationTextBox.getValue());
        bus.setLastStation(lastStationTextBox.getValue());
        bus.setTime(new Time(Integer.parseInt(hours.getSelectedValue()),Integer.parseInt(minuts.getSelectedValue())));
        return bus;
    }
}
