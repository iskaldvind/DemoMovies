package studio.iskaldvind.demomovies

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import studio.iskaldvind.demomovies.di.application
import studio.iskaldvind.demomovies.di.list

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(listOf(application, list))
        }
    }
}