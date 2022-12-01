package poyea.aoc.mmxxii.day01

import poyea.aoc.utils.readInput

fun part1(input: String): Int {
    return input.split("\n\n").map { it -> it.lines().sumOf { it.toInt() } }.maxOf { it }
}

fun part2(input: String): Int {
    return input.split("\n\n").map { it -> it.lines().sumOf { it.toInt() } }.sortedDescending().take(3).sum()
}

fun main() {
    println(part1(readInput("Day01")))
    println(part2(readInput("Day01")))
}
