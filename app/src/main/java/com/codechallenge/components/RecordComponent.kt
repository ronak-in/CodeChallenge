package com.codechallenge.components

import com.codechallenge.dagger.CoreComponent
import com.codechallenge.dagger.FeatureScope
import com.codechallenge.ui.city.CityFragment
import com.codechallenge.ui.dashboard.DashboardActivity
import com.codechallenge.ui.dashboard.HomeFragment
import dagger.Component

@FeatureScope
@Component(dependencies = [CoreComponent::class])
interface RecordComponent {
    fun inject(dashboardActivity: DashboardActivity)
    fun inject(homeFragment: HomeFragment)
    fun inject(cityFragment: CityFragment)
}