package poyea.aoc.mmxxii.day08

import poyea.aoc.utils.readInput

fun part1(input: String): Int {
    input.split("\n").toList().map { it.map(Char::digitToInt).toList() }.let { rows ->
        fun sightLine(x: Int, y: Int) =
            listOf(listOf((x - 1 downTo 0), (x + 1 until rows.size)).map { it.map { xx -> xx to y } },
                listOf((y - 1 downTo 0), (y + 1 until rows.size)).map { it.map { yy -> x to yy } }).flatten()
                .map { it.map { (x, y) -> rows[y][x] } }

        return rows.flatMapIndexed { y, l -> l.mapIndexed { x, h -> Triple(x, y, h) } }.count { (x, y, h) ->
            sightLine(x, y).any { it.all { ele -> ele < h } }
        }
    }
}

fun part2(input: String): Int {
    input.split("\n").toList().map { it.map(Char::digitToInt).toList() }.let { rows ->
        fun sightLine(x: Int, y: Int) =
            listOf(listOf((x - 1 downTo 0), (x + 1 until rows.size)).map { it.map { xx -> xx to y } },
                listOf((y - 1 downTo 0), (y + 1 until rows.size)).map { it.map { yy -> x to yy } }).flatten()
                .map { it.map { (x, y) -> rows[y][x] } }

        return rows.flatMapIndexed { y, l -> l.mapIndexed { x, h -> Triple(x, y, h) } }.maxOf { (x, y, h) ->
            sightLine(x, y).map {
                it.indexOfFirst { tree -> tree >= h }.let { idx -> if (idx >= 0) idx + 1 else it.size }
            }.reduce(Int::times)
        }
    }
}

fun main() {
    println(part1(readInput("Day08")))
    println(part2(readInput("Day08")))
}
