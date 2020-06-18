package com.example.deliverystatus.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.deliverystatus.R
import com.example.deliverystatus.databinding.HomeFragmentBinding

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    @SuppressLint("ShowToast")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = HomeFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        val adapter = DeliveryObjectAdapter(DeliveryObjectListener {
            homeViewModel.displayDeliveryDetails(deliveryObject = it)
        })
        binding.deliveryList.adapter = adapter

        homeViewModel.navigate.observe(viewLifecycleOwner, Observer {
            if(null != it) {
                this.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(it))
                homeViewModel.displayDeliveryDetailsComplete()
            }
        })

        homeViewModel.status.observe(viewLifecycleOwner, Observer {status ->
            when(status) {
                DeliveryApiStatus.LOADING -> {
                    binding.statusImage.visibility = View.VISIBLE
                    binding.statusImage.setImageResource(R.drawable.loading_animation)
                }

                DeliveryApiStatus.ERROR -> {
                    binding.statusImage.visibility = View.VISIBLE
                    binding.statusImage.setImageResource(R.drawable.ic_connection_error)
                }

                DeliveryApiStatus.DONE -> {
                    binding.statusImage.visibility = View.GONE
                }
            }
        })

        homeViewModel.deliveryObjects.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.data = it
            }
        })

        return binding.root
    }
}
