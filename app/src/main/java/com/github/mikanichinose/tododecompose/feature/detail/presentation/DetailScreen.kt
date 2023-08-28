package com.github.mikanichinose.tododecompose.feature.detail.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState

@Composable
fun DetailScreen(component: DetailComponent) {
    val model by component.model.subscribeAsState()
    DetailContent(model = model)
}

@Composable
fun DetailContent(model: TodoUiModel) {
    when (model) {
        TodoUiModel.Error -> Text(text = "error")
        TodoUiModel.Loading -> Text(text = "loading")
        is TodoUiModel.Success -> {
            Column {
                Text(text = model.title)
                Text(text = model.detail)
            }
        }
    }
}