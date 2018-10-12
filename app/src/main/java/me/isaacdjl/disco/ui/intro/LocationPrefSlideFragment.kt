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
import com.google.android.gms.common.api.Status
import com.google.android.gms.location.places.Place
import com.google.android.gms.location.places.ui.PlaceSelectionListener
import com.google.android.gms.location.places.ui.SupportPlaceAutocompleteFragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.heinrichreimersoftware.materialintro.app.SlideFragment
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_location_preference.*
import me.isaacdjl.disco.R
import me.isaacdjl.disco.ViewModelFactory
import javax.inject.Inject

/**
 * Slide Fragment which retrieves data about user location preferences
 * (i.e. where they would like to start from when going out to restaurants)
 *
 * @author Isaac Jensen-Large
 */

class LocationPrefSlideFragment: SlideFragment(), OnMapReadyCallback, PlaceSelectionListener {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var introViewModel: IntroViewModel

    var currentUserMarker: Marker? = null

    var map: GoogleMap? = null

    var mapIsReady: Boolean = false;

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    /**
     * Possible source of NPE. See the whart's explanation for this on reddit.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        introViewModel = ViewModelProviders.of(requireActivity(), viewModelFactory).get(IntroViewModel::class.java)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        return inflater.inflate(R.layout.fragment_location_preference, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        locationPrefMapView.onCreate(savedInstanceState)

        locationPrefMapView.getMapAsync(this)

        var locationPrefAutocompleteFragment = childFragmentManager.findFragmentByTag("locationPrefAutocompleteFragment") as SupportPlaceAutocompleteFragment?

        if (locationPrefAutocompleteFragment == null) {
            locationPrefAutocompleteFragment = SupportPlaceAutocompleteFragment()
            val ft = childFragmentManager.beginTransaction()
            ft.add(R.id.locationPrefPlacesAutocompleteFragContainer, locationPrefAutocompleteFragment, "locationPrefAutocompleteFragment")
            ft.commit()
            childFragmentManager.executePendingTransactions()
        }

        locationPrefAutocompleteFragment.setOnPlaceSelectedListener(this)
    }

    /**
     * Possible source of NPE here with call to getActivity, but it should only produce null
     * if this method were to be called before the onAttach, which I don't believe is possible
     * in this case
     *
     * Another possible source of NPE with the map!! but it shouldn't get shut down that fast. I
     * guess there are some edge cases where the user doesn't have enough memory to load the map
     * and it gets taken down by android, but if that's the case, then the user shouldn't be using
     * the app in the first place
     */
    override fun onMapReady(googleMap: GoogleMap?) {
        map = googleMap
        map?.uiSettings?.isMyLocationButtonEnabled = false
        if (ContextCompat.checkSelfPermission(activity!!, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            map?.isMyLocationEnabled = true
        }
        locationPrefMapView.onResume()

        map?.setOnMapClickListener { location ->
            if (null != location) {
                if (null != currentUserMarker) {
                    currentUserMarker?.remove()
                }
                currentUserMarker = map?.addMarker(MarkerOptions()
                        .position(location))
                introViewModel.changeUserLocationPreference(currentUserMarker!!.position)
            }
        }

        map?.setOnCameraIdleListener {
            introViewModel.changeUserCameraPosition(map!!.cameraPosition)
        }

        // Check if the user already has preferences that they are coming back to see
        if (introViewModel.userHasCameraPosition()) {
            map?.moveCamera(CameraUpdateFactory.newCameraPosition(introViewModel.retrieveUserCameraPosition()))
        }

        if (introViewModel.userHasLocationPreference()) {
            map?.addMarker(MarkerOptions().position(introViewModel.retrieveUserLocationPreference()))
        }

        mapIsReady = true
    }

    override fun onPlaceSelected(place: Place?) {
        if (mapIsReady && null != place) {
            map?.moveCamera(CameraUpdateFactory.newLatLngZoom(place.latLng, 15.toFloat()))
            if (null != currentUserMarker) {
                currentUserMarker?.remove()
            }
            currentUserMarker = map?.addMarker(MarkerOptions()
                    .position(place.latLng))
            introViewModel.changeUserLocationPreference(currentUserMarker!!.position)
            introViewModel.changeUserCameraPosition(map!!.cameraPosition)
        }
    }

    /**
     * Place selection listener method
     *
     * Would normally displayer alert dialog, but fragment seems to have trouble with that since I
     * finished the base activity in this case
     *
     * When you get the api going, you should send a message to the server if the user receives
     * a message saying the api key is expired or invalid
     */
    override fun onError(place: Status?) {
    }

    override fun canGoForward(): Boolean = (::introViewModel.isInitialized && introViewModel.userHasLocationPreference())
}