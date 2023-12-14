package com.d121211073.BAstudents.ui.theme.activities.detail

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.d121211073.BAstudents.data.models.Student
import com.d121211073.BAstudents.ui.theme.theme.FinalMobile

class DetailActivity : ComponentActivity() {

    private var selectedStudent: Student? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        selectedStudent = intent.getParcelableExtra("STUDENT")
        setContent {
            FinalMobile {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DetailScreen()
                }
            }
        }
    }
    @Composable
    fun DetailScreen() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(selectedStudent?.photoUrl)
                    .crossfade(true)
                    .build(), contentDescription = selectedStudent?.name,
                modifier = Modifier
                    .width(400.dp)
                    .height(530.dp)
                    .clip(MaterialTheme.shapes.medium),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Name : ${selectedStudent?.name.orEmpty()}", style = MaterialTheme.typography.displayMedium, fontWeight = FontWeight.ExtraBold)
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Birth Date       : ${selectedStudent?.birthday.orEmpty()}", style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Medium)
            Spacer(modifier = Modifier.height(16.dp))
            Row {
                Text(text = "School        :")
                AsyncImage(
                    model = ImageRequest.Builder(context = LocalContext.current)
                        .data(selectedStudent?.imageSchool)
                        .crossfade(true)
                        .build(), contentDescription = selectedStudent?.name,
                    modifier = Modifier
                        .width(23.dp)
                        .height(23.dp)
                        .clip(MaterialTheme.shapes.medium),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(3.dp))
                Text(text = selectedStudent?.school.orEmpty(), style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Medium)
                Spacer(modifier = Modifier.width(8.dp)) // Adjust the width based on your spacing preference
                // Add your image here

            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Damage Type : ${selectedStudent?.damageType.orEmpty()}", style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Medium)
        }
        }
    }



