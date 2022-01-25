package com.codemave.mobilecomputing3.ui.home.categoryPayment

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.viewmodel.compose.viewModel
import com.codemave.mobilecomputing3.R
import com.codemave.mobilecomputing3.data.entity.Payment
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun CategoryPayment() {
    val viewModel: CategoryPaymentViewModel = viewModel()
    val viewState by viewModel.state.collectAsState()

    Column(Modifier.fillMaxWidth()) {
        PaymentList(
            list = viewState.payments
        )
    }
}

@Composable
private fun PaymentList(
    list: List<Payment>
) {
    LazyColumn(
        contentPadding = PaddingValues(0.dp),
        verticalArrangement = Arrangement.Center
    ) {
        items(list) { item ->
            PaymentListItem(
                payment = item,
                onClick = {}
            )
        }
    }
}

@Composable
private fun PaymentListItem(
    payment: Payment,
    onClick: () -> Unit
) {
    // clickable means that the whole payment item is clickable in list
    ConstraintLayout(modifier = Modifier.clickable { onClick() }) {
        val (divider, paymentTitle, paymentCategory, icon, date) = createRefs()
        // create top divider between payments
        Divider(
            Modifier.constrainAs(divider) {
                top.linkTo(parent.top)
                centerHorizontallyTo(parent)
                width = Dimension.fillToConstraints
            }
        )

        // title
        Text(
            text = payment.paymentTitle,
            maxLines = 1,
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier.constrainAs(paymentTitle) {
                linkTo(
                    start = parent.start,    // start where the parent starts
                    end = icon.start,         // end where icon starts at the right side
                    startMargin = 24.dp,
                    endMargin = 16.dp
                )
                top.linkTo(parent.top, margin = 10.dp)
                width = Dimension.preferredWrapContent
            }
        )

        // category
        Text(
            text = payment.paymentCategory,
            maxLines = 1,
            style = MaterialTheme.typography.subtitle2,
            modifier = Modifier.constrainAs(paymentCategory) {
                linkTo(
                    start = parent.start,    // start where the parent starts
                    end = icon.start,         // end where icon starts at the right side
                    startMargin = 24.dp,
                    endMargin = 8.dp
                )
                top.linkTo(paymentTitle.bottom, margin = 6.dp)
                bottom.linkTo(parent.bottom, 10.dp)
                width = Dimension.preferredWrapContent
            }
        )

        // date
        Text(
            text = when {
                payment.paymentDate != null -> { payment.paymentDate.formatToString() }
                else -> Date().formatToString()
            },
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.caption,
            modifier = Modifier.constrainAs(date) {
                linkTo(
                    start = paymentCategory.end,
                    end = icon.start,
                    startMargin = 8.dp,
                    endMargin = 16.dp
                )
                centerVerticallyTo(paymentCategory)    // date on the same level as paymentcaregory
                top.linkTo(paymentTitle.bottom, 6.dp)
                bottom.linkTo(parent.bottom, 10.dp)
            }
        )

        // icon
        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .size(50.dp)
                .padding(6.dp)
                .constrainAs(icon) {
                    top.linkTo(parent.top, 10.dp)
                    bottom.linkTo(parent.bottom, 10.dp)
                    end.linkTo(parent.end)
                }
        ) {
            Icon(
                imageVector = Icons.Filled.Check,
                contentDescription = stringResource(R.string.check_mark)
            )
        }
    }
}

private fun Date.formatToString(): String {
    return SimpleDateFormat("MM dd, yyyy", Locale.getDefault()).format(this)
}