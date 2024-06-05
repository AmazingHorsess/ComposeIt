package com.composeit.local

import com.composeit.local.datasource.SearchLocalDataSource
import com.composeit.local.mapper.TaskWithCategoryMapper
import com.composeit.local.provider.DaoProvider
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
internal class SearchLocalDataSourceTest {

    private val mockDaoProvider = mockk<DaoProvider>(relaxed = true)

    private val mockMapper = mockk<TaskWithCategoryMapper>(relaxed = true)

    private val dataSource = SearchLocalDataSource(mockDaoProvider,mockMapper)

    @Before
    fun setup(){
        coEvery {
            mockDaoProvider.getTaskWithCategoryDao().findTaskByName(any())
        } returns flow {  }
    }

    @Test
    fun `check if the query is enclosed with percent char`() = runTest {
        val query = "name"
        dataSource.findTaskByName(query)
        coVerify { mockDaoProvider.getTaskWithCategoryDao().findTaskByName("$query") }
    }

}