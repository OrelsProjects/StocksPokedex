package com.example.stockspokedex.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.stockspokedex.R
import com.example.stockspokedex.ui.fragments.AuthFragment
import com.example.stockspokedex.utils.AppUtils
import com.google.firebase.FirebaseApp
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AuthActivity : AppCompatActivity() {

    @Inject
    lateinit var authFragment: AuthFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.splashScreenTheme)
        FirebaseApp.initializeApp(this)
        setContentView(R.layout.activity_auth)
        AppUtils.setNotificationAndNavigationBarsColors(this)
        AppUtils.addFragmentToActivity(
            supportFragmentManager,
            authFragment,
            R.id.contentFrameAuth
        )
    }
}