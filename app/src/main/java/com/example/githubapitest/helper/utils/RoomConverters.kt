package com.example.githubapitest.helper.utils

import androidx.room.TypeConverter
import com.example.githubapitest.helper.extensions.stringToData
import com.example.githubapitest.model.users.License
import com.example.githubapitest.model.users.Owner
import com.example.githubapitest.model.users.Permissions
import com.google.gson.Gson

object RoomConverters {

    @TypeConverter
    @JvmStatic
    fun ownerToJson(me: Owner?): String = Gson().toJson(me)

    @TypeConverter
    @JvmStatic
    fun jsonToOwner(me: String?): Owner? = Gson().stringToData(me)

    @TypeConverter
    @JvmStatic
    fun permissionsToJson(me: Permissions?): String = Gson().toJson(me)

    @TypeConverter
    @JvmStatic
    fun jsonToPermissions(me: String?): Permissions? = Gson().fromJson(me, Permissions::class.java)

    @TypeConverter
    @JvmStatic
    fun licenseToJson(me: License?): String = Gson().toJson(me)

    @TypeConverter
    @JvmStatic
    fun jsonToLicense(me: String?): License? = Gson().stringToData(me)


}