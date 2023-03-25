package fastcampus.part5.chapter2.ui

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.gson.Gson
import fastcampus.part5.chapter2.ui.basket.BasketScreen
import fastcampus.part5.chapter2.ui.category.CategoryScreen
import fastcampus.part5.chapter2.ui.main.LikeScreen
import fastcampus.part5.chapter2.ui.main.MainCategoryScreen
import fastcampus.part5.chapter2.ui.main.MainHomeScreen
import fastcampus.part5.chapter2.ui.main.MyPageScreen
import fastcampus.part5.chapter2.ui.product_detail.ProductDetailScreen
import fastcampus.part5.chapter2.ui.search.SearchScreen
import fastcampus.part5.chapter2.ui.theme.MyApplicationTheme
import fastcampus.part5.chapter2.viewmodel.MainViewModel
import fastcampus.part5.domain.model.Category

@Composable
fun MainScreen(googleSignInClient: GoogleSignInClient) {
    val viewModel = hiltViewModel<MainViewModel>()
    val scaffoldState = rememberScaffoldState()
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        topBar = {
            if (NavigationItem.MainNav.isMainRoute(currentRoute)) {
                MainHeader(viewModel = viewModel, navController = navController)
            }
        },
        scaffoldState = scaffoldState,
        bottomBar = {
            if (NavigationItem.MainNav.isMainRoute(currentRoute)) {
                MainBottomNavigationBar(navController, currentRoute)
            }
        }
    ) {
        MainNavigationScreen(viewModel = viewModel, navController = navController, googleSignInClient)
    }
}

@Composable
fun MainHeader(viewModel: MainViewModel, navController: NavHostController) {
    TopAppBar(
        title = { Text("My App") },
        actions = {
            IconButton(onClick = {
                viewModel.openSearchForm(navController)
            }) {
                Icon(Icons.Filled.Search, "SearchIcon")
            }
            IconButton(onClick = {
                viewModel.openBasket(navController)
            }) {
                Icon(Icons.Filled.ShoppingCart, "ShoppingIcon")
            }
        }
    )
}


@Composable
fun MainBottomNavigationBar(navController: NavHostController, currentRoute: String?) {
    val bottomNavigationItems = listOf(
        NavigationItem.MainNav.Home,
        NavigationItem.MainNav.Category,
        NavigationItem.MainNav.LIKE,
        NavigationItem.MainNav.MyPage,
    )

    BottomNavigation {

        bottomNavigationItems.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(item.icon, item.route) },
                selected = currentRoute == item.route, onClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let {
                            popUpTo(it) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true

                        restoreState = true
                    }

                })
        }
    }
}

@Composable
fun MainNavigationScreen(viewModel: MainViewModel, navController: NavHostController, googleSignInClient: GoogleSignInClient) {
    NavHost(navController = navController, startDestination = NavigationRouteName.MAIN_HOME) {
        composable(NavigationRouteName.MAIN_HOME) {
            MainHomeScreen(navController, viewModel)
        }
        composable(NavigationRouteName.MAIN_CATEGORY) {
            MainCategoryScreen(viewModel, navController)
        }
        composable(NavigationRouteName.MAIN_MY_PAGE) {
            MyPageScreen(viewModel = viewModel, googleSignInClient = googleSignInClient)
        }
        composable(NavigationRouteName.MAIN_LIKE) {
            LikeScreen(navController, viewModel)
        }
        composable(NavigationRouteName.BASKET) {
            BasketScreen()
        }
        composable(
            CategoryNav.routeWithArgName(),
            arguments = CategoryNav.arguments,
            deepLinks = CategoryNav.deepLinks
        ) {
            val category = CategoryNav.findArgument(it)
            if (category != null) {
                CategoryScreen(navHostController = navController, category = category)
            }
        }
        composable(
            NavigationRouteName.CATEGORY + "/{category}",
            arguments = listOf(navArgument("category") { type = NavType.StringType }),
            deepLinks = listOf()
        ) {
            val categoryString = it.arguments?.getString("category")
            val category = Gson().fromJson(categoryString, Category::class.java)
            if (category != null) {
                CategoryScreen(navHostController = navController, category = category)
            }
        }
        composable(
            NavigationRouteName.PRODUCT_DETAIL + "/{product}",
            arguments = listOf(navArgument("product") { type = NavType.StringType })
        ) {
            val productString = it.arguments?.getString("product")
            if (productString != null) {
                ProductDetailScreen(productString)
            }
        }
        composable(NavigationRouteName.SEARCH) {
            SearchScreen(navController)
        }
    }
}