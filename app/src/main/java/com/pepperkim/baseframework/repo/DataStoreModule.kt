package com.pepperkim.baseframework.repo

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class DataStoreModule(private val context:Context) {
    private val Context.datastore by preferencesDataStore(name = "datastore")

    private val user_id = stringPreferencesKey("user_id")
    private val service_token = stringPreferencesKey("service_token")

    private val user_age = intPreferencesKey("user_age")

    private val auto_login = booleanPreferencesKey("auto_login")

    //get
    //string
    fun getUserID() = getStringData(user_id)
    fun getServiceToken() = getStringData(service_token)

    //int
    fun getUserAge() = getIntData(user_age)

    //boolean
    fun getAutoLogin() = getBooleanData(auto_login)

    // set
    // string
    suspend fun setUserId(text : String) = context.datastore.edit {  it[user_id] = text }
    suspend fun setServiceToken(text : String) = context.datastore.edit {  it[service_token] = text }

    // int
    suspend fun setUserAge(num : Int) = context.datastore.edit {  it[user_age] = num }

    //boolean
    suspend fun setAutoLogin(bool : Boolean) = context.datastore.edit {  it[auto_login] = bool }


    private fun getStringData(param: Preferences.Key<String>) = context.datastore.data
            .catch { exception ->
                if(exception is IOException){
                    emit(emptyPreferences())
                }else{
                    throw exception
                }
            }
            .map { preferences ->
                preferences[param] ?: ""
            }

    private fun getIntData(param: Preferences.Key<Int>) = context.datastore.data
        .catch { exception ->
            if(exception is IOException){
                emit(emptyPreferences())
            }else{
                throw exception
            }
        }
        .map { preferences ->
            preferences[param] ?: ""
        }

    private fun getBooleanData(param: Preferences.Key<Boolean>) = context.datastore.data
        .catch { exception ->
            if(exception is IOException){
                emit(emptyPreferences())
            }else{
                throw exception
            }
        }
        .map { preferences ->
            preferences[param] ?: ""
        }
}