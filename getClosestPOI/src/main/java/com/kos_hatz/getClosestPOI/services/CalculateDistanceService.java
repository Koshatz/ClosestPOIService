/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kos_hatz.getClosestPOI.services;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author K. Chatzistamatis
 */
@Service
@Qualifier("simpleCalculator")
public class CalculateDistanceService implements CalculateInt {

    @Override
    public double calculateDistance(double startLatitude, double startLongitude, double endLatitude, double endLongitude) {
        if (Double.compare(endLatitude, endLatitude) == 0 && Double.compare(startLongitude, endLongitude) == 0) {
            return 0.0;
        } else {
            double theta = startLongitude - endLongitude;
            double dist = Math.sin(Math.toRadians(startLatitude)) * Math.sin(Math.toRadians(endLatitude)) + Math.cos(Math.toRadians(startLatitude)) * Math.cos(Math.toRadians(endLatitude)) * Math.cos(Math.toRadians(theta));
            dist = Math.acos(dist);
            dist = Math.toDegrees(dist);
            dist = dist * 60 * 1.1515;
            return dist;
        }
    }

}
