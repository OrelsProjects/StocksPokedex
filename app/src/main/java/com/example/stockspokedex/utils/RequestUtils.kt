package com.example.stockspokedex.utils

import android.content.Context
import com.example.stockspokedex.R

object RequestUtils {

    fun getIEXStockPricePath(stockTicker: String): String = "stock/$stockTicker/financials"
    fun getIEXSandboxTokenPath(context: Context): String =
        "?token=${context.getString(R.string.iex_api_token_sandbox)}"

    fun getIEXTokenPath(context: Context): String =
        "?token=${context.getString(R.string.iex_api_token)}"
}