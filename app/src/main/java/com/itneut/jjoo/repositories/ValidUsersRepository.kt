package com.itneut.jjoo.repositories

import android.widget.Button
import android.widget.TextView
import com.itneut.jjoo.controller.NavigationController
import com.itneut.jjoo.data.User

object ValidUsersRepository {

    private val users = mutableListOf<User>()
    private var user: User? = null

    init {
        users.add(User(1504L, "bbayarri", "abc123", "Brian", "Bayarri", 3500000.50, "2022/12/10"))
        users.add(User(2802L, "AHOZ", "lock_password", "Aylen", "Hoz", 200000.50, "2021/01/11"))
        users.add(User(1510L, "Diegote", "@12345", "Diego", "Gonzalez", 120000.0, "2018/04/15"))
    }

    fun login(nick: TextView, pass_word: TextView): User? {

        for (user in users){
            if(user.nickName.equals(nick))
                if (user.password.equals(pass_word)){
                    this.user = user
                    break
                }
        }
        return this.user
    }

    fun getUser(): User?{
        return this.user
    }
}
