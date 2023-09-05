package uz.turgunboyevjurabek.barchartdiagram.madels

import android.graphics.Color
import uz.turgunboyevjurabek.barchartdiagram.R

class Diagram_Class {
    var viewColor: Int = R.color.black
    var itemName:String="JurabekC"
    var itemCount:String ="123,12ta"

    constructor(viewColor: Int, itemName: String, itemCount: String) {
        this.viewColor = viewColor
        this.itemName = itemName
        this.itemCount = itemCount
    }
}