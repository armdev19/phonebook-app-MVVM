package com.infernal93.phonebookappmvvm.views.activities

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import com.infernal93.phonebookappmvvm.R
import com.infernal93.phonebookappmvvm.databinding.ActivityContactsBinding
import com.infernal93.phonebookappmvvm.entity.Contacts
import com.infernal93.phonebookappmvvm.viewmodels.ContactsViewModel
import com.infernal93.phonebookappmvvm.views.adapters.ContactsAdapter
import com.infernal93.phonebookappmvvm.views.interfaces.ContactsListener
import kotlinx.android.synthetic.main.activity_contacts.*


class ContactsActivity : AppCompatActivity(), ContactsListener {
    private val TAG = "ContactsActivity"

    private lateinit var mAdapter: ContactsAdapter

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_contacts)

        val binding: ActivityContactsBinding =
            DataBindingUtil.setContentView(this@ContactsActivity, R.layout.activity_contacts)
        val categoryViewModel: ContactsViewModel =
            ViewModelProviders.of(this@ContactsActivity).get(ContactsViewModel::class.java)

        binding.contactViewModel = categoryViewModel


        categoryViewModel.getContactsList().observe(this@ContactsActivity, Observer {

            mAdapter = ContactsAdapter(this@ContactsActivity, it)
            recycler_contacts.layoutManager =
                LinearLayoutManager(applicationContext, OrientationHelper.VERTICAL, false)
            recycler_contacts.adapter = mAdapter
            recycler_contacts.setHasFixedSize(true)


        })
    }

    override fun setupContactsList(contactsList: ArrayList<Contacts>) {

        mAdapter.setupContacts(contactsList = contactsList)
    }
}
