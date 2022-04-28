package com.samsdk.planetaapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Planet(
    val planetId: String = "",
    val title: String = "",
    val desc: String = "",
    val imgUrl: String = ""
): Parcelable