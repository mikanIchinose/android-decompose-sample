package com.github.mikanichinose.tododecompose

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.github.mikanichinose.tododecompose.feature.detail.presentation.DetailComponent
import com.github.mikanichinose.tododecompose.feature.list.presentation.ListComponent

interface RootComponent {
    val childStack: Value<ChildStack<*, Child>>

    sealed interface Child {
        class ListChild(val component: ListComponent) : Child
        class DetailChild(val component: DetailComponent) : Child
    }
}

