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

class FitnessDealsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fitness_deals)

        val brandsTitles = findViewById<ConstraintLayout>(R.id.shoppingBrandsLayout)
        val fitnessBrands: Array<Array<String>> = arrayOf(arrayOf("FreshGym", "Get hench at freshGym. Enjoy £0 signing on fee and your first month free! Whats your excuse now, punk? Get huge today at FreshGym"),
            arrayOf("PureGym", "Passion and exercise for all. Join the adventure of a lifetime at Pure Gym. Enjoy our coupon for one free exercise class, valid Monday - Fridays"),
            arrayOf("GymLife", "If Gym isn't your life, you're not wanted here. Love Boxing? how about MMA? Sign up today using the discount code to receive a whopping 25% our annual memberships"),
            arrayOf("FitnessDudes", "Enjoy £15 off our 3-month membership plan when you use the discount code down below. With a fantastic range of gym equipment and in house spa, join us and let us help you become the best version of yourself. Sign up today!"),
            arrayOf("ExerciseU", "Enjoy exercise? Looking for the gym community you deserve? Join ExerciseU today using the discount code below and receive a mystery welcome gift containing all the gym essentials you'll need to start your journey with us."))

        loadBrandDetails(brandsTitles, fitnessBrands)
        configureOnClickListeners(brandsTitles, fitnessBrands)

    }

    /**
     * Function to assign onclick listeners dynamically to the brand objects created.
     * @param brandsTitles - The constraint layout holding all of the brand ImageViews and TextViews
     * @param fitnessBrands - The array of arrays holding the information for each brand, to be displayed on the individual pages.
     */
    private fun configureOnClickListeners(brandsTitles: ConstraintLayout, fitnessBrands: Array<Array<String>>) {

        var counter = 0


        //Set onclick listeners for each brand object
        for (component in brandsTitles) {

            if (component is ImageView) {

                component.setOnClickListener {

                    val dealIntent = Intent(this, IndividualDealActivity::class.java)
                    dealIntent.putExtra("cameFrom", "fitnessPage")
                    dealIntent.putExtra("BrandName",  fitnessBrands[getBrandIndex(component)][0])
                    dealIntent.putExtra("BrandDesc", fitnessBrands[getBrandIndex(component)][1])
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
     * @return An integer value corresponding to the place of the brand information in the fitnessBrands array
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
     * @param fitnessBrands - The array of arrays holding the information for each brand, to be displayed on the individual pages.
     */
    private fun loadBrandDetails(brandsTitles: ConstraintLayout, fitnessBrands: Array<Array<String>>) {

        var textViewCounter = 0
        var imageViewCounter = 0
        for (component in brandsTitles) {

            if (component is TextView) {

                component.text = fitnessBrands[textViewCounter][0] //Set the text value to what is stored in the array
                textViewCounter += 1
            }

            if (component is ImageView) {

                val filename = formatBrandName(imageViewCounter, fitnessBrands)

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
     * @param fitnessBrands - The array of brands to access
     * @return the lowercase, whitespace removed brand name, inline with the drawable naming format
     */
    private fun formatBrandName(counter : Int, fitnessBrands: Array<Array<String>>): String {

        //Remove any spaces from the hospitality brand name
        val brandName = fitnessBrands[counter][0].filter { !it.isWhitespace() }

        //Make it lower case
        return brandName.lowercase()


    }
}