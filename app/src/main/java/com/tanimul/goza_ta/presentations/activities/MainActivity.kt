package com.tanimul.goza_ta.presentations.activities

import android.os.Bundle
import com.tanimul.goza_ta.base.BaseActivity
import com.tanimul.goza_ta.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun init(savedInstanceState: Bundle?) {
    }


}