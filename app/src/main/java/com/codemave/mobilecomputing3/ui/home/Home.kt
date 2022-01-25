package com.codemave.mobilecomputing3.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel  // not automatically
import androidx.navigation.NavController
import com.codemave.mobilecomputing3.R
import com.codemave.mobilecomputing3.data.entity.Category
import com.codemave.mobilecomputing3.ui.home.categoryPayment.CategoryPayment
import com.google.accompanist.insets.systemBarsPadding

@Composable
fun Home(
    viewModel: HomeViewModel = viewModel(),           // viewModel did not suggest import
    navController: NavController
) {
    val viewState by viewModel.state.collectAsState()

    val selectedCategory = viewState.selectedCategory

    if (viewState.categories.isNotEmpty() && selectedCategory != null) {
        Surface(modifier = Modifier.fillMaxSize()) {
            HomeContent(
                selectedCategory = selectedCategory,
                categories = viewState.categories,
                onCategorySelected = viewModel::onCategorySelected,
                navController = navController
            )
        }
    }
}

@Composable
fun HomeContent(
    selectedCategory: Category,
    categories: List<Category>,
    onCategorySelected: (Category) -> Unit,
    navController: NavController,
) {
    Scaffold (
        modifier = Modifier.padding(bottom = 24.dp),    // navication button not sink down, visible
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(route = "payment") },
                contentColor = Color.Green,
                modifier = Modifier.padding(all = 20.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null
                )
            }
        }
    ) {
        Column(
            modifier = Modifier
                .systemBarsPadding()
                .fillMaxWidth()
        ) {
            val appBarColor = MaterialTheme.colors.secondary.copy(alpha = 0.87f)
            //val appBarColor = MaterialTheme.colors.surface.copy(alpha = 0.87f) gived white back

            // Home bar
            HomeAppBar(
                backgroundColor = appBarColor
            )

            //Category tabs
            CategoryTabs(
                categories = categories,
                selectedCategory = selectedCategory,
                onCategorySelected = onCategorySelected
            )

            CategoryPayment()
        }
    }
}

@Composable
private fun HomeAppBar(
    backgroundColor: Color
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.app_name),
                color = MaterialTheme.colors.primary,
                modifier = Modifier
                    .padding(start = 4.dp)
                    .heightIn(max = 24.dp)
            )
        },
        backgroundColor = backgroundColor,
        actions = {
            IconButton( onClick = {}) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = stringResource(R.string.search))
            }
            IconButton( onClick = {}) {
                Icon(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = stringResource(R.string.account)
                )
            }
        }
    )
}

@Composable
private fun CategoryTabs(
    categories: List<Category>,
    selectedCategory: Category,
    onCategorySelected: (Category) -> Unit
) {
    val selectedIndex = categories.indexOfFirst { it == selectedCategory }
    ScrollableTabRow(
        selectedTabIndex = selectedIndex,
        edgePadding = 24.dp,
        indicator = emptyTabIndicator,
        modifier = Modifier.fillMaxWidth()
    ) {
        categories.forEachIndexed { index, category ->
            Tab(
                selected = index == selectedIndex,
                onClick = { onCategorySelected(category) }
            ) {
                ChoiceChipContent(
                    text = category.name,
                    //text = category.text,    // OPELLA OLI TALLAINEN
                    selected = index == selectedIndex,
                    modifier = Modifier.padding(horizontal = 4.dp, vertical = 16.dp)  // modifer
                )
            }
        }
    }
}

@Composable
private fun ChoiceChipContent(
    text: String,
    selected: Boolean,
    modifier: Modifier = Modifier   // modifer
) {
    Surface(
        color = when {
            selected -> MaterialTheme.colors.secondary.copy(alpha = 0.87f)  // Varit 1:01 aika
            //selected -> Color.White
            //selected -> MaterialTheme.colors.primary.copy(alpha = 0.08f)
            else -> MaterialTheme.colors.onSurface.copy(alpha = 0.12f)
        },
        contentColor = when {
            selected -> Color.Black.copy(alpha = 0.5f)    //MaterialTheme.colors.primary
            else -> MaterialTheme.colors.onSurface
        },
        shape = MaterialTheme.shapes.small,
        modifier = modifier
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.body2,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
    }
}

// This removes something, and line 129 indicator = emptyTabIndicator added also
private val emptyTabIndicator: @Composable (List<TabPosition>) -> Unit = {}