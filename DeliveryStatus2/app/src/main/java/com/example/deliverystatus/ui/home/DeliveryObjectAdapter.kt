package com.example.deliverystatus.ui.home

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.deliverystatus.R
import com.example.deliverystatus.network.DeliveryObject
import java.text.SimpleDateFormat
import java.util.*


class DeliveryObjectAdapter(val clickListener: DeliveryObjectListener) : RecyclerView.Adapter<DeliveryObjectAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val carrierImage: ImageView = itemView.findViewById(R.id.carrier_image)
        private val trailerId: TextView = itemView.findViewById(R.id.trailer_id)
        private val carrierId: TextView = itemView.findViewById(R.id.carrier_id)
        private val expectedArrivalTime: TextView = itemView.findViewById(R.id.expected_arrival)
        private val arrivalStatus: TextView = itemView.findViewById(R.id.status)

        fun bind(
            item: DeliveryObject,
            clickListener: DeliveryObjectListener
        ) {
            val currentTime = System.currentTimeMillis()
            val imageUri = item.carrierImageURL.toUri().buildUpon().scheme("https").build()

            itemView.setOnClickListener() {
                clickListener.onClick(item)
            }

            Glide.with(carrierImage.context)
                .load(imageUri)
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.loading_animation)
                        .error(R.drawable.ic_broken_image)
                )
                .into(carrierImage)

            trailerId.text = "Trailer ID: " + item.trailerId
            carrierId.text = "Carrier: " + item.carrierId

            val dateFormatter = SimpleDateFormat("h:mm a dd-MMM-yy", Locale.ENGLISH)
            expectedArrivalTime.text = "Expected at: " + dateFormatter.format(Date(item.expectedArrivalTime))
            if (currentTime < item.expectedArrivalTime) {
                arrivalStatus.text = "Arriving"
                arrivalStatus.setTextColor(Color.GREEN)
            }
            else {
                arrivalStatus.text = "Arrived"
                arrivalStatus.setTextColor(Color.BLACK)
            }
        }
    }

    var data = listOf<DeliveryObject>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.list_item_delivery_object_layout, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item, clickListener)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}

class DeliveryObjectListener(val clickListener: (deliveryObject: DeliveryObject) -> Unit) {
    fun onClick(deliveryObject: DeliveryObject) = clickListener(deliveryObject)
}