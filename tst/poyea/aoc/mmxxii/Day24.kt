package poyea.aoc.mmxxii.day24

import poyea.aoc.utils.readInput
import kotlin.test.Test
import kotlin.test.assertEquals

class Day24Test {
    @Test
    fun part1Ans() {
        assertEquals(part1(readInput("Day24")), 255)
    }

    @Test
    fun part2Ans() {
        assertEquals(part2(readInput("Day24")), 809)
    }
}
