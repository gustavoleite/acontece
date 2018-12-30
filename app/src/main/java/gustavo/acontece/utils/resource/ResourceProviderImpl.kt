package gustavo.acontece.utils.resource

import android.content.Context

class ResourceProviderImpl(val context: Context) : ResourceProvider {
    override fun getString(resourceId: Int): String {
        return context.getString(resourceId)
    }
}