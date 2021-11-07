package vboyko.gb.libs.lesson1.users

import android.util.Log
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpPresenter
import vboyko.gb.libs.lesson1.*

class UsersPresenter(
    private val usersRepo: GithubUsersRepo,
    private val router: Router,
    private val screens: AndroidScreens
) : MvpPresenter<UsersView>() {
    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GithubUser>()

        override var itemClickListener: ((UserItemView) -> Unit)? = null
        override fun getCount() = users.size
        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
            view.setAvatar(user.avatar_url)
        }
    }

    companion object {
        const val TAG = "UsersPresenter"
    }

    val usersListPresenter = UsersListPresenter()

    private val disposable = usersRepo.subscribeOnGithubUsersData()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({
            usersListPresenter.users.clear()
            usersListPresenter.users.addAll(it)
            viewState.updateList()
        }, {
            Log.e(TAG, it.message, it)
        })

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        usersRepo.getUsers()
        usersListPresenter.itemClickListener = { itemView ->
            router.replaceScreen(
                screens.currentUser(usersListPresenter.users[itemView.pos]),
            )
        }
    }

    fun backPressed(): Boolean {
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }
}