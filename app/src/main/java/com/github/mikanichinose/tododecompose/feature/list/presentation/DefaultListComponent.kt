package com.github.mikanichinose.tododecompose.feature.list.presentation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.github.mikanichinose.tododecompose.domain.model.TodoDomainModel
import com.github.mikanichinose.tododecompose.feature.list.domain.usecase.GetTodosUseCase
import com.github.mikanichinose.tododecompose.util.coroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class DefaultListComponent(
    componentContext: ComponentContext,
    private val getTodos: GetTodosUseCase,
    private val onClicked: (TodoUiModel) -> Unit,
) : ListComponent, ComponentContext by componentContext {
    private val scope = coroutineScope(Dispatchers.Main + SupervisorJob())
    private val _models: MutableValue<List<TodoUiModel>> = MutableValue(listOf())
    override val models: Value<List<TodoUiModel>> = _models

    init {
        scope.launch {
            getTodos()
                .map { it.map(TodoDomainModel::toUiModel) }
                .collect { _models.value = it }
        }

    }

    override fun onItemClicked(model: TodoUiModel) {
        onClicked(model)
    }
}
