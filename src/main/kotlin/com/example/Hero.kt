package com.example

import javafx.scene.image.Image

abstract class Hero: Actor {
    protected var vX: Double = 0.0
    protected var vY: Double = 0.0
    protected var lifeSpan: Double = 1000.0
    protected var damage: Double = 0.0
    protected var offsetX: Double = 0.0
    protected var offsetY: Double = 0.0
    protected var boundScale: Double = 0.0
    protected var boundRot: Double = 0.0
    protected var friction: Double = 0.0
    protected var gravity: Double = 0.0
    protected var bounce: Double = 0.0

    constructor(svgData: String,
                xLocation: Double,
                yLocation: Double,
                vararg spriteCels: Image): super(svgData, xLocation, yLocation, *spriteCels) {

        vX = 1.0
        vY = 1.0
    }

    open fun collide(other: Actor): Boolean {
        return false
    }
}