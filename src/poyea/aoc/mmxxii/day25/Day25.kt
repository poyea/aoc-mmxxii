package poyea.aoc.mmxxii.day25

import poyea.aoc.utils.readInput

fun part1(input: String): String {
    return generateSequence(
            input.lines().sumOf {
                it.map { "=-012".indexOf(it) - 2L }.reduce { acc, n -> 5 * acc + n }
            }
        ) {
            (it + 2) / 5
        }
        .takeWhile { it != 0L }
        .joinToString(separator = "") { "${"=-012"[((it + 2) % 5).toInt()]}" }
        .reversed()
}

fun part2(): String {
    return "*"
}

fun main() {
    println(part1(readInput("Day25")))
    println(part2())
}
