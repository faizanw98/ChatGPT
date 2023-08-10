package com.devfaiz.chatgpt

import android.app.Application
import android.text.format.DateUtils

class Helper:Application() {
    var time = DateUtils.formatDateTime(applicationContext,System.currentTimeMillis(),0)
}