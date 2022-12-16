package poyea.aoc.mmxxii.day16

import poyea.aoc.utils.readInput
import kotlin.test.Test
import kotlin.test.assertEquals

class Day16Test {
    @Test
    fun part1Ans() {
        assertEquals(part1(readInput("Day16")), 1796)
    }

    @Test
    fun part2Ans() {
        assertEquals(part2(readInput("Day16")), 1999)
    }
}
