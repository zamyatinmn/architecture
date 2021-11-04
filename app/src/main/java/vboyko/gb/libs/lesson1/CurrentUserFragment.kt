package vboyko.gb.libs.lesson1

import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.view.View
import android.widget.TextView
import com.bumptech.glide.Glide
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import vboyko.gb.libs.lesson1.databinding.FragmentCurrentUserBinding

class CurrentUserFragment :
    ViewBindingFragment<FragmentCurrentUserBinding>(FragmentCurrentUserBinding::inflate),
    CurrentUserView,
    BackButtonListener {

    companion object {
        const val LOGIN_KEY = "user_login"
        fun newInstance(user: GithubUser): MvpAppCompatFragment {
            val bundle = Bundle()
            bundle.putParcelable(LOGIN_KEY, user)
            return CurrentUserFragment().apply {
                arguments = bundle
            }
        }
    }

    private val presenter: CurrentUserPresenter by moxyPresenter {
        CurrentUserPresenter(GithubUsersRepo(), App.instance.router, AndroidScreens())
    }

    override fun backPressed() = presenter.backPressed()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val user = it.getParcelable<GithubUser>(LOGIN_KEY)!!
            presenter.loadRepos(user)
            Glide.with(requireContext())
                .load(user.avatar_url)
                .into(binding.ivAvatar)
            binding.tvLogin.text = user.login
        }
    }

    override fun createRepoView(repo: Repo) {
        val textValue = "${repo.name}, forks count - ${repo.forks_count}"
        val span = SpannableString(textValue)
        span.setSpan(
            StyleSpan(Typeface.BOLD), 0, repo.name.length,
            Spannable.SPAN_INCLUSIVE_INCLUSIVE
        )

        binding.container.addView(
            TextView(requireContext()).apply {
                setSingleLine()
                text = span
            }
        )
    }
}