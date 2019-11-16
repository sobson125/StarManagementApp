package com.company;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

    public static void main(String[] args) throws ParseException {
        Star star = new Star("gwiazda",
                "uklad",
                true,
                new Declination(10, 20, 30.25),
                new RightAscension(10, 5, 10),
                10,
                5,
                2300,
                2.5);
        System.out.println(star);
    }
}
