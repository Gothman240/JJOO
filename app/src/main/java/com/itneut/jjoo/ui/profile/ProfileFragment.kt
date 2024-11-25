package com.itneut.jjoo.ui.profile

import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.itneut.jjoo.R
import androidx.fragment.app.Fragment
import com.itneut.jjoo.repositories.UserRepository

class ProfileFragment : Fragment(R.layout.fragment_profile){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val name = view.findViewById<TextView>(R.id.name)
        val nickname = view.findViewById<TextView>(R.id.nickname)

        val user = UserRepository.loggedInUser

        if (user != null) {
            val fullName = "${user.name} ${user.surname}"
            name.text = fullName
            nickname.text = user.nickName

            /*Falta Recycler View aquí*/

        }

    }
}