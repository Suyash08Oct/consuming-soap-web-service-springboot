package com.suyash.consumingwebservice.client;

import com.suyash.consumingwebservice.constants.Constant;
import com.suyash.consumingwebservice.wsdl.GetCountryRequest;
import com.suyash.consumingwebservice.wsdl.GetCountryResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

public class CountryClient extends WebServiceGatewaySupport {

    private static final Logger logger = LoggerFactory.getLogger(CountryClient.class);

    public GetCountryResponse getCountry(String countryName) {
        GetCountryRequest request = new GetCountryRequest();
        request.setName(countryName);

        logger.info("Requesting Country Name: " + countryName);

        GetCountryResponse response =  (GetCountryResponse) getWebServiceTemplate()
                .marshalSendAndReceive(Constant.DEFAULT_WSDL_URI, request);
        return response;

    }

}
