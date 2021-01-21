package com.example.stockspokedex.ui.fragments

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.stockspokedex.R
import com.example.stockspokedex.activities.MainActivity
import com.example.stockspokedex.ui.base.BaseFragment
import com.example.stockspokedex.ui.viewmodels.LoginViewModel
import com.example.stockspokedex.ui.viewstates.LoginViewState
import com.example.stockspokedex.utils.General
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_login.*

@AndroidEntryPoint
class LoginFragment : BaseFragment<LoginViewModel, LoginViewState>(), View.OnClickListener {

    private lateinit var viewModel: LoginViewModel

    override fun initViewModel() {
        viewModel = ViewModelProvider(requireActivity()).get(LoginViewModel::class.java)
    }

    override fun getViewModel(): LoginViewModel = viewModel

    override fun updateUI(state: LoginViewState) {
        if(state.isLoginSuccessful){
            val mainActivityIntent = Intent(context, MainActivity::class.java)
            mainActivityIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context?.startActivity(mainActivityIntent)
        }
        state.reset()
    }

    override fun attachClickListeners() {
        loginButton.setOnClickListener(this)
    }

    override fun getLayoutResourceFile(): Int = R.layout.fragment_login
    override fun onClick(view: View) {
        when (view.id) {
            R.id.loginButton -> {
                viewModel.handleLogin()
            }
        }
    }

    override fun setCurrentFragment() {
        General.presentedFragment = General.Fragments.Login
    }
}