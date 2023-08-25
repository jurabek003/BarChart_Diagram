package uz.turgunboyevjurabek.barchartdiagram

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import uz.turgunboyevjurabek.barchartdiagram.databinding.ActivityMainBinding
import kotlin.math.min

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    lateinit var barChart: BarChart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)



        barChart = binding.barChart

        // on below line we are calling get bar
        // chart data to add data to our array list


        val dates = listOf(
            "July",
            "August",
            "September",
            "October"
        )
        val values = listOf(10f, 50f, 150f, 120f)
        // BarEntry ma'lumotlarini tayyorlash
        val entries: ArrayList<BarEntry> = ArrayList()
        for (i in values.indices) {
            entries.add(BarEntry(i.toFloat(), values[i]))
        }

        // X-osi bo'yicha datalarni joylash
        val barDataSet = BarDataSet(entries, "Ma'lumotlar")
        barDataSet.color = Color.BLUE
        // X-osi bo'yicha nomlarni joylash

        val xAxisLabels = dates.toTypedArray()
        val xAxis = barChart.xAxis
        xAxis.valueFormatter = IndexAxisValueFormatter(xAxisLabels)
        xAxis.position = XAxis.XAxisPosition.BOTTOM

        // BarChart konfiguratsiyalari
        val barData = BarData(barDataSet)
        barChart.data = barData
        barChart.setFitBars(true)
        barChart.description.isEnabled = true
        barChart.animateY(1000)
        barChart.axisRight.isEnabled=false
        val leftAxis = barChart.axisLeft

        // Diapazonlarni sozlash
        leftAxis.axisMinimum = 0f // Minimum qiymat
        leftAxis.axisMaximum = 100f // Maksimum qiymat

// Diapazon belgilari
        leftAxis.granularity = 2f // Belgilashning o'zgarish qadami

        // Tashqi chiziqlarni chizishni o'chirish
        leftAxis.setDrawAxisLine(false)
        leftAxis.setDrawGridLines(false)

// Tashqi belgilarni sozlash
//        leftAxis.setDrawLabels(true)
//        leftAxis.textSize = 12f


// Qiymatlar formatini o'zgartirish
        leftAxis.valueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                return when {
                    value >= 1000000 -> {
                        val son1=value.toInt()
                        val million="${son1 / 1000000}"
                        return million+"M"
                    } // 1M = 1000000

                    value >= 1000 -> {
                        val son2=value.toInt()
                        val ming="${son2 / 1000}"
                        return ming+"K"
                    } // 1K = 1000
                    else -> value.toInt().toString()
                }
            }
        }



        // Barchartni yangilash
        barChart.invalidate()
    }

    //maxsulot sotuv ulushi// products-selas-data2
    // ekranda korin yapti lekin figmadagidek emas

    }