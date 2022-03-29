package com.elirex.currencydemo.data.source.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.elirex.currencydemo.model.CurrencyInfo

@Dao
interface CurrencyDao {

    @Query("SELECT currency_id, name FROM currencies")
    fun queryAllCurrencies(): List<CurrencyEntity>

    @Query("SELECT currency_id, name FROM currencies ORDER BY currency_id ASC")
    fun queryAllCurrenciesByAscending(): List<CurrencyEntity>

}