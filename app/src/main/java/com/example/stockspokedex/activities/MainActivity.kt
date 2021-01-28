package com.example.stockspokedex.activities

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.stockspokedex.R
import com.example.stockspokedex.utils.AppIntents.EXTRA_IS_EDIT_STOCK
import com.example.stockspokedex.utils.AppIntents.EXTRA_STOCK_INFO_BUNDLE
import com.example.stockspokedex.utils.AppUtils
import com.example.stockspokedex.utils.General
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.FirebaseApp
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), View.OnClickListener,
    BottomNavigationView.OnNavigationItemSelectedListener {

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

    private fun showMainFragment() {
        if (General.presentedFragment == General.Fragments.Main) return
        navHostFragment.findNavController().navigate(R.id.mainFragment)
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
        this.startActivity(addStockActivityIntent)
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
        }
        return true
    }


}
