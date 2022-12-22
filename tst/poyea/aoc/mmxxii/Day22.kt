package poyea.aoc.mmxxii.day22

import poyea.aoc.utils.readInput
import kotlin.test.Test
import kotlin.test.assertEquals

class Day22Test {
    @Test
    fun part1Ans() {
        assertEquals(part1(readInput("Day22")), 58248)
    }

    @Test
    fun part2Ans() {
        assertEquals(part2(readInput("Day22")), 179091)
    }
}
