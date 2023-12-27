package com.pepperkim.baseframework.repo

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.pepperkim.baseframework.repo.mem.Auth
import com.pepperkim.baseframework.repo.mem.Setting
import com.pepperkim.baseframework.repo.mem.User
import kotlinx.coroutines.flow.Flow

object MemStore {
    val Auth = Auth()
    val Setting = Setting()
    val User = User()

    const val BASE_URL = "https://dapi.kakao.com"
}