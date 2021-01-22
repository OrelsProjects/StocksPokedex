package com.example.stockspokedex.data.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.stockspokedex.data.entities.CompanyEntity

@Dao
interface CompanyDao {
    /**
     * Inserts a company to the cache.
     */
    @Insert
    fun insertCompany(company: CompanyEntity)

    /**
     * Deletes a company from the cache.
     */
    @Delete
    fun deleteCompany(company: CompanyEntity)

    /**
     * Deletes a list of companies from the cache.
     */
    @Query("delete from CompanyEntity where companyID in (:companiesIDs)")
    fun deleteCompanies(companiesIDs: List<String>)

    /**
     * Updates a company in the cache.
     */
    @Update
    fun updateCompany(company: CompanyEntity)

    /**
     * Fetches a livedata list of all the companies.
     * @return a livedata object of the companies.
     */
    @Query("select * from CompanyEntity where isActive = 1")
    fun getAllCompaniesAsync(): LiveData<List<CompanyEntity>>

    /**
     * Fetches a list of all the companies.
     * @return a list of all the companies.
     */
    @Query("select * from CompanyEntity where isActive = 1")
    fun getAllCompaniesSync(): List<CompanyEntity>

    /**
     * Fetches a company with companyID = [id].
     * @param id is the id of the company requested.
     */
    @Query("select * from CompanyEntity where companyID = :id")
    fun getCompany(id: String): CompanyEntity
}