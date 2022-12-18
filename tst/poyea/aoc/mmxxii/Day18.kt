package poyea.aoc.mmxxii.day18

import poyea.aoc.utils.readInput
import kotlin.test.Test
import kotlin.test.assertEquals

class Day18Test {
    @Test
    fun part1Ans() {
        assertEquals(part1(readInput("Day18")), 3466)
    }

    @Test
    fun part2Ans() {
        assertEquals(part2(readInput("Day18")), 2012)
    }
}
