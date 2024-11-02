package com.tanimul.goza_ta.presentations.fragments.details.presentations

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.tanimul.goza_ta.R
import com.tanimul.goza_ta.base.BaseFragment
import com.tanimul.goza_ta.common.extention.launchAndRepeatWithViewLifecycle
import com.tanimul.goza_ta.databinding.FragmentDetailsBinding
import com.tanimul.goza_ta.presentations.fragments.see_all.presentations.SeeAllUiActions
import com.tanimul.goza_ta.presentations.fragments.see_all.presentations.SeeAllViewModel
import com.youth.banner.indicator.CircleIndicator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
@ExperimentalCoroutinesApi
class DetailsFragment : BaseFragment<FragmentDetailsBinding>(R.layout.fragment_details) {

    private val detailsViewModel: DetailsViewModel by viewModels()
    private var bannerAdapter: BannerCarouselAdapter? = null
    private val args: DetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding.apply {
            viewModel = detailsViewModel
        }

        args.recommended?.let {
            Timber.d("recommended: $it")
            bannerAdapter = BannerCarouselAdapter(
                listOf(
                    it.heroImage, it.detailImage, it.heroImage, it.detailImage
                )
            )
            mBinding.bannerLocation.setLoopTime(5000)
            mBinding.bannerLocation.addBannerLifecycleObserver(this)
                .setAdapter(bannerAdapter).indicator = CircleIndicator(requireContext())

            mBinding.details =it

        }

        launchAndRepeatWithViewLifecycle {
            launch {
                detailsViewModel.uiAction.collectLatest {
                    when (it) {
                        is DetailsUiActions.OnNavigateBack -> findNavController().popBackStack()
                        is DetailsUiActions.OnBookNow -> TODO()
                    }
                }
            }
        }


    }
}