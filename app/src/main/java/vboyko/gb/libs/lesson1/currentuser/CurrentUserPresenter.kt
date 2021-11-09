package vboyko.gb.libs.lesson1.currentuser

import android.util.Log
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter
import vboyko.gb.libs.lesson1.AndroidScreens
import vboyko.gb.libs.lesson1.CurrentUserView
import vboyko.gb.libs.lesson1.GithubUser
import vboyko.gb.libs.lesson1.GithubUsersRepo
import javax.inject.Inject


/**
 * Created by Maxim Zamyatin on 04.11.2021
 */


class CurrentUserPresenter : MvpPresenter<CurrentUserView>() {

    @Inject
    lateinit var usersRepo: GithubUsersRepo

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var screens: AndroidScreens

    lateinit var disposable: Disposable

    fun backPressed(): Boolean {
        router.replaceScreen(screens.users())
        return true
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        disposable = usersRepo.subscribeOnGithubReposData()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.forEach { repo ->
                    viewState.createRepoView(repo)
                }
            }, {
                Log.e("CurrentUserPresenter", it.message, it)
            })
    }

    fun loadRepos(user: GithubUser) = usersRepo.getRepos(user)

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }
}