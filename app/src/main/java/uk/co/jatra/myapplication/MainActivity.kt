package uk.co.jatra.myapplication

import android.R.color.holo_green_dark
import android.os.Bundle
import android.text.SpannableStringBuilder
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getColor
import androidx.core.text.*
import kotlinx.android.synthetic.main.activity_main.*
import uk.co.jatra.ui.*

const val GREEN = holo_green_dark
const val GREY = android.R.color.darker_gray
const val HEADLINE = android.R.style.TextAppearance_Material_Headline
const val BODY2 = android.R.style.TextAppearance_Material_Body2

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val green = getColor(this, GREEN)
        val grey = getColor(this, GREY)
        textview.text = SpannableStringBuilder()
            .color(green) { append("Green text") }
            .space
            .superscript { append("superscript") }
            .space
            .append("Normal text")
            .space
            .strikeThrough { append("strikethrough") }
            .nbspace
            .subscript { append("subscript") }
            .space
            .scale(0.5f) { append("Text at half size") }
            .space
            .backgroundColor(green) { append("Background green") }
            .space
            .bold { underline { italic { strikeThrough { append("Bold and underlined italic strikethrough") } } } }
            .newline
            .style(this, HEADLINE) { append("HEADLINE") }
            .newline
            .color(green) { style(this@MainActivity, HEADLINE) { append("GREEN HEADLINE") } }
            .newline
            .colorSecondary(this) { append("secondary") }



        textview2.text = SpannableStringBuilder()
            .colorPrimary(this) { append("$120.00") }
            .space
            .color(grey) { strikeThrough { append("$140.00") } }

        //Not used. I don't like struckThrough implicitly seeing the colour
        textview3.text = SpannableStringBuilder()
            .colorPrimary(this) { append("$120.00") }
            .space
            .struckThrough(this) { append("$140.00") }


        textview3.text = styled {
            //a sequence of operations on "this"
            //all of them are either explicitly or implicitly calling append
            underline {
                style(this@MainActivity, R.style.TextAppearance_AppCompat_Headline, "Using Styled")
            }
            newline
            italic("italic")
            space
            bold("bolded")
            nbspace
            strikeThrough("one")
            space
            bold {
                //Note this is using the ktx function that returns a SpannableStringBuilder
                strikeThrough("$140.00")
            }
            space
            append("E")
            bold { append("N") }
            append("D")
            newline
            color(this@MainActivity, R.color.colorAccent, "colour by resource id")
            newline
            color(green, "color ready resolved")
            bold("!!")
        }


        /*
    styleBuilder allows a context to be passed in.
    This is useful where the actions require access to resources,
    such as color definitions
     */

        textview4.text = styleBuilder(this) {
            colorPrimary("$120.00")
            space
            bold {
                strikeThrough("$140.00")
            }
            space
            color(R.color.colorAccent, "of course")
        }

        textview5.text = styleBuilder(this) {
            bold("bolded")
            space
            colorPrimary("$120.00")
            space
            strikeThrough("$140.00")

        }
    }
}



