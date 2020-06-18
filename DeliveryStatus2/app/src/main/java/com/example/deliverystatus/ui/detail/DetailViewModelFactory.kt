package com.example.deliverystatus.ui.detail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.deliverystatus.network.DeliveryObject
import java.lang.IllegalArgumentException

class DetailViewModelFactory(
    private val deliveryObject: DeliveryObject,
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(deliveryObject, application) as T
        }

        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}