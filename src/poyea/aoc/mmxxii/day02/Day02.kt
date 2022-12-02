package poyea.aoc.mmxxii.day02

import poyea.aoc.utils.readInput
import kotlin.Int.Companion.MIN_VALUE

fun part1(input: String): Int {
    return input.split("\n").map { it.split(" ") }.sumOf { info ->
        when (info[1]) {
            "X" -> {
                when (info[0]) {
                    "A" -> 3 + 1
                    "B" -> 0 + 1
                    "C" -> 6 + 1
                    else -> MIN_VALUE
                }
            }

            "Y" -> {
                when (info[0]) {
                    "A" -> 6 + 2
                    "B" -> 3 + 2
                    "C" -> 0 + 2
                    else -> MIN_VALUE
                }
            }

            "Z" -> {
                when (info[0]) {
                    "A" -> 0 + 3
                    "B" -> 6 + 3
                    "C" -> 3 + 3
                    else -> MIN_VALUE
                }
            }

            else -> MIN_VALUE
        }
    }
}

fun part2(input: String): Int {
    return input.split("\n").map { it.split(" ") }.sumOf { info ->
        when (info[1]) {
            "X" -> {
                when (info[0]) {
                    "A" -> 3 + 0
                    "B" -> 1 + 0
                    "C" -> 2 + 0
                    else -> MIN_VALUE
                }
            }

            "Y" -> {
                when (info[0]) {
                    "A" -> 1 + 3
                    "B" -> 2 + 3
                    "C" -> 3 + 3
                    else -> MIN_VALUE
                }
            }

            "Z" -> {
                when (info[0]) {
                    "A" -> 2 + 6
                    "B" -> 3 + 6
                    "C" -> 1 + 6
                    else -> MIN_VALUE
                }
            }

            else -> MIN_VALUE
        }
    }
}

fun main() {
    println(part1(readInput("Day02")))
    println(part2(readInput("Day02")))
}
