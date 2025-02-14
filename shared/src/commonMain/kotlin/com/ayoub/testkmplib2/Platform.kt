package com.ayoub.testkmplib2

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform