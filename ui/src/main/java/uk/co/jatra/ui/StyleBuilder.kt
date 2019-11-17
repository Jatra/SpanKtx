package uk.co.jatra.ui

import android.content.Context
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.text.style.StrikethroughSpan
import android.text.style.StyleSpan
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

/*
StyleBuilder useful for when styles to be applied need access to resources.
Generally it's probably easier to use the styled function.

ie with StyleBuidler:

styleBuilder(this) {
            colorPrimary("$120.00")
            space
            color(grey) {   //Note this is using the ktx function that returns a SpannableStringBuilder
                strikeThrough ("$140.00")
            }
            space
            color(R.color.colorAccent, "of course")
            bold("!!")


with styled:

val primaryColor = ContextCompat.getColor(this, R.color.colorPrimary)
val grey = ContextCompat.getColor(this, android.R.color.darker_gray)
val accentColor = ContextCompat.getColor(this, R.color.colorAccent)

styled {
    color(primaryColor, "$120.00)
    space
    color(grey) {   //Note this is using the ktx function that returns a SpannableStringBuilder
        strikeThrough ("$140.00")
    }
    space
    colored(accentColor, "of course")
    bolded("!!")
}

 */
fun styleBuilder(context: Context, builderAction: StyleBuilder.() -> Unit): StyleBuilder {
    val sb = StyleBuilder(context)
    sb.applyBuilderAction(null, builderAction = builderAction)
    return sb
}


class StyleBuilder(val context: Context) : SpannableStringBuilder() {

    inline fun applyBuilderAction(
        span: Any?,
        builderAction: StyleBuilder.() -> Unit
    ): StyleBuilder {
        val start = length
        builderAction()
        setSpan(span, start, length, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
        return this
    }

    fun colorPrimary(text: CharSequence) {
        val span = ForegroundColorSpan(ContextCompat.getColor(context, R.color.colorPrimary))
        applyBuilderAction(span) { append(text) }
    }

    //I'd say this is dubious. Why does strikethrough imply a specific colour?
    fun strikeThrough(text: CharSequence) {
        applyBuilderAction(
            StrikethroughSpan(),
            builderAction = { color(context, STRUCK_THROUGH_COLOR) { append(text) } }
        )
    }

    fun bold(text: CharSequence): StyleBuilder =
        applyBuilderAction(StyleSpan(Typeface.BOLD)) { append(text) }

    fun color(@ColorRes color: Int, text: CharSequence) {
        val span = ForegroundColorSpan(ContextCompat.getColor(context, color))
        applyBuilderAction(span) { append(text) }
    }

}
