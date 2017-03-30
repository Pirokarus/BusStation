package com.mySampleApplication.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.mySampleApplication.shared.Bus;
import com.mySampleApplication.shared.Reqest;
import com.mySampleApplication.shared.Responce;
import com.mySampleApplication.shared.Time;


public class MySampleApplication implements EntryPoint {

    public void onModuleLoad() {

        FlexTable resTable = new FlexTable();
        Label numColimn = new Label("Номер");
        Label startStanColumn = new Label("Начальная остановка");
        Label lastStanColumn = new Label("Конечная Остановка");
        Label tameColumn = new Label("Время остановки");
        numColimn.setStyleName("myClickBox");
        startStanColumn.setStyleName("myClickBox");
        lastStanColumn.setStyleName("myClickBox");
        tameColumn.setStyleName("myClickBox");

        resTable.setStyleName("myTextBox");

        resTable.setWidget(0, 0, numColimn);
        resTable.setWidget(0, 1, startStanColumn);
        resTable.setWidget(0, 2, lastStanColumn);
        resTable.setWidget(0, 3, tameColumn);

        Reqest req = new Reqest();

        MyAsyncCallback callback = new MyAsyncCallback(resTable);

        HorizontalPanel adminPanel = new HorizontalPanel();
        HorizontalPanel resultPanel = new HorizontalPanel();

        Button admButton = new Button("Включить панель администратора");

        adminPanel.add(admButton);

        admButton.addStyleName("myButton");
        admButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                adminPanel.remove(admButton);

                VerticalPanel addBusPanel = new VerticalPanel();
                AdminPanel aPanel = new AdminPanel();
                addBusPanel.add(aPanel);

                Button addButton = new Button("Добавить");
                Button closeButton = new Button("Закрыть");

                addButton.addStyleName("myButton");
                closeButton.addStyleName("myButton");

                addBusPanel.add(addButton);
                addBusPanel.add(closeButton);

                adminPanel.add(addBusPanel);

                addButton.addClickHandler(new ClickHandler() {
                    @Override
                    public void onClick(ClickEvent event) {

                        req.setBus(aPanel.getBus());
                        req.setRead(false);
                        MySampleApplicationService.App.getInstance().myMessage(req, callback);
                        req.setRead(true);
                        Reqest reqest = new Reqest();
                        MySampleApplicationService.App.getInstance().myMessage(reqest, callback);

                    }
                });

                closeButton.addClickHandler(new ClickHandler() {
                    @Override
                    public void onClick(ClickEvent event) {
                        adminPanel.remove(addBusPanel);
                        adminPanel.add(admButton);
                    }
                });

            }
        });


        VerticalPanel filterPanel = new VerticalPanel();

        filterPanel.setStyleName("myTextBox");
        Label poiskAvt = new Label("Поиск автобуса");
        filterPanel.add(poiskAvt);

        HorizontalPanel numberPunel = new HorizontalPanel();
        Label numLabel = new Label("Введите номер:");
        numLabel.setStyleName("myTextBox");
        TextBox numBox = new TextBox();
        numBox.setStyleName("myTextBox");
        numberPunel.add(numLabel);
        numberPunel.add(numBox);
        filterPanel.add(numberPunel);

        Label puth = new Label("Маршрут:");
        HorizontalPanel puthPanel = new HorizontalPanel();
        TextBox firstStation = new TextBox();
        TextBox lastStation = new TextBox();
        firstStation.setStyleName("myTextBox");
        lastStation.setStyleName("myTextBox");
        Label tire = new Label(" - ");

        tire.setStyleName("myTextBox");
        filterPanel.add(puth);
        puthPanel.add(firstStation);
        puthPanel.add(tire);
        puthPanel.add(lastStation);

        filterPanel.add(puthPanel);

        Label timeLabel = new Label("Введите время:");

        HorizontalPanel timePanel = new HorizontalPanel();

        ListBox startHours = new ListBox();
        ListBox lastHours = new ListBox();

        startHours.setStyleName("myClickBox");
        lastHours.setStyleName("myClickBox");

        for (Integer i = 0; i < 24; i++) {
            startHours.addItem(i.toString());
            lastHours.addItem(i.toString());
        }
        Label h1 = new Label("ч.");
        Label h2 = new Label("ч.");
        ListBox startMinuts = new ListBox();
        ListBox lastMinuts = new ListBox();

        startMinuts.setStyleName("myClickBox");
        lastMinuts.setStyleName("myClickBox");

        for (Integer i = 0; i < 60; i++) {
            startMinuts.addItem(i.toString());
            lastMinuts.addItem(i.toString());
        }

        lastHours.setItemSelected(23, true);
        lastMinuts.setItemSelected(59, true);

        Label m1 = new Label("м.");
        Label m2 = new Label("м.");
        h1.setStyleName("myTextBox");
        h2.setStyleName("myTextBox");
        m1.setStyleName("myTextBox");
        m2.setStyleName("myTextBox");

        timePanel.add(startHours);
        timePanel.add(h1);
        timePanel.add(startMinuts);
        timePanel.add(m1);
        Label tire2 = new Label(" - ");
        tire2.setStyleName("myTextBox");
        timePanel.add(tire2);
        timePanel.add(lastHours);
        timePanel.add(h2);
        timePanel.add(lastMinuts);
        timePanel.add(m2);

        filterPanel.add(timeLabel);
        filterPanel.add(timePanel);

        Button filterButton = new Button("Применить");
        filterPanel.add(filterButton);
        filterButton.addStyleName("myButton");

        resultPanel.add(filterPanel);

        HorizontalPanel horizontalPanel = new HorizontalPanel();

        horizontalPanel.add(resultPanel);
        VerticalPanel resPanel = new VerticalPanel();
        resPanel.add(resTable);
        Button moreButton = new Button("Показать следующие 10");
        resPanel.add(moreButton);
        moreButton.addStyleName("myButton");
        horizontalPanel.add(resPanel);

        moreButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                req.setStationsNum(req.getStationsNum() + 10);
                MySampleApplicationService.App.getInstance().myMessage(req, callback);
            }
        });

        VerticalPanel mainPanel = new VerticalPanel();
        mainPanel.add(adminPanel);
        mainPanel.add(horizontalPanel);

        MySampleApplicationService.App.getInstance().myMessage(req, callback);

        filterButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                //MyAsyncCallback callback1 = new MyAsyncCallback(resTable);
                //Reqest reqest = new Reqest();
                resTable.removeAllRows();
                resTable.setWidget(0, 0, numColimn);
                resTable.setWidget(0, 1, startStanColumn);
                resTable.setWidget(0, 2, lastStanColumn);
                resTable.setWidget(0, 3, tameColumn);

                if (numBox.getValue() != "") {
                    req.setNumFiltr(numBox.getValue());
                } else {
                    req.setNumFiltr(null);
                }

                if (firstStation.getValue() != "") {
                    req.setStartStationFiltr(firstStation.getValue());
                } else {
                    req.setStartStationFiltr(null);
                }

                if (lastStation.getValue() != "") {
                    req.setLastStationFiltr(lastStation.getValue());
                } else {
                    req.setLastStationFiltr(null);
                }

                req.setStationsNum(10);

                req.setStartTime(new Time(Integer.parseInt(startHours.getSelectedValue()), Integer.parseInt(startMinuts.getSelectedValue())));
                req.setEndTime(new Time(Integer.parseInt(lastHours.getSelectedValue()), Integer.parseInt(lastMinuts.getSelectedValue())));

                MySampleApplicationService.App.getInstance().myMessage(req, callback);
            }
        });

        numColimn.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                req.setStationsNum(10);
                req.setSortField("name");
                resTable.removeAllRows();
                resTable.setWidget(0, 0, numColimn);
                resTable.setWidget(0, 1, startStanColumn);
                resTable.setWidget(0, 2, lastStanColumn);
                resTable.setWidget(0, 3, tameColumn);
                MySampleApplicationService.App.getInstance().myMessage(req, callback);

            }
        });

        startStanColumn.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                req.setStationsNum(10);
                req.setSortField("startStation");
                resTable.removeAllRows();
                resTable.setWidget(0, 0, numColimn);
                resTable.setWidget(0, 1, startStanColumn);
                resTable.setWidget(0, 2, lastStanColumn);
                resTable.setWidget(0, 3, tameColumn);
                MySampleApplicationService.App.getInstance().myMessage(req, callback);
            }
        });

        lastStanColumn.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                req.setStationsNum(10);
                req.setSortField("lastStation");
                resTable.removeAllRows();
                resTable.setWidget(0, 0, numColimn);
                resTable.setWidget(0, 1, startStanColumn);
                resTable.setWidget(0, 2, lastStanColumn);
                resTable.setWidget(0, 3, tameColumn);
                MySampleApplicationService.App.getInstance().myMessage(req, callback);
            }
        });

        tameColumn.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                req.setStationsNum(10);
                req.setSortField("time");
                resTable.removeAllRows();
                resTable.setWidget(0, 0, numColimn);
                resTable.setWidget(0, 1, startStanColumn);
                resTable.setWidget(0, 2, lastStanColumn);
                resTable.setWidget(0, 3, tameColumn);
                MySampleApplicationService.App.getInstance().myMessage(req, callback);
            }
        });

        RootPanel.get("slot1").add(mainPanel);
    }

    private static class MyAsyncCallback implements AsyncCallback<Responce> {
        private FlexTable flexTable;

        public MyAsyncCallback(FlexTable flexTable) {
            this.flexTable = flexTable;
        }

        public void onSuccess(Responce result) {
            int index = 0;
            for (Bus bus : result.getBusList()) {
                index = index + 1;
                Label l1 = new Label(bus.getNumber());
                Label l2 = new Label(bus.getFirstStation());
                Label l3 = new Label(bus.getLastStation());
                Label l4 = new Label(bus.getTime().toString());
                l1.setStyleName("table");
                l2.setStyleName("table");
                l3.setStyleName("table");
                l4.setStyleName("table");
                flexTable.setWidget(index, 0, l1);
                flexTable.setWidget(index, 1, l2);
                flexTable.setWidget(index, 2, l3);
                flexTable.setWidget(index, 3, l4);
            }
        }

        public void onFailure(Throwable throwable) {
            RootPanel.get("slot2").add(new Label("falure"));
        }
    }
}
