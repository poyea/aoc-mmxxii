package poyea.aoc.mmxxii.day20

import poyea.aoc.utils.readInput

fun part1(input: String): Long {
    val original = input.split("\n")
                        .map { it.toInt() }
                        .mapIndexed { index, i -> Pair(index, i.toLong()) }
    val moved = original.toMutableList()
    original.forEach { p ->
        val index = moved.indexOf(p)
        moved.removeAt(index)
        moved.add((index + p.second).mod(moved.size), p)
    }
    return moved.map { it.second }.let {
        val firstIndex = it.indexOf(0)
        (1..3).sumOf { i -> it[(i * 1000 + firstIndex) % it.size] }
    }
}

fun part2(input: String): Long {
    val original = input.split("\n")
                        .map { it.toInt() }
                        .mapIndexed { index, i -> Pair(index, i.toLong() * 811589153) }
    val moved = original.toMutableList()
    repeat(10) {
        original.forEach { p ->
            val index = moved.indexOf(p)
            moved.removeAt(index)
            moved.add((index + p.second).mod(moved.size), p)
        }
    }
    return moved.map { it.second }.let {
        val firstIndex = it.indexOf(0)
        (1..3).sumOf { i -> it[(i * 1000 + firstIndex) % it.size] }
    }
}

fun main() {
    println(part1(readInput("Day20")))
    println(part2(readInput("Day20")))
}
