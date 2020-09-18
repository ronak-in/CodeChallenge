package com.codechallenge.ui.dashboard

import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.graphics.Color
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.codechallenge.R
import com.codechallenge.RecordApp
import com.codechallenge.interfaces.IOnComplete
import com.codechallenge.interfaces.IOnCompleteData
import com.codechallenge.model.UserDefinedLocations
import com.codechallenge.repository.LocalRepository
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.SnackbarLayout
import java.util.*
import javax.inject.Inject


class HomeFragment : Fragment(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener,
    GoogleMap.OnMapLongClickListener {

    private val TAG = javaClass.simpleName
    private lateinit var mMap: GoogleMap

    @Inject
    lateinit var localRepository: LocalRepository
    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity!!.applicationContext as RecordApp)
            .getRecordComponent()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_home, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (getString(R.string.maps_api_key).isEmpty()) {
            Toast.makeText(
                activity,
                "ADD YOUR API KEY",
                Toast.LENGTH_LONG
            ).show()
        }
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = childFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        // Add a marker in India and move the camera
        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.setOnMapLongClickListener(this)
        mMap.setOnMarkerClickListener(this)
        localRepository.getUserDefinedAllItem(object :
            IOnCompleteData {
            override fun onComplete(list: List<UserDefinedLocations>) {
                if (list.isNotEmpty()) {
                    val myLocation = LatLng(list[0].lat, list[0].longitude)
                    mMap.addMarker(MarkerOptions().position(myLocation).title(list[0].address))
                    mMap.animateCamera(
                        CameraUpdateFactory.newLatLngZoom(
                            myLocation, 8.0f
                        )
                    )
                } else {
                    val myLocation = LatLng(23.0225, 72.5714)
                    mMap.addMarker(MarkerOptions().position(myLocation).title("Ahmedabad"))
                    mMap.animateCamera(
                        CameraUpdateFactory.newLatLngZoom(
                            myLocation, 8.0f
                        )
                    )
                }
                for (model in list) {
                    mMap.addMarker(
                        MarkerOptions()
                            .position(LatLng(model.lat, model.longitude))
                            .title(model.address)
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                    )
                }
            }
        })

    }

    override fun onMapLongClick(point: LatLng?) {
        val geocoder = Geocoder(context, Locale.getDefault())
        val addresses: List<Address> =
            geocoder.getFromLocation(point!!.latitude, point!!.longitude, 1)
        val address: String = addresses[0].getAddressLine(0)
        mMap.addMarker(
            point.let {
                MarkerOptions()
                    .position(it)
                    .title(address)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
            }
        )
        localRepository.insertUserDefinedLocationData(
            UserDefinedLocations(
                System.currentTimeMillis(),
                point.latitude,
                point.longitude,
                address
            ), object :
                IOnComplete {
                override fun onComplete() {
                    val snackBar = Snackbar.make(
                        view!!, getString(R.string.location_bookmark),
                        Snackbar.LENGTH_LONG
                    ).setAction("", null)
                    snackBar.setActionTextColor(Color.BLUE)
                    val snackBarView = snackBar.view
                    snackBarView.setBackgroundColor(Color.RED)
                    val textView =
                        snackBarView.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
                    textView.setTextColor(Color.WHITE)
                    snackBar.show()
                }
            })
    }

    override fun onMarkerClick(p0: Marker?): Boolean {
        // Create the Snackbar
        val objLayoutParams: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        val snackbar = Snackbar.make(view!!, "", Snackbar.LENGTH_LONG)
        // Get the Snackbar's layout view
        // Get the Snackbar's layout view
        val layout = snackbar.view as SnackbarLayout
        layout.setPadding(0, 0, 0, 0)
        // Hide the text
        // Hide the text
        val textView =
            layout.findViewById<View>(R.id.snackbar_text) as TextView
        textView.visibility = View.INVISIBLE

        val mInflater = activity!!.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        // Inflate our custom view
        // Inflate our custom view
        val snackView = layoutInflater.inflate(R.layout.my_snackbar, null)
        // Configure the view
        // Configure the view
        val textViewOne =
            snackView.findViewById<View>(R.id.txtOne) as TextView

        textViewOne.setOnClickListener {
            snackbar.dismiss()
            (activity as DashboardActivity).openCityDetail(p0!!.position)
        }

        val textViewTwo =
            snackView.findViewById<View>(R.id.txtTwo) as TextView
        textViewTwo.setOnClickListener {
            snackbar.dismiss()
            localRepository.deleteUserDefinedLocationData(
                p0!!.position.latitude,
                p0.position.longitude
            )
            p0.remove()
        }

        // Add the view to the Snackbar's layout

        // Add the view to the Snackbar's layout
        layout.addView(snackView, objLayoutParams)
        // Show the Snackbar
        // Show the Snackbar
        snackbar.show()
        return false
    }

}