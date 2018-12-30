package gustavo.acontece.inject

import android.arch.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import gustavo.acontece.feature.events.EventsViewModel

@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(EventsViewModel::class)
    internal abstract fun bindEventsViewModel(viewModel: EventsViewModel)
}