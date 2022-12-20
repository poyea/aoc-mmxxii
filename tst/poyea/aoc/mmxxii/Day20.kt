package poyea.aoc.mmxxii.day20

import poyea.aoc.utils.readInput
import kotlin.test.Test
import kotlin.test.assertEquals

class Day20Test {
    @Test
    fun part1Ans() {
        assertEquals(part1(readInput("Day20")), 4224)
    }

    @Test
    fun part2Ans() {
        assertEquals(part2(readInput("Day20")), 861907680486)
    }
}
