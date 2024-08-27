package pl.org.seva.multiplatform

import android.app.Application
import org.koin.core.logger.Level
import org.koin.mp.KoinPlatform.startKoin
import pl.org.seva.multiplatform.di.dataModule
import pl.org.seva.multiplatform.di.dataSourceModule
import pl.org.seva.multiplatform.di.presentationModule
import pl.org.seva.multiplatform.di.uiModule

class CheckersApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin(
            modules = listOf(dataModule, dataSourceModule, presentationModule, uiModule),
            level = Level.DEBUG,
        )
    }

}
