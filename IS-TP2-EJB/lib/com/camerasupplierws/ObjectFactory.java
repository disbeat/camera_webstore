
package com.camerasupplierws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.camerasupplierws package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _SearchCamera_QNAME = new QName("www.camerasupplierws.com", "searchCamera");
    private final static QName _SearchCameraResponse_QNAME = new QName("www.camerasupplierws.com", "searchCameraResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.camerasupplierws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Camera }
     * 
     */
    public Camera createCamera() {
        return new Camera();
    }

    /**
     * Create an instance of {@link SearchCamera }
     * 
     */
    public SearchCamera createSearchCamera() {
        return new SearchCamera();
    }

    /**
     * Create an instance of {@link SearchCameraResponse }
     * 
     */
    public SearchCameraResponse createSearchCameraResponse() {
        return new SearchCameraResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchCamera }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "www.camerasupplierws.com", name = "searchCamera")
    public JAXBElement<SearchCamera> createSearchCamera(SearchCamera value) {
        return new JAXBElement<SearchCamera>(_SearchCamera_QNAME, SearchCamera.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchCameraResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "www.camerasupplierws.com", name = "searchCameraResponse")
    public JAXBElement<SearchCameraResponse> createSearchCameraResponse(SearchCameraResponse value) {
        return new JAXBElement<SearchCameraResponse>(_SearchCameraResponse_QNAME, SearchCameraResponse.class, null, value);
    }

}
