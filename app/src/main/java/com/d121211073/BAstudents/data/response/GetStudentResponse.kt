package com.d121211073.BAstudents.data.response

import com.d121211073.BAstudents.data.models.Student
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetStudentResponse(
    @SerialName("data")
    val data: List<Student>,
    @SerialName("dataAllPage")
    val dataAllPage: Int?,
    @SerialName("message")
    val message: String?
)