package com.example.to9deals

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        configureClickListeners()
    }

    /**
     * The function to configure the onclick listeners for the button and text element.
     */
    private fun configureClickListeners() {

        //Define the submit button as a variable and the EditText that holds the serial number
        val submitButton = findViewById<Button>(R.id.submitButton)
        val submitText = findViewById<EditText>(R.id.enterSerialNumber)

        //Add the onclick listener to verify the input and go to the Featured Page
        submitButton.setOnClickListener {

            if (validInput(submitText)) { //If the input is valid

                //Create the new intent and go to the featured page
                val featuredPageIntent = Intent(this, FeaturedPageActivity::class.java)
                startActivity(featuredPageIntent)

            }
        }

        val tAndCText = findViewById<TextView>(R.id.textTandCs)

        tAndCText.setOnClickListener {

            val privacyPolicyIntent = Intent(this, TandCActivity::class.java)

            privacyPolicyIntent.putExtra("cameFrom", "Login")

            startActivity(privacyPolicyIntent)
        }
    }

    /**
     * @param submitText - The EditText element that holds the users input on the screen
     * @return A boolean true of false, that verifies both of the conditions are met
     */
    private fun validInput(submitText: EditText): Boolean {

        if (submitText.text.length == 15) { //If the number entered is 15 digits long
            if ( checkFormat(submitText)) {  //If the number is in the correct format

                return true

            } else {
                //Otherwise, create a snack bar to notify the user of the issue
                Snackbar
                    .make(findViewById(R.id.submitButton), "The text needs to be fully numeric", Snackbar.LENGTH_SHORT)
                    .show()

                return false
            }
        } else {

            //Otherwise, create a snack bar to notify the user of the issue
            Snackbar
                .make(findViewById(R.id.submitButton), "The number should be 15 digits long", Snackbar.LENGTH_SHORT)
                .show()

            return false
        }
    }


    /**
     * @param textBox - The EditText element that holds the users input on the screen
     * @return A boolean true of false, that verifies all of the characters are digits in the input
     */
    private fun checkFormat(textBox: EditText): Boolean {

        val textToCheck = textBox.text

        //Check all of the characters in the input are numbers.
        return textToCheck.all { char -> char.isDigit() }


    }
}