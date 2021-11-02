package com.rq.networkrequestcallback

import java.lang.Exception

interface HttpCallbackListener {

    fun onFinish(response : String)
    fun onError(e: Exception)
}