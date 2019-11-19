# SpanKtx
Using KTX spans


Android KTX adds useful extension functions, including for SpannableStrings.

Some are illustrated here, plus some more extension functions to make it even more DSL like.

```
val colorPrimary = ContextCompat.getColor(this, R.color.colorPrimary)

        textview3.text = styled {
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
```
