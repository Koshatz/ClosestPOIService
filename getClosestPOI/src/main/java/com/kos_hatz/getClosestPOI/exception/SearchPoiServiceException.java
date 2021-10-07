package com.kos_hatz.getClosestPOI.exception;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author K. Chatzistamatis
 */

@SoapFault(faultCode = FaultCode.CUSTOM,locale="en",faultStringOrReason = "Fetching Entries Fault",customFaultCode="{http://getClosestPoi-web-service}501")
public class SearchPoiServiceException extends RuntimeException {

    public SearchPoiServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public SearchPoiServiceException(String message) {
        super(message);
    }
    
    

}
