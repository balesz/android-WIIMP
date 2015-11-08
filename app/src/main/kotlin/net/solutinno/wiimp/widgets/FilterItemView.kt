package net.solutinno.wiimp.widgets

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.Toast
import kotlinx.android.synthetic.filter_item.view.button
import kotlinx.android.synthetic.filter_item.view.subtitle
import kotlinx.android.synthetic.filter_item.view.title
import net.solutinno.wiimp.R

class FilterItemView : FrameLayout {

    constructor(ctx: Context, attrs: AttributeSet? = null, dsa: Int = 0) : super(ctx, attrs, dsa) {
        val array = ctx.theme.obtainStyledAttributes(attrs, R.styleable.FilterItemView, dsa, 0)
        val titleText = array.getString(R.styleable.FilterItemView_filter_title)?:""
        val subtitleText = array.getString(R.styleable.FilterItemView_filter_subtitle)?:""
        array.recycle()
        addView(inflate(ctx, R.layout.filter_item, null).apply {
            layoutParams = generateDefaultLayoutParams()
            title.text = titleText
            subtitle.text = subtitleText
            button.setOnClickListener {
                Toast.makeText(it.context, "Hello World", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
