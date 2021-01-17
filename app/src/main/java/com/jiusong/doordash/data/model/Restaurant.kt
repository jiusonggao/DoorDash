package com.jiusong.doordash.data.model

import androidx.annotation.Keep

/**
 * Created by jiusong.gao on 1/16/21.
 */
@Keep
data class Restaurant(val id: String,
                      val phone_number: String,
                      val offers_delivery: Boolean,
                      val delivery_fee: String,
                      val average_rating: Float,
                      val tags: List<String>,
                      val cover_img_url: String,
                      val header_image_url: String,
                      val business_id: Int,
                      val object_type: String,
                      val description: String,
                      val status_type: String,
                      val status: String,
                      val business: Business,
                      val address: Address)