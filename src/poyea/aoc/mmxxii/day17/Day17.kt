package poyea.aoc.mmxxii.day17

import poyea.aoc.utils.readInput

data class Point(val x: Int, val y: Int) {
    operator fun plus(other: Point) = Point(x + other.x, y + other.y)
}

data class Shape(val points: List<Point>) {
    fun push(jet: Char) = when (jet) {
        '>' -> copy(points = points.map { it.copy(x = it.x + 1) })
        '<' -> copy(points = points.map { it.copy(x = it.x - 1) })
        else -> copy(points)
    }

    fun fall() = copy(points = points.map { it.copy(y = it.y - 1) })

    companion object {
        fun generate(order: Long): Shape = Shape(
            when (order) {
                0L -> listOf(Point(2, 0), Point(3, 0), Point(4, 0), Point(5, 0))
                1L -> listOf(Point(3, 0), Point(2, 1), Point(3, 1), Point(4, 1), Point(3, 2))
                2L -> listOf(Point(2, 0), Point(3, 0), Point(4, 0), Point(4, 1), Point(4, 2))
                3L -> listOf(Point(2, 0), Point(2, 1), Point(2, 2), Point(2, 3))
                4L -> listOf(Point(2, 0), Point(2, 1), Point(3, 0), Point(3, 1))
                else -> listOf()
            }
        )
    }
}

data class State(val top: List<Int>, val jetIndex: Int, val shapeIndex: Long)

class Cave(private val jets: String) {
    private val points = (0..6).map { Point(it, -1) }.toMutableSet()

    private var currentJet = 0
        set(value) {
            field = value % jets.length
        }

    private var currentShape = 0L
        set(value) {
            field = value % 5
        }

    fun height() = points.maxOf { it.y + 1 }

    fun addOne() {
        var shape = nextShape()
        while (true) {
            val pushed = shape.push(jets[currentJet++])
            if (!pushed.hit()) shape = pushed

            val fallen = shape.fall()
            if (fallen.hit()) break else shape = fallen
        }
        points += shape.points
    }

    fun currentState() = State(
        top = points
            .groupBy { it.x }
            .entries
            .sortedBy { it.key }
            .map { it.value.maxBy { point -> point.y } }
            .let { points ->
                points.map { point -> point.y - height() }
            },
        jetIndex = currentJet,
        shapeIndex = currentShape
    )

    private fun nextShape(): Shape {
        val shape = Shape.generate(currentShape++)
        val bottom = (points.maxOfOrNull { p -> p.y } ?: -1) + 4
        return shape.copy(points = shape.points.map { it + Point(0, bottom) })
    }

    private fun Shape.hit() = points.intersect(this@Cave.points).isNotEmpty() || points.any { it.x !in (0..6) }
}

fun simulate(jets: String, numberOfRocks: Long): Long {
    val cave = Cave(jets)
    val cache = mutableMapOf<State, Pair<Long, Int>>()

    for (r in 0..numberOfRocks) {
        cave.addOne()
        val state = cave.currentState()

        if (state in cache) {
            val (prevRocks, prevHeight) = cache.getValue(state)
            val diffRocks = numberOfRocks - r
            val totalRocks = r - prevRocks
            val loopsRemaining = diffRocks / totalRocks
            val rocksRemaining = diffRocks % totalRocks
            val heightRemaining =
                    cache.values.first { (r, _) -> r == prevRocks + (rocksRemaining - 1) }.second - prevHeight

            val height = (cave.height() - prevHeight)
            return cave.height() + loopsRemaining * height + heightRemaining
        }

        cache[state] = Pair(r, cave.height())
    }

    return -1
}

fun part1(input: String): Long {
    return simulate(input, 2022)
}

fun part2(input: String): Long {
    return simulate(input, 1_000_000_000_000)
}

fun main() {
    println(part1(readInput("Day17")))
    println(part2(readInput("Day17")))
}
