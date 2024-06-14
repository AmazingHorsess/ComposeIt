package com.composeit.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.composeit.local.converter.AlarmIntervalConverter
import com.composeit.local.converter.DateConverter
import com.composeit.local.dao.CategoryDao
import com.composeit.local.dao.TaskDao
import com.composeit.local.dao.TaskWithCategoryDao
import com.composeit.local.model.AlarmInterval
import com.composeit.local.model.Category
import com.composeit.local.model.Task

@Database(entities = [Task::class,Category::class], version = 1)
@TypeConverters(DateConverter::class,AlarmIntervalConverter::class)
abstract class TaskRoomDatabase : RoomDatabase(){

    abstract fun taskDao(): TaskDao

    abstract fun taskWithCategoryDao(): TaskWithCategoryDao

    abstract fun categoryDao(): CategoryDao


}