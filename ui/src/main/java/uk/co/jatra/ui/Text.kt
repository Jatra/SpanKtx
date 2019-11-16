package uk.co.jatra.ui

import android.content.Context
import android.graphics.Typeface
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.text.style.StrikethroughSpan
import android.text.style.StyleSpan
import android.text.style.TextAppearanceSpan
import androidx.annotation.ColorRes
import androidx.annotation.StyleRes
import androidx.core.content.ContextCompat
import androidx.core.text.inSpans

const val STRUCK_THROUGH_COLOR = android.R.color.darker_gray

val SpannableStringBuilder.space: SpannableStringBuilder
    get() {
        this.append(' ')
        return this
    }
val SpannableStringBuilder.nbspace: SpannableStringBuilder
    get() {
        this.append('\u00A0')
        return this
    }
val SpannableStringBuilder.newline: SpannableStringBuilder
    get() {
        this.append('\n')
        return this
    }

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

inline fun SpannableStringBuilder.colorPrimary(
    context: Context,
    builderAction: SpannableStringBuilder.() -> Unit
): SpannableStringBuilder = inSpans(
    ForegroundColorSpan(ContextCompat.getColor(context, R.color.colorPrimary)),
    builderAction = builderAction
)

inline fun SpannableStringBuilder.colorSecondary(
    context: Context,
    builderAction: SpannableStringBuilder.() -> Unit
): SpannableStringBuilder = inSpans(
    ForegroundColorSpan(ContextCompat.getColor(context, R.color.colorSecondary)),
    builderAction = builderAction
)

inline fun SpannableStringBuilder.struckThrough(
    context: Context,
    crossinline builderAction: SpannableStringBuilder.() -> Unit
): SpannableStringBuilder {
    return inSpans(
        StrikethroughSpan(),
        builderAction = { color(context, STRUCK_THROUGH_COLOR) { builderAction() } }
    )
}

fun SpannableStringBuilder.embolden(text: CharSequence): SpannableStringBuilder =
    inSpans(StyleSpan(Typeface.BOLD)) { append(text) }

fun SpannableStringBuilder.strikeThrough(text: CharSequence): SpannableStringBuilder =
    inSpans(StrikethroughSpan()) { append(text) }

fun styled(builderAction: SpannableStringBuilder.() -> Unit): SpannableStringBuilder {
    val ssb = SpannableStringBuilder()
    ssb.inSpans(ssb, builderAction = builderAction)
    return ssb
}

