package fastcampus.part5.chapter2.utils

import android.net.Uri
import android.os.Parcelable
import androidx.navigation.NavHostController
import com.google.gson.Gson
import fastcampus.part5.domain.model.Category
import fastcampus.part5.domain.model.Product

object NavigationUtils {

    fun navigate(
        controller: NavHostController,
        routeName: String,
        args: Any? = null,
        backStackRouteName: String? =null,
        isLaunchSingleTop: Boolean= true,
        needToRestoreState: Boolean= true
    ) {
        var argument = ""
        if(args != null) {
            when(args) {
                is Parcelable-> {
                    argument = String.format("/%s", Uri.parse(Gson().toJson(args)))
                }
                is Category -> {
                    argument = String.format("/%s", Uri.parse(Gson().toJson(args)))
                }
                is Product -> {
                    argument = String.format("/%s", args.productId)
                }
            }
        }
        controller.navigate("$routeName$argument") {
            if(backStackRouteName != null) {
                popUpTo(backStackRouteName) { saveState = true}
            }
            launchSingleTop = isLaunchSingleTop
            restoreState = needToRestoreState
        }
    }
    fun navigatev2(
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
}