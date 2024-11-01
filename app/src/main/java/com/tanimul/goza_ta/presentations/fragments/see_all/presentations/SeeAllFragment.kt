package com.tanimul.goza_ta.presentations.fragments.see_all.presentations

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.tanimul.goza_ta.R
import com.tanimul.goza_ta.base.BaseFragment
import com.tanimul.goza_ta.common.extention.launchAndRepeatWithViewLifecycle
import com.tanimul.goza_ta.databinding.FragmentSeeAllBinding
import com.tanimul.goza_ta.presentations.fragments.home.presentations.HomeFragmentDirections
import com.tanimul.goza_ta.presentations.fragments.home.presentations.HomeUiActions
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
@OptIn(ExperimentalCoroutinesApi::class)
class SeeAllFragment : BaseFragment<FragmentSeeAllBinding>(R.layout.fragment_see_all) {

    private val seeAllViewModel: SeeAllViewModel by viewModels()
    private val args: SeeAllFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding.apply {
            viewModel = seeAllViewModel
        }

        args.recommendedList?.let {
            Timber.d("recommendedList: ${it.toList()}")
            seeAllViewModel.recommended(it.toList())
        }

        launchAndRepeatWithViewLifecycle {
            launch {
                seeAllViewModel.uiAction.collectLatest {
                    when (it) {
                        is SeeAllUiActions.OnNavigateBack -> findNavController().popBackStack()
                        is SeeAllUiActions.SelectedPlace -> TODO()
                    }
                }
            }
        }
    }
}