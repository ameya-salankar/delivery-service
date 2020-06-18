package com.example.deliverystatus.ui.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.deliverystatus.network.DeliveryObject

class DetailViewModel(deliveryObject: DeliveryObject, app: Application) : AndroidViewModel(app) {
    private val _selectedObject = MutableLiveData<DeliveryObject>()
    val selectedObject: LiveData<DeliveryObject>
        get() = _selectedObject

    init {
        _selectedObject.value = deliveryObject
    }
}


