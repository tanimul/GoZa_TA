package com.tanimul.goza_ta.presentations.fragments.profile.presentations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.tanimul.goza_ta.R
import com.tanimul.goza_ta.base.BaseFragment
import com.tanimul.goza_ta.databinding.FragmentProfileBinding
import com.tanimul.goza_ta.databinding.FragmentSeeAllBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>(R.layout.fragment_profile) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}