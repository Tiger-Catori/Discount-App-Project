package com.example.to9deals

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import kotlin.random.Random

class IndividualDealActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_individual_deal)

        unloadIntent()
        configureOnClickListeners()
    }

    private fun unloadIntent() {

        val dealTitle = findViewById<TextView>(R.id.BrandName)
        val dealDesc = findViewById<TextView>(R.id.brandDesc)

        dealTitle.text = intent.getStringExtra("BrandName")
        dealDesc.text = intent.getStringExtra("BrandDesc")

        loadBrandImage(dealTitle.text as String)
    }

    private fun loadBrandImage(brandName: String) {

        val name = formatBrandName(brandName)

        val brandImageView = findViewById<ImageView>(R.id.individualBrandImage)

        brandImageView.setImageResource(resources.getIdentifier(name, "drawable", packageName))
    }

    /**
     * Function to format the name of the brand to the format necessary to access the drawable resource
     * to display an image
     * @return the lowercase, whitespace removed brand name, inline with the drawable naming format
     */
    private fun formatBrandName(brandName: String): String {

        //Remove any spaces from the hospitality brand name
        var brandNameSpaceless = brandName.filter { !it.isWhitespace() }

        //Make it lower case
        return brandNameSpaceless.lowercase()


    }

    private fun configureOnClickListeners() {

        val backButton = findViewById<Button>(R.id.IndividualDealBack)
        val revealCodeButton = findViewById<Button>(R.id.revealCodeButton)

        backButton.setOnClickListener {

            when (intent.getStringExtra("cameFrom")) {
                "FeaturedPage" -> {

                    val returnIntent = Intent(this, FeaturedPageActivity::class.java)
                    startActivity(returnIntent)

                }
                "fitnessPage" -> {

                    val returnIntent = Intent(this, FitnessDealsActivity::class.java)
                    startActivity(returnIntent)

                }
                "hospitalityPage" -> {

                    val returnIntent = Intent(this, HospitalityDealsActivity::class.java)
                    startActivity(returnIntent)
                }
                "shoppingPage" -> {

                    val returnIntent = Intent( this, ShoppingDealsActivity::class.java)
                    startActivity(returnIntent)

                }
                "technologyPage" -> {

                    val returnIntent = Intent( this, TechnologyDealsActivity::class.java)
                    startActivity(returnIntent)

                }
            }

        }

        var promoCode = ""
        revealCodeButton.setOnClickListener {

            if (promoCode == "") {
                promoCode = generateCode()
            }

            revealCodeButton.text = promoCode

        }

    }

    private fun generateCode(): String {

        val brandNameNoSpaces = findViewById<TextView>(R.id.BrandName).text.filter {!it.isWhitespace()}
        val numbsNeeded = 20 - brandNameNoSpaces.length
        var promoCode = brandNameNoSpaces.toString()

        for (loopNum in 0..numbsNeeded) {

            promoCode += Random.nextInt(9).toString()

        }

        return promoCode
    }


    //val backButton = findViewById<Button>(R.id.backButton)
}