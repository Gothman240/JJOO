package com.itneut.jjoo.ui.profile

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.itneut.jjoo.R
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.itneut.jjoo.repositories.UserRepository

class ProfileFragment : Fragment(R.layout.fragment_profile){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nickname = view.findViewById<TextView>(R.id.nickname)
        val money = view.findViewById<TextView>(R.id.money)
        val name = view.findViewById<TextView>(R.id.name)
        val profilePicture = view.findViewById<TextView>(R.id.profilePicture)
        val logOut = view.findViewById<LinearLayout>(R.id.LogOut)

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

        }

    }

    fun createProfilePicture(name: String): String{
        return name.substring(0, 1).uppercase()
    }
}