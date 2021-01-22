package com.example.stockspokedex.ui.fragments

import androidx.lifecycle.ViewModelProvider
import com.example.stockspokedex.R
import com.example.stockspokedex.data.entities.CompanyEntity
import com.example.stockspokedex.ui.base.BaseFragment
import com.example.stockspokedex.ui.viewmodels.MainViewModel
import com.example.stockspokedex.ui.views.StockCardView
import com.example.stockspokedex.ui.viewstates.MainViewState
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
            setCompaniesCards(it)
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

    private fun setCompaniesCards(companies: List<CompanyEntity>) {
        stocksFlexbox.removeAllViews()
        companies.forEach { company ->
            activity?.let { activity ->
                company.checklistEntity?.let { checklistEntity ->
                    var marginStart = 40
                    var marginEnd = 40
                    val marginTop = 40
                    val marginBottom = 40
                    if (stocksFlexbox.childCount % 2 == 0) { // To keep the margin the same around the object.
                        marginEnd = 20
                    } else {
                        marginStart = 20
                    }
                    stocksFlexbox.addView(
                        StockCardView(
                            activity,
                            company,
                            checklistEntity,
                            stocksFlexbox,
                            mainFragmentLayout.height,
                            mainFragmentLayout.width,
                            marginStart, marginEnd, marginTop, marginBottom
                        ).view
                    )
                }
            }
        }
    }


}