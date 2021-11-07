package vboyko.gb.libs.lesson1

import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import vboyko.gb.libs.lesson1.currentuser.CurrentUserFragment
import vboyko.gb.libs.lesson1.users.UsersFragment

class AndroidScreens : IScreens {
    override fun users(): Screen {
        return FragmentScreen { UsersFragment.newInstance() }
    }
    override fun currentUser(user: GithubUser): Screen {
        return FragmentScreen { CurrentUserFragment.newInstance(user) }
    }
}