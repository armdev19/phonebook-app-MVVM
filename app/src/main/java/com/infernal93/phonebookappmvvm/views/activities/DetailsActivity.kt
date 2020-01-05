package com.infernal93.phonebookappmvvm.views.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.infernal93.phonebookappmvvm.R
import com.infernal93.phonebookappmvvm.entity.Contacts
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_details.*


class DetailsActivity : AppCompatActivity() {
    private val TAG = "DetailsActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val clickedContact: Contacts = intent.getSerializableExtra("contact") as Contacts

        details_first_name.text = clickedContact.firstName
        details_last_name.text = clickedContact.lastName
        details_phone.text = clickedContact.phone
        details_email.text = clickedContact.email
        details_notes.text = clickedContact.notes

        Picasso.with(this@DetailsActivity).load(clickedContact.images).into(details_image)


    }
}
