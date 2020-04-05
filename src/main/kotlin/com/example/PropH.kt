package com.example

import javafx.scene.image.Image

class PropH : Actor {
    constructor(svgData: String, xLocation: Double, yLocation: Double, vararg spriteCels: Image)
            : super(svgData, xLocation, yLocation, *spriteCels) {
        this.isFlipH = true
        spriteFrame.scaleX = -1.0
        spriteFrame.translateX = xLocation
        spriteFrame.translateY = yLocation
    }

    override fun update() {

    }
}