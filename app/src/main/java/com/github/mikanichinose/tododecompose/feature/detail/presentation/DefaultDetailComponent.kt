package com.github.mikanichinose.tododecompose.feature.detail.presentation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.github.mikanichinose.tododecompose.domain.model.TodoId
import com.github.mikanichinose.tododecompose.feature.detail.domain.usecase.GetTodoUseCase
import com.github.mikanichinose.tododecompose.util.coroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class DefaultDetailComponent(
    componentContext: ComponentContext,
    id: Int,
    getTodo: GetTodoUseCase,
) : DetailComponent, ComponentContext by componentContext {
    private val scope = coroutineScope(Dispatchers.Main + SupervisorJob())

    private val _model: MutableValue<TodoUiModel> = MutableValue(TodoUiModel.Loading)
    override val model: Value<TodoUiModel> = _model

    init {
        scope.launch {
            _model.value = getTodo(TodoId(id))?.toUiModel() ?: TodoUiModel.Error
        }
    }
}