package poyea.aoc.mmxxii.day06

import poyea.aoc.utils.readInput

fun String.isUnique(): Boolean = all(hashSetOf<Char>()::add)

fun part1(input: String): Int {
    return input.windowed(4).takeWhile {
        !it.isUnique()
    }.let { it.size + 4 }
}

fun part2(input: String): Int {
    return input.windowed(14).takeWhile {
        !it.isUnique()
    }.let { it.size + 14 }
}

fun main() {
    println(part1(readInput("Day06")))
    println(part2(readInput("Day06")))
}
