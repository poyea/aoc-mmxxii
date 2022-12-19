package poyea.aoc.mmxxii.day19

import java.util.PriorityQueue
import kotlin.math.ceil
import kotlin.math.max
import poyea.aoc.utils.readInput

class Factory(val blueprint: Blueprint, val minutes: Int) {
    fun search(): Int {
        var max = 0
        val queue = PriorityQueue<State>()
        queue.add(State(minutes = minutes))

        while (queue.isNotEmpty()) {
            val state = queue.poll()
            if (state.betterThan(max)) {
                queue.addAll(state.nextStates())
            }
            max = max(max, state.geode + state.geodeRobots * (state.minutes - 1))
        }

        return max
    }

    private fun State.nextStates() = buildList {
        if (blueprint.maxOreCost > oreRobots) {
            add(next(blueprint.oreRobot))
        }
        if (blueprint.maxClayCost > clayRobots) {
            add(next(blueprint.clayRobot))
        }
        if (clayRobots > 0 && blueprint.maxObsidianCost > obsidianRobots) {
            add(next(blueprint.obsidianRobot))
        }
        if (obsidianRobots > 0) {
            add(next(blueprint.geodeRobot))
        }
    }.filter { it.minutes > 0 }
}

data class State(
    val ore: Int = 1,
    val oreRobots: Int = 1,
    val clay: Int = 0,
    val clayRobots: Int = 0,
    val obsidian: Int = 0,
    val obsidianRobots: Int = 0,
    val geode: Int = 0,
    val geodeRobots: Int = 0,
    val minutes: Int
) : Comparable<State> {
    override fun compareTo(other: State) = other.geode.compareTo(geode)

    fun next(robot: Robot): State {
        val minutes = findTime(robot)
        return copy(
            ore = ore + oreRobots * minutes - robot.oreCost,
            oreRobots = oreRobots + robot.oreRobotsBuilt,
            clay = clay + clayRobots * minutes - robot.clayCost,
            clayRobots = clayRobots + robot.clayRobotsBuilt,
            obsidian = obsidian + obsidianRobots * minutes - robot.obsidianCost,
            obsidianRobots = obsidianRobots + robot.obsidianRobotsBuilt,
            geode = geode + geodeRobots * minutes,
            geodeRobots = geodeRobots + robot.geodeRobotsBuilt,
            minutes = this.minutes - minutes
        )
    }

    fun betterThan(best: Int): Boolean {
        val potential = (0..(minutes - 1)).sumOf { it + geodeRobots }
        return geode + potential > best
    }

    private fun findTime(robot: Robot): Int {
        val remainingOre = (robot.oreCost - ore).coerceAtLeast(0)
        val remainingClay = (robot.clayCost - clay).coerceAtLeast(0)
        val remainingObsidian = (robot.obsidianCost - obsidian).coerceAtLeast(0)
        return maxOf(
            ceil(remainingOre / oreRobots.toFloat()).toInt(),
            ceil(remainingClay / clayRobots.toFloat()).toInt(),
            ceil(remainingObsidian / obsidianRobots.toFloat()).toInt(),
        ) + 1
    }
}

data class Blueprint(
    val id: Int,
    val oreRobot: Robot,
    val clayRobot: Robot,
    val obsidianRobot: Robot,
    val geodeRobot: Robot
) {
    val maxOreCost = maxOf(oreRobot.oreCost, clayRobot.oreCost, obsidianRobot.oreCost, geodeRobot.oreCost)
    val maxClayCost = obsidianRobot.clayCost
    val maxObsidianCost = geodeRobot.obsidianCost

    companion object {
        fun from(line: String): Blueprint {
            val numbers = "\\d+".toRegex().findAll(line).map { it.value.toInt() }.toList()
            return Blueprint(
                id = numbers[0],
                oreRobot = Robot(oreCost = numbers[1], oreRobotsBuilt = 1),
                clayRobot = Robot(oreCost = numbers[2], clayRobotsBuilt = 1),
                obsidianRobot = Robot(oreCost = numbers[3], clayCost = numbers[4], obsidianRobotsBuilt = 1),
                geodeRobot = Robot(oreCost = numbers[5], obsidianCost = numbers[6], geodeRobotsBuilt = 1)
            )
        }
    }
}

data class Robot(
    val oreCost: Int = 0,
    val clayCost: Int = 0,
    val obsidianCost: Int = 0,
    val oreRobotsBuilt: Int = 0,
    val clayRobotsBuilt: Int = 0,
    val obsidianRobotsBuilt: Int = 0,
    val geodeRobotsBuilt: Int = 0,
)

fun part1(input: String): Int {
    return input.split("\n")
                .map(Blueprint::from)
                .sumOf { it.id * Factory(it, minutes = 24).search() }
}

fun part2(input: String): Int {
    return input.split("\n")
                .map(Blueprint::from)
                .take(3)
                .map { Factory(it, minutes = 32).search() }
                .reduce(Int::times)
}

fun main() {
    println(part1(readInput("Day19")))
    println(part2(readInput("Day19")))
}
