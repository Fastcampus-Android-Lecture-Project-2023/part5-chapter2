package fastcampus.part5.domain.model

sealed class SearchFilter(open val type: Type ){
    enum class Type {
        PRICE, CATEGORY
    }

    open fun isAvailableProduct(product: Product) : Boolean{
        return true
    }

    open fun clear() { }

    data class PriceFilter(val priceRange: Pair<Int,Int>, var selectedRange: Pair<Int, Int>?= null) : SearchFilter(Type.PRICE) {

        override fun isAvailableProduct(product: Product): Boolean {
            return product.price.finalPrice in (selectedRange?.first ?: 0)..(selectedRange?.second ?: 0)
        }

        override fun clear() {
            selectedRange = null
        }
    }

    data class CategoryFilter(val categories: List<Category>, var selectedCategory: Category?=null) : SearchFilter(Type.CATEGORY) {

        override fun isAvailableProduct(product: Product): Boolean {
            return product.category.categoryId == selectedCategory?.categoryId
        }

        override fun clear() {
            selectedCategory = null
        }
    }
}
