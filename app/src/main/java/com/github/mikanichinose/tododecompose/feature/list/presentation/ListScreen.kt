package com.github.mikanichinose.tododecompose.feature.list.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState

@Composable
fun ListScreen(
    component: ListComponent,
) {
    val model by component.models.subscribeAsState()
    ListContent(
        models = model,
        onItemClicked = { component.onItemClicked(it) },
    )
}

@Composable
fun ListContent(
    models: List<TodoUiModel>,
    onItemClicked: (TodoUiModel) -> Unit
) {
    LazyColumn {
        items(models) {
            Box(
                modifier = Modifier
                    .padding(10.dp)
                    .clickable { onItemClicked(it) }
            ) {
                Text(text = it.title)
            }
        }
    }
}