package gustavo.acontece.utils.resource

interface ResourceProvider {
    fun getString(resourceId: Int) : String
}