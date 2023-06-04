package com.jiusong.doordash.data.model

import androidx.annotation.Keep

/**
 * Created by jiusong.gao on 1/15/21.
 */
@Keep
data class Status(val unavailable_reason: String,
                  val pickup_available: Boolean,
                  val asap_available: Boolean,
                  val scheduled_available: Boolean,
                  val asap_minutes_range: List<Int>)