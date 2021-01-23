package com.example.stockspokedex.models

import androidx.lifecycle.LiveData
import com.example.stockspokedex.data.entities.db.CompanyEntity

interface CompanyInteractor {
    fun insertCompany(company: CompanyEntity)
    fun deleteCompany(company: CompanyEntity)
    fun deleteCompanies(companyIDs: List<String>)
    fun updateCompany(company: CompanyEntity)
    fun getCompany(companyID: String): CompanyEntity
    fun getCompanyByTicker(ticker: String): CompanyEntity?
    fun getAllCompaniesAsync(): LiveData<List<CompanyEntity>>
    fun getAllCompaniesSync(): List<CompanyEntity>
}