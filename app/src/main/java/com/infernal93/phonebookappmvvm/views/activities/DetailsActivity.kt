package com.infernal93.phonebookappmvvm.views.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.infernal93.phonebookappmvvm.R
import com.infernal93.phonebookappmvvm.entity.Contacts
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_details.*


class DetailsActivity : AppCompatActivity() {
    private val TAG = "DetailsActivity"

    private lateinit  var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        // Toolbar implementation
        toolbar = findViewById(R.id.toolbar_details_info)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeButtonEnabled(true)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        toolbar.title = getString(R.string.details_toolbar_title)
        toolbar.setNavigationOnClickListener{
            onBackPressed()
            overridePendingTransition(0, 0)
        }

        val clickedContact: Contacts = intent.getSerializableExtra("contact") as Contacts

        details_first_name.text = clickedContact.firstName
        details_last_name.text = clickedContact.lastName
        details_phone.text = clickedContact.phone
        details_email.text = clickedContact.email
        details_notes.text = clickedContact.notes

        Picasso.with(this@DetailsActivity).load(clickedContact.images).into(details_image)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(0, 0)
    }
}
