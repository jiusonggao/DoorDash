package com.jiusong.doordash.data.model

import androidx.annotation.Keep

/**
 * Created by jiusong.gao on 1/16/21.
 */
@Keep
data class Item(val id: String,
                val price: String,
                val img_url: String,
                val name: String,
                val description: String
)