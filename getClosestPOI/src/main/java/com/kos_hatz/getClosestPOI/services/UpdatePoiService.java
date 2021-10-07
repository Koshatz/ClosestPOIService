/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kos_hatz.getClosestPOI.services;

import com.kos_hatz.getClosestPOI.commons.StringConstants;
import com.kos_hatz.getClosestPOI.dao.PointOfInterestDao;
import com.kos_hatz.getClosestPOI.entities.PointOfInterest;
import com.kos_hatz.getClosestPOI.exception.UpdatePoiServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author K. Chatzistamatis
 */
@Service
public class UpdatePoiService {

    @Autowired
    private PointOfInterestDao pointOfInterestDao;

    public void updateHitsOnPoi(PointOfInterest poiToUpdate) throws UpdatePoiServiceException{
        try {
            pointOfInterestDao.update(poiToUpdate);
        } catch (Exception e) {
            throw new UpdatePoiServiceException(StringConstants.UPDATE_HITS_EXCEPTION_MSG, e);
        }
    }

}
