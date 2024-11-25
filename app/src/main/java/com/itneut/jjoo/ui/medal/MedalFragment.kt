package com.itneut.jjoo.ui.medal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.itneut.jjoo.R
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.itneut.jjoo.repositories.MedalTableRepository

class MedalFragment : Fragment(R.layout.fragment_medal) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.rvMedalTable)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = MedalTableAdapter(MedalTableRepository.get())
    }


}