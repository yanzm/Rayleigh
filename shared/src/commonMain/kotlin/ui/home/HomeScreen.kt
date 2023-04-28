package ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import remote.Follow
import remote.Like
import remote.Post
import remote.Repost

@Composable
fun HomeScreen(
    viewModel: HomeViewModel
) {
    HomeContent(
        uiState = viewModel.uiState
    )
}

@Composable
private fun HomeContent(
    uiState: HomeViewModel.UiState
) {
    when (uiState) {
        HomeViewModel.UiState.Initial,
        HomeViewModel.UiState.Loading -> {
            CircularProgressIndicator(
                modifier = Modifier.fillMaxSize()
                    .wrapContentSize()
            )
        }

        is HomeViewModel.UiState.Error -> {
            Text(
                text = uiState.e.message,
                modifier = Modifier.fillMaxSize()
                    .wrapContentSize()
            )
        }

        is HomeViewModel.UiState.Success -> {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(uiState.timeline.feed) { feed ->
                    Column(
                        modifier = Modifier.padding(
                            horizontal = 16.dp,
                            vertical = 8.dp,
                        )
                    ) {
                        feed.post.author.displayName?.let {
                            Text(
                                text = it
                            )
                        }
                        when (val record = feed.post.record) {
                            is Follow -> {
                                Text(
                                    text = record.subject
                                )
                            }

                            is Like -> {
                                Text(
                                    text = "like"
                                )
                            }

                            is Post -> {
                                Text(
                                    text = record.text
                                )
                            }

                            is Repost -> {
                                Text(
                                    text = "repost"
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
