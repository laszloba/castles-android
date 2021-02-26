package com.vaslufi.castles.ui.list

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.vaslufi.castles.R
import com.vaslufi.castles.databinding.ViewCastleListItemBinding
import com.vaslufi.castles.model.CastleListItemViewModel

class CastleListItemView : LinearLayout {

    private val binding = ViewCastleListItemBinding.inflate(LayoutInflater.from(context), this)

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) :
        super(context, attrs, defStyleAttr)

    init {
        layoutParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.WRAP_CONTENT
        )
        orientation = HORIZONTAL

        val padding = resources.getDimension(R.dimen.margin_padding_size_small).toInt()
        setPadding(padding, padding, padding, padding)
    }

    fun bind(model: CastleListItemViewModel) {
        binding.nameTextView.text = model.name
    }
}
