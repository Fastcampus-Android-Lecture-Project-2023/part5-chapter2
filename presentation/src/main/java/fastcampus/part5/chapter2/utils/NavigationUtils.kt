package fastcampus.part5.chapter2.utils

import androidx.navigation.NavHostController
import fastcampus.part5.chapter2.ui.BasketNav
import fastcampus.part5.chapter2.ui.CategoryNav
import fastcampus.part5.chapter2.ui.Destination
import fastcampus.part5.chapter2.ui.MainNav
import fastcampus.part5.chapter2.ui.NavigationRouteName
import fastcampus.part5.chapter2.ui.ProductDetailNav
import fastcampus.part5.chapter2.ui.PurchaseHistoryNav
import fastcampus.part5.chapter2.ui.SearchNav

object NavigationUtils {

    fun navigate(
        controller: NavHostController,
        routeName: String,
        backStackRouteName: String? =null,
        isLaunchSingleTop: Boolean= true,
        needToRestoreState: Boolean= true
    ) {
        controller.navigate(routeName) {
            if(backStackRouteName != null) {
                popUpTo(backStackRouteName) { saveState = true}
            }
            launchSingleTop = isLaunchSingleTop
            restoreState = needToRestoreState
        }
    }

    fun findDestination(route : String?) : Destination {
        return when(route) {
            NavigationRouteName.MAIN_MY_PAGE -> MainNav.MyPage
            NavigationRouteName.MAIN_LIKE -> MainNav.Like
            NavigationRouteName.MAIN_HOME -> MainNav.Home
            NavigationRouteName.MAIN_CATEGORY -> MainNav.Category
            NavigationRouteName.SEARCH -> SearchNav
            NavigationRouteName.BASKET -> BasketNav
            NavigationRouteName.PURCHASE_HISTORY -> PurchaseHistoryNav

            ProductDetailNav.routeWithArgName() -> ProductDetailNav
            CategoryNav.routeWithArgName() -> CategoryNav
            else -> MainNav.Home
        }
    }
}