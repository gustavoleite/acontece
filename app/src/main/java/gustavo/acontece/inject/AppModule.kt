package gustavo.acontece.inject

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import gustavo.acontece.R
import gustavo.acontece.data.api.CerimonyApi
import gustavo.acontece.util.resourceprovider.ResourceProvider
import gustavo.acontece.util.resourceprovider.ResourceProviderImpl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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

    @Provides
    @Singleton
    fun provideRetrofit(resourceProvider: ResourceProvider): Retrofit {
        return Retrofit.Builder()
            .baseUrl(resourceProvider.getString(R.string.API_BASE_URL))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideCerimonyApi(retrofit: Retrofit): CerimonyApi {
        return retrofit.create<CerimonyApi>(CerimonyApi::class.java)
    }
}