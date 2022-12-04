package poyea.aoc.mmxxii.day04

import poyea.aoc.utils.readInput

fun part1(input: String): Int {
    return input.split("\n").flatMap {
        it.split(",").map { sections ->
            sections.split("-").map { array -> array.toInt() }
        }.chunked(2).map { assignment ->
            val list1 = assignment[0]
            val list2 = assignment[1]
            // Full intersection
            if (list1[0] >= list2[0] && list1[1] <= list2[1] || list1[0] <= list2[0] && list1[1] >= list2[1]) 1 else 0
        }
    }.sum()
}

fun part2(input: String): Int {
    return input.split("\n").flatMap {
        it.split(",").map { sections ->
            sections.split("-").map { array -> array.toInt() }
        }.chunked(2).map { assignment ->
            val list1 = assignment[0]
            val list2 = assignment[1]
            // Any intersection
            if (list1[0] <= list2[1] && list1[1] >= list2[0]) 1 else 0
        }
    }.sum()
}

fun main() {
    println(part1(readInput("Day04")))
    println(part2(readInput("Day04")))
}
