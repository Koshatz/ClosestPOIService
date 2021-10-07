/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kos_hatz.getClosestPOI.soap;

import org.springframework.ws.soap.server.endpoint.SoapFaultAnnotationExceptionResolver;

/**
 *
 * @author K. Chatzistamatis
 */
public class PoiServiceExceptionResolver extends SoapFaultAnnotationExceptionResolver{

    public PoiServiceExceptionResolver() {
        super();
    }
    
    
}
