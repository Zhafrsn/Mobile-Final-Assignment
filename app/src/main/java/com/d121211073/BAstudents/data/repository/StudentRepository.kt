package com.d121211073.BAstudents.data.repository

import com.d121211073.BAstudents.data.response.GetStudentResponse
import com.d121211073.BAstudents.data.source.remote.ApiService

class StudentRepository(private val apiService: ApiService) {

    suspend fun getStudent(): GetStudentResponse {
        return apiService.getStudent()
    }
}