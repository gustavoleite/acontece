package gustavo.acontece.ui.checkin

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import gustavo.acontece.MainApplication
import gustavo.acontece.R
import gustavo.acontece.databinding.ActivityCheckinBinding
import javax.inject.Inject


class CheckinActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCheckinBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainApplication.appComponent.inject(this)
        val viewModel = provideViewModel()
        setupBinding(viewModel)
        setupObservers()
        viewModel.eventId = (intent.extras?.getString(CheckinActivity.CHECKIN_ARG) ?: throw Exception())
    }

    private fun provideViewModel(): CheckinViewModel {
        return ViewModelProviders.of(this, viewModelFactory).get(CheckinViewModel::class.java)
    }

    private fun setupBinding(viewModel: CheckinViewModel) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_checkin)
        binding.viewModel = viewModel
    }

    private fun setupObservers() {
    }

    companion object {

        private const val CHECKIN_ARG = "CHECKIN_ARG"

        fun newInstance(intent: Intent, eventId: String): Intent {
            return intent.apply {
                putExtra(CHECKIN_ARG, eventId)
            }
        }
    }
}