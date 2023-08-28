package com.github.mikanichinose.tododecompose

import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.router.stack.active
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.arkivanov.essenty.lifecycle.resume
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class RootComponentTest {

    private lateinit var component: RootComponent
    private val activeChild
        get() = component.childStack.active.instance

    @BeforeEach
    fun setUp() {
    }

    @Test
    fun when_create_then_ListChild_active() {
    }

    private fun createComponent() {
        val lifecycle = LifecycleRegistry()
        component = DefaultRootComponent(
            componentContext = DefaultComponentContext(lifecycle = lifecycle)
        )
        lifecycle.resume()
    }
}