package com.d121211073.BAstudents.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class Student(
    val _id: String?,
    val birthday: String?,
    val damageType: String?,
    val image: String?,
    val imageSchool: String?,
    val name: String?,
    val photoUrl: String?,
    val school: String?
): Parcelable