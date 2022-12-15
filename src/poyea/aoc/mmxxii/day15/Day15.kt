package poyea.aoc.mmxxii.day15

import kotlin.math.abs
import poyea.aoc.utils.readInput

data class Beacon(val x: Int, val y: Int)

data class Sensor(
    val x: Int,
    val y: Int,
    val min_x: Int,
    val max_x: Int,
    val min_y: Int,
    val max_y: Int
) {
    companion object {
        fun of(x: Int, y: Int, beacon: Beacon): Sensor {
            val distance = abs(beacon.x - x) + abs(beacon.y - y)
            return Sensor(
                x = x,
                y = y,
                min_x = x - distance,
                max_x = x + distance,
                min_y = y - distance,
                max_y = y + distance
            )
        }
    }

    fun canCover(x: Int, y: Int): Boolean {
        if (x in min_x..max_x && y in min_y..max_y) {
            val rowDiff = abs(this.y - y)
            return x in (min_x + rowDiff)..(max_x - rowDiff)
        }
        return false
    }

    fun intervalEnds(y: Int): Int {
        return max_x - abs(this.y - y) + 1
    }
}

fun part1(input: String): Int {
    val sensors = mutableListOf<Sensor>()
    val beacons = mutableListOf<Beacon>()
    input
        .split("\n")
        .map { Regex("-?\\d+").findAll(it).map { it.value }.toList() }
        .map {
            beacons.add(Beacon(it[2].toInt(), it[3].toInt()))
            sensors.add(Sensor.of(it[0].toInt(), it[1].toInt(), beacons.last()))
        }

    val (min_x, max_x) = sensors.minOf { it.min_x } to sensors.maxOf { it.max_x }
    return (min_x..max_x)
        .fold(mutableListOf<Pair<Int, Int>>()) { coveredPoints, x ->
            if (sensors.any { it.canCover(x, 2000000) }) {
                coveredPoints.add(x to 2000000)
            }
            coveredPoints
        }
        .filter { point ->
            sensors.none { it.x == point.first && it.y == point.second } &&
                beacons.none { it.x == point.first && it.y == point.second }
        }
        .size
}

fun part2(input: String): Long {
    val sensors = mutableListOf<Sensor>()
    val beacons = mutableListOf<Beacon>()
    input
        .split("\n")
        .map { Regex("-?\\d+").findAll(it).map { it.value }.toList() }
        .map {
            beacons.add(Beacon(it[2].toInt(), it[3].toInt()))
            sensors.add(Sensor.of(it[0].toInt(), it[1].toInt(), beacons.last()))
        }

    (0..4000000).map { y ->
        var x = 0
        while (x < 4000000) {
            x =
                sensors.firstOrNull { it.canCover(x, y) }?.intervalEnds(y)
                    ?: return x * 4000000L + y
        }
    }

    return 1L
}

fun main() {
    println(part1(readInput("Day15")))
    println(part2(readInput("Day15")))
}
