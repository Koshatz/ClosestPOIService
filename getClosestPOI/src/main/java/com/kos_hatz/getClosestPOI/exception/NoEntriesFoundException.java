/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kos_hatz.getClosestPOI.exception;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

/**
 *
 * @author K. Chatzistamatis
 */
@SoapFault(faultCode = FaultCode.CUSTOM,locale="en",faultStringOrReason = "No entries found",customFaultCode="{http://getClosestPoi-web-service}204")
public class NoEntriesFoundException extends RuntimeException{

    public NoEntriesFoundException(String message) {
        super(message);
    }

    public NoEntriesFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
