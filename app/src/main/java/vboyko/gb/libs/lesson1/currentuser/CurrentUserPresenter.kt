package vboyko.gb.libs.lesson1.currentuser

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import vboyko.gb.libs.lesson1.AndroidScreens
import vboyko.gb.libs.lesson1.CurrentUserView
import vboyko.gb.libs.lesson1.GithubUser
import vboyko.gb.libs.lesson1.GithubUsersRepo


/**
 * Created by Maxim Zamyatin on 04.11.2021
 */


class CurrentUserPresenter(
    val usersRepo: GithubUsersRepo,
    val router: Router,
    val screens: AndroidScreens
) : MvpPresenter<CurrentUserView>() {

    fun backPressed(): Boolean {
        router.replaceScreen(screens.users())
        return true
    }

    fun loadRepos(user: GithubUser) =
        usersRepo.getRepos(user.repos_url)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { repos ->
                repos.forEach { repo ->
                    viewState.createRepoView(repo)
                }
            }
}