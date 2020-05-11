package com.example.zvent.results

import androidx.lifecycle.ViewModel
/**
 * <h1>ResultsViewModel</h1>
 *<p>
 * ViewModel of the Results fragment
 *</p>
 *
 * @author Pablo Ruiz (PingMaster99)
 * @version 1.0
 * @since 2020-05-10
 **/
class ResultsViewModel: ViewModel() {
    var numberOfGuests = 0  // Number of guests
    var registered = 0      // Registered guests
    var guestString = ""    // Guests in a formatted string
}