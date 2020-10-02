package com.suyash.consumingwebservice.client;

import com.suyash.consumingwebservice.constants.Constant;
import com.suyash.consumingwebservice.wsdl.GetCountryRequest;
import com.suyash.consumingwebservice.wsdl.GetCountryResponse;
import com.suyash.consumingwebservice.wsdl.ObjectFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.transport.context.TransportContext;
import org.springframework.ws.transport.context.TransportContextHolder;
import org.springframework.ws.transport.http.HttpUrlConnection;

import javax.xml.transform.TransformerException;
import java.io.IOException;

@Slf4j
public class CountryClient extends WebServiceGatewaySupport {
    public GetCountryResponse getCountry(String countryName) {
        boolean isAuth = false;

        GetCountryRequest request = new GetCountryRequest();
        request.setName(countryName);

        log.info("Requesting Country Name: " + countryName);

        GetCountryResponse response = null;

        if(!isAuth){
            response =  (GetCountryResponse) getWebServiceTemplate().marshalSendAndReceive(Constant.DEFAULT_WSDL_URI, request);
        } else {
            response =  (GetCountryResponse) getWebServiceTemplate().marshalSendAndReceive(Constant.DEFAULT_WSDL_URI, request, new WebServiceMessageCallback() {
                @Override
                public void doWithMessage(WebServiceMessage message) throws IOException, TransformerException {
                    TransportContext context = TransportContextHolder.getTransportContext();
                    HttpUrlConnection connection = (HttpUrlConnection) context.getConnection();
                    connection.getConnection().addRequestProperty("Authorization", "Token");
                }
            });
        }

        log.info("Response: " + response.getCountry());

        return response;
    }

    /**
     * This method is just to show the encapsulation of SOAP request into JAXB Response
     */
    public GetCountryResponse getJaxbCountry(String countryName){
        ObjectFactory objectFactory = new ObjectFactory();
        GetCountryRequest request = objectFactory.createGetCountryRequest();
        if(request != null) {
            request.setName(countryName);
        }

        // Encapsulate the request into JAXBElement with the help of object Mapper
        //JAXBElement<GetCountryRequest> jaxbRequest = objectFactory.createCountry(request);

        // Process the JAXBRequest
        /*JAXBElement<GetCountryResponse> jaxbResponse = (JAXBElement<GetCountryResponse>)
                getWebServiceTemplate().marshalSendAndReceive(Constant.DEFAULT_WSDL_URI, jaxbRequest, new WebServiceMessageCallback() {
                    @Override
                    public void doWithMessage(WebServiceMessage message) throws IOException, TransformerException {
                        TransportContext context = TransportContextHolder.getTransportContext();
                        HttpUrlConnection connection = (HttpUrlConnection) context.getConnection();
                        connection.getConnection().addRequestProperty("Authorization", "Token");
                    }
                });*/

        //Retrieve the actual response from the JAXB Element
        //return jaxbResponse.getValue();

        return null;
    }

}
