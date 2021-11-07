package vboyko.gb.libs.lesson1.currentuser

import android.util.Log
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpPresenter
import vboyko.gb.libs.lesson1.AndroidScreens
import vboyko.gb.libs.lesson1.CurrentUserView
import vboyko.gb.libs.lesson1.GithubUser
import vboyko.gb.libs.lesson1.GithubUsersRepo
import vboyko.gb.libs.lesson1.users.UsersPresenter


/**
 * Created by Maxim Zamyatin on 04.11.2021
 */


class CurrentUserPresenter(
    private val usersRepo: GithubUsersRepo,
    private val router: Router,
    private val screens: AndroidScreens
) : MvpPresenter<CurrentUserView>() {

    fun backPressed(): Boolean {
        router.replaceScreen(screens.users())
        return true
    }

    private val disposable = usersRepo.subscribeOnGithubReposData()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({
            it.forEach { repo ->
                viewState.createRepoView(repo)
            }
        }, {
            Log.e(UsersPresenter.TAG, it.message, it)
        })

    fun loadRepos(user: GithubUser) = usersRepo.getRepos(user)

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }
}