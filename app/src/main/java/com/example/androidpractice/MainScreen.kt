package com.example.androidpractice

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.navigation3.ViewModelStoreNavEntryDecorator
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.scene.DialogSceneStrategy
import androidx.navigation3.ui.NavDisplay
import com.example.androidpractice.anime_list.presentation.screen.AnimeDetailsDialog
import com.example.androidpractice.anime_list.presentation.screen.AnimeListScreen
import com.example.androidpractice.core.model.Anime
import com.example.androidpractice.navigation.Route
import com.example.androidpractice.navigation.TopLevelBackStack
import org.koin.java.KoinJavaComponent.inject

interface TopLevelRoute: Route {
    val icon: ImageVector
}

data object MainList: TopLevelRoute {
    override val icon = Icons.AutoMirrored.Filled.List
}

data object Favourite: TopLevelRoute {
    override val icon = Icons.Default.FavoriteBorder
}

data class AnimeDetails(val anime: Anime): Route

@Composable
fun MainScreen() {
    val topLevelBackStack by inject<TopLevelBackStack<Route>>(TopLevelBackStack::class.java)

    Scaffold(
        bottomBar = {
            NavigationBar {
                listOf(MainList, Favourite).forEach { route ->
                    NavigationBarItem(
                        icon = { Icon(route.icon, null) },
                        selected = topLevelBackStack.topLevelKey == route,
                        onClick = { topLevelBackStack.addTopLevel(route) }
                    )
                }
            }
        }
    ) { paddingValues ->
        NavDisplay(
            backStack = topLevelBackStack.backStack,
            onBack = { topLevelBackStack.removeLast() },
            modifier = Modifier.padding(paddingValues),
            entryDecorators = listOf(
                rememberSaveableStateHolderNavEntryDecorator(),
                rememberViewModelStoreNavEntryDecorator()
            ),
            sceneStrategy = DialogSceneStrategy(),
            entryProvider = entryProvider {
                entry<MainList> {
                    AnimeListScreen(topLevelBackStack)
                }
                entry<Favourite> {

                }
                entry<AnimeDetails>(
                    metadata = DialogSceneStrategy.dialog(DialogProperties())
                ) {
                    AnimeDetailsDialog(it.anime, topLevelBackStack)
                }
            }
        )
    }
}

@Preview
@Composable
fun MainScreenPreview(){
    MainScreen()
}