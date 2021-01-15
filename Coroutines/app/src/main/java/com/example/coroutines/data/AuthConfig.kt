package com.example.coroutines.data

import net.openid.appauth.ResponseTypeValues

object AuthConfig {

    const val AUTH_URI = "https://github.com/login/oauth/authorize"
    const val TOKEN_URI = "https://github.com/login/oauth/access_token"
    const val RESPONSE_TYPE = ResponseTypeValues.CODE
    const val SCOPE = "user,repo"

    const val CLIENT_ID = "3e3e6941be4caa461e60"
    const val CLIENT_SECRET = "f375c1f6f713c31160e374b2d83eaff3d8577360"
    const val CALLBACK_URL = "skillbox://skillbox.ru/callback"
}