package com.jiusong.doordash.data.model

import androidx.annotation.Keep

/**
 * Created by jiusong.gao on 1/16/21.
 */
@Keep
data class Address(val city: String,
                   val id: String,
                   val printable_address: String,
                   val state: String,
                   val street: String,
                   val country: String,
                   val lat: String,
                   val lng: String,
                   val shortname: String,
                   val zip_code: String)