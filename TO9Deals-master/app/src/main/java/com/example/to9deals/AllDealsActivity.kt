package com.example.to9deals

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class AllDealsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_deals)

        configureOnClickListeners()
    }

    /**
     * Function to add onclick listeners for all of the necessary buttons / ImageViews on the page
     * Primarily these onclick listeners will launch an intent to the required activity.
     */
    private fun configureOnClickListeners() {

        //Save the widget objects in variables

        val backToFeaturedPageButton = findViewById<Button>(R.id.AllDealsBackButton)
        val toFitnessDealsButton = findViewById<ImageView>(R.id.imageFitnessDeals)
        val toHospitalityDealsButton = findViewById<ImageView>(R.id.imageHospitalityDeals)
        val toShoppingDealsButton = findViewById<ImageView>(R.id.imageShoppingDeals)
        val toTechnologyDealsButton = findViewById<ImageView>(R.id.imageTechnologyDeals)

        backToFeaturedPageButton.setOnClickListener {

            //Create the intent to return back to the featured page.
            val returnIntent = Intent(this, FeaturedPageActivity::class.java)
            startActivity(returnIntent)

        }

        toFitnessDealsButton.setOnClickListener {

            val fitnessIntent = Intent(this, FitnessDealsActivity::class.java)
            startActivity(fitnessIntent)

        }

        toHospitalityDealsButton.setOnClickListener {

            val hospitalityIntent = Intent(this, HospitalityDealsActivity::class.java)
            startActivity(hospitalityIntent)
        }

        toShoppingDealsButton.setOnClickListener {

            val shoppingIntent = Intent(this, ShoppingDealsActivity::class.java)
            startActivity(shoppingIntent)
        }

        toTechnologyDealsButton.setOnClickListener {

            val technologyIntent = Intent(this, TechnologyDealsActivity::class.java)
            startActivity(technologyIntent)
        }
    }
}