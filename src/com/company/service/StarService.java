package com.company.service;

import com.company.factory.StarFactory;
import com.company.model.Star;

import java.io.*;
import java.util.List;


public class StarService {
    private List<Star> stars;
    private String fileName = "objects.obj";

    public StarService() throws IOException {
        stars = StarFactory.fillList();
        createBase();
    }

    //tworzy plik objects.obj
    private void createBase() {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            for (Star star : stars) {
                objectOutputStream.writeObject(star);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //dodaje gwiazde do pliku
    public void addStar(Star newStar) {
        stars.add(newStar);
        updateCatalogName(newStar);
        createBase();
    }

    public void getAllStars() {
        Object object;
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            while ((object = objectInputStream.readObject()) != null) {
                if (object instanceof Star) {
                    System.out.println(object);
                }
            }
        } catch (EOFException e) {
            System.out.println("koniec pliku");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void getStarsByConsellation(String constellation) {
        Object object;
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            while ((object = objectInputStream.readObject()) != null) {
                if (object instanceof Star) {
                    if (constellation.equals(((Star) object).getConstellation())) {
                        System.out.println(object);
                    }
                }
            }
        } catch (EOFException e) {
            System.out.println("koniec pliku");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void getStarsByDistance(double distance) {
        stars.stream()
                .filter(star -> star.getDistanceInLightYears() == distance * 3.26)
                .forEach(System.out::println);
    }

    public void getStarsByTemperature(double min, double max) {
        stars.stream()
                .filter(star -> star.getTemperature() >= min && star.getTemperature() <= max)
                .forEach(System.out::println);
    }

    public void getStarsFromEitherNorthOrSouthPole(boolean north) {
        stars.stream()
                .filter(star -> star.isNorthernHemisphere() == north)
                .forEach(System.out::println);
    }

    public void getAllSupernovas() {
        stars.stream()
                .filter(star -> star.getMass() > 1.44)
                .forEach(System.out::println);
    }

    public void deleteStar(Star star) {
        stars.removeIf(star1 -> star1.equals(star));
        StarFactory.list.remove(star);
        //stars.forEach(System.out::println);
        updateCatalogName(star);
        createBase();
    }

    //metoda do updateowania nazwy katalogu
    private void updateCatalogName(Star star) {
        StarFactory.GreekAlphabet[] values = StarFactory.GreekAlphabet.values();
        int howManyFound = 0;
//        String catalogName = values[0].name() + star.getConstellation();
        for (int i = 0; i < stars.size(); i++) {
            if (stars.get(i).getConstellation().equals(star.getConstellation())) {
                String catalogName = values[howManyFound].name() + star.getConstellation();
                howManyFound++;
                stars.get(i).setCatalogName(catalogName);
            }
        }
    }
}
