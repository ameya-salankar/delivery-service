package com.example.deliverystatus.network

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class DeliveryObject(
    val trailerId: String,
    val carrierId: String,
    val deliveryNumber: Long,
    val deliveryType: String,
    val expectedArrivalTime: Long,
    val carrierImageURL: String
): Parcelable