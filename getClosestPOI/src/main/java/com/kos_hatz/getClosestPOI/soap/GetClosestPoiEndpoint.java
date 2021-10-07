/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kos_hatz.getClosestPOI.soap;

import com.kos_hatz.getClosestPOI.commons.StringConstants;
import com.kos_hatz.getClosestPOI.entities.PointOfInterest;
import com.kos_hatz.getClosestPOI.exception.InvalidArgumentException;
import com.kos_hatz.getClosestPOI.services.SearchForPoiService;
import com.kos_hatz.getClosestPOI.services.UpdatePoiService;
import getclosestpoi_web_service.GetClosestPoiRequest;
import getclosestpoi_web_service.GetClosestPoiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

/**
 *
 * @author K. Chatzistamatis
 */
@Endpoint
public class GetClosestPoiEndpoint {

    @Autowired
    private SearchForPoiService searchForPoiService;
    @Autowired
    private UpdatePoiService updatePoiService;



    @PayloadRoot(namespace = StringConstants.NAMESPACE_URI, localPart = "getClosestPoiRequest")
    @ResponsePayload
    public GetClosestPoiResponse getClosestPOI(@RequestPayload GetClosestPoiRequest request) throws InvalidArgumentException{
        GetClosestPoiResponse response = new GetClosestPoiResponse();
        if (request != null && request.getLocation() != null) {
            if (Double.compare(request.getLocation().getLatitude(), 0.0) == 0
                    && Double.compare(request.getLocation().getLongitude(), 0.0) == 0) {
                throw new InvalidArgumentException(StringConstants.INVALID_ARGS_EXCEPTION_MSG);
            }

            PointOfInterest nearestPoint = searchForPoiService.getClosestPoi(request.getLocation().getLatitude(),
                    request.getLocation().getLongitude());
            if (nearestPoint != null) {
                response.setPoi(nearestPoint.getName());
                nearestPoint.setCounter(nearestPoint.getCounter() + 1);
                updatePoiService.updateHitsOnPoi(nearestPoint);
            } else {
                response.setPoi(StringConstants.NOPOIFOUND);
            }
        } else {
            throw new InvalidArgumentException(StringConstants.INVALID_ARGS_EXCEPTION_MSG);
        }
        return response;

    }
}
