package com.example.restservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class DeliveryService {

    public List<DeliveryObject> getDeliveries(String fromTime, String toTime) {
        int dataSize = 30;

        // Invalid times.
        if (fromTime == "-1" || toTime == "-1")
            return new ArrayList<DeliveryObject>();

        // Generate dataSize random numbers
        Random r = new Random();
        long[] randomNumbers = r.longs(dataSize, 10000, 99999).toArray();
        long[] randomDeliveryTimes = r.longs(dataSize, Long.valueOf(fromTime), Long.valueOf(toTime)).toArray();

        // Generate trailer Ids and DeliveryNumbers
        List<String> trailerIds = new ArrayList<String>();
        List<Long> deliveryNumbers = new ArrayList<Long>();
        for (long randomNumber : randomNumbers) {
            trailerIds.add('A' + String.valueOf(randomNumber));
            deliveryNumbers.add(randomNumber);
        }

        String carrierId = "FEDEX";
        String deliveryType = "LIVE";
        String carrierImageURL = "https://dummyimage.com/600x400/000/fff";

        List<DeliveryObject> deliveryObjects = new ArrayList<DeliveryObject>();
        for (int i = 0; i < 30; i++) {
            deliveryObjects.add(new DeliveryObject(trailerIds.get(i), carrierId, deliveryNumbers.get(i), deliveryType,
                    randomDeliveryTimes[i], carrierImageURL));
        }

        return deliveryObjects;
    }
}