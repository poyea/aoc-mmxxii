package poyea.aoc.mmxxii.day23

import poyea.aoc.utils.readInput
import kotlin.test.Test
import kotlin.test.assertEquals

class Day23Test {
    @Test
    fun part1Ans() {
        assertEquals(part1(readInput("Day23")), 4247)
    }

    @Test
    fun part2Ans() {
        assertEquals(part2(readInput("Day23")), 1049)
    }
}
