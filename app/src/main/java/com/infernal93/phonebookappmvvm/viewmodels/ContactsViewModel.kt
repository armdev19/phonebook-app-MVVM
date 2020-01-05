package com.infernal93.phonebookappmvvm.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.infernal93.phonebookappmvvm.entity.Contacts
import com.infernal93.phonebookappmvvm.remote.ContactsClient

/**
 * Created by Armen Mkhitaryan on 03.01.2020.
 */

class ContactsViewModel : ViewModel() {


    fun getContactsList(): MutableLiveData<ArrayList<Contacts>> {

        return ContactsClient().loadContacts()
    }
}