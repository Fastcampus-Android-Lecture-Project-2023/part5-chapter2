package fastcampus.part5.chapter2.model

import fastcampus.part5.chapter2.delegate.BannerDelegate
import fastcampus.part5.domain.model.BannerList

class BannerListVM(model: BannerList, private val bannerDelegate: BannerDelegate):PresentationVM<BannerList>(model)
{
    fun openBannerList(bannerId: String) {
        bannerDelegate.openBanner(bannerId)
    }
}