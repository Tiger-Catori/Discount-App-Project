
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

class TechnologyDealsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_technology_deals)

        val brandsTitles = findViewById<ConstraintLayout>(R.id.technologyBrandsLayout)
        val technologyBrands: Array<Array<String>> = arrayOf(arrayOf("CodeAcademy", "Want a career change? sign up and start your coding journey with CodeAcademy with 50% your first month when using the discount code below"),
            arrayOf("Microsoft", "Receive a fantastic £0.50 discount when buying a digital copy of Microsoft Office 365 when you use the discount code below"),
            arrayOf("PCPartPicker", "Receive free postage and assembly of any computer or tech purchased on our website when using the discount code below. What are you waiting for? build your dream PC today"),
            arrayOf("Razor",  "Love Gaming? Love our high performance products? Receive a free wireless Razor Mouse with any purchases over £79.99 on our website."),
            arrayOf("Samsung", "Think android is better then iOS? Don't worry, we agree. Receive 10% off any Samsung Galaxy Phone in store or online using the code below. Break the rules of innovation."))

        loadBrandDetails(brandsTitles, technologyBrands)
        configureOnClickListeners(brandsTitles, technologyBrands)

    }

    /**
     * Function to assign onclick listeners dynamically to the brand objects created.
     * @param brandsTitles - The constraint layout holding all of the brand ImageViews and TextViews
     * @param technologyBrands - The array of arrays holding the information for each brand, to be displayed on the individual pages.
     */
    private fun configureOnClickListeners(brandsTitles: ConstraintLayout, technologyBrands: Array<Array<String>>) {

        var counter = 0


        //Set onclick listeners for each brand object
        for (component in brandsTitles) {

            if (component is ImageView) {

                component.setOnClickListener {

                    val dealIntent = Intent(this, IndividualDealActivity::class.java)
                    dealIntent.putExtra("cameFrom", "technologyPage")
                    dealIntent.putExtra("BrandName",  technologyBrands[getBrandIndex(component)][0])
                    dealIntent.putExtra("BrandDesc", technologyBrands[getBrandIndex(component)][1])
                    startActivity(dealIntent)

                }

                counter += 1

            }

        }

        //Set an onclick listener for the back button
        val backButton = findViewById<Button>(R.id.technologyDealsBack)

        backButton.setOnClickListener {

            val backToAllDeals = Intent(this, AllDealsActivity::class.java)
            startActivity(backToAllDeals)

        }

    }

    /**
     * Function used to get the index of the corresponding brand information in the array of brands
     * @param component - The component that the brand information will be displayed on
     * @return An integer value corresponding to the place of the brand information in the technologyBrands array
     */
    private fun getBrandIndex(component : ImageView): Int {

        //Filter the content description to find which number brand it is
        val digit = component.contentDescription.filter {it.isDigit()}

        //Return the integer value, altered to correspond to the array of brand information
        return Integer.parseInt(digit.toString())-1
    }


    /**
     * Function used to get the brand titles for each brand stores and display them on the screen
     * @param brandsTitles - The constraint layout holding all of the brand ImageViews and TextViews
     * @param technologyBrands - The array of arrays holding the information for each brand, to be displayed on the individual pages.
     */
    private fun loadBrandDetails(brandsTitles: ConstraintLayout, technologyBrands: Array<Array<String>>) {

        var textViewCounter = 0
        var imageViewCounter = 0
        for (component in brandsTitles) {

            if (component is TextView) {

                component.text = technologyBrands[textViewCounter][0] //Set the text value to what is stored in the array
                textViewCounter += 1
            }

            if (component is ImageView) {

                val filename = formatBrandName(imageViewCounter, technologyBrands)

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
     * @param technologyBrands - The array of brands to access
     * @return the lowercase, whitespace removed brand name, inline with the drawable naming format
     */
    private fun formatBrandName(counter : Int, technologyBrands: Array<Array<String>>): String {

        //Remove any spaces from the hospitality brand name
        val brandName = technologyBrands[counter][0].filter { !it.isWhitespace() }

        //Make it lower case
        return brandName.lowercase()


    }
}
