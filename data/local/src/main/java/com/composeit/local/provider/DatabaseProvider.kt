package com.composeit.local.provider

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.composeit.local.R
import com.composeit.local.TaskRoomDatabase
import com.composeit.local.model.Category
import com.libraries.core.coroutines.AppCoroutineScope
import com.libraries.core.extension.getStringColor

class DatabaseProvider(
    private val context:Context,
    private val appScope: AppCoroutineScope
) {
    private var database: TaskRoomDatabase? = null

    fun getInstance(): TaskRoomDatabase =
        database ?: synchronized(this) {
            database ?: buildDatabase().also { database = it }
        }

        private fun buildDatabase(): TaskRoomDatabase =
            Room.databaseBuilder(context, TaskRoomDatabase::class.java, "todo-db")
                .addCallback(onCreateDatabase())
                .build()

    private fun onCreateDatabase() =
        object : RoomDatabase.Callback(){
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                appScope.launch {
                    database?.categoryDao()?.insertCategory(getDefaultCategoryList())
                }
            }
        }
    private fun getDefaultCategoryList() =
        listOf(
            Category(
                name = "Shopping",
                categoryColor = context.getStringColor(R.color.blue),
            ),
            Category(
                name = "School",
                categoryColor = context.getStringColor(R.color.green),
            ),
            Category(
                name = "Work",
                categoryColor = context.getStringColor(R.color.orange),
            )
        )

}