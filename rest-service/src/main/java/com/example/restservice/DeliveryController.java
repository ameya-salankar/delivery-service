package com.example.restservice;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeliveryController {

	private DeliveryService deliveries = new DeliveryService();

	@GetMapping("/deliveries")
	public List<DeliveryObject> getThirtyDeliveries(
			@RequestParam(value = "fromTime", defaultValue = "1000") String fromTime,
			@RequestParam(value = "toTime", defaultValue = "10000") String toTime) {
		return deliveries.getDeliveries(fromTime, toTime);
	}
}