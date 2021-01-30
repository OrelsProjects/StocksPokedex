package com.example.stockspokedex.ui.fragments

import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.stockspokedex.R
import com.example.stockspokedex.activities.MainActivity
import com.example.stockspokedex.ui.base.BaseFragment
import com.example.stockspokedex.ui.viewmodels.AuthViewModel
import com.example.stockspokedex.ui.viewstates.AuthViewState
import com.example.stockspokedex.utils.AppUtils
import com.example.stockspokedex.utils.Constants
import com.example.stockspokedex.utils.General
import com.example.stockspokedex.utils.UserUtils
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_auth.*


@AndroidEntryPoint
class AuthFragment : BaseFragment<AuthViewModel, AuthViewState>(), View.OnClickListener {

    private lateinit var viewModel: AuthViewModel
    private var googleSignInClient: GoogleSignInClient? = null

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == Constants.GOOGLE_SIGN_IN_RESULT_CODE) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                viewModel.handleGoogleLogin(account.idToken!!)
            } catch (e: ApiException) {
                when (e.statusCode) {
                    12501 -> {
                        println("Gmail window dismissed")
                    }
                    else -> {
                        AppUtils.reportCrash(e)
                    }
                }
            } catch (e: Exception) {
                // account.idToken!! failed
                AppUtils.reportCrash(Throwable("account.idToken most likely failed."))
            }
        }
    }

    override fun onViewCreated() {
        initGoogleSignInClient()
        viewModel.handleIsUserLoggedIn()
    }

    override fun initViewModel() {
        viewModel = ViewModelProvider(requireActivity()).get(AuthViewModel::class.java)
    }

    private fun initGoogleSignInClient() {
        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient =
            activity?.let { GoogleSignIn.getClient(it, googleSignInOptions) }
    }

    private fun handleGoogleLogin() {
        val googleSignInIntent = googleSignInClient?.signInIntent
            ?: // todo something went wrong
            return
        startActivityForResult(googleSignInIntent, Constants.GOOGLE_SIGN_IN_RESULT_CODE)
    }

    override fun getViewModel(): AuthViewModel = viewModel

    override fun updateUI(state: AuthViewState) {
        if (state.isLoginFailed) {
            Toast.makeText(context, "Login failed", Toast.LENGTH_LONG).show()
        }
        if (state.isLoginSuccessful) {
            UserUtils.currentUser = state.connectedUser!!
            val mainActivityIntent = Intent(context, MainActivity::class.java)
            mainActivityIntent.flags =
                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            context?.startActivity(mainActivityIntent)
            activity?.finish()
        }
        state.reset()
    }

    override fun attachClickListeners() {
        googleLogin.setOnClickListener(this)
        testButton.setOnClickListener(this)
    }

    override fun getLayoutResourceFile(): Int = R.layout.fragment_auth
    override fun onClick(view: View) {
        when (view.id) {
            R.id.googleLogin -> {
                handleGoogleLogin()
            }
        }
    }

    override fun setCurrentFragment() {
        General.presentedFragment = General.Fragments.Login
    }
}