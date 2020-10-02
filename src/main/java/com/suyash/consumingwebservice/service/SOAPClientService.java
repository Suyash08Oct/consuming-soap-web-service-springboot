package com.suyash.consumingwebservice.service;

import com.suyash.consumingwebservice.wsdl.GetCountryResponse;

public interface SOAPClientService {
    GetCountryResponse processRequest(String countryName);
}
