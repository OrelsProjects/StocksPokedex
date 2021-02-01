package com.example.stockspokedex.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.stockspokedex.R
import com.example.stockspokedex.ui.fragments.LoadingFragment
import com.example.stockspokedex.ui.fragments.StockInfoFragment
import com.example.stockspokedex.utils.AppIntents.EXTRA_STOCK_INFO_BUNDLE
import com.example.stockspokedex.utils.AppUtils
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class StockInfoActivity : AppCompatActivity() {

    @Inject
    lateinit var addStockFragment: StockInfoFragment

    @Inject
    lateinit var loadingFragment: LoadingFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stock_info)
        if (intent.getBundleExtra(EXTRA_STOCK_INFO_BUNDLE) != null) {
            addStockFragment.arguments = intent.extras
        }
        AppUtils.addFragmentToActivity(
            supportFragmentManager,
            addStockFragment,
            R.id.contentFrameStockInfo
        )
        AppUtils.setNotificationAndNavigationBarsColors(this)
    }

    fun hideLoadingFragment() {
        AppUtils.detachFragment(supportFragmentManager, loadingFragment)
    }

    fun showLoadingFragment() {
        AppUtils.addFragmentToActivity(
            supportFragmentManager,
            loadingFragment,
            R.id.loadingFrameStockInfo
        )
    }
}