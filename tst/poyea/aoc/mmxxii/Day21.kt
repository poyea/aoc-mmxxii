package poyea.aoc.mmxxii.day21

import poyea.aoc.utils.readInput
import kotlin.test.Test
import kotlin.test.assertEquals

class Day21Test {
    @Test
    fun part1Ans() {
        assertEquals(part1(readInput("Day21")), 282285213953670)
    }

    @Test
    fun part2Ans() {
        assertEquals(part2(readInput("Day21")), 3699945358564)
    }
}
