package com.itneut.jjoo.ui.profile

import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.itneut.jjoo.R
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.itneut.jjoo.repositories.MedalTableRepository
import com.itneut.jjoo.repositories.PurchaseRepository
import com.itneut.jjoo.repositories.UserRepository
import com.itneut.jjoo.ui.medal.MedalTableAdapter

class ProfileFragment : Fragment(R.layout.fragment_profile){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.rvProfile)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = ProfileAdapter(PurchaseRepository.get(-1))

        val name = view.findViewById<TextView>(R.id.name)
        val nickname = view.findViewById<TextView>(R.id.nickname)

        val user = UserRepository.loggedInUser

        if (user != null) {
            val fullName = "${user.name} ${user.surname}"
            name.text = fullName
            nickname.text = user.nickName

        }

    }
}