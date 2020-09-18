package com.codechallenge.ui.dashboard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.codechallenge.R
import com.codechallenge.RecordApp
import com.codechallenge.ui.city.CityFragment
import com.codechallenge.utilities.replaceFragment
import com.codechallenge.utilities.replaceFragmentWithBackStack
import com.google.android.gms.maps.model.LatLng

class DashboardActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as RecordApp)
            .getRecordComponent()
            .inject(this@DashboardActivity)
        setContentView(R.layout.activity_dashboard)
        replaceFragment(R.id.container, ::HomeFragment)
    }

    fun openCityDetail(position: LatLng) {
        replaceFragmentWithBackStack(R.id.container) { CityFragment.newInstance(position) }
    }
}
