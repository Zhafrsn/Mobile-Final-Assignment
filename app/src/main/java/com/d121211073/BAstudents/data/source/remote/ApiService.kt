package com.d121211073.BAstudents.data.source.remote

import com.d121211073.BAstudents.data.response.GetStudentResponse
import retrofit2.http.GET

interface ApiService {
    @GET("api/characters")
    suspend fun getStudent(): GetStudentResponse
}