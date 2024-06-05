package com.composeit.glance.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import androidx.datastore.dataStoreFile
import androidx.glance.state.GlanceStateDefinition
import com.composeit.glance.model.Task
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import kotlinx.serialization.json.encodeToStream
import java.io.File
import java.io.InputStream
import java.io.OutputStream

internal object TaskListStateDefinition : GlanceStateDefinition<List<Task>> {

    private const val DATA_STORE_FILENAME = "taskList"

    private val Context.datastore by dataStore(DATA_STORE_FILENAME, TaskListSerializer)


    override suspend fun getDataStore(
        context: Context,
        fileKey: String,
    ): DataStore<List<Task>> =
        context.datastore


    override fun getLocation(context: Context, fileKey: String): File =
        context.dataStoreFile(DATA_STORE_FILENAME)

    suspend fun updateData(
        context: Context,
        newTasks: List<Task>,
    ) = getDataStore(context, DATA_STORE_FILENAME).updateData { newTasks }



    object TaskListSerializer: Serializer<List<Task>>{

        override val defaultValue: List<Task>
            get() = listOf()

        override suspend fun readFrom(input: InputStream): List<Task> =
            Json.decodeFromStream(input)

        override suspend fun writeTo(t: List<Task>, output: OutputStream) =
            Json.encodeToStream(t,output)


    }



}