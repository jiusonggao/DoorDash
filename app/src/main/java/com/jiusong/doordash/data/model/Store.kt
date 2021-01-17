package com.jiusong.doordash.data.model

import androidx.annotation.Keep

/**
 * Created by jiusong.gao on 1/15/21.
 */
@Keep
data class Store (val id: String,
                  val name: String,
                  val location: Location,
                  val status: Status,
                  val description: String,
                  val cover_img_url: String,
                  val header_img_url: String,
                  val next_close_time: String,
                  val next_open_time: String,
                  val menus: List<Menu>,
                  val average_rating: Float,
                  val distance_from_consumer: String)