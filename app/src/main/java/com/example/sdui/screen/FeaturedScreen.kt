package com.example.sdui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sdui.data.FeaturedContent
import com.example.sdui.data.UiComponent


@Composable
fun FeaturedScreen(
    modifier: Modifier = Modifier,
    viewModel: FeaturedViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        when (val currentState = uiState) {
            is FeaturedViewModel.UiState.Loading -> LoadingState()
            is FeaturedViewModel.UiState.Success -> SuccessState(currentState.content)
            is FeaturedViewModel.UiState.Error -> ErrorState(
                message = currentState.message,
                onRetry = viewModel::retryLoading
            )
        }
    }
}

// Loading State
@Composable
private fun LoadingState() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(16.dp)
        ) {
            CircularProgressIndicator(
                modifier = Modifier.size(56.dp),
                color = MaterialTheme.colorScheme.primary,
                strokeWidth = 6.dp
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Loading content...",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

// Success State
@Composable
private fun SuccessState(content: FeaturedContent) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(
            count = content.layout.size,
            key = { index -> content.layout[index].type }
        ) { index ->
            val component = content.layout[index]
            ComponentWrapper(component)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

// Error State
@Composable
private fun ErrorState(
    message: String,
    onRetry: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Oops! Something went wrong",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.error
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = message,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = onRetry,
                modifier = Modifier.width(200.dp)
            ) {
                Text("Retry")
            }
        }
    }
}

// Component Wrapper
@Composable
private fun ComponentWrapper(component: UiComponent) {
    when (component.type) {
        "header" -> HeaderComponent(component)
        "card" -> CardComponent(component)
        "banner" -> BannerComponent(component)
    }
}

// Individual Components
@Composable
private fun HeaderComponent(component: UiComponent) {

    val textColor = when (component.properties["textColor"] as? String) {
        "RED" -> Color.Red
        "BLUE" -> Color.Blue
        // Add more color cases as needed
        else -> MaterialTheme.colorScheme.onBackground
    }
    Text(
        text = component.properties["title"] as? String ?: "",
        style = MaterialTheme.typography.bodyMedium,
        color = textColor,
        modifier = Modifier.padding(vertical = 8.dp)
    )
}

@Composable
private fun CardComponent(component: UiComponent) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.elevatedCardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = component.properties["title"] as? String ?: "",
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = component.properties["description"] as? String ?: "",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
private fun BannerComponent(component: UiComponent) {
    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = component.properties["message"] as? String ?: "",
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.padding(16.dp)
        )
    }
}

