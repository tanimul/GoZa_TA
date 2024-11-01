package com.tanimul.goza_ta.common.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Recommended(

    @SerializedName("property_name") var propertyName: String? = null,
    @SerializedName("location") var location: String? = null,
    @SerializedName("rating") var rating: Double? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("fare") var fare: Int? = null,
    @SerializedName("fare_unit") var fareUnit: String? = null,
    @SerializedName("is_available") var isAvailable: Boolean? = null,
    @SerializedName("hero_image") var heroImage: String? = null,
    @SerializedName("detail_image") var detailImage: String? = null,
    @SerializedName("currency") var currency: String? = null

) : Parcelable