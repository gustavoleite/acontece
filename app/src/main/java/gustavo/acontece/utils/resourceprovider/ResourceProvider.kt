package gustavo.acontece.utils.resourceprovider

interface ResourceProvider {
    fun getString(resourceId: Int) : String
}