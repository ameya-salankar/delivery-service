package com.example.restservice;

public class DeliveryObject {
    private String trailerId;
    private String carrierId;
    private long deliveryNumber;
    private String deliveryType;
    private long expectedArrivalTime;
    private String carrierImageURL;

    public DeliveryObject(String trailerId, String carrierId, long deliveryNumber, String deliveryType,
            long expectedArrivalTime, String carrierImageURL) {
        this.trailerId = trailerId;
        this.carrierId = carrierId;
        this.deliveryNumber = deliveryNumber;
        this.deliveryType = deliveryType;
        this.expectedArrivalTime = expectedArrivalTime;
        this.carrierImageURL = carrierImageURL;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    public String getCarrierImageURL() {
        return carrierImageURL;
    }

    public void setCarrierImageURL(String carrierImageURL) {
        this.carrierImageURL = carrierImageURL;
    }

    public long getExpectedArrivalTime() {
        return expectedArrivalTime;
    }

    public void setExpectedArrivalTime(long expectedArrivalTime) {
        this.expectedArrivalTime = expectedArrivalTime;
    }

    public long getDeliveryNumber() {
        return deliveryNumber;
    }

    public void setDeliveryNumber(long deliveryNumber) {
        this.deliveryNumber = deliveryNumber;
    }

    public String getCarrierId() {
        return carrierId;
    }

    public void setCarrierId(String carrierId) {
        this.carrierId = carrierId;
    }

    public String getTrailerId() {
        return trailerId;
    }

    public void setTrailerId(String trailerId) {
        this.trailerId = trailerId;
    }

}