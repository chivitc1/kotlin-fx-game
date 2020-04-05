package com.example

import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.shape.SVGPath
import java.util.*
import kotlin.collections.ArrayList

abstract class Actor {
    protected val imageStates = ArrayList<Image>()
    var spriteFrame: ImageView
    var spriteBound: SVGPath
    protected var iX: Double = 0.0
    var iY: Double = 0.0
    protected var pX: Double = 0.0
    protected var pY: Double = 0.0
    protected var isAlive = false
    protected var isFixed = false
    protected var isBonus = false
    protected var hasValu = false
    protected var isFlipV = false
    protected var isFlipH = false

    constructor(svgData: String,
                xLocation: Double,
                yLocation: Double,
                vararg spriteCels: Image) {
        spriteBound = SVGPath()
        spriteBound.content = svgData
        spriteFrame = ImageView(spriteCels[0])
        imageStates.addAll(Arrays.asList(*spriteCels))
        iX = xLocation
        iY = yLocation
    }

    abstract fun update()
}