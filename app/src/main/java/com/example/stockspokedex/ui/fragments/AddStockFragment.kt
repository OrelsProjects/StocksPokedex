package com.example.stockspokedex.ui.fragments

import android.animation.LayoutTransition
import android.content.Intent
import android.view.View
import androidx.core.view.children
import androidx.lifecycle.ViewModelProvider
import com.example.stockspokedex.R
import com.example.stockspokedex.activities.MainActivity
import com.example.stockspokedex.data.entities.ChecklistEntity
import com.example.stockspokedex.data.entities.CompanyEntity
import com.example.stockspokedex.ui.base.BaseFragment
import com.example.stockspokedex.ui.viewmodels.AddStockViewModel
import com.example.stockspokedex.ui.viewstates.AddStockViewState
import com.example.stockspokedex.utils.AppUtils.hideKeyboard
import com.example.stockspokedex.utils.General
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.add_file_bullish_thesis.*
import kotlinx.android.synthetic.main.add_file_dcf.*
import kotlinx.android.synthetic.main.add_file_price_target.*
import kotlinx.android.synthetic.main.check_box_cc.*
import kotlinx.android.synthetic.main.check_box_ceo.*
import kotlinx.android.synthetic.main.check_box_investors_presentation.*
import kotlinx.android.synthetic.main.check_box_news.*
import kotlinx.android.synthetic.main.check_box_ten_k.*
import kotlinx.android.synthetic.main.check_box_ten_q.*
import kotlinx.android.synthetic.main.fragment_add_stock.*
import java.util.*
import javax.inject.Inject

class AddStockFragment @Inject constructor(
) : BaseFragment<AddStockViewModel, AddStockViewState>(),
    View.OnClickListener {

    private lateinit var viewModel: AddStockViewModel

    override fun initViewModel() {
        viewModel = ViewModelProvider(requireActivity()).get(AddStockViewModel::class.java)
    }

    override fun getViewModel(): AddStockViewModel = viewModel

    override fun updateUI(state: AddStockViewState) {
        if (state.isStockSaveDone) {
            navigateMainActivity()
        }
        state.reset()
    }

    override fun attachClickListeners() {
        addStockBackground.setOnClickListener(this)
        checklistLayout.setOnClickListener(this)
        addBullishThesisFileImage.setOnClickListener(this)
        addPriceTargetFileImage.setOnClickListener(this)
        addDCFFileImage.setOnClickListener(this)
        saveStockButton.setOnClickListener(this)
        tenKRadio.setOnClickListener(this)
        ceoRadio.setOnClickListener(this)
        ccRadio.setOnClickListener(this)
        tenQRadio.setOnClickListener(this)
        investorsPresentationRadio.setOnClickListener(this)
        newsRadio.setOnClickListener(this)
    }

    override fun getLayoutResourceFile(): Int = R.layout.fragment_add_stock

    override fun onViewCreated() =
        setAnimationsToFileLayouts()


    private fun onBackgroundClick() {
        clearFocus()
        hideKeyboard()
    }


    private fun clearFocus() {
        tickerInput.clearFocus()
        companyNameInput.clearFocus()
    }

    private fun saveCompany() {
        val companyName = companyNameEdit.text.toString()
        val companyTicker = tickerEdit.text.toString()
        val company = CompanyEntity(
            companyName = companyName,
            companyTicker = companyTicker,
            companyID = Date().time.toString()
        )
        val checklist = ChecklistEntity(
            checklistID = Date().time.toString(),
            tenK = tenKRadio.isSelected,
            ceo = ceoRadio.isSelected,
            conferenceCall = ccRadio.isSelected,
            tenQ = tenQRadio.isSelected,
            investorsPresentation = investorsPresentationRadio.isSelected,
            news = newsRadio.isSelected
        )
        viewModel.handleSaveStock(company, checklist)
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

    private fun rotateChecklistOpen() {
        checklistToggleImage.animate().rotation(270f).setDuration(200).start()
    }

    private fun removeItemsFromChecklist() {
        checklistFlexbox.children.forEach {
            it.visibility = View.GONE
        }
    }

    private fun showItemsInChecklist() {
        checklistFlexbox.children.forEach {
            it.visibility = View.VISIBLE
        }
    }

    private fun setAnimationsToFileLayouts() {
        bullishThesisLayout.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)
        dcfLayout.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)
        priceTargetLayout.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)
    }

    private fun navigateMainActivity() {
        val mainActivityIntent = Intent(context, MainActivity::class.java)
        mainActivityIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context?.startActivity(mainActivityIntent)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.addStockBackground -> onBackgroundClick()
            R.id.checklistLayout -> showChecklist()
            R.id.saveStockButton -> saveCompany()
            R.id.tenKRadio
                    or R.id.ceoRadio
                    or R.id.ccRadio
                    or R.id.tenQRadio
                    or R.id.investorsPresentationRadio
                    or R.id.ceoRadio -> {
                onBackgroundClick()
            }
        }
    }

    override fun setCurrentFragment() {
        General.presentedFragment = General.Fragments.AddStock
    }
}