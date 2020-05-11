package com.example.zvent.models
/**
 * <h1>Guest</h1>
 *<p>
 * Data class that contains the template of a guest
 *</p>
 *
 * @author Pablo Ruiz (PingMaster99)
 * @version 1.0
 * @since 2020-04-26
 * updated 2020-05-10
 **/
data class Guest(var name: String = "", var phone: String = "",
                 var email: String = "", var registered: String = "no" )