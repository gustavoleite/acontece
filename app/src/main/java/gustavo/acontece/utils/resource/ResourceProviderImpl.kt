package gustavo.acontece.utils.resource

import android.content.Context
import javax.inject.Inject

class ResourceProviderImpl @Inject constructor(val context: Context) : ResourceProvider {
    override fun getString(resourceId: Int, vararg formatArgs: Any?): String {
        return context.getString(resourceId, formatArgs)
    }
}