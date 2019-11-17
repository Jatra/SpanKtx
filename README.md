# SpanKtx
Using KTX spans


Android KTX adds useful extension functions, including for SpannableStrings.

Some are illustrated here, plus some more extension functions to make it even more DSL like.

```
val colorPrimary = ContextCompat.getColor(this, R.color.colorPrimary)

        textview3.text = styled {
            bolded("bolded")
            nbspace
            strikeThrough("one")
            space
            bold {
                //Note this is using the ktx function that returns a SpannableStringBuilder
                strikeThrough("$140.00")
            }
            space
            append("E")
            append("N")
            append("D")
            colored(this@MainActivity, R.color.colorAccent, "!!")
            newline
            colored(colorPrimary, "color ready resolved")
            bolded("!!")
        }
```
