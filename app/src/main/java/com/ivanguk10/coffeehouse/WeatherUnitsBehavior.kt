package com.ivanguk10.coffeehouse

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import androidx.core.math.MathUtils
import androidx.core.view.ViewCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.absoluteValue

class WeatherUnitsBehavior(): CoordinatorLayout.Behavior<ConstraintLayout>() {
    constructor(context: Context, atrs: AttributeSet): this()

    private var translationUnitsX = 0f
    private var translationXRecycler = 0f
    private var childWidth: Float = 0f

    override fun onStartNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: ConstraintLayout,
        directTargetChild: View,
        target: View,
        axes: Int,
        type: Int
    ): Boolean {
        Log.i("TAG", "It is working")
        child.isVisible = true
        return axes == ViewCompat.SCROLL_AXIS_HORIZONTAL
    }

    override fun onNestedPreScroll(
        coordinatorLayout: CoordinatorLayout,
        child: ConstraintLayout,
        target: View,
        dx: Int,
        dy: Int,
        consumed: IntArray,
        type: Int
    ) {
        Log.i("TAG", "It is pre")

        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type)
    }

    override fun onMeasureChild(
        parent: CoordinatorLayout,
        child: ConstraintLayout,
        parentWidthMeasureSpec: Int,
        widthUsed: Int,
        parentHeightMeasureSpec: Int,
        heightUsed: Int
    ): Boolean {
        childWidth = child.measuredWidth.toFloat()
        translationUnitsX = -childWidth
        Log.i("WIDTH", translationUnitsX.toString())
        return super.onMeasureChild(
            parent,
            child,
            parentWidthMeasureSpec,
            widthUsed,
            parentHeightMeasureSpec,
            heightUsed
        )
    }

    override fun onNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: ConstraintLayout,
        target: View,
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int,
        type: Int,
        consumed: IntArray
    ) {
        target as RecyclerView

        translationXRecycler += dxConsumed
        Log.i("consumed", translationXRecycler.toString())
        if (translationXRecycler <= -childWidth && translationXRecycler >= -1300f) {
            translationUnitsX += -dxConsumed
            //target.translationX = -translationXRecycler - childWidth
            target.translationX = MathUtils.clamp(-translationXRecycler - childWidth, 0f, childWidth)

            //child.translationX = MathUtils.clamp(translationUnitsX, -childWidth, 0f)



            child.translationX = translationUnitsX
//            if (child.translationX > 0f) {
//                child.translationX = 0f
//            }
        }
    }

}
