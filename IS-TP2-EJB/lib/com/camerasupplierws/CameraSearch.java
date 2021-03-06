
package com.camerasupplierws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.3-b02-
 * Generated source version: 2.0
 * 
 */
@WebService(name = "CameraSearch", targetNamespace = "www.camerasupplierws.com")
public interface CameraSearch {


    /**
     * 
     * @param arg0
     * @return
     *     returns com.camerasupplierws.Camera
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "searchCamera", targetNamespace = "www.camerasupplierws.com", className = "com.camerasupplierws.SearchCamera")
    @ResponseWrapper(localName = "searchCameraResponse", targetNamespace = "www.camerasupplierws.com", className = "com.camerasupplierws.SearchCameraResponse")
    public Camera searchCamera(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

}
