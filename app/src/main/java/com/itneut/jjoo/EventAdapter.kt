package com.itneut.jjoo

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.itneut.jjoo.data.Event

class EventAdapter(private val events: List<Event>) : RecyclerView.Adapter<EventAdapter.EventViewHolder>() {
    class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvEventName: TextView = itemView.findViewById(R.id.tvEventName)
        val tvEventDate: TextView = itemView.findViewById(R.id.tvEventDate)
        val tvEventPlace: TextView = itemView.findViewById(R.id.tvEventPlace)
        val tvEventPrice: TextView = itemView.findViewById(R.id.tvEventPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_event, parent, false)
        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = events[position]
        holder.tvEventName.text = event.sport.name
        holder.tvEventDate.text = "Fecha: ${event.date} - Hora: ${event.hour}"
        holder.tvEventPlace.text = "Lugar: ${event.place}"
        holder.tvEventPrice.text = "Precio: $${event.price}"
    }

    override fun getItemCount(): Int = events.size
}