package gustavo.acontece.inject

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import gustavo.acontece.feature.events.EventsViewModel

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    internal abstract fun bindEventsViewModel(viewModel: EventsViewModel)
}