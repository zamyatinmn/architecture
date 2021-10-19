package vboyko.gb.libs.lesson1

import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun users(): Screen
    fun currentUser(user: GithubUser): Screen
    fun settings() = Unit
}

