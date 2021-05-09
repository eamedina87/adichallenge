package tech.medina.adichallenge.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Review(
    val id: String,
    val locale: String,
    val rating: Int,
    val text: String
): Parcelable