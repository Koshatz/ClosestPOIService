/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kos_hatz.getClosestPOI.services;

import com.kos_hatz.getClosestPOI.commons.StringConstants;
import com.kos_hatz.getClosestPOI.dao.PointOfInterestDao;
import com.kos_hatz.getClosestPOI.entities.PointOfInterest;
import com.kos_hatz.getClosestPOI.exception.CalculationServiceException;
import java.util.List;
import com.kos_hatz.getClosestPOI.exception.SearchPoiServiceException;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author K. Chatzistamatis
 */
@Service
public class SearchForPoiService {

    @Autowired
    private PointOfInterestDao pointOfInterestDao;
    @Autowired
    private @Qualifier("harvesiveCalculator") CalculateInt calcSrv;

    public PointOfInterest getClosestPoi(double startLatitude, double startLongitude) throws SearchPoiServiceException {
        try {
            List<PointOfInterest> allPOis = pointOfInterestDao.getAll();

            for (PointOfInterest poi : allPOis) {
                if (poi != null) {
                    poi.setDistance(calcSrv.calculateDistance(startLatitude, startLongitude, poi.getLocation().getX(), poi.getLocation().getY()));
                }
            }

            List<PointOfInterest> allPoisSortedBycalculatedDist = allPOis.parallelStream()
                    .sorted((a, b) -> Double.compare(a.getDistance(), b.getDistance()))
                    .collect(Collectors.toList());

            return allPoisSortedBycalculatedDist.size() > 0 ? allPoisSortedBycalculatedDist.get(0) : null;
        } catch (Exception e) {
            throw new SearchPoiServiceException(StringConstants.CLOSEST_API_EXCEPTION_MSG, e);
        }
    }

    public List<PointOfInterest> getAllWithHitsNum(int hits) {
        try {
            return pointOfInterestDao.getAllWithHitsNum(hits);
        } catch (Exception e) {
            throw new SearchPoiServiceException(StringConstants.MOST_HITTED_API_EXCEPTION_MSG, e);
        }
    }

}
