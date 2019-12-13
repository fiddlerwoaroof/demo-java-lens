package com.company.main

import com.company.main.Main.Person.*
import java.util.concurrent.atomic.AtomicReference

fun main() {
    val x = Main.Person("Bob", "Johnson")
    val y = Main.Person("Alice", "Johnson")
    val xy = Main.Relationship(x, y, "spouse")

    val xyYouName = xy.you.._name

    println("name: ${x.name()}")
    println("status: ${x.status()}")
    println("youLens name: ${xyYouName()}")

    println("youLens name: ${xyYouName()}")

    val personRef = AtomicReference(x)

    println("ref name: ${personRef.get().name()}")
    personRef.updateAndGet { t ->
        t.status.set("feelin'g good")
    }
    println("ref name: ${personRef.get().name()}")
}