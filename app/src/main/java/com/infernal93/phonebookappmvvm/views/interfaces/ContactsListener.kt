package com.infernal93.phonebookappmvvm.views.interfaces

import com.infernal93.phonebookappmvvm.entity.Contacts

/**
 * Created by Armen Mkhitaryan on 03.01.2020.
 */
interface ContactsListener {

    fun setupContactsList(contactsList: ArrayList<Contacts>)
}