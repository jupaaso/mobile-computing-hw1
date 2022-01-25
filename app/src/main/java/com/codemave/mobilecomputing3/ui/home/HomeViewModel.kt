package com.codemave.mobilecomputing3.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codemave.mobilecomputing3.data.entity.Category
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val _state = MutableStateFlow(HomeViewState())
    private val _selectedCategory = MutableStateFlow<Category?>( null)

    val state: StateFlow<HomeViewState>
        get() = _state

    fun onCategorySelected(category: Category) {
        _selectedCategory.value = category
    }

    init {
        val categories = MutableStateFlow<List<Category>>(
            mutableListOf(
                Category( 1, "Food"),
                Category( 2, "Health"),
                Category( 4, "Savings"),
                Category( 5, "Drinks"),
                Category( 6, "Clothing"),
                Category( 7, "Investment"),
                Category( 8, "Travel"),
                Category( 9, "Fuel"),
                Category( 10, "Repairs"),
                Category( 11, "Coffee")
            )
        )

        viewModelScope.launch {
            combine(
                categories.onEach { category ->
                    if (categories.value.isNotEmpty() && _selectedCategory.value == null) {
                        _selectedCategory.value = category[0]   // set 1st as selected category
                    }
                },
                _selectedCategory
            ) { categories, selectedCategory ->
                HomeViewState(
                    categories = categories,
                    selectedCategory = selectedCategory
                )
            }.collect { _state.value = it }
        }
    }
}

data class HomeViewState(
    val categories: List<Category> = emptyList(),
    val selectedCategory: Category? = null
)