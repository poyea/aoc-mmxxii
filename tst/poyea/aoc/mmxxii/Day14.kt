package poyea.aoc.mmxxii.day14

import poyea.aoc.utils.readInput
import kotlin.test.Test
import kotlin.test.assertEquals

class Day14Test {
    @Test
    fun part1Ans() {
        assertEquals(part1(readInput("Day14")), 913)
    }

    @Test
    fun part2Ans() {
        assertEquals(part2(readInput("Day14")), 30762)
    }
}
