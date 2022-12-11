package poyea.aoc.mmxxii.day11

import poyea.aoc.utils.readInput
import kotlin.test.Test
import kotlin.test.assertEquals

class Day11Test {
    @Test
    fun part1Ans() {
        assertEquals(part1(readInput("Day11")), 111210)
    }

    @Test
    fun part2Ans() {
        assertEquals(part2(readInput("Day11")), 15447387620)
    }
}
