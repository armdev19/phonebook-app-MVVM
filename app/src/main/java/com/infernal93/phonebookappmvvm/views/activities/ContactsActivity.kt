package com.infernal93.phonebookappmvvm.views.activities

import android.annotation.SuppressLint
import android.content.Intent
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

    private val contactsViewModel: ContactsViewModel? = null
    companion object {
        const val ADD_CONTACT_REQUEST = 200
    }

    private lateinit var mAdapter: ContactsAdapter

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val binding: ActivityContactsBinding =
            DataBindingUtil.setContentView(this@ContactsActivity, R.layout.activity_contacts)
        val contactsViewModel: ContactsViewModel =
            ViewModelProviders.of(this@ContactsActivity).get(ContactsViewModel::class.java)

        binding.contactViewModel = contactsViewModel

        binding.addNewContactBtn.setOnClickListener {
            val intent = Intent(this@ContactsActivity, AddContactActivity::class.java)
            startActivityForResult(intent, ADD_CONTACT_REQUEST)
        }

        binding.toolbarContacts.title = getString(R.string.contacts_toolbar_title)


        contactsViewModel.getContactsList().observe(this@ContactsActivity, Observer {

            mAdapter = ContactsAdapter(this@ContactsActivity, it)

            recycler_contacts.layoutManager =
                LinearLayoutManager(applicationContext, OrientationHelper.VERTICAL, false)
            recycler_contacts.adapter = mAdapter
            recycler_contacts.setHasFixedSize(true)

            mAdapter.sortByName()
            //mAdapter.addItem(contactsModel = contactsViewModel.add()!!)
            //mAdapter.notifyDataSetChanged()
        })

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
            if (resultCode == ADD_CONTACT_REQUEST && resultCode == RESULT_OK) {
                val firstName: String? = data?.getStringExtra(AddContactActivity.EXTRA_FIRST_NAME)
                val lastName: String? = data?.getStringExtra(AddContactActivity.EXTRA_LAST_NAME)
                val phone: String? = data?.getStringExtra(AddContactActivity.EXTRA_PHONE)
                val email: String? = data?.getStringExtra(AddContactActivity.EXTRA_EMAIL)
                val notes: String? = data?.getStringExtra(AddContactActivity.EXTRA_NOTES)

                val contacts = Contacts(firstName = firstName.toString(), lastName = lastName.toString(), phone = phone.toString(),
                    email = email.toString(), notes = notes.toString(), images = "")

                    contactsViewModel?.insertItem(contacts)
            }
        }



    override fun setupContactsList(contactsList: ArrayList<Contacts>) {

        mAdapter.setupContacts(contactsList = contactsList)
    }
}
