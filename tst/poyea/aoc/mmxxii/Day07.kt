package poyea.aoc.mmxxii.day07

import kotlin.test.Test
import kotlin.test.assertEquals
import poyea.aoc.utils.readInput as readInput

class Day07Test {
    @Test fun part1Ans() {
        assertEquals(part1(readInput("Day07")), 1908462)
    }

    @Test fun part2Ans() {
        assertEquals(part2(readInput("Day07")), 3979145)
    }
}
