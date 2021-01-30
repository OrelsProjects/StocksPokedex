package com.example.stockspokedex.ui.fragments

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.stockspokedex.R
import com.example.stockspokedex.activities.MainActivity
import com.example.stockspokedex.data.entities.db.ChecklistEntity
import com.example.stockspokedex.data.entities.db.CompanyEntity
import com.example.stockspokedex.data.entities.db.StockEntity
import com.example.stockspokedex.ui.base.BaseFragment
import com.example.stockspokedex.ui.viewmodels.MainViewModel
import com.example.stockspokedex.ui.views.StockCardView
import com.example.stockspokedex.ui.viewstates.MainViewState
import com.example.stockspokedex.utils.AppIntents.EXTRA_CHECKLIST_TO_EDIT
import com.example.stockspokedex.utils.AppIntents.EXTRA_COMPANY_TO_EDIT
import com.example.stockspokedex.utils.AppIntents.EXTRA_IS_EDIT_STOCK
import com.example.stockspokedex.utils.AppIntents.EXTRA_STOCK_TO_EDIT
import com.example.stockspokedex.utils.General
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_main.*

@AndroidEntryPoint
class MainFragment : BaseFragment<MainViewModel, MainViewState>() {

    private lateinit var viewModel: MainViewModel

    override fun onViewCreated() {
        stocksFlexbox.post {
            viewModel.setCompanies()
        }
    }

    override fun updateUI(state: MainViewState) {
        state.companies.observe(this, {
            setStocksCards(it)
        })
        state.reset()
    }

    override fun attachClickListeners() {

    }

    override fun getLayoutResourceFile(): Int = R.layout.fragment_main

    override fun getViewModel(): MainViewModel = viewModel

    override fun initViewModel() {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun setCurrentFragment() {
        General.presentedFragment = General.Fragments.Main
    }

    private fun editStock(
        company: CompanyEntity?,
        stock: StockEntity?,
        checklist: ChecklistEntity?
    ) {
        val bundle = Bundle()
        bundle.putString(EXTRA_COMPANY_TO_EDIT, company?.toJson())
        bundle.putString(EXTRA_STOCK_TO_EDIT, stock?.toJson())
        bundle.putString(EXTRA_CHECKLIST_TO_EDIT, checklist?.toJson())
        bundle.putBoolean(EXTRA_IS_EDIT_STOCK, true)
        (activity as MainActivity).showStockInfoActivity(bundle)
    }

    private fun setStocksCards(companies: List<CompanyEntity>) {
        stocksFlexbox.removeAllViews()
        companies.forEach { company ->
            activity?.let { activity ->
                var marginStart = 40
                var marginEnd = 40
                val marginTop = 40
                val marginBottom = 40
                if (stocksFlexbox.childCount % 2 == 0) { // To keep the margin the same around the object.
                    marginEnd = 20
                } else {
                    marginStart = 20
                }
                val stockCardView = StockCardView(
                    activity,
                    company,
                    company.stockEntity,
                    company.checklistEntity,
                    stocksFlexbox,
                    mainFragmentLayout.height,
                    mainFragmentLayout.width,
                    marginStart, marginEnd, marginTop, marginBottom
                )
                stockCardView.view.setOnLongClickListener {
                    editStock(company, company.stockEntity, company.checklistEntity)
                    return@setOnLongClickListener true
                }
                stockCardView.view.setOnClickListener {
                    editStock(company, company.stockEntity, company.checklistEntity)
                }
                stocksFlexbox.addView(stockCardView.view)
            }
        }
    }


}