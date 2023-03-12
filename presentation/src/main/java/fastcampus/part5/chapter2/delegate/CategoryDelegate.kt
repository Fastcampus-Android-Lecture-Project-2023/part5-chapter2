package fastcampus.part5.chapter2.delegate

import androidx.navigation.NavHostController
import fastcampus.part5.domain.model.Category

interface CategoryDelegate {
    fun openCategory(navHostController: NavHostController, category: Category)
}