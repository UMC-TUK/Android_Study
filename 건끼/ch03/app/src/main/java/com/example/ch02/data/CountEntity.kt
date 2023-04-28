package com.example.ch02.data

import android.os.Parcelable
import androidx.versionedparcelable.ParcelField
import kotlinx.parcelize.Parcelize

@Parcelize
data class CountEntity(
    val Name : String,
    val money : String
): Parcelable
