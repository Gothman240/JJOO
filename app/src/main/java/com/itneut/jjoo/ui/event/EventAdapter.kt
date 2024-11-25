package com.itneut.jjoo.ui.event

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.itneut.jjoo.R
import com.itneut.jjoo.data.Event
import com.squareup.picasso.Picasso
import java.text.NumberFormat

class EventAdapter(
    private var events: List<Event>, // Cambiado a var para permitir actualizaciones
    private val onEventClick: (Event) -> Unit
) : RecyclerView.Adapter<EventAdapter.EventViewHolder>() {

    class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvEventName: TextView = itemView.findViewById(R.id.tvEventName)
        val tvEventDate: TextView = itemView.findViewById(R.id.tvEventDate)
        val tvEventPlace: TextView = itemView.findViewById(R.id.tvEventPlace)
        val tvEventPrice: TextView = itemView.findViewById(R.id.tvEventPrice)
        val sportLogo: ImageView = itemView.findViewById(R.id.sportLogo)
        val tvCalification: TextView = itemView.findViewById(R.id.tvCalification)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_event, parent, false)
        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = events[position]

        // Configura los datos del evento en las vistas
        holder.tvEventName.text = event.sport.name
        holder.tvEventDate.text = "${event.date}, ${event.hour}"
        holder.tvEventPlace.text = event.place
        holder.tvEventPrice.text = NumberFormat.getCurrencyInstance().format(event.price)
        holder.tvCalification.text = event.sport.stars.toString()
        Picasso.get().load(event.sport.logo).resize(350, 400).centerInside().into(holder.sportLogo)

        // Configura el clic en el ítem
        holder.itemView.setOnClickListener {
            onEventClick(event)
        }
    }

    override fun getItemCount(): Int = events.size

    // Método para actualizar los eventos mostrados
    fun updateEvents(newEvents: List<Event>) {
        events = newEvents
        notifyDataSetChanged() // Actualiza la lista del RecyclerView
    }
}
