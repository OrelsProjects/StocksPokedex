package com.example.stockspokedex.activities

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.stockspokedex.R
import com.example.stockspokedex.ui.fragments.LoadingFragment
import com.example.stockspokedex.utils.AppIntents.EXTRA_IS_EDIT_STOCK
import com.example.stockspokedex.utils.AppIntents.EXTRA_STOCK_INFO_BUNDLE
import com.example.stockspokedex.utils.AppUtils
import com.example.stockspokedex.utils.General
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.FirebaseApp
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), View.OnClickListener,
    BottomNavigationView.OnNavigationItemSelectedListener {

    @Inject
    lateinit var loadingFragment: LoadingFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        setContentView(R.layout.activity_main)
        attachOnClickListeners()
        AppUtils.setNotificationAndNavigationBarsColors(this)
    }

    private fun attachOnClickListeners() {
        mainFAB.setOnClickListener(this)
        bottomMenuBar.setOnNavigationItemSelectedListener(this)
    }

    fun hideLoadingFragment() {
        AppUtils.detachFragment(supportFragmentManager, loadingFragment)
    }

    fun showLoadingFragment() {
        AppUtils.addFragmentToActivity(
            supportFragmentManager,
            loadingFragment,
            R.id.loadingFrameMain
        )
    }

    private fun showMainFragment() {
        if (General.presentedFragment == General.Fragments.Main) return
        navHostFragment.findNavController().navigate(R.id.mainFragment)
    }

    private fun showSettingsActivity(bundle: Bundle? = null) {
        val settingsIntent = Intent(this, SettingsActivity::class.java)
        settingsIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(settingsIntent)
    }

    fun showStockInfoActivity(bundle: Bundle? = null) {
        val addStockActivityIntent = Intent(this, StockInfoActivity::class.java)
        var bundleToPass: Bundle? = bundle
        if (bundleToPass == null) {
            bundleToPass = Bundle()
            bundleToPass.putBoolean(EXTRA_IS_EDIT_STOCK, false)
        }
        addStockActivityIntent.putExtra(EXTRA_STOCK_INFO_BUNDLE, bundleToPass)
        addStockActivityIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(addStockActivityIntent)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.mainFAB -> {
                showStockInfoActivity()
            }
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_home -> showMainFragment()
//            R.id.action_settings -> showSettingsActivity()
        }
        return true
    }


}
