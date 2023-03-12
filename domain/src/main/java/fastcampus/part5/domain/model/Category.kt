package fastcampus.part5.domain.model

import android.os.Parcelable

open class Category(
    val categoryId: String,
    val categoryName: String,
) {
    object Top : Category("1","상의")
    object Outerwear : Category("2","아우터")
    object Dress : Category("3","원피스")
    object Pants : Category("4","바지")
    object Skirt : Category("5","치마")
    object Shoes : Category("6","신발")
    object Bag : Category("7","가방")
    object FashionAccessories : Category("8","패션잡화")
}
