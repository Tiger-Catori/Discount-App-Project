
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

class ShoppingDealsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping_deals)

        val brandsTitles = findViewById<ConstraintLayout>(R.id.shoppingBrandsLayout)
        val shoppingBrands: Array<Array<String>> = arrayOf(arrayOf("JD Sports", "To dare is todo. Enjoy £10 off any product over £34.99 PLUS free delivery nationwide. What are you waiting for?"),
            arrayOf("Nike", "Buy our products, Just do it. Enjoy 25% discount on selected sports ranges using the discount code below. Just. Do. It."),
            arrayOf("Primark", "All of our clothing is 100% high quality, fair priced goods. Use our discount code down below to receive a free makeup set including cruelty free lipstick, eyeliner and set of bath bombs."),
            arrayOf("Ralph Lauren", "Enjoy £20 off orders over £99.99 on our website. Buy it, its made to be worn. Code also valid over seasonal periods. terms and conditions apply."),
            arrayOf("River Island", "Enjoy exclusive discounts of all our summer collection at riverIsland. Discounts of up to 75% on selected ranges and colours. Look fresh this summer with River Island"))


        loadBrandDetails(brandsTitles, shoppingBrands)
        configureOnClickListeners(brandsTitles, shoppingBrands)

    }

    /**
     * Function to assign onclick listeners dynamically to the brand objects created.
     * @param brandsTitles - The constraint layout holding all of the brand ImageViews and TextViews
     * @param shoppingBrands - The array of arrays holding the information for each brand, to be displayed on the individual pages.
     */
    private fun configureOnClickListeners(brandsTitles: ConstraintLayout, shoppingBrands: Array<Array<String>>) {

        var counter = 0


        //Set onclick listeners for each brand object
        for (component in brandsTitles) {

            if (component is ImageView) {

                component.setOnClickListener {

                    val dealIntent = Intent(this, IndividualDealActivity::class.java)
                    dealIntent.putExtra("cameFrom", "shoppingPage")
                    dealIntent.putExtra("BrandName",  shoppingBrands[getBrandIndex(component)][0])
                    dealIntent.putExtra("BrandDesc", shoppingBrands[getBrandIndex(component)][1])
                    startActivity(dealIntent)

                }

                counter += 1

            }

        }

        //Set an onclick listener for the back button
        val backButton = findViewById<Button>(R.id.shoppingDealsBack)

        backButton.setOnClickListener {

            val backToAllDeals = Intent(this, AllDealsActivity::class.java)
            startActivity(backToAllDeals)

        }

    }

    /**
     * Function used to get the index of the corresponding brand information in the array of brands
     * @param component - The component that the brand information will be displayed on
     * @return An integer value corresponding to the place of the brand information in the shoppingBrands array
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
     * @param shoppingBrands - The array of arrays holding the information for each brand, to be displayed on the individual pages.
     */
    private fun loadBrandDetails(brandsTitles: ConstraintLayout, shoppingBrands: Array<Array<String>>) {

        var textViewCounter = 0
        var imageViewCounter = 0
        for (component in brandsTitles) {

            if (component is TextView) {

                component.text = shoppingBrands[textViewCounter][0] //Set the text value to what is stored in the array
                textViewCounter += 1
            }

            if (component is ImageView) {

                val filename = formatBrandName(imageViewCounter, shoppingBrands)

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
     * @param shoppingBrands - The array of brands to access
     * @return the lowercase, whitespace removed brand name, inline with the drawable naming format
     */
    private fun formatBrandName(counter : Int, shoppingBrands: Array<Array<String>>): String {

        //Remove any spaces from the hospitality brand name
        val brandName = shoppingBrands[counter][0].filter { !it.isWhitespace() }

        //Make it lower case
        return brandName.lowercase()


    }
}
