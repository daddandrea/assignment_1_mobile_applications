package com.example.myapplication

import org.junit.Test

import org.junit.Assert.*

open class Parent {
    open fun presentSelf() {
        println("I'm a parent")
    }
}
class Child: Parent() {
    override fun presentSelf() {
        println("I'm a child")
    }
}

class ExampleUnitTest {
    @Test
    fun instanceCreation() {
        println(Child())
    }

    @Test
    fun variableCreation() {
        val orangeCount: Int = 0    // val is immutable
        var appleCount = 0
        appleCount = 1
        println(appleCount)
    }

    @Test
    fun inheritanceTest() {
        var personA: Parent = Parent()
        personA.presentSelf()
        personA = Child()
        personA.presentSelf()
    }
}