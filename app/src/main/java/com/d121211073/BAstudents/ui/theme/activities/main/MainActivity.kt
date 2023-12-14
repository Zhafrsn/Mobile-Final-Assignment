package com.d121211073.BAstudents.ui.theme.activities.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.d121211073.BAstudents.data.models.Student
import com.d121211073.BAstudents.ui.theme.activities.detail.DetailActivity
import com.d121211073.BAstudents.ui.theme.theme.FinalMobile

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FinalMobile {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        CenterAlignedTopAppBar(
                            title = {
                                Text(
                                    text = "All Blue Archive Character",
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        )
                        val mainViewModel: MainViewModel =
                            viewModel(factory = MainViewModel.Factory)
                        ListStudentScreen(mainViewModel.mainUiState)
                    }

                }
            }
        }
    }

    @Composable
    private fun ListStudentScreen(mainUiState: MainUiState, modifier: Modifier = Modifier) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .then(modifier)
        ) {
            when (mainUiState) {
                is MainUiState.Loading -> {
                    Text(
                        text = "Loading, Please Wait.....",
                        fontSize = 16.sp,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                is MainUiState.Error -> {
                    Text(
                        text = "An Error Occurred.",
                        fontSize = 16.sp,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                is MainUiState.Success -> {
                    StudentList(mainUiState.data)
                }
            }
        }
    }

    @Composable
    fun StudentList(data: List<Student>, modifier: Modifier = Modifier) {
        LazyColumn(modifier = modifier) {
            items(data) { student ->
                StudentItem(student = student)
            }
        }
    }

    @Composable
    fun StudentItem(student: Student) {
        Card(
            modifier = Modifier
                .padding(16.dp)
                .clickable {
                    val intent = Intent(this, DetailActivity::class.java)
                    intent.putExtra("STUDENT", student)
                    startActivity(intent)
                }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                // Student Photo
                AsyncImage(
                    model = ImageRequest.Builder(context = LocalContext.current)
                        .data(student.photoUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = student.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .clip(MaterialTheme.shapes.medium),
                    contentScale = ContentScale.Crop
                )

                // Student Details
                Spacer(modifier = Modifier.height(8.dp))
                Row {
                    AsyncImage(
                        model = ImageRequest.Builder(context = LocalContext.current)
                            .data(student?.imageSchool)
                            .crossfade(true)
                            .build(), contentDescription = student?.name,
                        modifier = Modifier
                            .width(40.dp)
                            .height(40.dp)
                            .clip(MaterialTheme.shapes.medium),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = student.name.orEmpty(), style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
                }
            }
        }

    }
}