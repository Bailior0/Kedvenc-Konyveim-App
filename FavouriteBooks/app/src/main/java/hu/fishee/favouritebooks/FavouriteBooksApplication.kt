package hu.fishee.favouritebooks

import android.app.Application
import co.zsmb.rainbowcake.config.rainbowCake
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
open class FavouriteBooksApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        rainbowCake {
            isDebug = BuildConfig.DEBUG
        }
    }
}