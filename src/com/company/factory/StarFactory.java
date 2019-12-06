package com.company.factory;

import com.company.model.Declination;
import com.company.model.RightAscension;
import com.company.model.Star;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class StarFactory {

    // pomocnicza lista, tylko i wylacznie po to, zeby manipulowac nazwa katalogu
    public static List<Star> list = new ArrayList<>();
   // public static final Map<String, Integer> CONSTELLATIONS = new HashMap<>();

    public enum GreekAlphabet {
        ALPHA, BETA, GAMMA, DELTA, EPSILON, ZETA, ETA, THETA, IOTA, KAPPA, LAMBDA,
        MU,NU,XI,OMICRON,PI,RHO,SIGMA,TAU,UPSILON,PHI,CHI,PSI,OMEGA
    }

    public static Star createStar(String name, String constellation, boolean isNorthernHemisphere, Declination declination, RightAscension rightAscension,
                           double observedMagnitude, double distanceInLightYears, double temperature, double mass){
        Star star = new Star();
        // w poleceniu zalozone jest, ze nazwa zawsze jest prawidlowa, wiec jej nie waliduje
        star.setName(name);
        star.setConstellation(constellation);
//        checkWhetherConstellationAlreadyExists(constellation);
        star.setNorthernHemisphere(isNorthernHemisphere);
        star.setDeclination(checkWhetherDeclinationIsValid(star,declination));
        star.setRightAscension(checkWhetherRightAscensionIsValid(rightAscension));
        star.setObservedMagnitude(checkWhetherObservedMagnitudeIsValdi(observedMagnitude));
        star.setDistanceInLightYears(distanceInLightYears);
        star.setAbsoluteMagnitude(calculateAbsoluteMagnitude(star));
        star.setTemperature(checkWhetherTemperatureIsValid(temperature));
        star.setMass(checkWhetherMassIsValid(mass));
//        star.setCatalogName(createCatalogName(star));
        star.setCatalogName(createCatalogName(star));
        list.add(star);
        return star;
    }

    //Wszystkie walidacje w przypadku nie spełnienia oczekiwań rzucają wyjątki, jako, że obiekty tworzymy ręcznie, to
    //nie obsługuje tych wyjatkow

    private static Declination checkWhetherDeclinationIsValid(Star star, Declination declination) {
        if (star.isNorthernHemisphere()) {
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

    private static RightAscension checkWhetherRightAscensionIsValid(RightAscension rightAscension) {
        if (rightAscension.getHours() < 0 || rightAscension.getHours() > 24) {
            throw new IllegalArgumentException("Right ascension is not valid");
        }
        return rightAscension;
    }

    private static double checkWhetherObservedMagnitudeIsValdi(double value){
        if (value>15.00 || value < -26.74){
            throw new IllegalArgumentException("Observed Magnitude is not valid");
        }
        return value;
    }

    /*
    //Method checks whether there are any Stars in given constellation
    private static void checkWhetherConstellationAlreadyExists(String constellation) {
        if (CONSTELLATIONS.containsKey(constellation)) {
            CONSTELLATIONS.replace(constellation, CONSTELLATIONS.get(constellation).intValue() + 1);
        } else {
            CONSTELLATIONS.put(constellation, 0);
        }
    }

     */

    public static String createCatalogName(Star star){
        GreekAlphabet[] values = GreekAlphabet.values();
        int howManyFound = 0;
        String catalogName = values[0].name() + star.getConstellation();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getCatalogName().equals(catalogName)) {
                howManyFound++;
                catalogName = values[howManyFound].name() + star.getConstellation();
            }
        }
        return catalogName;
    }

    /*
    //Method creates catalog name based on constelation and greek alphabet
    public static String createCatalogName(Star star) {
        GreekAlphabet[] values = GreekAlphabet.values();
        String output = values[CONSTELLATIONS.get(star.getConstellation()).intValue()].name() + " " + star.getConstellation();
        return output;
    }
    */

    //Simple methods to check if the values are valid.
    private static double calculateAbsoluteMagnitude(Star star) {
        return star.getObservedMagnitude() - 5 * Math.log10(star.getDistanceInLightYears()/ 3.26) + 5;

    }

    private static double checkWhetherMassIsValid(double mass) {
        if (mass<0.1 || mass>50.0){
            throw new IllegalArgumentException("Mass must be within 0.1 and 50 interval");
        }
        return mass;
    }

    private static double checkWhetherTemperatureIsValid(double temperature) {
        if (temperature<2000){
            throw new IllegalArgumentException("Temperature must be higher or equal to 2000");
        }
        return temperature;
    }

    //inicjuje liste domyslnymi gwiazdami
    public static List<Star> fillList() {
        Star star = createStar("ABC1234",
                "uklad",
                true,
                new Declination(50, 20, 30.25),
                new RightAscension(10, 5, 10),
                10,
                5,
                2300,
                1);
        Star star1 = createStar("DEF5678",
                "uklad",
                true,
                new Declination(60, 30, 40.25),
                new RightAscension(12, 6, 12),
                11,
                7,
                2800,
                2.9);
        Star star2 = createStar("ZZZ1278",
                "uklad",
                true,
                new Declination(60, 30, 40.25),
                new RightAscension(12, 6, 12),
                11,
                7,
                2800,
                2.9);
        Star star3 = createStar("GHI9100",
                "inna",
                true,
                new Declination(50, 20, 30.25),
                new RightAscension(10, 5, 10),
                10,
                5,
                2300,
                2.5);

        Star star4 = createStar("XXX1000",
                "inna",
                true,
                new Declination(50, 20, 30.25),
                new RightAscension(10, 5, 10),
                10,
                5,
                2300,
                2.5);

        return Stream.of(star,star1,star2, star3,star4).collect(Collectors.toList());
    }

}
