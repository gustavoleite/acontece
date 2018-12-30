package gustavo.acontece.inject

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import gustavo.acontece.utils.resource.ResourceProvider
import gustavo.acontece.utils.resource.ResourceProviderImpl
import javax.inject.Singleton

@Module
class AppModule(val application: Application) {

    @Provides
    @Singleton
    fun provideApplication(): Application {
        return application
    }

    @Provides
    @Singleton
    fun provideContext(): Context {
        return application
    }

    @Provides
    @Singleton
    fun provideResourceProvider(): ResourceProvider {
        return ResourceProviderImpl(application)
    }
}