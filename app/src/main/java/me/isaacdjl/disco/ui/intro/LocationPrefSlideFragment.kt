package me.isaacdjl.disco.ui.intro

import android.Manifest
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.heinrichreimersoftware.materialintro.app.SlideFragment
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_location_preference.*
import me.isaacdjl.disco.R
import me.isaacdjl.disco.ViewModelFactory
import javax.inject.Inject

/**
 * Fragment for second intro slide which retrieves data about user location preferences
 * (i.e. where they would like to start from when going out to restaurants)
 *
 * @author Isaac Jensen-Large
 */

class LocationPrefSlideFragment: SlideFragment(), OnMapReadyCallback {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var introViewModel: IntroViewModel

    var map: GoogleMap? = null

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        // Retrieve the viewModel
        introViewModel = ViewModelProviders.of(this, viewModelFactory).get(IntroViewModel::class.java)

        return inflater.inflate(R.layout.fragment_location_preference, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        locationPrefMapView.onCreate(savedInstanceState)

        locationPrefMapView.getMapAsync(this)
    }

    override fun canGoBackward(): Boolean {
        return false
    }

    /**
     * Possible source of NPE here with call to getActivity here, but it should only produce null
     * if this method were to be called before the onAttach, which I don't believe is possible
     * in this case
     */
    override fun onMapReady(googleMap: GoogleMap?) {
        map = googleMap
        map?.uiSettings?.isMyLocationButtonEnabled = false
        if (ContextCompat.checkSelfPermission(activity!!, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            map?.isMyLocationEnabled = true
        }
        locationPrefMapView.onResume()
    }
}