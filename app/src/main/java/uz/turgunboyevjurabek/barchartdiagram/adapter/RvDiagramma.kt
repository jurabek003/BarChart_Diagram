package uz.turgunboyevjurabek.barchartdiagram.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import uz.turgunboyevjurabek.barchartdiagram.databinding.DiagramItemRvBinding
import uz.turgunboyevjurabek.barchartdiagram.madels.Diagram_Class

class RvDiagramma(val arrayList: ArrayList<Diagram_Class>):RecyclerView.Adapter<RvDiagramma.Vh>() {
    inner class Vh(val diagramItemRvBinding: DiagramItemRvBinding):ViewHolder(diagramItemRvBinding.root){
        fun onBind(diagramClass: Diagram_Class){
            diagramItemRvBinding.itemCount.text=diagramClass.itemCount.toString()+"ta"
            diagramItemRvBinding.itemName.text=diagramClass.itemName.toString()
            diagramItemRvBinding.itemViewColor.setBackgroundResource(diagramClass.viewColor)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(DiagramItemRvBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(arrayList[position])
    }

}
