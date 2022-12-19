package poyea.aoc.mmxxii.day19

import poyea.aoc.utils.readInput
import kotlin.test.Test
import kotlin.test.assertEquals

class Day19Test {
    @Test
    fun part1Ans() {
        assertEquals(part1(readInput("Day19")), 1081)
    }

    @Test
    fun part2Ans() {
        assertEquals(part2(readInput("Day19")), 2415)
    }
}
