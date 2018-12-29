package gustavo.acontece.inject

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import gustavo.acontece.util.resourceprovider.ResourceProvider
import gustavo.acontece.util.resourceprovider.ResourceProviderImpl
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