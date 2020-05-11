package com.example.zvent.add_guest
import androidx.lifecycle.ViewModel
import com.example.zvent.models.Guest
/**
 * <h1>AddGuestViewModel</h1>
 *<p>
 * ViewModel of the AddGuest framgent
 *</p>
 *
 * @author Pablo Ruiz (PingMaster99)
 * @version 1.0
 * @since 2020-05-10
 **/
class AddGuestViewModel: ViewModel() {
    var addedGuest = Guest()    // Empty initial guest

    /**
     * Adds the guest by updating the original guest's attributes
     * @param name of the guest
     * @param phone
     * @param email
     */
    fun addGuest(name: String, phone: String, email: String) {
        addedGuest.name = name
        addedGuest.email = email
        addedGuest.phone = phone
    }
}