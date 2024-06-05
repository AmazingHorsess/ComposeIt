package com.composeit.local.provider

import com.composeit.local.dao.CategoryDao
import com.composeit.local.dao.TaskDao
import com.composeit.local.dao.TaskWithCategoryDao

class DaoProvider(private val database: DatabaseProvider) {

    /**
     * Gets the [TaskDao].
     *
     * @return the [TaskDao]
     */
    fun getTaskDao(): TaskDao =
        database.getInstance().taskDao()

    /**
     * Gets the [TaskWithCategoryDao].
     *
     * @return the [TaskWithCategoryDao]
     */
    fun getTaskWithCategoryDao(): TaskWithCategoryDao =
        database.getInstance().taskWithCategoryDao()

    /**
     * Gets the [CategoryDao].
     *
     * @return the [CategoryDao]
     */
    fun getCategoryDao(): CategoryDao =
        database.getInstance().categoryDao()
}