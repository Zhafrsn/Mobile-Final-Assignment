package com.d121211073.BAstudents.data

import com.d121211073.BAstudents.data.repository.StudentRepository
import com.d121211073.BAstudents.data.source.remote.ApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val studentRepository: StudentRepository
}

class DefaultAppContainer: AppContainer {

    private val BASE_URL = "https://api-blue-archive.vercel.app/"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()

    private val retrofitService : ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

    override val studentRepository: StudentRepository
        get() = StudentRepository(retrofitService)

}
