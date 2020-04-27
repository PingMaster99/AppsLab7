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
 **/
data class Guest(var name: String = "", var phone: String = "1234-4332",
                 var email: String = "example@example.com", var registered: String = "no" )