package com.itneut.jjoo.ui.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.itneut.jjoo.R
import com.itneut.jjoo.data.Purchase

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
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_purchase_profile, parent, false)
        return PurchaseViewHolder(view)
    }

    override fun onBindViewHolder(holder: PurchaseViewHolder, position: Int) {
        val purchase = purchases[position]

        // Setear datos de la compra
        holder.eventName.text = "Evento ID: ${purchase.eventId}" // Cambia por el nombre del evento
        holder.seatNumber.text = "Asiento: ${purchase.seat}"
        holder.purchaseDate.text = purchase.createdDate
        holder.eventPrice.text = "$${purchase.amount}"

        // Usa Picasso o Glide para cargar imágenes (si las tienes)
        // Picasso.get().load(event.image).into(holder.eventImage)
    }

    override fun getItemCount(): Int = purchases.size
}