package com.spiritflightapps.capitalsflashcards

import com.google.android.gms.maps.model.LatLng

/**
 * Created by neil on 8/17/17.
 */
data class City (val name : String, val state : String, val latLong : LatLng, val elevation : Int  = 0)