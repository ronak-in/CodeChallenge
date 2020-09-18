package com.codechallenge.ui.dashboard

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.codechallenge.R
import com.codechallenge.RecordApp
import com.codechallenge.ui.city.CityFragment
import com.codechallenge.ui.help.HelpFragment
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
        //getting the toolbar
        val toolbar: Toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        toolbar.title = getString(R.string.app_name)
        setSupportActionBar(toolbar)
    }

    fun openCityDetail(position: LatLng) {
        replaceFragmentWithBackStack(R.id.container) { CityFragment.newInstance(position) }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.getItemId()) {
            R.id.action_help -> {
                replaceFragmentWithBackStack(R.id.container) { HelpFragment.newInstance() }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
