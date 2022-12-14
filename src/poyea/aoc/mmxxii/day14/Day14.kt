package poyea.aoc.mmxxii.day14

import kotlin.math.abs
import kotlin.math.sign
import poyea.aoc.utils.readInput

fun lineBetween(p1: Pair<Int, Int>, p2: Pair<Int, Int>): List<Pair<Int, Int>> {
    val dx = (p2.first - p1.first).sign
    val dy = (p2.second - p1.second).sign
    val steps = maxOf(abs(p1.first - p2.first), abs(p1.second - p2.second))
    return (1..steps).scan(p1) { last, _ -> Pair(last.first + dx, last.second + dy) }
}

fun solve(input: String, infinite: Boolean = false): Int {
    val rocks =
        input
            .split("\n")
            .flatMap { path ->
                path
                    .split(" -> ")
                    .map { it.split(",") }
                    .map { (x, y) -> Pair(x.toInt(), y.toInt()) }
                    .zipWithNext()
                    .flatMap { (p1, p2) -> lineBetween(p1, p2) }
            }
            .toSet()
    val rests = mutableSetOf<Pair<Int, Int>>()
    val initial = Pair(500, 0)
    val last = rocks.maxOf { it.second }
    var current = initial
    if (!infinite) {
        while (current.second != last) {
            val next_possible =
                listOf(
                        Pair(current.first, current.second + 1),
                        Pair(current.first - 1, current.second + 1),
                        Pair(current.first + 1, current.second + 1)
                    )
                    .firstOrNull { it !in rocks && it !in rests }
            current = next_possible ?: initial.also { rests.add(current) }
        }
    } else {
        val floor = 2 + rocks.maxOf { it.second }
        while (initial !in rests) {
            val next_possible =
                listOf(
                        Pair(current.first, current.second + 1),
                        Pair(current.first - 1, current.second + 1),
                        Pair(current.first + 1, current.second + 1)
                    )
                    .firstOrNull { it !in rocks && it !in rests }
            current =
                when (next_possible == null || next_possible.second == floor) {
                    true -> initial.also { rests.add(current) }
                    else -> next_possible
                }
        }
    }

    return rests.size
}

fun part1(input: String): Int {
    return solve(input)
}

fun part2(input: String): Int {
    return solve(input, true)
}

fun main() {
    println(part1(readInput("Day14")))
    println(part2(readInput("Day14")))
}
