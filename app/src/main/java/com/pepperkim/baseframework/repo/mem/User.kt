package com.pepperkim.baseframework.repo.mem

import com.pepperkim.baseframework.api.model.Login

class User {
    var userAccount: String = ""
        private set
    var robotUserId: Long = 0
        private set
    var userName: String = ""
        private set

    fun updateUserInfo(user: Login.Response) {
        with(user) {
            userAccount = userId
            userName = userNm
            robotUserId = robotUserSeq
        }
    }
}