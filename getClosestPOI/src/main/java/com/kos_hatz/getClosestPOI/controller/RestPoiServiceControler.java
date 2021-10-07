/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kos_hatz.getClosestPOI.controller;

import com.kos_hatz.getClosestPOI.commons.StringConstants;
import com.kos_hatz.getClosestPOI.entities.PointOfInterest;
import com.kos_hatz.getClosestPOI.services.SearchForPoiService;
import com.kos_hatz.getClosestPOI.services.UpdatePoiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author K. Chatzistamatis
 */
@RestController
@RequestMapping("/api")
public class RestPoiServiceControler {

    

    @Autowired
    private SearchForPoiService searchForPoiService;
    @Autowired
    private UpdatePoiService updatePoiService;

    @GetMapping("/getClosestPoi/{latitude}/{longitude}")
    public String getNameOfClosestPoint(@PathVariable double latitude, @PathVariable double longitude) {
        PointOfInterest closestPoi = searchForPoiService.getClosestPoi(latitude, longitude);

        if (closestPoi != null) {

            closestPoi.setCounter(closestPoi.getCounter() + 1);
            updatePoiService.updateHitsOnPoi(closestPoi);
            return closestPoi.getName();
        } else {
            return StringConstants.NOPOIFOUND;
        }

    }

}
