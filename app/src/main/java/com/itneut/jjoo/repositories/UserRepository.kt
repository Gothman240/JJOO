package com.itneut.jjoo.repositories

import com.itneut.jjoo.data.User

object UserRepository {
    private val users = mutableListOf<User>()

    var loggedInUser: User? = null
    init {
        users.add(User(1504L, "bbayarri", "abc123", "Brian", "Bayarri", 3500000.50, "2022/12/10"))
        users.add(User(2802L, "AHOZ", "lock_password", "Aylen", "Hoz", 200000.50, "2021/01/11"))
        users.add(User(1510L, "Diegote", "@12345", "Diego", "Gonzalez", 120000.0, "2018/04/15"))
    }

    fun getUser(nickname: String, password: String): User? {
         return users.find { it.nickName == nickname && it.password == password }
    }
    // Función para verificar si el usuario está logueado
    fun isLoggedIn(): Boolean {
        return loggedInUser != null
    }

    fun logout() {
        loggedInUser = null
    }

}