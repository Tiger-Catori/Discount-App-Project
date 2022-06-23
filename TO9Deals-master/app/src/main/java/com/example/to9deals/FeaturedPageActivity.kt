package com.example.to9deals

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.iterator
import java.lang.Exception

class FeaturedPageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_featured_page)

        //Array [[BrandName, BrandDescription], [BrandName, BrandDescription], [BrandName, BrandDescription]]
        val featuredDeals: Array<Array<String>> = arrayOf(arrayOf("FreshGym", "Get hench at freshGym. Enjoy £0 signing on fee and your first month free! Whats your excuse now, punk? Get huge today at FreshGym"),
            arrayOf("Gordon Ramsay", "Gordon Ramsay is a famous celebrity chef, his high end michelin star restaurants being some of the most well known in the country. Use this code to enjoy a tasting experience for two. Quote your code on booking through the restaurant in order for it to be redeemed."),
            arrayOf("River Island", "Enjoy exclusive discounts of all our summer collection at riverIsland. Discounts of up to 75% on selected ranges and colours. Look fresh this summer with River Island"))

        configureOnClickListeners(featuredDeals)
        updateDealsText(featuredDeals)
        loadBrandImages(featuredDeals)
    }


    /**
     * Function to configure the onclick listeners for the Brands and Privacy Policy buttons on the
     * page.
     * @param featuredDeals - The Array of arrays containing information on each of the featured deals.
     */
    private fun configureOnClickListeners(featuredDeals: Array<Array<String>>) {

        //Declare variables to hold the widgets on the screen
        val privacyPolicy = findViewById<TextView>(R.id.privacyPolicy)
        val featuredOne = findViewById<ImageView>(R.id.featuredOne)
        val featuredTwo = findViewById<ImageView>(R.id.featuredTwo)
        val featuredThree = findViewById<ImageView>(R.id.featuredThree)
        val viewMoreButton = findViewById<Button>(R.id.viewMoreButton)

        //Set an onclick listener for the privacy policy to take the user there.
        privacyPolicy.setOnClickListener {

            val  privacyPolicyIntent = Intent(this, TandCActivity::class.java)
            //Add an extra to the intent, declaring where the user clicked the privacy policy from
            privacyPolicyIntent.putExtra("cameFrom", "Logged In")
            startActivity(privacyPolicyIntent)
        }

        val  dealIntent = Intent(this, IndividualDealActivity::class.java)
        featuredOne.setOnClickListener {

            //Add extras to the intent, where the user came from, and the brand details to display
            dealIntent.putExtra("cameFrom", "FeaturedPage")
            dealIntent.putExtra("BrandName",  featuredDeals[0][0])
            dealIntent.putExtra("BrandDesc", featuredDeals[0][1])

            startActivity(dealIntent)
        }

        featuredTwo.setOnClickListener {

            //Add extras to the intent, where the user came from, and the brand details to display
            dealIntent.putExtra("cameFrom", "FeaturedPage")
            dealIntent.putExtra("BrandName",  featuredDeals[1][0])
            dealIntent.putExtra("BrandDesc", featuredDeals[1][1])

            startActivity(dealIntent)
        }

        featuredThree.setOnClickListener {

            //Add extras to the intent, where the user came from, and the brand details to display
            dealIntent.putExtra("cameFrom", "FeaturedPage")
            dealIntent.putExtra("BrandName", featuredDeals[2][0])
            dealIntent.putExtra("BrandDesc", featuredDeals[2][1])

            startActivity(dealIntent)
        }

        viewMoreButton.setOnClickListener {

            val allDealsIntent = Intent(this, AllDealsActivity::class.java)
            startActivity(allDealsIntent)

        }

    }

    /**
     * Function to update the brand titles on each of the famous brands.
     * @param featuredDeals - Array of arrays holding the information on the featured deals to display
     */
    private fun updateDealsText(featuredDeals: Array<Array<String>>) {

        //Get the widget objects for each title on screen
        val featuredOneText = findViewById<TextView>(R.id.featuredOneText)
        val featuredTwoText = findViewById<TextView>(R.id.featuredTwoText)
        val featuredThreeText = findViewById<TextView>(R.id.featuredThreeText)

        //Update the title of each brand to the stored name
        featuredOneText.text = featuredDeals[0][0]
        featuredTwoText.text = featuredDeals[1][0]
        featuredThreeText.text = featuredDeals[2][0]

    }

    private fun loadBrandImages(featuredDeals : Array<Array<String>>) {

        var imageViewCounter = 0

        for (component in findViewById<ConstraintLayout>(R.id.FeaturedConstraintLayout)) {

            if (component is ImageView) {

                val filename = formatBrandName(imageViewCounter, featuredDeals)

                try { //Try to set the background image to the imageView, if there isn't one then keep looping

                    component.setImageResource( //Set the background image as the formatted file name
                        resources.getIdentifier(
                            filename,
                            "drawable",
                            packageName
                        )
                    )
                } catch (e: Exception) {

                }

                imageViewCounter += 1

            }

        }

    }

    /**
     * Function to format the name of the brand to the format necessary to access the drawable resource
     * to display an image
     * @param counter - The index of the brand needing to be displayed on the imageview, In the hospitalitybrand Array
     * @param featuredDeals - The array of brands to access
     * @return the lowercase, whitespace removed brand name, inline with the drawable naming format
     */
    private fun formatBrandName(counter : Int, featuredDeals: Array<Array<String>>): String {

        //Remove any spaces from the hospitality brand name
        val brandName = featuredDeals[counter][0].filter { !it.isWhitespace() }

        //Make it lower case
        return brandName.lowercase()


    }



}