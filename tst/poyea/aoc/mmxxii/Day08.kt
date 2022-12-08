package poyea.aoc.mmxxii.day08

import kotlin.test.Test
import kotlin.test.assertEquals
import poyea.aoc.utils.readInput as readInput

class Day08Test {
    @Test fun part1Ans() {
        assertEquals(part1(readInput("Day08")), 1715)
    }

    @Test fun part2Ans() {
        assertEquals(part2(readInput("Day08")), 374400)
    }
}
