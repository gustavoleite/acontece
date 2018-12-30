package gustavo.acontece.inject

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import gustavo.acontece.R
import gustavo.acontece.data.api.EventsApi
import gustavo.acontece.data.repository.EventRepositoryImpl
import gustavo.acontece.utils.resource.ResourceProvider
import gustavo.acontece.utils.resource.ResourceProviderImpl
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
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
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideEventApi(retrofit: Retrofit): EventsApi {
        return retrofit.create<EventsApi>(EventsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideEventRepositoryImpl(eventsApi: EventsApi): EventRepositoryImpl {
        return EventRepositoryImpl(eventsApi)
    }
}