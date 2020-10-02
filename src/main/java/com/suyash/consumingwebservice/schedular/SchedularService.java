package com.suyash.consumingwebservice.schedular;

import com.suyash.consumingwebservice.service.SOAPClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SchedularService {

    @Autowired
    SOAPClientService service;

    @Scheduled(cron="0/2 * * * * *", zone = "IST")
    public void fetchData(){

        String[] array = {"India", "Poland", "United Kingdom"};
        for (int i = 0; i < array.length; i++){
            service.processRequest(array[i]).getCountry().getCapital();
        }

    }
}
