
package com.shipping;

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
@WebService(name = "ShippingBean", targetNamespace = "www.shipping.com")
public interface ShippingBean {


    /**
     * 
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "add", targetNamespace = "www.shipping.com", className = "com.shipping.Add")
    @ResponseWrapper(localName = "addResponse", targetNamespace = "www.shipping.com", className = "com.shipping.AddResponse")
    public boolean add(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0);

}