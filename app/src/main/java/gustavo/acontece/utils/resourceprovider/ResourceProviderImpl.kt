package gustavo.acontece.utils.resourceprovider

import android.content.Context

class ResourceProviderImpl(private val context: Context) : ResourceProvider {
    override fun getString(resourceId: Int): String {
        return context.getString(resourceId)
    }
}