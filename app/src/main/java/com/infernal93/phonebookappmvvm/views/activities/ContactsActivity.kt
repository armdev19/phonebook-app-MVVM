package com.infernal93.phonebookappmvvm.views.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import com.infernal93.phonebookappmvvm.R
import com.infernal93.phonebookappmvvm.di.ContactsViewModelFactory
import com.infernal93.phonebookappmvvm.entity.Contacts
import com.infernal93.phonebookappmvvm.viewmodels.ContactsViewModel
import com.infernal93.phonebookappmvvm.views.adapters.ContactsAdapter
import com.infernal93.phonebookappmvvm.views.interfaces.ContactsListener
import dagger.Binds
import dagger.Component
import dagger.Module
import kotlinx.android.synthetic.main.activity_contacts.*
import javax.inject.Inject


class ContactsActivity : AppCompatActivity(), ContactsListener {
    private val TAG = "ContactsActivity"

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    lateinit var contactsViewModel: ContactsViewModel

    private lateinit  var toolbar: Toolbar
    private lateinit var addNewContactBtn: ImageButton


    companion object {
        const val ADD_CONTACT_REQUEST = 200
    }

    private lateinit var mAdapter: ContactsAdapter

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts)

        toolbar = findViewById(R.id.toolbar_contacts)
        addNewContactBtn = findViewById(R.id.add_new_contact_btn)

        setSupportActionBar(toolbar)
        toolbar.title = getString(R.string.contacts_toolbar_title)


        addNewContactBtn.setOnClickListener {
            val intent = Intent(this@ContactsActivity, AddContactActivity::class.java)
            startActivityForResult(intent, ADD_CONTACT_REQUEST)
        }

        DaggerContactsActivity_ContactsComponent.create().inject(this@ContactsActivity)
        contactsViewModel = ViewModelProviders.of(this@ContactsActivity, factory).get(ContactsViewModel::class.java)
        contactsViewModel.getContactsList().observe(this@ContactsActivity, Observer {

            mAdapter = ContactsAdapter(this@ContactsActivity, it)
            recycler_contacts.layoutManager =
                LinearLayoutManager(applicationContext, OrientationHelper.VERTICAL, false)
            recycler_contacts.adapter = mAdapter
            recycler_contacts.setHasFixedSize(true)

            mAdapter.sortByName()
        })

    }

    // Dagger create
    @Component (modules = [ContactsModule::class])
    interface ContactsComponent {

        fun inject (activity: ContactsActivity)
    }

    @Module
    abstract class ContactsModule {

        @Binds
    abstract fun bindViewModelFactory(factory: ContactsViewModelFactory): ViewModelProvider.Factory
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

            }
        }

    override fun setupContactsList(contactsList: ArrayList<Contacts>) {

        mAdapter.setupContacts(contactsList = contactsList)
    }
}
