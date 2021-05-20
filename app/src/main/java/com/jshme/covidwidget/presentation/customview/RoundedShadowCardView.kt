package com.jshme.covidwidget.presentation.customview

import android.content.Context
import android.graphics.Outline
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewOutlineProvider
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.ConstraintLayout
import com.jshme.covidwidget.R
import com.jshme.covidwidget.databinding.RoundedShadowCardviewBinding
import com.jshme.covidwidget.presentation.util.pixelsFrom


enum class TrendType {
    UP,
    DOWN,
    MIDDLE
}

class RoundedShadowCardView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null
) : ConstraintLayout(context, attributeSet) {

    private val binding = RoundedShadowCardviewBinding.inflate(LayoutInflater.from(context), this)
    private val cardViewRadius = resources.pixelsFrom(20f)

    //타입에 따라 string resource 분리할 것
    var trendType: TrendType? = null
        set(value) {
            field = value
            when (value) {
                TrendType.UP -> {
                    binding.icon.setImageResource(R.drawable.ic_up_arrow)
                    binding.title.text = "주변 사람들을 위해 단체활동은 금지!"
                }
                TrendType.MIDDLE -> {
                    binding.icon.setImageResource(R.drawable.ic_delete)
                    binding.title.text = "아직 안심하기에는 일러요!"
                }
                TrendType.DOWN -> {
                    binding.icon.setImageResource(R.drawable.ic_down_arrow)
                    binding.title.text = "우리 지금처럼만 열심히 노력해요!"
                }
            }
        }

    var title: CharSequence? = null
        get() = binding.title.text
        set(value) {
            field = value
            binding.title.text = value
        }

    var description: CharSequence? = null
        get() = binding.description.text
        set(value) {
            field = value
            binding.description.text = value
        }

    var descriptionVisibility: Int = View.VISIBLE
        set(value) {
            field = value
            binding.description.visibility = value
        }

    @DrawableRes
    var icon: Int? = null
        set(value) {
            field = value
            value?.let { binding.icon.setImageResource(it) }
        }

    init {
        binding.cardView.radius = cardViewRadius
        clipToPadding = false

        //API 29
//        binding.cardView.outlineSpotShadowColor = resources.getColor(R.color.blue_30p, null)
        binding.cardView.setCardBackgroundColor(resources.getColor(R.color.white, null))

    }
}
