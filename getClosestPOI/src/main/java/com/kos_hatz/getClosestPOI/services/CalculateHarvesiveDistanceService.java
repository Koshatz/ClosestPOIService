/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kos_hatz.getClosestPOI.services;

import com.kos_hatz.getClosestPOI.commons.StringConstants;
import com.kos_hatz.getClosestPOI.exception.CalculationServiceException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author K. Chatzistamatis
 */
@Service
@Qualifier("harvesiveCalculator")
public class CalculateHarvesiveDistanceService implements CalculateInt{

    @Override
    public double calculateDistance (double startLatitude, double startLongitude, double endLatitude, double endLongitude) throws CalculationServiceException {
        try {
            if (Double.compare(endLatitude, endLatitude) == 0 && Double.compare(startLongitude, endLongitude) == 0) {
                return 0;
            } else {
                final int R = 6371; // Radious of the earth
                double lat1 = startLatitude;
                double lon1 = startLongitude;
                double lat2 = endLatitude;
                double lon2 = endLongitude;
                double latDistance = toRad(lat2 - lat1);
                double lonDistance = toRad(lon2 - lon1);
                double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                        + Math.cos(toRad(lat1)) * Math.cos(toRad(lat2))
                        * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
                Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
                Double distance = R * c;
                return distance;
            }
        } catch (Exception e) {
            throw new CalculationServiceException(StringConstants.CALC_EXCEPTION_MSG, e);
        }
    }

    private double toRad(Double value) {
        return value * Math.PI / 180;
    }
}
