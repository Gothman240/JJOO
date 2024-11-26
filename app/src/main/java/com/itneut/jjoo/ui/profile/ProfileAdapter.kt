package com.itneut.jjoo.ui.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.itneut.jjoo.R
import com.itneut.jjoo.data.Country
import com.itneut.jjoo.data.Event
import com.itneut.jjoo.data.Purchase
import com.itneut.jjoo.repositories.EventRepository
import com.itneut.jjoo.repositories.PurchaseRepository
import com.itneut.jjoo.repositories.UserRepository
import com.itneut.jjoo.ui.login.LogInFragment

class ProfileAdapter(private val purchases: List<Purchase>) :
    RecyclerView.Adapter<ProfileAdapter.ProfileHolder>() {
    class ProfileHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val sportValue: TextView = itemView.findViewById(R.id.tvSportValue)
        val dateValue: TextView = itemView.findViewById(R.id.tvDateValue)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_profile, parent, false)
        return ProfileHolder(view)
    }


    override fun onBindViewHolder(holder: ProfileHolder, position: Int) {
        val purchase = purchases[position]
        val userId : Long = purchase.userId
        val events = EventRepository.get()
        var sportType: String? = null
        val eventID: Long = purchase.eventId

        for (event in events) {
            if (event.id == eventID && userId  == UserRepository.loggedInUser?.id ) {
                sportType = event.sport.name
                holder.sportValue.text = sportType
                holder.dateValue.text = purchase.createdDate
            }
        }


    }

    override fun getItemCount(): Int = purchases.size

}
