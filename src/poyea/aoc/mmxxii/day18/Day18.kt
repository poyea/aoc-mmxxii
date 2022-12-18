package poyea.aoc.mmxxii.day18

import poyea.aoc.utils.readInput

data class Point(val x: Int, val y: Int, val z: Int) {
    operator fun plus(other: Point) = Point(x + other.x, y + other.y, z + other.z)
    
    fun neighbours() = listOf(
        copy(x = x - 1),
        copy(x = x + 1),
        copy(y = y - 1),
        copy(y = y + 1),
        copy(z = z - 1),
        copy(z = z + 1),
    )
}

fun part1(input: String): Int {
    val listOfPoints = input.split("\n").map { it -> 
        val (x, y, z) = it.split(",", limit = 3)
        Point(x.toInt(), y.toInt(), z.toInt())
    }
    var total = 0
    for (point in listOfPoints) {
        total += point.neighbours().count { it !in listOfPoints }
    }
    return total
}

fun part2(input: String): Int {
    val listOfPoints = input.split("\n").map { it -> 
        val (x, y, z) = it.split(",", limit = 3)
        Point(x.toInt(), y.toInt(), z.toInt())
    }
    val minX = listOfPoints.minBy { it.x }.x
    val maxX = listOfPoints.maxBy { it.x }.x
    val minY = listOfPoints.minBy { it.y }.y
    val maxY = listOfPoints.maxBy { it.y }.y
    val minZ = listOfPoints.minBy { it.z }.z
    val maxZ = listOfPoints.maxBy { it.z }.z

    val outside = buildSet {
        val queue = mutableListOf(Point(minX - 1, minY - 1, minZ - 1).also { add(it) })
        while (queue.isNotEmpty()) {
            for (neighbour in queue.removeLast().neighbours()) {
                if (neighbour.x in minX - 1..maxX + 1
                    && neighbour.y in minY - 1..maxY + 1
                    && neighbour.z in minZ - 1..maxZ + 1
                    && neighbour !in listOfPoints
                ) {
                    if (add(neighbour)) {
                        queue.add(neighbour)
                    }
                }
            }
        }
    }
    var total = 0
    for (point in listOfPoints) {
        total += point.neighbours().count { it in outside }
    }
    return total
}

fun main() {
    println(part1(readInput("Day18")))
    println(part2(readInput("Day18")))
}
