package com.itneut.jjoo.ui.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.itneut.jjoo.R
import com.itneut.jjoo.data.Purchase
import com.itneut.jjoo.ui.event.EventAdapter.EventViewHolder


class ProfileAdapter(private val purchases: List<Purchase>) :
    RecyclerView.Adapter<ProfileAdapter.PurchaseViewHolder>() {
    class PurchaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvPurchaseDetail: TextView = itemView.findViewById(R.id.tvPurchaseDetail)
        val tvPurchaseDate: TextView = itemView.findViewById(R.id.tvPurchaseDate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PurchaseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_event, parent, false)
        return PurchaseViewHolder(view)
    }

    override fun onBindViewHolder(holder: PurchaseViewHolder, position: Int) {
        val purchase = purchases[position]
        holder.tvPurchaseDetail.text = purchase.eventId.toString() // EXTRAER DATOS DE EVENTO RELACIONADO
        holder.tvPurchaseDate.text = purchase.createdDate


    }


        override fun getItemCount(): Int = purchases.size
}



