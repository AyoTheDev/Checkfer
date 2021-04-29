package com.ayo.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DogDomain(
    val bred_for: String?,
    val breed_group: String?,
    val country_code: String?,
    val description: String?,

    val imperialHeight: String?,
    val metricHeight: String?,

    val history: String?,
    val id: Int?,

    val imageHeight: Int?,
    val imageWidth: Int?,
    val imageId: String?,
    val imageUrl: String?,

    val imperialWeight: String?,
    val metricWeight: String?,

    val life_span: String?,
    val name: String?,
    val origin: String?,
    val reference_image_id: String?,
    val temperament: String?,
) : Parcelable
