package com.example

import javafx.animation.AnimationTimer
import javafx.geometry.Pos

class GamePlayLoop(
        private val invinciBagel: InvinciBagel
): AnimationTimer() {
    var location: Pos? = null

    override fun handle(now: Long) {
        invinciBagel.iBagel?.update()
        invinciBagel.iBeagle?.update()
    }
}