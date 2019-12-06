package com.company.repo;

import com.company.model.Star;

import java.util.List;

public interface StarRepository {
    void addStar(Star star);

    List<Star> getStar();

    Star getStarInCertainDistance(double distance);
    Star getStar(double distance);

}
