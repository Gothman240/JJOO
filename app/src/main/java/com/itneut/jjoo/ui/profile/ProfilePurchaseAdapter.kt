package com.itneut.jjoo.ui.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.itneut.jjoo.R
import com.itneut.jjoo.data.Event
import com.itneut.jjoo.data.Purchase
import com.itneut.jjoo.repositories.EventRepository
import com.squareup.picasso.Picasso

class PurchaseAdapter(
    private val purchases: List<Purchase>
) : RecyclerView.Adapter<PurchaseAdapter.PurchaseViewHolder>() {

    class PurchaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val eventImage: ImageView = view.findViewById(R.id.eventImage)
        val eventName: TextView = view.findViewById(R.id.eventName)
        val seatNumber: TextView = view.findViewById(R.id.seatNumber)
        val purchaseDate: TextView = view.findViewById(R.id.purchaseDate)
        val eventPrice: TextView = view.findViewById(R.id.eventPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PurchaseViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_purchase_profile, parent, false)
        return PurchaseViewHolder(view)
    }

    override fun onBindViewHolder(holder: PurchaseViewHolder, position: Int) {
        val purchase = purchases[position]
        val event: Iterator<Event> = EventRepository.get().iterator()

        // Setear datos de la compra
        holder.eventName.text = "Evento ID: ${purchase.eventId}" // Cambia por el nombre del evento
        holder.seatNumber.text = "Asiento: ${purchase.seat}"
        holder.purchaseDate.text = purchase.createdDate
        holder.eventPrice.text = "$${purchase.amount}"

//        Picasso.get().load("https://img.freepik.com/vector-premium/icono-vectorial-futbol-puede-usarse-conjunto-iconos-juegos-olimpicos_717774-65217.jpg").into(holder.eventImage)

//         Usa Picasso o Glide para cargar imágenes (si las tienes)s
        while (event.hasNext()){
            var eventLocal: Event= event.next()
            if (eventLocal.sport.id.equals(purchase.eventId)) {
                Picasso.get().load(eventLocal .sport.logo).into(holder.eventImage)
            }
        }

    }

    override fun getItemCount(): Int = purchases.size
}