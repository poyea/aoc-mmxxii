package poyea.aoc.mmxxii.day03

import poyea.aoc.utils.readInput

fun part1(input: String): Int {
    return input.split("\n").sumOf {
        val char = it.substring(0, it.length / 2).toSet().intersect(it.substring(it.length / 2).toSet()).first()
        if (char.isLowerCase()) char - 'a' + 1 else char - 'A' + 27
    }
}

fun part2(input: String): Int {
    return input.split("\n").chunked(3).sumOf {
        val char = it[0].toSet().intersect(it[1].toSet().intersect(it[2].toSet())).first()
        if (char.isLowerCase()) char - 'a' + 1 else char - 'A' + 27
    }
}

fun main() {
    println(part1(readInput("Day03")))
    println(part2(readInput("Day03")))
}
