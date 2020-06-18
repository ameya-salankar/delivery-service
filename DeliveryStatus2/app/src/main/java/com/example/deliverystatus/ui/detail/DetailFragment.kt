package com.example.deliverystatus.ui.detail

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.deliverystatus.R
import com.example.deliverystatus.databinding.DetailFragmentBinding
import java.text.SimpleDateFormat
import java.util.*

class DetailFragment : Fragment() {

    private lateinit var detailViewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val application = requireNotNull(activity).application
        val binding = DetailFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val deliveryObject = DetailFragmentArgs.fromBundle(requireArguments()).selectedObject
        val viewModelFactory = DetailViewModelFactory(deliveryObject, application)
        detailViewModel = ViewModelProvider(this, viewModelFactory).get(DetailViewModel::class.java)

        val currentTime = System.currentTimeMillis()
        val dateFormatter = SimpleDateFormat("h:mm a dd-MMM-yy", Locale.ENGLISH)
        detailViewModel.selectedObject.observe(viewLifecycleOwner, Observer {
            binding.apply {
                trailerId.text = "TrailerId: " + it.trailerId
                carrier.text = "Carrier: " + it.carrierId
                deliveryNumber.text = "Delivery Number: " + it.deliveryNumber.toString()
                deliveryType.text = "Delivery Type: " + it.deliveryType
                arrivalTime.text = "Expected Arrival: " + dateFormatter.format(Date(it.expectedArrivalTime))
            }

            if (currentTime < it.expectedArrivalTime) {
                binding.status.text = "Arriving"
                binding.status.setTextColor(Color.GREEN)
            }
            else {
                binding.status.text = "Arrived"
                binding.status.setTextColor(Color.BLACK)
            }

            val imageUri = it.carrierImageURL.toUri().buildUpon().scheme("https").build()
            Glide.with(binding.detailImage.context)
                .load(imageUri)
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.loading_animation)
                        .error(R.drawable.ic_broken_image)
                )
                .into(binding.detailImage)
        })

        return binding.root
    }
}
