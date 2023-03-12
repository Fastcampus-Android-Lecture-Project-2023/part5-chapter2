package fastcampus.part5.chapter2.model

import fastcampus.part5.domain.model.BaseModel

sealed class PresentationVM<T : BaseModel>(val model: T) {

    //some func..
}