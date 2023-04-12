package com.jpmc.theater.util;

public class Util {
    public static double roundToTwoDecimals(double value) {
        return (Math.round(value * 100)) / 100d;
    }
}
