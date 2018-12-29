package gustavo.acontece.util.resourceprovider

interface ResourceProvider {
    fun getString(resourceId: Int) : String
}