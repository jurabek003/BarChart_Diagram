package uz.turgunboyevjurabek.barchartdiagram

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.anychart.AnyChart
import com.anychart.AnyChartView
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.charts.Pie
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.utils.MPPointF
import uz.turgunboyevjurabek.barchartdiagram.adapter.RvDiagramma
import uz.turgunboyevjurabek.barchartdiagram.databinding.ActivityMainBinding
import uz.turgunboyevjurabek.barchartdiagram.madels.Diagram_Class

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    lateinit var barChart: BarChart
    private lateinit var pieChart: PieChart
    private lateinit var anyChartView: AnyChartView
    private lateinit var rvDiagramma: RvDiagramma
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        barChartFunction()
        pieChartFunction()
        rvAdapter()
    }

    private fun barChartFunction() {
        barChart = binding.barChart

        // Barchartni to'ldirish uchun ma'lumotlar
        val entries = mutableListOf<BarEntry>()
        entries.add(BarEntry(1f, 5f))
        entries.add(BarEntry(2f, 10f))
        entries.add(BarEntry(3f, 8f))
        entries.add(BarEntry(4f, 12f))


        val dates = listOf(
            "January",
            "February",
            "March",
            "April",
        )

        val dataSet = BarDataSet(entries, "Sample Data")
        dataSet.colors = mutableListOf(Color.WHITE)
        dataSet.setDrawValues(false)

        // X-osi bo'yicha nomlarni joylash
        val xAxisLabels = dates.toTypedArray()
        val xAxis = barChart.xAxis
        xAxis.valueFormatter = IndexAxisValueFormatter(xAxisLabels)
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.isGranularityEnabled = true
        xAxis.granularity = 1f


        val dataSets = mutableListOf<IBarDataSet>()
        dataSets.add(dataSet)

        val data = BarData(dataSets)
        barChart.data = data
        data.barWidth=0.15f
        barChart.setFitBars(true)
        barChart.description.isEnabled = false
        barChart.animateY(1000)

        // Maxsus rendererni yaratish va sozlash
        val customRenderer = CustomBarChartRender(barChart, barChart.animator,
            barChart.viewPortHandler)
        customRenderer.setRadius(40) // radiusni belgilash
        barChart.renderer = customRenderer

        //  BarChart konfiguratsiyalari
        barChart.setDrawBarShadow(false)
        barChart.setDrawValueAboveBar(false)
        barChart.setDrawGridBackground(false)
        barChart.description.isEnabled = false
        barChart.legend.isEnabled = false
        barChart.axisRight.isEnabled=false
        barChart.setDrawValueAboveBar(false)
        barChart.setDrawGridBackground(false)
        barChart.setBackgroundResource(R.drawable.bacround)

        val leftAxis = barChart.axisLeft

        // Diapazon belgilari
        leftAxis.granularity = 0.2f // Belgilashning o'zgarish qadami

        // Tashqi chiziqlarni chizishni o'chirish
        leftAxis.setDrawAxisLine(false)
        leftAxis.setDrawGridLines(false)



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
    private fun pieChartFunction() {
        pieChart = binding.myPieChart
        pieChart.setUsePercentValues(true)
        pieChart.getDescription().setEnabled(false)

        // on below line we are setting hole

        pieChart.setDrawHoleEnabled(false)  // o'rtadagi yumaloqni boshqarish


        // on below line we are setting animation for our pie chart
        pieChart.animateY(1400, Easing.EaseInOutQuad)

        // on below line we are disabling our legend for pie chart
        pieChart.legend.isEnabled = true
        pieChart.setEntryLabelColor(Color.WHITE)
        pieChart.setEntryLabelTextSize(12f)

        // adding data to it to display in pie chart
        val entries: ArrayList<PieEntry> = ArrayList()
        entries.add(PieEntry(50f))
        entries.add(PieEntry(30f))
        entries.add(PieEntry(10f))
        entries.add(PieEntry(40f))

        // on below line we are setting pie data set
        val dataSet = PieDataSet(entries, "Mobile OS")

        dataSet.sliceSpace = 0f
        dataSet.iconsOffset = MPPointF(0f, 40f)
        dataSet.selectionShift = 7f

        // add a lot of colors to list
        val colors: ArrayList<Int> = ArrayList()
        colors.add(resources.getColor(R.color.purple_200))
        colors.add(resources.getColor(R.color.yellow))
        colors.add(resources.getColor(R.color.red))
        colors.add(resources.getColor(R.color.black))
        // on below line we are setting colors.
        dataSet.colors = colors

        // on below line we are setting pie data set
        val data = PieData(dataSet)
        data.setValueFormatter(PercentFormatter())
        data.setValueTextSize(10f)
        data.setValueTextColor(Color.WHITE)
        pieChart.setData(data)


        // loading chart
        pieChart.invalidate()


    }
    private fun rvAdapter(){
        val list=ArrayList<Diagram_Class>()
        list.add(Diagram_Class(R.color.black,"Jurabek 1","123,45"))
        list.add(Diagram_Class(R.color.red,"Jurabek 2","223,45"))
        list.add(Diagram_Class(R.color.yellow,"Jurabek 3","323,45"))
        list.add(Diagram_Class(R.color.purple_200,"Jurabek 4","423,45"))
        list.add(Diagram_Class(R.color.kulrang,"Jurabek 5","523,45"))
        list.add(Diagram_Class(androidx.appcompat.R.color.abc_background_cache_hint_selector_material_dark,"Jurabek 6","623,45"))

        rvDiagramma=RvDiagramma(list)
        binding.diagramRv.adapter=rvDiagramma

    }




}