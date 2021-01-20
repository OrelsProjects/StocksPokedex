package com.example.stockspokedex.ui.fragments

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.View
import androidx.core.view.children
import androidx.lifecycle.ViewModelProvider
import com.example.stockspokedex.R
import com.example.stockspokedex.ui.base.BaseFragment
import com.example.stockspokedex.ui.viewmodels.AddStockViewModel
import com.example.stockspokedex.ui.viewstates.AddStockViewState
import com.example.stockspokedex.utils.AppUtils
import com.example.stockspokedex.utils.AppUtils.hideKeyboard
import com.example.stockspokedex.utils.General
import kotlinx.android.synthetic.main.add_file_bullish_thesis.*
import kotlinx.android.synthetic.main.add_file_dcf.*
import kotlinx.android.synthetic.main.add_file_price_target.*
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
        addBullishThesisFileImage.setOnClickListener(this)
        addPriceTargetFileImage.setOnClickListener(this)
        addDCFFileImage.setOnClickListener(this)
    }

    override fun getLayoutResourceFile(): Int = R.layout.fragment_add_stock

    private fun onBackgroundClick() {
        clearFocus()
        hideKeyboard()
    }


    private fun clearFocus() {
        tickerInput.clearFocus()
        companyNameInput.clearFocus()
    }

    private fun showChecklist() {
        if (checklistFlexbox.getChildAt(0).visibility == View.VISIBLE) {
            rotateChecklistToggleClose()
            removeItemsFromChecklist()
        } else {
            rotateChecklistOpen()
            showItemsInChecklist()
        }
    }

    private fun rotateChecklistToggleClose() {
        checklistToggleImage.animate().rotation(90f).setDuration(200).start()
    }

    private fun rotateChecklistOpen(){
        checklistToggleImage.animate().rotation(270f).setDuration(200).start()
    }

    private fun removeItemsFromChecklist(){
        checklistFlexbox.children.forEach {
            it.visibility = View.GONE
        }
    }

    private fun showItemsInChecklist(){
        checklistFlexbox.children.forEach {
            it.visibility = View.VISIBLE
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