package fastcampus.part5.domain.model

sealed class SearchFilter(open val type: Type ){
    enum class Type {
        PRICE, CATEGORY
    }

    open fun isAvailableProduct(product: Product) : Boolean{
        return true
    }

    open fun clear() { }

    data class PriceFilter(val priceRange: Pair<Float,Float>, var selectedRange: Pair<Float, Float>?= null) : SearchFilter(Type.PRICE) {

        override fun isAvailableProduct(product: Product): Boolean {
            return selectedRange == null || product.price.finalPrice.toFloat() in (selectedRange?.first ?: 0f)..(selectedRange?.second ?: 0f)
        }

        override fun clear() {
            selectedRange = null
        }
    }

    data class CategoryFilter(val categories: List<Category>, var selectedCategory: Category?=null) : SearchFilter(Type.CATEGORY) {

        override fun isAvailableProduct(product: Product): Boolean {
            return selectedCategory == null || product.category.categoryId == selectedCategory?.categoryId
        }

        override fun clear() {
            selectedCategory = null
        }
    }
}
