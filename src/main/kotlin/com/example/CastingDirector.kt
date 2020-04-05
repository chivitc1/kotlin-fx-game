package com.example

import kotlin.collections.ArrayList
import kotlin.collections.HashSet

class CastingDirector {
    val CURRENT_CAST = ArrayList<Actor>()
    val COLLIDE_CHECKLIST = ArrayList<Actor>()
    val REMOVE_ACTORS = HashSet<Actor>()

    fun addCurrentCast(vararg actors: Actor) {
        CURRENT_CAST.addAll(actors)
    }

    fun removeCurrentCast(vararg actors: Actor) {
        CURRENT_CAST.removeAll(actors)
    }

    fun resetCurrentCast() {
        CURRENT_CAST.clear()
    }

    fun resetCollideCheckList() {
        COLLIDE_CHECKLIST.clear()
        COLLIDE_CHECKLIST.addAll(CURRENT_CAST)
    }

    fun addToRemoveActors(vararg actors: Actor) {
        if (actors.size > 1) REMOVE_ACTORS.addAll(actors)
        else REMOVE_ACTORS.add(actors[0])
    }

    fun resetRemoveActors() {
        CURRENT_CAST.removeAll(REMOVE_ACTORS)
        REMOVE_ACTORS.clear()
    }
}