package vboyko.gb.libs.lesson1.users

import android.annotation.SuppressLint
import androidx.recyclerview.widget.LinearLayoutManager
import moxy.ktx.moxyPresenter
import vboyko.gb.libs.lesson1.App
import vboyko.gb.libs.lesson1.BackButtonListener
import vboyko.gb.libs.lesson1.UsersView
import vboyko.gb.libs.lesson1.ViewBindingFragment
import vboyko.gb.libs.lesson1.databinding.FragmentUsersBinding

class UsersFragment : ViewBindingFragment<FragmentUsersBinding>(FragmentUsersBinding::inflate),
    UsersView, BackButtonListener {

    companion object {
        fun newInstance() = UsersFragment().apply { App.instance.appComponent.inject(this) }
    }

    private val presenter: UsersPresenter by moxyPresenter {
        UsersPresenter().apply {
            App.instance.appComponent.inject(this)
        }
    }
    var adapter: UsersRVAdapter? = null

    override fun init() {
        binding.rvUsers.layoutManager = LinearLayoutManager(context)
        adapter = UsersRVAdapter(presenter.usersListPresenter)
        binding.rvUsers.adapter = adapter
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backPressed()
}

