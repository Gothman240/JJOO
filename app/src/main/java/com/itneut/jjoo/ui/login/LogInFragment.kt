package com.itneut.jjoo.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.itneut.jjoo.R
import com.itneut.jjoo.repositories.ValidUsersRepository

class LogInFragment: Fragment(R.layout.fragment_log_in) {


        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            lateinit var text1: TextView
            lateinit var text2: TextView
            lateinit var button: Button

            text1.findViewById<TextView>(R.id.EmailAddress)
            text2.findViewById<TextView>(R.id.Password)
            button.findViewById<Button>(R.id.ButtonLogin)

            button.setOnClickListener {
                ValidUsersRepository.login(text1, text2)
            }
            return super.onCreateView(inflater, container, savedInstanceState)
        }


    }
