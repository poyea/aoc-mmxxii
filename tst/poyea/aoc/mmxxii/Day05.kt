package poyea.aoc.mmxxii.day05

import poyea.aoc.utils.readInput
import kotlin.test.Test
import kotlin.test.assertEquals

class Day05Test {
    @Test
    fun part1Ans() {
        assertEquals(part1(readInput("Day05")), "LBLVVTVLP")
    }

    @Test
    fun part2Ans() {
        assertEquals(part2(readInput("Day05")), "TPFFBDRJD")
    }
}
