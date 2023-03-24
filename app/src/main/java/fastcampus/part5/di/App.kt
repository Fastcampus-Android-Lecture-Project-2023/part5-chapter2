package fastcampus.part5.di

import android.app.Application
import com.kakao.sdk.common.KakaoSdk
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        KakaoSdk.init(this,"ff5243be912f4fea421acaae0136481d")
    }
}
