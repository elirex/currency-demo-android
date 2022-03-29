package com.elirex.currencydemo.data.source.local.db

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currencies")
data class CurrencyEntity(
    @PrimaryKey
    @ColumnInfo(name = "currency_id")
    @NonNull
    val currencyId: String,

    @ColumnInfo(name = "name")
    @NonNull
    val name: String
)