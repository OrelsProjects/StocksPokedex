package com.example.stockspokedex.models

import androidx.lifecycle.LiveData
import com.example.stockspokedex.data.entities.db.CompanyEntity

interface CompanyInteractor {
    suspend fun insertCompany(uid: String, company: CompanyEntity): CompanyEntity?
    suspend fun deleteCompany(company: CompanyEntity):  CompanyEntity?
    suspend fun deleteCompanies(companyIDs: List<String>)
    suspend fun updateCompany(uid: String, company: CompanyEntity):  CompanyEntity?
    suspend fun getCompany(companyID: String): CompanyEntity
    suspend fun getAllCompaniesAsync(): LiveData<List<CompanyEntity>>
    suspend fun getCompaniesFromFirestore(uid: String): List<CompanyEntity>
    fun insertCompanies(list:List<CompanyEntity>)
    fun getAllCompaniesSync(): List<CompanyEntity>
    fun getCompanyByTickerFromSync(ticker: String): CompanyEntity?
    fun clear()
}