package gustavo.acontece.ui.home

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import gustavo.acontece.MainApplication
import gustavo.acontece.R
import gustavo.acontece.databinding.ActivityHomeBinding
import javax.inject.Inject

class HomeActivity : AppCompatActivity() {

    lateinit var viewModel: HomeViewModel
    lateinit var binding: ActivityHomeBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var adapter: EventsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainApplication.appComponent.inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel::class.java)
        setupBinding()
        setupAdapter()
        setupObservers(viewModel)
        viewModel.loadData()
    }

    private fun setupBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        binding.viewModel = viewModel
    }

    private fun setupAdapter() {
        binding.homeRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.homeRecyclerView.adapter = adapter
    }

    private fun setupObservers(viewModel: HomeViewModel) {
        viewModel.eventPreviewList.observe(this, Observer {
            it?.let {
                adapter.setEventPreviewList(it)
            }
        })
    }
}