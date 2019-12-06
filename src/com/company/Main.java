package com.company;

import com.company.factory.StarFactory;
import com.company.model.Declination;
import com.company.model.RightAscension;
import com.company.model.Star;
import com.company.service.StarService;

import java.io.IOException;

public class Main {

    //Some basic method executions
    public static void main(String[] args) throws IOException {

        StarService starService = new StarService();
       // starService.createBase();
        Star star2 = StarFactory.createStar("GKS9100",
                "inna",
                true,
                new Declination(50, 20, 30.25),
                new RightAscension(10, 5, 10),
                10,
                5,
                2300,
                2.5);
        Star star = StarFactory.createStar("ABC8100",
                "inna",
                true,
                new Declination(50, 20, 30.25),
                new RightAscension(10, 5, 10),
                10,
                3.26,
                2300,
                2.5);
        starService.addStar(star);
        starService.addStar(star2);
//        starService.getStarsByDistance(1);
//        starService.getStarsByTemperature(2000, 2300);
//        starService.getStarsFromEitherNorthOrSouthPole(true);
       // starService.getAllSupernovas();
        starService.deleteStar(star2);
        starService.addStar(star2);
        starService.getAllStars();
    }
}
