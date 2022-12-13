package poyea.aoc.mmxxii.day13

import poyea.aoc.utils.readInput
import kotlin.test.Test
import kotlin.test.assertEquals

class Day13Test {
    @Test
    fun part1Ans() {
        assertEquals(part1(readInput("Day13")), 5557)
    }

    @Test
    fun part2Ans() {
        assertEquals(part2(readInput("Day13")), 22425)
    }
}
