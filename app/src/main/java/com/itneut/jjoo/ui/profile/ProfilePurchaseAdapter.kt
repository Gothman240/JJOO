package com.itneut.jjoo.ui.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.itneut.jjoo.R
import com.itneut.jjoo.data.Purchase
import com.itneut.jjoo.repositories.EventRepository
import com.itneut.jjoo.utils.Format

class PurchaseAdapter(
    private val purchases: List<Purchase>
) : RecyclerView.Adapter<PurchaseAdapter.PurchaseViewHolder>() {

    class PurchaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val eventName: TextView = view.findViewById(R.id.eventName)
        val seatNumber: TextView = view.findViewById(R.id.seatNumber)
        val purchaseDate: TextView = view.findViewById(R.id.purchaseDate)
        val eventPrice: TextView = view.findViewById(R.id.eventPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PurchaseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_purchase_profile, parent, false)
        return PurchaseViewHolder(view)
    }

    override fun onBindViewHolder(holder: PurchaseViewHolder, position: Int) {
        val purchase = purchases[position]

        // Obtén el evento relacionado
        val event = EventRepository.getEventById(purchase.eventId)

        // Setea datos de la compra
        holder.eventName.text = event?.sport?.name ?: "Evento desconocido"
        holder.seatNumber.text = "Asiento: ${purchase.seat}"
        holder.purchaseDate.text = "Comprado: ${Format.date(purchase.createdDate)}"
        holder.eventPrice.text = "${Format.amount(purchase.amount)}"

        // Usa Picasso o Glide para cargar imágenes (si las tienes)
        // Picasso.get().load(event.image).into(holder.eventImage)
    }

    override fun getItemCount(): Int = purchases.size
}