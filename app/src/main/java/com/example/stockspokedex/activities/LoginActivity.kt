package com.example.stockspokedex.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.stockspokedex.R
import com.example.stockspokedex.ui.fragments.LoginFragment
import com.example.stockspokedex.utils.AppUtils
import com.google.firebase.FirebaseApp
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity  : AppCompatActivity()  {

    @Inject
    lateinit var loginFragment: LoginFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        setContentView(R.layout.activity_login)
        AppUtils.addFragmentToActivity(supportFragmentManager, loginFragment, R.id.contentFrameLogin)
        AppUtils.setNotificationAndNavigationBarsColors(this)
    }
}