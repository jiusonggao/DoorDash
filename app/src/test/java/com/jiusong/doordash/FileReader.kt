package com.jiusong.doordash

import java.io.InputStreamReader

/**
 * Created by jiusong.gao on 1/16/21.
 */
class FileReader(path: String) {

    val content: String

    init {
        val reader = InputStreamReader(javaClass.classLoader!!.getResourceAsStream(path))
        content = reader.readText()
        reader.close()
    }
}