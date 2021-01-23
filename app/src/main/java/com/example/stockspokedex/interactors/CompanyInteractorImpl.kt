package com.example.stockspokedex.interactors

import androidx.lifecycle.LiveData
import com.example.stockspokedex.data.daos.CompanyDao
import com.example.stockspokedex.data.entities.db.CompanyEntity
import com.example.stockspokedex.models.CompanyInteractor
import javax.inject.Inject

class CompanyInteractorImpl @Inject constructor(private val db: CompanyDao) : CompanyInteractor {


    override fun insertCompany(company: CompanyEntity) {
        db.insertCompany(company)
    }

    override fun deleteCompany(company: CompanyEntity) {
        db.deleteCompany(company)
    }

    override fun deleteCompanies(companyIDs: List<String>) {
        db.deleteCompanies(companyIDs)
    }

    override fun updateCompany(company: CompanyEntity) {
        db.updateCompany(company)
    }

    override fun getCompany(companyID: String): CompanyEntity = db.getCompany(companyID)

    override fun getCompanyByTicker(ticker: String): CompanyEntity? = db.getCompanyByTicker(ticker)

    override fun getAllCompaniesAsync(): LiveData<List<CompanyEntity>> = db.getAllCompaniesAsync()

    override fun getAllCompaniesSync(): List<CompanyEntity> = db.getAllCompaniesSync()
}