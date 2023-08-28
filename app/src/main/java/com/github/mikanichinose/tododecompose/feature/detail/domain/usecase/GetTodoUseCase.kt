package com.github.mikanichinose.tododecompose.feature.detail.domain.usecase

import com.github.mikanichinose.tododecompose.domain.model.TodoDomainModel
import com.github.mikanichinose.tododecompose.domain.model.TodoId
import com.github.mikanichinose.tododecompose.domain.repository.TodoRepository

class GetTodoUseCase(private val repository: TodoRepository) {
    suspend operator fun invoke(id: TodoId): TodoDomainModel? {
        return repository.getById(id)
    }
}