package com.space.data.model

data class LoginIntranetModel(
    var _token: String? = null,
    val id: String? = null,
    val pw: String? = null
){
    fun setToken(token: String){
        this._token = token
    }
}
