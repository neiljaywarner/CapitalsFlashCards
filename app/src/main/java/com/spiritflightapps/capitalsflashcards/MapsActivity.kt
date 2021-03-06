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
import org.jetbrains.anko.browse
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

        // center on okc for now
        val okc = LatLng(35.4825666,-97.6196248)
        //mMap.setMaxZoomPreference(6.1f)
        mMap.moveCamera(CameraUpdateFactory.newLatLng(okc))
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(okc, 13f))
        val cameraPosition = CameraPosition.Builder()
                .target(okc)      // Sets the center of the map to location user
                .zoom(4.7f)                   // Sets the zoom;
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
            mMap.addMarker(MarkerOptions().position(it.latLong).title(it.name)).tag = it
        }

        //todo: see https://stackoverflow.com/questions/13904651/android-google-maps-v2-how-to-add-marker-with-multiline-snippet
        // make an easy way to show the state, etc.


        // TODO: Links to open info in wikipedia when you tap on it
        // https://en.wikipedia.org/wiki/Saint_Paul,_Minnesota
       //  https://en.wikipedia.org/wiki/Montgomery,_Alabama


        mMap.setOnInfoWindowClickListener {
            val city = it.tag as City
            val link = "https://en.wikipedia.org/wiki/" + city.name.replace(" ", "_") + ",_" + city.state.replace(" ", "_")
            // TODO: Kotlinify
            browse(link)
        }

        // TOD: GIve social ability for teacher app, for teacher's to add info via firebase real time db :)
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
        cities.add(City("Tallahassee", "Florida", LatLng(30.4671207,-84.3270675)))
        cities.add(City("Atlanta", "Georgia", LatLng(33.7676334,-84.5610314)))


        //NOTE: google's zoom levels in urls change when you put it into maps, perhaps i could use redirects
// to figure out what level to zoom or something depending on small state... (the redirect is veyr quick)



        // thisk is the first time i wish i had isaiah my11 year old here who likes to sing a song about the 50nifty states



        cities.add(City("Honolulu", "Hawaii", LatLng(21.3280192,-157.8692849)))
        cities.add(City("Boise City", "Idaho", LatLng(43.6007846,-116.3041093)))
        cities.add(City("Springfield", "Illinois", LatLng(39.7637528,-89.8112582)))
        cities.add(City("Indianapolis", "Indiana", LatLng(39.7796999,-86.2731768)))
        cities.add(City("Des Moines", "Iowa", LatLng(41.5666486,-93.6767274)))


        cities.add(City("Topeka", "Kansas", LatLng(39.01,-95.85)))
        cities.add(City("Frankford", "Kentucky", LatLng(38.1944403,-84.9017307)))
        cities.add(City("Baton Rouge", "Louisiana", LatLng(30.4413988,-91.2518464)))
        cities.add(City("Augusta", "Maine", LatLng(44.3334104,-69.8009034)))
        cities.add(City("Annapolis", "Maryland", LatLng(38.9724637,-76.5397997)))

        cities.add(City("Boston", "Massachusetts", LatLng(42.3142643,-71.1107105)))
        cities.add(City("Lansing", "Michigan", LatLng(42.7086601,-84.6296389)))
        cities.add(City("St Paul", "Minnesota", LatLng(44.939686,-93.1762647)))
        cities.add(City("Jackson", "Mississippi", LatLng(32.310309,-90.2590991)))
        cities.add(City("Jefferson City", "Missouri", LatLng(38.5711449,-92.2326164)))


        // very odd maps.google.com url is wrong, weird.
        //cities.add(City("Helena", "Montana", LatLng(46.5933526,-116.4982019)))
        cities.add(City("Helena", "Montana", LatLng(46.5884,-112.0245)))

        cities.add(City("Lincoln", "Nebraska", LatLng(40.8005877,-96.7609397)))
        cities.add(City("Carson City", "Nevada", LatLng(39.1677492,-119.9167539)))
        cities.add(City("Concord", "New Hampshire", LatLng(43.23078,-71.6328168)))
        cities.add(City("Trenton", "New Jersey", LatLng(40.2160482,-74.8093107)))

        cities.add(City("Santa Fe", "New Mexico", LatLng(35.682473,-106.053247)))
        cities.add(City("Albany", "New York", LatLng(42.668063,-73.8808924)))
        cities.add(City("Raleigh", "North Carolina", LatLng(35.8436863,-78.7854832)))
        cities.add(City("Bismarck", "North Dakota", LatLng(46.8090545,-100.8372658)))
        cities.add(City("Columbus", "Ohio", LatLng(39.9828667,-83.1312558)))

        cities.add(City("Oklahoma City", "Oklahoma", LatLng(35.4825666,-97.6196248)))
        cities.add(City("Salem", "Oregon", LatLng(44.9329699,-123.0984187)))
        cities.add(City("Harrisburg", "Pennsylvania", LatLng(40.2821392,-76.9155307)))
        cities.add(City("Providence", "Rhode Island", LatLng(41.8169872,-71.4562857)))
        cities.add(City("Columbia", "South Carolina", LatLng(34.0374291,-81.0779924)))

        cities.add(City("Pierre", "South Dakota", LatLng(44.3707626,-100.3556437)))
        cities.add(City("Nashville", "Tennessee", LatLng(36.1865585,-86.9256723)))
        cities.add(City("Austin", "Texas", LatLng(30.3076859,-97.8938288)))
        cities.add(City("Salt Lake City", "Utah", LatLng(40.7765867,-111.9906964)))
        cities.add(City("Montpelier", "Vermont", LatLng(44.2741865,-72.6038251)))

        cities.add(City("Richmond", "Virginia", LatLng(37.5246402,-77.5634729)))
        cities.add(City("Olympia", "Washington", LatLng(47.0393281,-122.9289738)))
        cities.add(City("Charleston", "West Virginia", LatLng(38.343428,-81.7137549)))
        cities.add(City("Madison", "Wisconsin", LatLng(43.084972,-89.4766317)))
        cities.add(City("Cheyenne", "Wyoming", LatLng(41.1475272,-104.8025098)))












        return cities
    }

}
