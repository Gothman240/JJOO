package com.itneut.jjoo.ui.login

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.itneut.jjoo.R
import com.itneut.jjoo.repositories.UserRepository


class LogInFragment : Fragment(R.layout.fragment_log_in) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val etNickName: EditText = view.findViewById(R.id.etNickName)
        val etPassword: EditText = view.findViewById(R.id.etPassword)
        val btnLogin: Button = view.findViewById(R.id.btnLogin)

        btnLogin.setOnClickListener {
            // TODO: Implement login logic
            val nickName = etNickName.text.toString()
            val password = etPassword.text.toString()

            val user = UserRepository.getUser(nickName, password)

            user?.let {
                UserRepository.loggedInUser = it
                Toast.makeText(requireContext(), "Bienvenido ${it.nickName}", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_logInFragment_to_profileFragment)
            }  ?: Toast.makeText(requireContext(), "Credenciales incorrectas", Toast.LENGTH_SHORT).show()


        }

    }

}