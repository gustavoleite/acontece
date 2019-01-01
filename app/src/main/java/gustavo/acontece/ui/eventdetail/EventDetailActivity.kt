package gustavo.acontece.ui.eventdetail

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import gustavo.acontece.MainApplication
import gustavo.acontece.R
import gustavo.acontece.databinding.ActivityEventDetailBinding
import javax.inject.Inject

class EventDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEventDetailBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var listAdapter: PeopleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainApplication.appComponent.inject(this)
        val viewModel = provideViewModel()
        setupBinding(viewModel)
        setupAdapter()
        setupObservers(viewModel)
        viewModel.loadData(intent.extras?.getString(EVENT_DETAIL_ARG) ?: throw Exception())
    }

    private fun provideViewModel(): EventDetailViewModel {
        return ViewModelProviders.of(this, viewModelFactory).get(EventDetailViewModel::class.java)
    }

    private fun setupBinding(viewModel: EventDetailViewModel) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_event_detail)
        binding.viewModel = viewModel
    }

    private fun setupAdapter() {
        with(binding.eventDetailRecyclerView) {
            layoutManager = LinearLayoutManager(this@EventDetailActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = listAdapter
        }
    }

    private fun setupObservers(viewModel: EventDetailViewModel) {
        with(viewModel) {
            event.observe(this@EventDetailActivity, Observer {
                it?.let {
                    listAdapter.setPeopleList(it.peoples)
                }
            })
            /*loaderVisibility.observe(this@EventDetailActivity, Observer {
                it?.let {
                    binding.homeSwipeRefreshLayout.isRefreshing = it
                }
            })
            errorMessage.observe(this@EventDetailActivity, Observer {
                binding.homeFaceAnimation.repeatCount = LottieDrawable.INFINITE
                if (it.isNullOrBlank()) {
                    hideNetworkingInfo()
                } else if (!binding.homeFaceAnimation.isAnimating) {
                    showNetworkingInfo(it)
                }
            })*/
        }
    }

    /*private fun showNetworkingInfo(it: String?) {
        listAdapter.setEventPreviewList(emptyList())
        binding.homeMessageTextView.text = it
        binding.homeFaceAnimation.playAnimation()
        binding.homeFaceAnimation.visibility = View.VISIBLE
    }

    private fun hideNetworkingInfo() {
        binding.homeMessageTextView.text = null
        binding.homeFaceAnimation.pauseAnimation()
        binding.homeFaceAnimation.visibility = View.GONE
    }*/

    companion object {

        private val EVENT_DETAIL_ARG = "EVENT_DETAIL_ARG"

        fun newInstance(intent: Intent, eventId: String): Intent {
            return intent.apply {
                putExtra(EVENT_DETAIL_ARG, eventId)
            }
        }
    }
}