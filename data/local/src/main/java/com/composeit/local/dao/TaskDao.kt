package com.composeit.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.composeit.local.model.Task

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: Task)

    @Update
    suspend fun updateTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

    @Query("DELETE FROM task")
    suspend fun cleanTable()

    @Query("SELECT * FROM task WHERE task_due_date IS NOT NULL")
    suspend fun findAllTasksWithDueDate(): List<Task>

    @Query("SELECT * FROM task WHERE task_id = :taskId")
    suspend fun getTaskById(taskId:Long): Task

}