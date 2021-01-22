package com.example.stockspokedex.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.stockspokedex.R
import com.example.stockspokedex.ui.fragments.AddStockFragment
import com.example.stockspokedex.utils.AppUtils
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AddStockActivity : AppCompatActivity() {

    @Inject
    lateinit var addStockFragment: AddStockFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_stock)
        AppUtils.addFragmentToActivity(supportFragmentManager, addStockFragment, R.id.contentFrameLogin)
        AppUtils.setNotificationAndNavigationBarsColors(this)
    }
}