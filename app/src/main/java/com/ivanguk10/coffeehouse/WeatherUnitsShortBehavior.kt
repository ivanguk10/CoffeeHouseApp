package com.ivanguk10.coffeehouse

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import androidx.core.view.isVisible

class WeatherUnitsShortBehavior(): CoordinatorLayout.Behavior<ConstraintLayout>() {
    constructor(context: Context, atrs: AttributeSet): this()

    override fun onStartNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: ConstraintLayout,
        directTargetChild: View,
        target: View,
        axes: Int,
        type: Int
    ): Boolean {
        Log.i("TAG", "It is working")
        child.isVisible = false

        return axes == ViewCompat.SCROLL_AXIS_HORIZONTAL
    }

}