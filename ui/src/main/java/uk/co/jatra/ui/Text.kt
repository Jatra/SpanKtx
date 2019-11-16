package uk.co.jatra.ui

import android.content.Context
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.text.style.StrikethroughSpan
import android.text.style.TextAppearanceSpan
import androidx.annotation.ColorRes
import androidx.annotation.StyleRes
import androidx.core.content.ContextCompat
import androidx.core.text.inSpans

const val STRUCK_THROUGH_COLOR = android.R.color.darker_gray


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

