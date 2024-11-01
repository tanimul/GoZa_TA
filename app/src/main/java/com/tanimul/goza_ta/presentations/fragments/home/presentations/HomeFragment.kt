package com.tanimul.goza_ta.presentations.fragments.home.presentations

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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
import timber.log.Timber

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
                        is HomeUiActions.OnSelectedPlace -> findNavController().navigate(
                            HomeFragmentDirections.actionHomeFragmentToDetailsFragment(
                                it.recommended
                            )
                        )

                        is HomeUiActions.OnNavigateSeeAll -> findNavController().navigate(
                            HomeFragmentDirections.actionHomeFragmentToSeeAllFragment(
                                it.recommendedList.toTypedArray()
                            )
                        )
                    }
                }
            }
        }

        mBinding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                Timber.d("onQueryTextChange: $s")
                homeViewModel.searchPlaces("$s")
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun afterTextChanged(s: Editable) {}
        })

    }
}