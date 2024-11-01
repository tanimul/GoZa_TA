package com.tanimul.goza_ta.presentations.fragments.details.presentations

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.tanimul.goza_ta.R
import com.tanimul.goza_ta.base.BaseFragment
import com.tanimul.goza_ta.databinding.FragmentDetailsBinding
import com.youth.banner.indicator.CircleIndicator
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class DetailsFragment : BaseFragment<FragmentDetailsBinding>(R.layout.fragment_details) {
    private var bannerAdapter: BannerCarouselAdapter? = null
    private val args: DetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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


    }
}