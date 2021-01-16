package com.jiusong.doordash.data.model

import androidx.annotation.Keep

/**
 * Created by jiusong.gao on 1/15/21.
 */
@Keep
data class StoreFeedResponse(val num_results: Int,
                             val is_first_time_user: Boolean,
                             val sort_order: String,
                             val next_offset: Int,
                             val show_list_as_pickup: Boolean,
                             val stores: List<Store>)