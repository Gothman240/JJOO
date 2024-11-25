package com.itneut.jjoo.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.itneut.jjoo.R
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.itneut.jjoo.R.id.rvProfilePurchases
import com.itneut.jjoo.repositories.MedalTableRepository
import com.itneut.jjoo.repositories.PurchaseRepository
import com.itneut.jjoo.ui.medal.MedalTableAdapter

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(rvProfilePurchases)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = ProfileAdapter(PurchaseRepository.get())
    }

}