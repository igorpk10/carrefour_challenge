package com.igor.carrefourchallenge.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val id: Int,
    val login: String,
    val avatarUrl: String,
    val url: String
) : Parcelable