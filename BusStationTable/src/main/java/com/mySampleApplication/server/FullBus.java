package com.mySampleApplication.server;

import com.mySampleApplication.shared.Bus;

import java.util.Comparator;

public class FullBus extends Bus {

    public static Comparator<FullBus> numberComparator = new Comparator<FullBus>() {
        @Override
        public int compare(FullBus o1, FullBus o2) {
            return o1.getNumber().compareTo(o2.getNumber());
        }
    };

    public static Comparator<FullBus> firstStationComparator = new Comparator<FullBus>() {
        @Override
        public int compare(FullBus o1, FullBus o2) {
            return o1.getFirstStation().compareTo(o2.getFirstStation());
        }
    };

    public static Comparator<FullBus> lastStationComparator = new Comparator<FullBus>() {
        @Override
        public int compare(FullBus o1, FullBus o2) {
            return o1.getLastStation().compareTo(o2.getLastStation());
        }
    };

    public static Comparator<FullBus> timeComparator = new Comparator<FullBus>() {
        @Override
        public int compare(FullBus o1, FullBus o2) {
            return o2.getTime().compareTo(o1.getTime());
        }
    };

    public FullBus(String number, String firstStation, String lastStation, int h, int m) {
        super(number, firstStation, lastStation, h, m);
    }
}
