package com.png.interview

import android.app.Application
import android.content.Context
import androidx.annotation.StringRes
import com.png.interview.dagger.component.AppComponent
import com.png.interview.dagger.component.DaggerAppComponent
import com.png.interview.dagger.module.AppModule
import timber.log.Timber

class App : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        mInstance = this
        Timber.plant(Timber.DebugTree())
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
        appComponent.inject(this)
    }

    companion object {
        private lateinit var mInstance: App
        fun get(context: Context): App = context.applicationContext as App
        private fun getAppContext(): Context = mInstance.applicationContext

        @JvmStatic
        fun getStringText(@StringRes resId: Int): String = getAppContext().getString(resId)
    }

}