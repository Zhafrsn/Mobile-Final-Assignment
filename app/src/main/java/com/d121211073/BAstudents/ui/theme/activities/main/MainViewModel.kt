package com.d121211073.BAstudents.ui.theme.activities.main

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.d121211073.BAstudents.MyApplication
import com.d121211073.BAstudents.data.models.Student
import com.d121211073.BAstudents.data.repository.StudentRepository
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface MainUiState {
    data class Success(val  data: List<Student>) : MainUiState
    object Error : MainUiState
    object Loading : MainUiState
}

class MainViewModel(private val studentRepository: StudentRepository): ViewModel() {
    var mainUiState: MainUiState by mutableStateOf(MainUiState.Loading)
        private set

    fun getStudent() = viewModelScope.launch {
        mainUiState = MainUiState.Loading
        try {
            val result = studentRepository.getStudent()
            Log.d("MainViewModel", "getStudent: ${result.data?.size}")
            mainUiState = MainUiState.Success(result.data.orEmpty())
        } catch (e: IOException) {
            Log.d("MainViewMode", "getStudent error: ${e.message}")
            mainUiState = MainUiState.Error
        }
    }

    init {
        getStudent()
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as MyApplication)
                val studentRepository = application.container.studentRepository
                MainViewModel(studentRepository)
            }
        }
    }

}