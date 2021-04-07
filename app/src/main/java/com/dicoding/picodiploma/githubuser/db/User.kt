package com.dicoding.picodiploma.githubuser.db

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val username: String?,
    val name: String?,
    val location: String?,
    val repository: String?,
    val company: String?,
    val follower: Int?,
    val following: Int?,
    val avatar: Int?
) : Parcelable