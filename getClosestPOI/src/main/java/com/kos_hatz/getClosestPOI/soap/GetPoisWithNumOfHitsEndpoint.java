/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kos_hatz.getClosestPOI.soap;

import com.kos_hatz.getClosestPOI.commons.StringConstants;
import com.kos_hatz.getClosestPOI.entities.PointOfInterest;
import com.kos_hatz.getClosestPOI.exception.InvalidArgumentException;
import com.kos_hatz.getClosestPOI.exception.NoEntriesFoundException;
import com.kos_hatz.getClosestPOI.services.SearchForPoiService;
import getclosestpoi_web_service.GetPoisWithHitsRequest;
import getclosestpoi_web_service.GetPoisWithHitsResponse;
import getclosestpoi_web_service.LocationType;
import getclosestpoi_web_service.PoiType;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

/**
 *
 * @author K. Chatzistamatis
 */
@EnableWs
@Endpoint
public class GetPoisWithNumOfHitsEndpoint {

    @Autowired
    private SearchForPoiService searchForPoiService;

    @PayloadRoot(namespace = StringConstants.NAMESPACE_URI, localPart = "getPoisWithHitsRequest")
    @ResponsePayload
    public GetPoisWithHitsResponse getHittedPois(@RequestPayload GetPoisWithHitsRequest request) throws InvalidArgumentException, NoEntriesFoundException {
        GetPoisWithHitsResponse response = new GetPoisWithHitsResponse();
        if (request != null) {
            List<PoiType> convertedPois = convertPoisToPoiType(searchForPoiService.getAllWithHitsNum(request.getHits()));
            response.getPoi().addAll(convertedPois);
            if (convertedPois.size() > 0) {
                if (request.getHits() < 1) {
                    throw new InvalidArgumentException(StringConstants.INVALID_ARGS_EXCEPTION_MSG);
                }
            }else{
                throw new NoEntriesFoundException(StringConstants.NO_POIS_WITH_HITS_EXCEPTION_MSG);
            }
        }
        return response;
    }

    private List<PoiType> convertPoisToPoiType(List<PointOfInterest> poiList) {
        List<PoiType> result = new ArrayList();
        for (PointOfInterest poiItem : poiList) {
            PoiType newItem = new PoiType();
            newItem.setHits(poiItem.getCounter());
            newItem.setId(poiItem.getId());
            newItem.setName(poiItem.getName());
            newItem.setDescription(poiItem.getDescription());
            if (poiItem.getLocation() != null) {
                LocationType loc = new LocationType();
                loc.setLatitude(poiItem.getLocation().getY());
                loc.setLongitude(poiItem.getLocation().getX());
                newItem.setPoint(loc);
            }
            result.add(newItem);
        }

        return result;
    }

}
