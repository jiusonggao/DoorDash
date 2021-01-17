package com.jiusong.doordash.data.model

import androidx.annotation.Keep

/**
 * Created by jiusong.gao on 1/16/21.
 */
@Keep
data class Menu(val id: String,
                val name: String,
                val subtitle: String,
                val is_catering: Boolean,
                val popular_items: List<Item>)