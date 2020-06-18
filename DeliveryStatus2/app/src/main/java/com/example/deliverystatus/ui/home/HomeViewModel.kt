package com.example.deliverystatus.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.deliverystatus.network.DeliveryApi
import com.example.deliverystatus.network.DeliveryObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

enum class DeliveryApiStatus { LOADING, ERROR, DONE }

class HomeViewModel : ViewModel() {

    private val _status = MutableLiveData<DeliveryApiStatus>()
    val status: LiveData<DeliveryApiStatus>
        get() = _status

    private val _deliveryObjects = MutableLiveData<List<DeliveryObject>>()
    val deliveryObjects: LiveData<List<DeliveryObject>>
        get() = _deliveryObjects

    private val _navigate = MutableLiveData<DeliveryObject>()
    val navigate: LiveData<DeliveryObject>
        get() = _navigate

    init {
        getDeliveryObjects()
        _status.value = DeliveryApiStatus.LOADING
    }

    private fun getDeliveryObjects() {
        val currentTime = System.currentTimeMillis()
        val fourHoursPrev: String = (currentTime - (4 * 3600 * 1000)).toString()
        val eightHoursAhead: String = (currentTime + (8 * 3600 * 1000)).toString()

        _status.value = DeliveryApiStatus.LOADING
        DeliveryApi.retrofitApiService.getDeliveries(fourHoursPrev, eightHoursAhead).enqueue(
            object : Callback<List<DeliveryObject>> {
                override fun onFailure(call: Call<List<DeliveryObject>>, t: Throwable) {
                    _status.value = DeliveryApiStatus.ERROR
                    _deliveryObjects.value = ArrayList()
                }

                override fun onResponse(call: Call<List<DeliveryObject>>, response: Response<List<DeliveryObject>>) {
                    _status.value = DeliveryApiStatus.DONE
                    val listResult = response.body()
                    if(listResult?.size!! > 0)
                        _deliveryObjects.value = listResult
                }
            })
    }

    fun displayDeliveryDetails(deliveryObject: DeliveryObject) {
        _navigate.value = deliveryObject
    }

    fun displayDeliveryDetailsComplete() {
        _navigate.value = null
    }
}