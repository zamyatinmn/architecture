package vboyko.gb.libs.lesson1

import android.util.Log
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class UsersPresenter(val usersRepo: GithubUsersRepo, val router: Router, val screens: AndroidScreens) : MvpPresenter<UsersView>() {
    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GithubUser>()

        override var itemClickListener: ((UserItemView) -> Unit)? = null
        override fun getCount() = users.size
        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
        }
    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
        usersListPresenter.itemClickListener = { itemView ->
            router.navigateTo(screens.currentUser(usersListPresenter.users.get(itemView.pos)), false)
        }
    }

    private fun loadData() {
        val users = usersRepo.getUsers()
        users.subscribe({
            usersListPresenter.users.add(it)
        }, {
            Log.e(this.javaClass.name, "Error repo get users", it)
        }, {
            viewState.updateList()
        })
    }

    fun backPressed(): Boolean{
        return true
    }
}