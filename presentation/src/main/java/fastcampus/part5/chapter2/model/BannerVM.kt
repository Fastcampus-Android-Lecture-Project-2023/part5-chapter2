package fastcampus.part5.chapter2.model

import fastcampus.part5.chapter2.delegate.BannerDelegate
import fastcampus.part5.domain.model.Banner

class BannerVM(model: Banner, bannerDelegate: BannerDelegate)
    : PresentationVM<Banner>(model), BannerDelegate by bannerDelegate