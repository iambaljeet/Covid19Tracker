package com.app.covid19tracker.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CasesValueDataModel(
    val detail: String?,
    val value: Int?
) : Parcelable