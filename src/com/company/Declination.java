package com.company;

public class Declination {
    private int degrees;
    private int minutes;
    private double seconds;

    public Declination(int degrees, int minutes, double seconds) {
        this.degrees = degrees;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public int getDegrees() {
        return degrees;
    }

    public int getMinutes() {
        return minutes;
    }

    public double getSeconds() {
        return seconds;
    }

    @Override
    public String toString() {
        return "Declination{" +
                "degrees=" + degrees +
                ", minutes=" + minutes +
                ", seconds=" + seconds +
                '}';
    }
}
