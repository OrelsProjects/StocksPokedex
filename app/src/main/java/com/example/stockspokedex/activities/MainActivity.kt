package com.example.stockspokedex.activities

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.stockspokedex.R
import com.example.stockspokedex.utils.AppUtils
import com.example.stockspokedex.utils.General
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), View.OnClickListener,
    BottomNavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

    private fun showAddStockActivity() {
        val addStockActivityIntent = Intent(this, AddStockActivity::class.java)
        addStockActivityIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        this.startActivity(addStockActivityIntent)
    }
//
//    private fun selectPlaceholderIcon() {
//        for (i in 0 until bottomMenuBar.menu.size()) {
//            if (bottomMenuBar.menu.getItem(i).itemId == R.id.placeholder_action) {
//                bottomMenuBar.menu.getItem(i).isChecked = true
//                return
//            }
//        }
//    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.mainFAB -> {
                showAddStockActivity()
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
