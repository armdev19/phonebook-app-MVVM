package com.infernal93.phonebookappmvvm.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Contacts(

    @SerializedName("firstName")
    val firstName: String,
    @SerializedName("lastName")
    val lastName: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("notes")
    val notes: String,
    @SerializedName("images")
    val images: String?
) : Serializable