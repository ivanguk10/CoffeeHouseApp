package com.ivanguk10.coffeehouse.ui

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.core.content.ContextCompat
import com.ivanguk10.coffeehouse.R

class GantWeatherView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var valueAnimator: ValueAnimator? = null

    private var nightPercent: Int = 0
    private var morningPercent: Int = 0
    private var afternoonPercent: Int = 0
    private var eveningPercent: Int = 0

    private val tableStart = 100f
    private val columnValueMargin = 30f
    private val topOfTable = 40f
    private val bottomOfTable = 540f
    private val bottomOfValueColumn = bottomOfTable - 2f
    private val radius = 30f
    private val timeValueMargin = 50f

    private var nightColumn = bottomOfTable
    private var morningColumn = bottomOfTable
    private var afternoonColumn = bottomOfTable
    private var eveningColumn = bottomOfTable

    private lateinit var nightColumnPaint: Paint
    private lateinit var morningColumnPaint: Paint
    private lateinit var afternoonColumnPaint: Paint
    private lateinit var eveningColumnPaint: Paint

    private var percentAlpha = 0

    private val linesOfTable = Paint().apply {
        color = ContextCompat.getColor(context, R.color.table)
        strokeWidth = resources.getDimension(R.dimen.width_1_5dp)
    }

    private val greenColumn = Paint().apply {
        style = Paint.Style.FILL
        shader = LinearGradient(
            0f,
            0f,
            0f,
            bottomOfTable,
            intArrayOf(
                ContextCompat.getColor(context, R.color.greenColumn),
                ContextCompat.getColor(context, R.color.greenColumnDark)
            ),
            floatArrayOf(0.3f, 0.9f),
            Shader.TileMode.REPEAT
        )
    }


    private val yellowColumn = Paint().apply {
        style = Paint.Style.FILL
        shader = LinearGradient(
            0f,
            150f,
            0f,
            bottomOfTable,
            intArrayOf(
                ContextCompat.getColor(context, R.color.yellowColumn),
                ContextCompat.getColor(context, R.color.yellowColumnDark)
            ),
            floatArrayOf(0.3f, 0.9f),
            Shader.TileMode.REPEAT
        )
    }

    private val redColumn = Paint().apply {
        style = Paint.Style.FILL
        shader = LinearGradient(
            0f,
            300f,
            0f,
            bottomOfTable,
            intArrayOf(
                ContextCompat.getColor(context, R.color.redColumn),
                ContextCompat.getColor(context, R.color.redColumnDark)
            ),
            floatArrayOf(0.3f, 0.9f),
            Shader.TileMode.REPEAT
        )
    }

    private val columnDark = Paint().apply {
        style = Paint.Style.FILL
        color = ContextCompat.getColor(context, R.color.columnDark)
    }

    private val tableStroke = Paint().apply {
        style = Paint.Style.STROKE
        color = ContextCompat.getColor(context, R.color.table)
        strokeWidth = resources.getDimension(R.dimen.width_1_5dp)
    }

    private val textPaint = Paint().apply {
        textSize = resources.getDimension(R.dimen.width_16dp)
        color = ContextCompat.getColor(context, R.color.tableText)
    }

    val percentPaint = Paint().apply {
        textSize = resources.getDimension(R.dimen.width_14dp)
        color = Color.WHITE
        alpha = percentAlpha
    }

    private val uncheckedPaint = Paint().apply {
        style = Paint.Style.FILL
        color = ContextCompat.getColor(context, R.color.unchecked)
    }

    fun load() {
        if (nightPercent != 0 && morningPercent != 0 && afternoonPercent != 0 && eveningPercent != 0) {
            nightColumnPaint = valueColumnPercentColor(nightPercent)
            morningColumnPaint = valueColumnPercentColor(morningPercent)
            afternoonColumnPaint = valueColumnPercentColor(afternoonPercent)
            eveningColumnPaint = valueColumnPercentColor(eveningPercent)

            animateNightColumn()
            animateMorningColumn()
            animateAfternoonColumn()
            animateEveningColumn()
        }
    }

    override fun onDraw(canvas: Canvas?) {
        val columnWidth = (width - tableStart) / 4

        canvas?.firstTableColumn(columnWidth)
        canvas?.thirdTableColumn(columnWidth)
        canvas?.drawHorizontalLines()
        canvas?.drawVerticalLine()
        canvas?.tableStroke()

        canvas?.tablePercentValues()
        canvas?.tableTimeValues(columnWidth)

        canvas?.drawNightColumn(columnWidth, nightColumnPaint)
        canvas?.drawMorningColumn(columnWidth, morningColumnPaint)
        canvas?.drawAfternoonColumn(columnWidth, afternoonColumnPaint)
        canvas?.drawEveningColumn(columnWidth, eveningColumnPaint)

        canvas?.drawColumnsPercents()

        super.onDraw(canvas)
    }

    private fun Canvas.drawColumnsPercents() {
        this.drawText("$nightPercent%", 156f, nightColumn + 50f, percentPaint)
        this.drawText("$morningPercent%", 376f, morningColumn + 50f, percentPaint)
        this.drawText("$afternoonPercent%", 586f, afternoonColumn + 50f, percentPaint)
        this.drawText("$eveningPercent%", 796f, eveningColumn + 50f, percentPaint)

    }

    private fun Canvas.tableTimeValues(columnWidth: Float) {
        this.drawText("06:00", tableStart + columnWidth - timeValueMargin, bottomOfTable + timeValueMargin, textPaint)
        this.drawText("12:00", tableStart + 2 * columnWidth - timeValueMargin, bottomOfTable + timeValueMargin, textPaint)
        this.drawText("18:00", tableStart + 3 * columnWidth - timeValueMargin, bottomOfTable + timeValueMargin, textPaint)
    }

    private fun Canvas.tablePercentValues() {
        this.drawText("0", 50f, bottomOfTable, textPaint)
        this.drawText("20", 30f, 450f, textPaint)
        this.drawText("40", 30f, 350f, textPaint)
        this.drawText("60", 30f, 250f, textPaint)
        this.drawText("80", 30f, 150f, textPaint)
        this.drawText("100", 10f, 60f, textPaint)
    }

    private fun Canvas.firstTableColumn(columnWidth: Float) {
        val leftOfRound = tableStart
        val leftOfRectangle = tableStart + columnWidth - radius
        val right = columnWidth + tableStart
        val bottom = bottomOfTable

        this.drawRoundRect(leftOfRound, topOfTable, right, bottom, radius, radius, columnDark)
        this.drawRect(leftOfRectangle, topOfTable, right, bottom, columnDark)
    }

    private fun Canvas.thirdTableColumn(columnWidth: Float) {
        val left = tableStart + columnWidth * 2
        val top = topOfTable
        val right = tableStart + columnWidth * 3
        val bottom = bottomOfTable

        this.drawRect(left, top, right, bottom, columnDark)
    }

    private fun Canvas.tableStroke() {
        val left = tableStart
        val right = width.toFloat() - 4f
        val top = topOfTable
        this.drawRoundRect(left, top, right, bottomOfTable, radius, radius, tableStroke)
    }

    private fun Canvas.drawHorizontalLines() {
        var y = topOfTable
        val stopX = width.toFloat()
        for (i in 1..4) {
            y += 100f
            this.drawLine(tableStart, y, stopX, y, linesOfTable)
        }
    }

    private fun Canvas.drawVerticalLine() {
        var x: Float
        val widthToAdd = (width - tableStart) / 4
        val y = topOfTable
        val stopY = bottomOfTable
        for (i in 1..3) {
            x = widthToAdd * i
            this.drawLine(x + tableStart, y, x + tableStart, stopY, linesOfTable)
        }
    }
/////////////////////////////
    private fun Canvas.drawNightColumn(columnWidth: Float, paint: Paint) {
        val left = tableStart + columnValueMargin
        val right = tableStart + columnWidth - columnValueMargin

        this.drawRoundRect(left, nightColumn, right, bottomOfValueColumn, radius, radius, paint)
        this.drawRect(left, bottomOfValueColumn - radius, right, bottomOfValueColumn, paint)
    }
/////////////////////////////
    private fun Canvas.drawMorningColumn(columnWidth: Float, paint: Paint) {
        val left = tableStart + columnWidth + columnValueMargin
        val right = tableStart + 2 * columnWidth - columnValueMargin

        this.drawRoundRect(left, morningColumn, right, bottomOfValueColumn, radius, radius, paint)
        this.drawRect(left, bottomOfValueColumn - radius, right, bottomOfValueColumn, paint)
    }

    private fun Canvas.drawAfternoonColumn(columnWidth: Float, paint: Paint) {
        val left = tableStart + 2 * columnWidth + columnValueMargin
        val right = tableStart + 3 * columnWidth - columnValueMargin

        this.drawRoundRect(left, afternoonColumn, right, bottomOfValueColumn, radius, radius, paint)
        this.drawRect(left, bottomOfValueColumn - radius, right, bottomOfValueColumn, paint)
    }

    private fun Canvas.drawEveningColumn(columnWidth: Float, paint: Paint) {
        val left = tableStart + 3 * columnWidth + columnValueMargin
        val right = tableStart + 4 * columnWidth - columnValueMargin

        this.drawRoundRect(left, eveningColumn, right, bottomOfValueColumn, radius, radius, paint)
        this.drawRect(left, bottomOfValueColumn - radius, right, bottomOfValueColumn, paint)
    }

    private fun percentValueConverter(percentValue: Int): Float {
        val allHeight = 500f
        val allPercents = 100
        val heightValue: Float

        if (percentValue < 15) {
            heightValue = allHeight - (15 * allHeight) / allPercents
        } else {
            heightValue = allHeight - (percentValue * allHeight) / allPercents
        }
        return heightValue + 40f
    }

    private fun valueColumnPercentColor(percentValue: Int): Paint {
        return when {
            percentValue < 38 -> {
                redColumn
            }
            percentValue <= 70 -> {
                yellowColumn
            }
            percentValue > 70 -> {
                greenColumn
            }
            else -> redColumn
        }
    }

    private fun animateNightColumn() {
        valueAnimator = ValueAnimator.ofFloat(bottomOfTable, percentValueConverter(nightPercent)).apply {
            duration = 1000
            interpolator = LinearInterpolator()
            addUpdateListener { valueAnimator ->
                nightColumn = valueAnimator.animatedValue as Float
                invalidate()
            }
        }
        valueAnimator?.start()
    }

    private fun animateMorningColumn() {
        valueAnimator = ValueAnimator.ofFloat(bottomOfTable, percentValueConverter(morningPercent)).apply {
            duration = 1000
            interpolator = LinearInterpolator()
            addUpdateListener { valueAnimator ->
                morningColumn = valueAnimator.animatedValue as Float
                invalidate()
            }
        }
        valueAnimator?.start()
    }

    private fun animateAfternoonColumn() {
        valueAnimator = ValueAnimator.ofFloat(bottomOfTable, percentValueConverter(afternoonPercent)).apply {
            duration = 1000
            interpolator = LinearInterpolator()
            addUpdateListener { valueAnimator ->
                afternoonColumn = valueAnimator.animatedValue as Float
                invalidate()
            }
        }
        valueAnimator?.start()
    }

    private fun animateEveningColumn() {
        valueAnimator = ValueAnimator.ofFloat(bottomOfTable, percentValueConverter(eveningPercent)).apply {
            duration = 1000
            interpolator = LinearInterpolator()
            addUpdateListener { valueAnimator ->
                eveningColumn = valueAnimator.animatedValue as Float
                invalidate()
            }
        }
        valueAnimator?.start()
    }

    fun setNightPercent(percentValue: Int) {
        this.nightPercent = percentValue
        invalidate()
        requestLayout()
    }
    fun setMorningPercent(percentValue: Int) {
        this.morningPercent = percentValue
        invalidate()
        requestLayout()
    }
    fun setAfternoonPercent(percentValue: Int) {
        this.afternoonPercent = percentValue
        invalidate()
        requestLayout()
    }
    fun setEveningPercent(percentValue: Int) {
        this.eveningPercent = percentValue
        invalidate()
        requestLayout()
    }

    fun checkEveningColumnPaint() {
        this.nightColumnPaint = uncheckedPaint
        this.morningColumnPaint = uncheckedPaint
        this.afternoonColumnPaint = uncheckedPaint
        this.eveningColumnPaint = valueColumnPercentColor(eveningPercent)
        invalidate()
    }

    fun checkAfternoonColumnPaint() {
        this.nightColumnPaint = uncheckedPaint
        this.morningColumnPaint = uncheckedPaint
        this.afternoonColumnPaint = valueColumnPercentColor(afternoonPercent)
        this.eveningColumnPaint = uncheckedPaint
        invalidate()
    }

    fun checkMorningColumnPaint() {
        this.nightColumnPaint = uncheckedPaint
        this.morningColumnPaint = valueColumnPercentColor(morningPercent)
        this.afternoonColumnPaint = uncheckedPaint
        this.eveningColumnPaint = uncheckedPaint
        invalidate()
    }

    fun checkNightColumnPaint() {
        this.nightColumnPaint = valueColumnPercentColor(nightPercent)
        this.morningColumnPaint = uncheckedPaint
        this.afternoonColumnPaint = uncheckedPaint
        this.eveningColumnPaint = uncheckedPaint
        invalidate()
    }
}