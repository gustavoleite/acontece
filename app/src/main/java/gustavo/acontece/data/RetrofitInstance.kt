package gustavo.acontece.data

import gustavo.acontece.R
import gustavo.acontece.util.resourceprovider.ResourceProvider
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class RetrofitInstance {
    companion object {
        fun get(resourceProvider: ResourceProvider): Retrofit {
            return retrofit2
                .Retrofit
                .Builder()
                .baseUrl(resourceProvider.getString(R.string.API_BASE_URL))
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}