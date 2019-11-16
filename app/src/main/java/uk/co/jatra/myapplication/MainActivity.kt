package uk.co.jatra.myapplication

import android.R.color.holo_green_dark
import android.content.Context
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.text.style.TextAppearanceSpan
import androidx.annotation.ColorRes
import androidx.annotation.StyleRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getColor
import androidx.core.text.*
import kotlinx.android.synthetic.main.activity_main.*

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



        textview2.text = SpannableStringBuilder()
            .color(this, holo_green_dark) { append("$120.00") }
            .space()
            .color(grey) { strikeThrough { append("$140.00") } }

    }
}

fun SpannableStringBuilder.lineBreak() = append('\n')
fun SpannableStringBuilder.space() = append(' ')
fun SpannableStringBuilder.nbspace() = append('\u00A0')

inline fun SpannableStringBuilder.style(
    context: Context,
    @StyleRes style: Int,
    builderAction: SpannableStringBuilder.() -> Unit
) = inSpans(TextAppearanceSpan(context, style), builderAction = builderAction)

inline fun SpannableStringBuilder.color(
    context: Context,
    @ColorRes color: Int,
    builderAction: SpannableStringBuilder.() -> Unit
) = inSpans(
    ForegroundColorSpan(ContextCompat.getColor(context, color)),
    builderAction = builderAction
)