package com.jiusong.doordash.data.model

import androidx.annotation.Keep

/**
 * Created by jiusong.gao on 1/16/21.
 */
@Keep
data class Business(val business_vertical: String,
                    val id: Int,
                    val name: String)