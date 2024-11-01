package com.tanimul.goza_ta.presentations.fragments.home.presentations

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.tanimul.goza_ta.R
import com.tanimul.goza_ta.base.BaseFragment
import com.tanimul.goza_ta.common.extention.launchAndRepeatWithViewLifecycle
import com.tanimul.goza_ta.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
@OptIn(ExperimentalCoroutinesApi::class)
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding.apply {
            viewModel = homeViewModel
        }

        launchAndRepeatWithViewLifecycle {
            launch {
                homeViewModel.uiAction.collectLatest {
                    when (it) {
                        is HomeUiActions.SelectedPlace -> TODO()
                        is HomeUiActions.OnNavigateSeeAll ->  findNavController().navigate(
                            HomeFragmentDirections.actionHomeFragmentToSeeAllFragment(
                                it.recommendedList.toTypedArray()
                            )
                        )
                    }
                }
            }
        }
    }
}