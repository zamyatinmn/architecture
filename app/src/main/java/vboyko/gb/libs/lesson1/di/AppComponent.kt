package vboyko.gb.libs.lesson1.di

import dagger.Component
import vboyko.gb.libs.lesson1.MainActivity
import vboyko.gb.libs.lesson1.MainPresenter
import vboyko.gb.libs.lesson1.currentuser.CurrentUserFragment
import vboyko.gb.libs.lesson1.currentuser.CurrentUserPresenter
import vboyko.gb.libs.lesson1.users.UsersFragment
import vboyko.gb.libs.lesson1.users.UsersPresenter
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        CiceroneModule::class,
        CacheModule::class,
        ApiModule::class,
        RepoModule::class
    ]
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(usersPresenter: UsersPresenter)
    fun inject(currentUserPresenter: CurrentUserPresenter)
    fun inject(usersFragment: UsersFragment)
    fun inject(currentUserFragment: CurrentUserFragment)
}