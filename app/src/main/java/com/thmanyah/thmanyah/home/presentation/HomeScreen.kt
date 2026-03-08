package com.thmanyah.thmanyah.home.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.thmanyah.thmanyah.core.ui.components.ErrorStateView
import com.thmanyah.thmanyah.core.ui.components.LoadingStateView
import com.thmanyah.thmanyah.core.ui.components.SectionView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onNavigateToSearch: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val sections = viewModel.homeSections.collectAsLazyPagingItems()

    HomeScreenContent(
        sections = sections,
        onNavigateToSearch = onNavigateToSearch
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenContent(
    sections: androidx.paging.compose.LazyPagingItems<com.thmanyah.thmanyah.core.model.DomainSection>,
    onNavigateToSearch: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "ثمانية",
                        style = MaterialTheme.typography.headlineSmall
                    )
                },
                actions = {
                    IconButton(onClick = onNavigateToSearch) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        },
        contentWindowInsets = WindowInsets(0.dp)
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when (val refreshState = sections.loadState.refresh) {
                is LoadState.Loading -> {
                    LoadingStateView()
                }
                is LoadState.Error -> {
                    ErrorStateView(
                        message = refreshState.error.message ?: "An error occurred",
                        onRetry = { sections.retry() }
                    )
                }
                is LoadState.NotLoading -> {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(
                            count = sections.itemCount,
                            key = sections.itemKey { "${it.name}_${it.order}" },
                            contentType = sections.itemContentType { it.type }
                        ) { index ->
                            sections[index]?.let { section ->
                                SectionView(section = section)
                            }
                        }
                        
                        if (sections.loadState.append is LoadState.Loading) {
                            item {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    CircularProgressIndicator()
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
