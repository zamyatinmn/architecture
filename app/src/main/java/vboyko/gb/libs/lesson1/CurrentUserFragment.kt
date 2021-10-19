package vboyko.gb.libs.lesson1

import android.os.Bundle
import android.view.View
import moxy.MvpAppCompatFragment
import vboyko.gb.libs.lesson1.databinding.FragmentCurrentUserBinding

class CurrentUserFragment :
    ViewBindingFragment<FragmentCurrentUserBinding>(FragmentCurrentUserBinding::inflate),
    CurrentUserView {
    companion object {
        const val LOGIN_KEY = "user_login"
        fun newInstance(user: GithubUser): MvpAppCompatFragment {
            val bundle = Bundle()
            bundle.putString(LOGIN_KEY, user.login)
            return CurrentUserFragment().apply {
                arguments = bundle
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            binding.tvLogin.text = it.getString(LOGIN_KEY)
        }
    }
}