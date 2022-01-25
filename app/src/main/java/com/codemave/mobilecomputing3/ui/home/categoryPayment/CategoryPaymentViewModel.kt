package com.codemave.mobilecomputing3.ui.home.categoryPayment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codemave.mobilecomputing3.data.entity.Payment
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.*

class CategoryPaymentViewModel : ViewModel() {
    private val _state = MutableStateFlow(CategoryPaymentViewState())

    val state: StateFlow<CategoryPaymentViewState>
        get() = _state

    init {
        val list = mutableListOf<Payment>()
        for (x in 1..20) {
            list.add(
                Payment(
                    paymentId = x.toLong(),
                    paymentTitle = "$x payment",
                    paymentCategory = "Food",
                    paymentDate = Date()
                )
            )
        }

        viewModelScope.launch {
            _state.value = CategoryPaymentViewState(
                payments = list
            )
        }
    }
}

data class CategoryPaymentViewState(
    val payments: List<Payment> = emptyList()
)