package gustavo.acontece

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LifecycleRegistry
import io.mockk.every
import io.mockk.mockkClass

abstract class BaseTest {
    fun mockLifecycleOwner(): LifecycleOwner {
        val lifecycleOwner = mockkClass(LifecycleOwner::class)

        val registry = LifecycleRegistry(lifecycleOwner)
        registry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
        every { lifecycleOwner.lifecycle } returns registry

        return lifecycleOwner
    }
}