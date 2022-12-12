package poyea.aoc.mmxxii.day12

import poyea.aoc.utils.readInput
import kotlin.test.Test
import kotlin.test.assertEquals

class Day12Test {
    @Test
    fun part1Ans() {
        assertEquals(part1(readInput("Day12")), 391)
    }

    @Test
    fun part2Ans() {
        assertEquals(part2(readInput("Day12")), 386)
    }
}
