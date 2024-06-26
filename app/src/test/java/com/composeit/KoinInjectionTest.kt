package com.composeit

import android.content.Context
import io.mockk.MockKSettings.relaxed
import io.mockk.mockk
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.koinApplication
import org.koin.test.KoinTest
import org.koin.test.check.checkModules

internal class KoinInjectionTest: KoinTest {

    private val context = mockk<Context>(relaxed=true)

    @Test
    fun `test all the injections are declared`(){
        val modules = ComposeItApp().getAllModules()

        koinApplication {
            androidContext(context)
            modules(modules)
        }.checkModules()
    }
}