package fastcampus.part5.chapter2.model

import fastcampus.part5.chapter2.delegate.ProductDelegate
import fastcampus.part5.domain.model.Product

class ProductVM(model: Product, productDelegate: ProductDelegate) :
    PresentationVM<Product>(model), ProductDelegate by productDelegate