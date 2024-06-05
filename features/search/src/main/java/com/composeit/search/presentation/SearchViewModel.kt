package com.composeit.search.presentation

import androidx.lifecycle.ViewModel
import com.composeit.domain.model.TaskWithCategory
import com.composeit.domain.usecase.search.SearchTaskByName
import com.composeit.search.mapper.TaskSearchMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class SearchViewModel(
    private val findTaskUseCase: SearchTaskByName,
    private val mapper: TaskSearchMapper
): ViewModel() {

    fun findTaskByName(name: String = ""): Flow<SearchViewState> = flow {
        findTaskUseCase(name).collect{ taskList ->
            val state = if(taskList.isNotEmpty()){
                onListLoaded(taskList)
            } else {
                SearchViewState.Empty
            }

        }
    }
    private fun onListLoaded(taskList: List<TaskWithCategory>): SearchViewState {
        val searchList = mapper.toView(taskList)
        return SearchViewState.Loaded(searchList)
    }


}