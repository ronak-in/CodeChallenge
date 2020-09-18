package com.codechallenge.ui.city

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.codechallenge.RecordApp
import com.codechallenge.databinding.FragmentCityBinding
import com.codechallenge.interfaces.IWeatherService
import com.codechallenge.repository.LocalRepository
import com.google.android.gms.maps.model.LatLng
import javax.inject.Inject


class CityFragment : Fragment() {

    private val TAG = javaClass.simpleName
    var position: LatLng? = null

    @Inject
    lateinit var localRepository: LocalRepository

    @Inject
    lateinit var mediaService: IWeatherService


    lateinit var cityViewModel: CityViewModel
    lateinit var binding: FragmentCityBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity!!.applicationContext as RecordApp)
            .getRecordComponent()
            .inject(this)
        if (arguments != null) {
            position = arguments!!.getParcelable("data")
        }
        Log.d(TAG, "onAttach: ")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        cityViewModel = CityViewModel(position!!, mediaService, localRepository)
        binding = FragmentCityBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.cityViewModel = cityViewModel
        cityViewModel.weatherData.observe(viewLifecycleOwner, Observer { t ->

        })
        cityViewModel.init()
        return binding.root
    }

    companion object {
        fun newInstance(position: LatLng): CityFragment {
            val fragment = CityFragment()
            val args = Bundle()
            args.putParcelable("data", position)
            fragment.arguments = args
            return fragment
        }
    }


}