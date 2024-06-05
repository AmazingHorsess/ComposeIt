package com.composeit.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.composeit.local.model.Category
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {

    @Query("SELECT * FROM category")
    fun findAllCategories(): Flow<List<Category>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(category: Category)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(category: List<Category>)

    @Update
    suspend fun updateCategory(category: Category)

    @Delete
    suspend fun deleteCategory(category: Category)

    @Query("DELETE FROM category")
    suspend fun cleanTable()

    @Query("SELECT * FROM category WHERE category_name = :name")
    suspend fun findCategoryByName(name: String): Category

    @Query("SELECT * FROM category WHERE category_id = :categoryId")
    suspend fun findCategoryById(categoryId: Long): Category

}