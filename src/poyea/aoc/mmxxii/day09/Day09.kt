package poyea.aoc.mmxxii.day09

import poyea.aoc.utils.readInput
import kotlin.math.abs
import kotlin.math.sign

data class Point(val x: Int, val y: Int) {
    fun moveTowards(other: Point) = this + Point((other.x - x).sign, (other.y - y).sign)

    fun isNear(other: Point) = abs(other.x - x) < 2 && abs(other.y - y) < 2

    operator fun plus(other: Point) = Point(other.x + x, other.y + y)
}

fun part1(input: String): Int {
    val points = mutableSetOf<Point>()
    var head = Point(0, 0)
    var tail = Point(0, 0)
    input.split("\n").forEach { action ->
        val items = action.split(" ")
        val direction = items[0]
        val units = items[1].toInt()
        for (i in 1..units) {
            head += when (direction) {
                "U" -> Point(0, 1)
                "D" -> Point(0, -1)
                "L" -> Point(-1, 0)
                "R" -> Point(1, 0)
                else -> Point(0, 0)
            }
            if (!tail.isNear(head)) {
                tail = tail.moveTowards(head)
            }
            points.add(tail)
        }
    }
    return points.size
}

fun part2(input: String): Int {
    val knots = MutableList(10) { Point(0, 0) }
    val points = mutableSetOf<Point>()
    input.split("\n").forEach { action ->
        val items = action.split(" ")
        val direction = items[0]
        val units = items[1].toInt()
        for (i in 1..units) {
            knots[0] += when (direction) {
                "U" -> Point(0, 1)
                "D" -> Point(0, -1)
                "L" -> Point(-1, 0)
                "R" -> Point(1, 0)
                else -> Point(0, 0)
            }
            knots.indices.windowed(2) { (head, tail) ->
                if (!knots[tail].isNear(knots[head])) {
                    knots[tail] = knots[tail].moveTowards(knots[head])
                }
            }
            points.add(knots.last())
        }
    }
    return points.size
}

fun main() {
    println(part1(readInput("Day09")))
    println(part2(readInput("Day09")))
}
