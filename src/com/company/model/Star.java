package com.company.model;


import java.io.Serializable;
import java.util.Objects;

public class Star implements Serializable {

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

    //No args constructor
    public Star() {
    }


    //GETTERS

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


    //SETTERS


    public void setName(String name) {
        this.name = name;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public void setNorthernHemisphere(boolean northernHemisphere) {
        isNorthernHemisphere = northernHemisphere;
    }

    public void setDeclination(Declination declination) {
        this.declination = declination;
    }

    public void setRightAscension(RightAscension rightAscension) {
        this.rightAscension = rightAscension;
    }

    public void setObservedMagnitude(double observedMagnitude) {
        this.observedMagnitude = observedMagnitude;
    }

    public void setAbsoluteMagnitude(double absoluteMagnitude) {
        this.absoluteMagnitude = absoluteMagnitude;
    }

    public void setDistanceInLightYears(double distanceInLightYears) {
        this.distanceInLightYears = distanceInLightYears;
    }

    public void setConstellation(String constellation) {
        this.constellation = constellation;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    //OVERRIDED TO STRING
    @Override
    public String toString() {
        return "Star{" +
                "name='" + name + '\'' +
                ", constellation='" + constellation + '\'' +
                ", catalogName='" + catalogName + '\'' +
                ", isNorthernHemisphere=" + isNorthernHemisphere +
                ", declination=" + declination +
                ", rightAscension=" + rightAscension +
                ", observedMagnitude=" + observedMagnitude +
                ", absoluteMagnitude=" + absoluteMagnitude +
                ", distanceInLightYears=" + distanceInLightYears +
                ", temperature=" + temperature +
                ", mass=" + mass +
                '}';
    }

    //HASHCODE AND EQUALS TO DELETE
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Star star = (Star) o;
        return isNorthernHemisphere == star.isNorthernHemisphere &&
                Double.compare(star.observedMagnitude, observedMagnitude) == 0 &&
                Double.compare(star.absoluteMagnitude, absoluteMagnitude) == 0 &&
                Double.compare(star.distanceInLightYears, distanceInLightYears) == 0 &&
                Double.compare(star.temperature, temperature) == 0 &&
                Double.compare(star.mass, mass) == 0 &&
                Objects.equals(name, star.name) &&
                Objects.equals(catalogName, star.catalogName) &&
                Objects.equals(declination, star.declination) &&
                Objects.equals(rightAscension, star.rightAscension) &&
                Objects.equals(constellation, star.constellation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, catalogName, isNorthernHemisphere, declination, rightAscension, observedMagnitude, absoluteMagnitude, distanceInLightYears, constellation, temperature, mass);
    }
}
