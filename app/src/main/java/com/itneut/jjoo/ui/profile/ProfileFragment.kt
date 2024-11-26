package com.itneut.jjoo.ui.profile

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.itneut.jjoo.R
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.itneut.jjoo.repositories.PurchaseRepository
import com.itneut.jjoo.repositories.UserRepository

class ProfileFragment : Fragment(R.layout.fragment_profile){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nickname = view.findViewById<TextView>(R.id.nickname)
        val money = view.findViewById<TextView>(R.id.money)
        val name = view.findViewById<TextView>(R.id.name)
        val profilePicture = view.findViewById<TextView>(R.id.profilePicture)
        val logOut = view.findViewById<LinearLayout>(R.id.LogOut)
        val recyclerView = view.findViewById<RecyclerView>(R.id.rvPurchases)


        val user = UserRepository.loggedInUser

        logOut.setOnClickListener {
            UserRepository.logout()
            findNavController().navigate(R.id.action_profileFragment_to_logInFragment)
        }

        if (user != null) {
            val fullName = "${user.name} ${user.surname}"
            money.text = user.money.toString()
            name.text = fullName
            nickname.text = user.nickName
            profilePicture.text = createProfilePicture(fullName)

            /*Falta Recycler View aquí*/
            // Configurar RecyclerView con las compras del usuario
            val purchases = PurchaseRepository.get(user.id)
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            recyclerView.adapter = PurchaseAdapter(purchases)

        }

    }

    fun createProfilePicture(name: String): String{
        return name.substring(0, 1).uppercase()
    }
}