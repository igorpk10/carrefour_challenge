package com.igor.carrefourchallenge.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class LicensesResponse(
    @SerializedName("key") var key: String,
    @SerializedName("name") var name: String,
    @SerializedName("spdx_id") var spdxId: String,
    @SerializedName("url") var url: String,
    @SerializedName("node_id") var nodeId: String
) : Parcelable
