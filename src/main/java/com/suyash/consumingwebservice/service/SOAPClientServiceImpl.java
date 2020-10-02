package com.suyash.consumingwebservice.service;

import com.suyash.consumingwebservice.client.CountryClient;
import com.suyash.consumingwebservice.configuration.SoapClientConfiguration;
import com.suyash.consumingwebservice.wsdl.GetCountryResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SOAPClientServiceImpl implements SOAPClientService {

    @Override
    public GetCountryResponse processRequest(String countryName) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(SoapClientConfiguration.class);
        CountryClient client = annotationConfigApplicationContext.getBean(CountryClient.class);
        return client.getCountry(countryName);
    }
}
