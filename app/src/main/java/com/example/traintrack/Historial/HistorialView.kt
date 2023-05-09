package com.example.traintrack.Historial

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.traintrack.R
import java.time.LocalDateTime

data class Activity(val id: Int, val title: String, val date: LocalDateTime)

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryView(activities: List<Activity>) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Historial de Actividades") }
            )
        }
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            LazyColumn() {
                items(activities.take(3)) { activity ->
                    ActivityItem(activity = activity)
                }
            }
        }
    }
}

@Composable
fun ActivityItem(activity: Activity) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = { /*TODO*/ })
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(text = activity.title, style = MaterialTheme.typography.bodyMedium)
            Text(text = activity.date.toString(), style = MaterialTheme.typography.bodyMedium)
        }
        Icon(
            painter = painterResource(id = R.drawable.baseline_arrow_forward_24),
            contentDescription = null,
            modifier = Modifier.size(32.dp)
        )
    }
}

@Composable
fun PlaceholderImage() {
    Image(
        painter = painterResource(id = R.drawable.baseline_image_24),
        contentDescription = null,
        modifier = Modifier.size(64.dp)
    )
}