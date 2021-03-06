package com.example.stockspokedex.ui.views

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.example.stockspokedex.R
import com.example.stockspokedex.data.entities.db.ChecklistEntity
import com.example.stockspokedex.data.entities.db.CompanyEntity
import com.example.stockspokedex.data.entities.db.StockEntity
import com.example.stockspokedex.utils.AppUtils
import com.example.stockspokedex.utils.Constants
import kotlinx.android.synthetic.main.layout_stock_card.view.*
import java.math.BigDecimal

class StockCardView(
    activity: Activity,
    company: CompanyEntity,
    stock: StockEntity?,
    checklistEntity: ChecklistEntity?,
    parent: ViewGroup,
    containerHeight: Int,
    containerWidth: Int,
    marginStart: Int = 40,
    marginEnd: Int = 40,
    marginTop: Int = 40,
    marginBottom: Int = 40
) {

    private val margin = 40

    val view: View =
        LayoutInflater.from(activity).inflate(R.layout.layout_stock_card, parent, false)

    init {
        view.companyNameText.text = company.companyName
        view.companyTickerText.text = company.companyTicker
        val width = (containerWidth - margin * 4) / CARDS_IN_ROW
        val height = (containerHeight - margin * 4) / CARDS_IN_COLUMN
        val layoutParams = FrameLayout.LayoutParams(
            width, height
        )
        layoutParams.marginStart = marginStart
        layoutParams.marginEnd = marginEnd
        layoutParams.topMargin = marginTop
        layoutParams.bottomMargin = marginBottom
        view.layoutParams = layoutParams
        if (stock?.isABuy() == true) {
            view.isBuyTextView.setTextColor(
                AppUtils.getColorFromAttributes(
                    activity,
                    R.attr.colorGreenVariant
                )
            )
            view.isBuyTextView.text = Constants.STRING_BUY
        } else {
            view.isBuyTextView.setTextColor(
                AppUtils.getColorFromAttributes(
                    activity,
                    R.attr.colorRedVariant
                )
            )
            view.isBuyTextView.text = Constants.STRING_SELL
        }
        if (stock?.currentPrice?.toDoubleOrNull() == null) {
            stock?.currentPrice = "1"
        }
        view.priceTextView.text = AppUtils.getPriceTextDollars(
            stock?.currentPrice?.toFloat()?.toBigDecimal()?.setScale(2, BigDecimal.ROUND_HALF_DOWN)
                .toString()
        )
    }

    companion object {
        const val CARDS_IN_ROW = 2
        const val CARDS_IN_COLUMN = 4
    }
}