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
            .space()
            .superscript { append("superscript") }
            .space()
            .append("Normal text")
            .space()
            .strikeThrough { append("strikethrough") }
            .nbspace()
            .subscript { append("subscript") }
            .space()
            .scale(0.5f) { append("Text at half size") }
            .space()
            .backgroundColor(green) { append("Background green") }
            .space()
            .bold { underline { italic { strikeThrough { append("Bold and underlined italic strikethrough") } } } }
            .lineBreak()
            .style(this, HEADLINE) { append("headline") }
            .lineBreak()
            .color(green) { style(this@MainActivity, HEADLINE) { append("green headline") } }
            .lineBreak()
            .colorSecondary(this) { append("secondary") }



        textview2.text = SpannableStringBuilder()
            .colorPrimary(this) { append("$120.00") }
            .space()
            .color(grey) { strikeThrough { append("$140.00") } }

        textview3.text = SpannableStringBuilder()
            .colorPrimary(this) { append("$120.00") }
            .space()
            .struckThrough(this) { append("$140.00") }

    }
}




