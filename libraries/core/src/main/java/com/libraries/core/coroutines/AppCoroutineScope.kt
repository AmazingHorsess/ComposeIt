package com.libraries.core.coroutines

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

class AppCoroutineScope(
    val context: CoroutineContext = SupervisorJob() + Dispatchers.Main
) {
    private val coroutineScope = object : CoroutineScope{
        override val coroutineContext: CoroutineContext
            get() = context
    }

    fun launch(
        context: CoroutineContext = EmptyCoroutineContext,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend CoroutineScope.() -> Unit,
    ): Job = coroutineScope.launch(context = context, start = start, block = block)
}