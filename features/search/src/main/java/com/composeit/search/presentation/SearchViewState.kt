package com.composeit.search.presentation

import com.composeit.search.model.TaskSearchItem

internal sealed class SearchViewState {

    internal object Loading: SearchViewState()

    internal data class Loaded(val taskList: List<TaskSearchItem>): SearchViewState()

    internal object Empty: SearchViewState()

}