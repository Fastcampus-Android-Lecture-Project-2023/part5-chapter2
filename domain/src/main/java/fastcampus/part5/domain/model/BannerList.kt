package fastcampus.part5.domain.model

data class BannerList(
    val bannerId: String,
    val imageList: List<String>,
    override val type: ModelType = ModelType.BANNER_LIST,
    ) : BaseModel()