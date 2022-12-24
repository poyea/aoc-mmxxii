package poyea.aoc.mmxxii.day24

import poyea.aoc.utils.readInput

data class Point(val x: Int, val y: Int) {
    operator fun plus(other: Point) = Point(x + other.x, y + other.y)

    fun neighbours(): List<Point> {
        return listOf(1, 0, -1, 0, 1).zipWithNext().map { (dx, dy) -> Point(x + dx, y + dy) }
    }
}

data class Blizzard(val point: Point, val dir: Point) {
    fun moved(boundary: Point): Blizzard {
        val next = point + dir
        return copy(
            point =
                when {
                    next.x == 0 -> Point(boundary.x - 1, next.y)
                    next.y == 0 -> Point(next.x, boundary.y - 1)
                    next.x == boundary.x -> Point(1, next.y)
                    next.y == boundary.y -> Point(next.x, 1)
                    else -> next
                }
        )
    }
}

data class Valley(val blizzards: List<Blizzard>, val boundary: Point) {
    val unsafePoints = blizzards.map { it.point }.toSet()

    fun moved() = copy(blizzards = blizzards.map { it.moved(boundary) })

    fun isSafe(point: Point): Boolean {
        return point !in unsafePoints &&
            point.x >= 1 &&
            point.x < boundary.x &&
            point.y >= 1 &&
            point.y < boundary.y
    }

    companion object {
        fun from(input: List<String>): Valley {
            return Valley(
                blizzards =
                    input.flatMapIndexed { y, line ->
                        line.mapIndexedNotNull { x, char ->
                            when (char) {
                                '^' -> Blizzard(Point(x, y), Point(0, -1))
                                'v' -> Blizzard(Point(x, y), Point(0, 1))
                                '<' -> Blizzard(Point(x, y), Point(-1, 0))
                                '>' -> Blizzard(Point(x, y), Point(1, 0))
                                else -> null
                            }
                        }
                    },
                boundary = Point(input.last().lastIndex, input.lastIndex)
            )
        }
    }
}

fun go(newValley: Valley, start: Point, end: Point): Pair<Int, Valley> {
    var minutes = 0
    var valley = newValley
    var batch = setOf(start)

    while (true) {
        ++minutes
        valley = valley.moved()
        batch = buildSet {
            batch.forEach { current ->
                addAll(
                    current
                        .neighbours()
                        .onEach { if (it == end) return minutes to valley }
                        .filter(valley::isSafe)
                )
                if (valley.isSafe(current) || current == start) add(current)
            }
        }
    }
}

fun part1(input: String): Int {
    val lines = input.lines()
    val valley = Valley.from(lines)
    val start = Point(lines.first().indexOf('.'), 0)
    val end = Point(lines.last().indexOf('.'), lines.lastIndex)
    return go(valley, start, end).first
}

fun part2(input: String): Int {
    val lines = input.lines()
    val valley = Valley.from(lines)
    val start = Point(lines.first().indexOf('.'), 0)
    val end = Point(lines.last().indexOf('.'), lines.lastIndex)
    val first = go(valley, start, end)
    val second = go(first.second, end, start)
    val third = go(second.second, start, end)
    return first.first + second.first + third.first
}

fun main() {
    println(part1(readInput("Day24")))
    println(part2(readInput("Day24")))
}
