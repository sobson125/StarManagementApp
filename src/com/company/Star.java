package com.company;


import java.util.HashMap;
import java.util.Map;

public class Star {
    //private static int counter = 0;
    private static Map<String, Integer> constellations = new HashMap<>();

    private String name;
    private String catalogName;
    private boolean isNorthernHemisphere;
    private Declination declination;
    private RightAscension rightAscension;
    private double observedMagnitude;
    private double absoluteMagnitude;
    private double distanceInLightYears;
    private String constellation;
    private double temperature;
    private double mass;

    public Star(String name, String constellation, boolean isNorthernHemisphere, Declination declination, RightAscension rightAscension, double observedMagnitude, double distanceInLightYears, double temperature, double mass) {
        this.name = name;
        this.isNorthernHemisphere = isNorthernHemisphere;
        this.declination = checkWhetherDeclinationIsValid(declination);
        this.rightAscension = checkWhetherRightAscensionIsValid(rightAscension);
        this.observedMagnitude = checkWhetherObservedMagnitudeIsValdi(observedMagnitude);
        this.absoluteMagnitude = calculateAbsoluteMagnitude();
        this.distanceInLightYears = distanceInLightYears;
        this.constellation = constellation;
        checkWhetherConstellationAlreadyExists(this.constellation);
        this.temperature = checkWhetherTemperatureIsValid(temperature);
        this.mass = checkWhetherMassIsValid(mass);
        this.catalogName = createCatalogName();
    }

    private Declination checkWhetherDeclinationIsValid(Declination declination) {
        boolean isCorrect = true;
        if (isNorthernHemisphere) {
            if (declination.getDegrees() > 90 || declination.getDegrees() < 0) {
                throw new IllegalArgumentException("Declination is not valid");
            }
        } else {
            if (declination.getDegrees() > 0 || declination.getDegrees() < -90) {
                throw new IllegalArgumentException("Declination is not valid");
            }
        }
        return declination;
    }

    public RightAscension checkWhetherRightAscensionIsValid(RightAscension rightAscension) {
        if (rightAscension.getHours() < 0 || rightAscension.getHours() > 24) {
            throw new IllegalArgumentException("Right ascension is not valid");
        }
        return rightAscension;
    }

    private double checkWhetherObservedMagnitudeIsValdi(double value){
        if (value>15.00 || value < -26.74){
            throw new IllegalArgumentException("Observed Magnitude is not valid");
        }
        return value;
    }

    //Method checks whether there are any Stars from given constellation
    private void checkWhetherConstellationAlreadyExists(String constellation) {
        if (constellations.containsKey(constellation)) {
            constellations.replace(constellation, constellations.get(constellation).intValue() + 1);
        } else {
            constellations.put(constellation, 0);
        }
    }

    //Method creates catalog name based on constelation and greek alphabet
    private String createCatalogName() {
        GreekAlphabet[] values = GreekAlphabet.values();
        String output = values[constellations.get(constellation).intValue()].name() + " " + constellation;
        return output;
    }

    private double calculateAbsoluteMagnitude() {
        return observedMagnitude - 5 * Math.log10(distanceInLightYears / 3.26) + 5;

    }

    private double checkWhetherMassIsValid(double mass) {
        if (mass<0.1 || mass>50.0){
            throw new IllegalArgumentException("Mass must be within 0.1 and 50 interval");
        }
        return mass;
    }

    private double checkWhetherTemperatureIsValid(double temperature) {
        if (temperature<2000){
            throw new IllegalArgumentException("Temperature must be higher or equal to 2000");
        }
        return temperature;
    }


    //GETTERS

    public static Map<String, Integer> getConstellations() {
        return constellations;
    }

    public String getName() {
        return name;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public boolean isNorthernHemisphere() {
        return isNorthernHemisphere;
    }

    public Declination getDeclination() {
        return declination;
    }

    public RightAscension getRightAscension() {
        return rightAscension;
    }

    public double getObservedMagnitude() {
        return observedMagnitude;
    }

    public double getAbsoluteMagnitude() {
        return absoluteMagnitude;
    }

    public double getDistanceInLightYears() {
        return distanceInLightYears;
    }

    public String getConstellation() {
        return constellation;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getMass() {
        return mass;
    }


    //OVERRIDED TO STRING
    @Override
    public String toString() {
        return "Star{" +
                "name='" + name + '\'' +
                ", catalogName='" + catalogName + '\'' +
                ", isNorthernHemisphere=" + isNorthernHemisphere +
                ", declination=" + declination +
                ", rightAscension=" + rightAscension +
                ", observedMagnitude=" + observedMagnitude +
                ", absoluteMagnitude=" + absoluteMagnitude +
                ", distanceInLightYears=" + distanceInLightYears +
                ", constellation='" + constellation + '\'' +
                ", temperature=" + temperature +
                ", mass=" + mass +
                '}';
    }
}
