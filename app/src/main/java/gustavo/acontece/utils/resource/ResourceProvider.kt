package gustavo.acontece.utils.resource

import android.support.annotation.StringRes

interface ResourceProvider {
    fun getString(@StringRes resourceId: Int, vararg formatArgs: Any?) : String
}