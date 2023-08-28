package com.github.mikanichinose.tododecompose.feature.list.domain.usecase

import com.github.mikanichinose.tododecompose.domain.model.TodoDomainModel
import com.github.mikanichinose.tododecompose.domain.repository.TodoRepository
import kotlinx.coroutines.flow.Flow

class GetTodosUseCase(
    private val repository: TodoRepository
) {
    suspend operator fun invoke(): Flow<List<TodoDomainModel>> {
        return repository.getAll()
    }
}