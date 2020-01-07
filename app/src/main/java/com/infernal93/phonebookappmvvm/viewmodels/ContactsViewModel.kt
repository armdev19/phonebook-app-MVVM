package com.infernal93.phonebookappmvvm.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.infernal93.phonebookappmvvm.entity.Contacts
import com.infernal93.phonebookappmvvm.remote.ContactsClient

/**
 * Created by Armen Mkhitaryan on 03.01.2020.
 */

class ContactsViewModel : ViewModel() {

    var conta: Contacts? = null

    fun getContactsList(): MutableLiveData<ArrayList<Contacts>> {

        return ContactsClient().loadContacts()
    }

    fun insertItem(contacts: Contacts): Contacts {
        conta = contacts
        return conta as Contacts
    }

    fun add(): Contacts? {
        return conta
    }

}