package com.example.stockspokedex.models

import androidx.lifecycle.LiveData
import com.example.stockspokedex.data.entities.CompanyEntity

interface CompanyInteractor {
    fun insertCompany(company: CompanyEntity)
    fun deleteCompany(company: CompanyEntity)
    fun deleteCompanies(companyIDs: List<String>)
    fun updateCompany(company: CompanyEntity)
    fun getCompany(companyID: String): CompanyEntity
    fun getAllCompanies(): LiveData<List<CompanyEntity>>
}