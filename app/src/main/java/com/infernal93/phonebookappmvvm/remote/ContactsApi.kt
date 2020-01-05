package com.infernal93.phonebookappmvvm.remote

import com.infernal93.phonebookappmvvm.entity.Contacts
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by Armen Mkhitaryan on 03.01.2020.
 */
interface ContactsApi {

    @GET(value = "contacts")

    fun fetchAllContacts() : Call<List<Contacts>>
}