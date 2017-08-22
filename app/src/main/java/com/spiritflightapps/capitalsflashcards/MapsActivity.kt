package com.spiritflightapps.capitalsflashcards

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

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
import java.util.*

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
        mMap.setMaxZoomPreference(5.1f)
        mMap.moveCamera(CameraUpdateFactory.newLatLng(donna))
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(donna, 13f))
        val cameraPosition = CameraPosition.Builder()
                .target(donna)      // Sets the center of the map to location user
                .zoom(4.8f)                   // Sets the zoom;
                .bearing(0f)                // Sets the orientation of the camera to north
                .tilt(40f)                   // Sets the tilt of the camera to 30 degrees
                .build()                   // Creates a CameraPosition from the builder
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
        mMap.uiSettings.isMapToolbarEnabled = true
        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.uiSettings.setAllGesturesEnabled(true)
        mMap.uiSettings.isMyLocationButtonEnabled = true


        addMarkers()
        mMap.mapType = GoogleMap.MAP_TYPE_NORMAL // hybrid didn't have the lines several asked for.


    }

    fun addMarkers() {
        // set markers, can use bder, see
        // https://developers.google.com/maps/documentation/android-api/map

        //mMap.addMarker(MarkerOptions().position(LatLng(32.34,86.31)).title("Montgomery 240'"))
        //mMap.addMarker(MarkerOptions().position(LatLng(32.7,-92.4)).title("Little Rock pop 198,541 lev 335'"))
        //mMap.addMarker(MarkerOptions().position(LatLng(32.7,-92.4)).title("Little Rock 335'"))

        //mMap.addMarker(MarkerOptions().position(LatLng(30.2,-97.7)).title("Austin pop 947,890\nelev 489'"))
        //mMap.addMarker(MarkerOptions().position(LatLng(30.2,-97.7)).title("Austin 489'"))

        //mMap.addMarker(MarkerOptions().position(LatLng(39.01,-95.85)).title("Topeka"))

        //TODO: maybe fix
        // Also fix screenshots to have the rest of the capitals!!! and the zoom in the northeast somehow?
        // NOTE: the list could be the harder quiz, the map could be the easier funner one!!!
        // eg dont worry aout the zoom issue, it's like a little hint, these aren't it...


        val cities = addCities()
        cities.forEach {
            mMap.addMarker(MarkerOptions().position(it.latLong).title(it.name));
        }

        //todo: see https://stackoverflow.com/questions/13904651/android-google-maps-v2-how-to-add-marker-with-multiline-snippet
        // make an easy way to show the state, etc.

    }

    fun addCities(): ArrayList<City> {
        val cities: ArrayList<City> = ArrayList<City>();
        cities.add(City("Montgomery", "Alabama", LatLng(32.34, -86.31)))
        cities.add(City("Juneau", "Alaska", LatLng(58.3801046, -135.3213959)))
        cities.add(City("Phoenix", "Arizona", LatLng(33.6050976, -112.4059231)))
        cities.add(City("Little Rock", "Arkansas", LatLng(34.7239848, -92.4081391)))
        cities.add(City("Sacramento", "California", LatLng(38.5614566, -121.5833395)))

        cities.add(City("Denver", "Colorado", LatLng(39.7642543, -104.9955383)))
        cities.add(City("Hartford", "Connecticut", LatLng(41.7656821, -72.7151922)))
        cities.add(City("Dover", "Delaware", LatLng(39.1563948, -75.5836314)))


        //NOTE: google's zoom levels in urls change when you put it into maps, perhaps i could use redirects
// to figure out what level to zoom or something depending on small state...
                /*
        cities.add(City("Tallahassee", "Florida", LatLng(3,-)))
        cities.add(City("Atlanta", "Georgia", LatLng(3,-)))


        // thisk is the first time i wish i had isaiah my11 year old here who likes to sing a song about the 50nifty states



        cities.add(City("Honolulu", "Hawaii", LatLng(3,-)))
        cities.add(City("Boise City", "Idaho", LatLng(3,-)))
        cities.add(City("Springfield", "Illinio", LatLng(3,-)))
        cities.add(City("Indianapolis", "Indiana", LatLng(3,-)))
        cities.add(City("Des Moines", "Iowa", LatLng(3,-)))


        cities.add(City("Topeka", "Kansas", LatLng(39.01,-95.85)))

        cities.add(City("Frankford", "Kentucy", LatLng(3,-)))
        cities.add(City("Baton Rouge", "Louisiana", LatLng(3,-)))
        cities.add(City("Augusta", "Maine", LatLng(3,-)))
        cities.add(City("Annapolis", "Maryland", LatLng(3,-)))

        cities.add(City("Boston", "Massachusetts", LatLng(3,-)))
        cities.add(City("Lansing", "Michigan", LatLng(3,-)))
        cities.add(City("St Paul", "Minnesota", LatLng(3,-)))
        cities.add(City("Jackson", "Mississippi", LatLng(3,-)))
        cities.add(City("Jefferson City", "Missouri", LatLng(3,-)))


        cities.add(City("", "Montana", LatLng(3,-)))
        cities.add(City("", "Hebraska", LatLng(3,-)))
        cities.add(City("", "Nevada", LatLng(3,-)))
        cities.add(City("", "New Hampshire", LatLng(3,-)))
        cities.add(City("", "New Jersey", LatLng(3,-)))

        cities.add(City("", "", LatLng(3,-)))
        cities.add(City("", "", LatLng(3,-)))
        cities.add(City("", "", LatLng(3,-)))
        cities.add(City("", "", LatLng(3,-)))
        cities.add(City("", "", LatLng(3,-)))

        cities.add(City("", "", LatLng(3,-)))
        cities.add(City("", "", LatLng(3,-)))
        cities.add(City("", "", LatLng(3,-)))
        cities.add(City("", "", LatLng(3,-)))
        cities.add(City("", "", LatLng(3,-)))

        cities.add(City("", "", LatLng(3,-)))
        cities.add(City("", "", LatLng(3,-)))
        cities.add(City("", "", LatLng(3,-)))
        cities.add(City("", "", LatLng(3,-)))
        cities.add(City("", "", LatLng(3,-)))

        cities.add(City("", "", LatLng(3,-)))
        cities.add(City("", "", LatLng(3,-)))
        cities.add(City("", "", LatLng(3,-)))
        cities.add(City("", "", LatLng(3,-)))
        cities.add(City("", "", LatLng(3,-)))
*/











        return cities
    }

}
