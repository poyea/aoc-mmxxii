package poyea.aoc.mmxxii.day04

import poyea.aoc.utils.readInput
import kotlin.test.Test
import kotlin.test.assertEquals

class Day04Test {
    @Test
    fun part1Ans() {
        assertEquals(part1(readInput("Day04")), 573)
    }

    @Test
    fun part2Ans() {
        assertEquals(part2(readInput("Day04")), 867)
    }
}
