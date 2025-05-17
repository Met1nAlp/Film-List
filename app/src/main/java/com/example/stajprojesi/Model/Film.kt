package com.example.stajprojesi.Model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Film(
    @SerializedName("title") val adi: String,
    @SerializedName("overview") val ozet: String,
    @SerializedName("genre_ids") val tur: List<Int>,
    @SerializedName("release_date") val cikisTarihi: String,
    @SerializedName("runtime") val sure: Int?,
    @SerializedName("poster_path") val gorsel: String?
) : Parcelable