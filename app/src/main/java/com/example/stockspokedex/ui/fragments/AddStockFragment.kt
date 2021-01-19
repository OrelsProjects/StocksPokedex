package com.example.stockspokedex.ui.fragments

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.stockspokedex.R
import com.example.stockspokedex.ui.base.BaseFragment
import com.example.stockspokedex.ui.viewmodels.AddStockViewModel
import com.example.stockspokedex.ui.viewstates.AddStockViewState
import com.example.stockspokedex.utils.AppUtils
import com.example.stockspokedex.utils.General
import kotlinx.android.synthetic.main.fragment_add_stock.*

class AddStockFragment : BaseFragment<AddStockViewModel, AddStockViewState>(),
    View.OnClickListener {

    private lateinit var viewModel: AddStockViewModel

    override fun initViewModel() {
        viewModel = ViewModelProvider(requireActivity()).get(AddStockViewModel::class.java)
    }

    override fun getViewModel(): AddStockViewModel = viewModel

    override fun updateUI(state: AddStockViewState) {

    }

    override fun attachClickListeners() {
        addStockBackground.setOnClickListener(this)
        checklistLayout.setOnClickListener(this)
    }

    override fun getLayoutResourceFile(): Int = R.layout.fragment_add_stock

    @SuppressLint("RestrictedApi")
    private fun onBackgroundClick() {
        clearFocus()
        tickerEdit.supportBackgroundTintList = ColorStateList.valueOf(Color.parseColor("#FF00FF"));
        AppUtils.hideKeyboard(activity)
    }


    private fun clearFocus() {
        tickerInput.clearFocus()
    }

    private fun showChecklist(){
        if(checklistFlexbox.visibility == View.VISIBLE) {
            checklistFlexbox.visibility = View.GONE
        } else {
            checklistFlexbox.visibility = View.VISIBLE
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.addStockBackground -> onBackgroundClick()
            R.id.checklistLayout -> showChecklist()
        }
    }

    override fun setCurrentFragment() {
        General.presentedFragment = General.Fragments.AddStock
    }
}