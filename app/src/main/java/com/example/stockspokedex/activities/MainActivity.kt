package com.example.stockspokedex.activities

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.stockspokedex.R
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
    }

    private fun attachOnClickListeners() {
        addNewStockButton.setOnClickListener(this)
        bottomMenuBar.setOnNavigationItemSelectedListener(this)
    }

    private fun showMainFragment() {
        if (General.presentedFragment == General.Fragments.Main) return
        navHostFragment.findNavController().navigate(R.id.mainFragment)
    }

    private fun showAddStockFragment() {
        if (General.presentedFragment == General.Fragments.AddStock) return
        navHostFragment.findNavController()
            .navigate(R.id.action_mainFragment_to_addStockFragment)
        selectPlaceholderIcon()
    }

    private fun selectPlaceholderIcon() {
        for (i in 0 until bottomMenuBar.menu.size()) {
            if (bottomMenuBar.menu.getItem(i).itemId == R.id.placeholder_action) {
                bottomMenuBar.menu.getItem(i).isChecked = true
                return
            }
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.addNewStockButton -> showAddStockFragment()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_home -> showMainFragment()
        }
        return true
    }


}
