package com.spiritflightapps.capitalsflashcards

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import org.jetbrains.anko.alert
import org.jetbrains.anko.toast
import org.jetbrains.anko.yesButton

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {

        //

        //
        mMap = googleMap

        // Add a marker in donna and move the camera
        val donna = LatLng(26.17, -98.05)
        mMap.setMaxZoomPreference(4.0f)
        mMap.moveCamera(CameraUpdateFactory.newLatLng(donna))
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(donna, 13f))
        val cameraPosition = CameraPosition.Builder()
                .target(donna)      // Sets the center of the map to location user
                .zoom(4.3f)                   // Sets the zoom;
                .bearing(0f)                // Sets the orientation of the camera to north
                .tilt(40f)                   // Sets the tilt of the camera to 30 degrees
                .build()                   // Creates a CameraPosition from the builder
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
        mMap.uiSettings.isMapToolbarEnabled = true
        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.uiSettings.setAllGesturesEnabled(true)
        mMap.uiSettings.isMyLocationButtonEnabled = true


        addMarkers()
        mMap.mapType = GoogleMap.MAP_TYPE_HYBRID



    }

    fun addMarkers() {
        // set markers, can use builder, see
        // https://developers.google.com/maps/documentation/android-api/map

        mMap.addMarker(MarkerOptions().position(LatLng(32.34,86.31)).title("Montgomery 240'"))
        //mMap.addMarker(MarkerOptions().position(LatLng(32.7,-92.4)).title("Little Rock pop 198,541 lev 335'"))
        mMap.addMarker(MarkerOptions().position(LatLng(32.7,-92.4)).title("Little Rock 335'"))

        //mMap.addMarker(MarkerOptions().position(LatLng(30.2,-97.7)).title("Austin pop 947,890\nelev 489'"))
        mMap.addMarker(MarkerOptions().position(LatLng(30.2,-97.7)).title("Austin 489'"))

        mMap.addMarker(MarkerOptions().position(LatLng(39.01,-95.85)).title("Topeka"))

        //TODO: FIXME before release
        // Also fix screenshots to have the rest of the capitals!!! and the zoom in the northeast somehow?
        // NOTE: the list could be the harder quiz, the map could be the easier funner one!!!
        // eg dont worry aout the zoom issue, it's like a little hint, these aren't it...


        alert("Happy Anniversary!  love you mucho!", "My lovely Carina!") {
            yesButton { toast("You're the best!…") }
        }.show()
    }
}
