package poyea.aoc.mmxxii.day07

import poyea.aoc.utils.readInput
import kotlin.Int.Companion.MAX_VALUE
import kotlin.math.min

class Node(val path: String, var size: Int, val isDir: Boolean, val parent: Node?, val children: MutableSet<Node>)

fun buildSize(node: Node?): Int {
    if (node == null) {
        return 0
    }
    var sum = 0
    if (!node.isDir) {
        return node.size
    }
    for (child in node.children) {
        sum += buildSize(child)
    }
    node.size = sum
    // println("${node.path} : ${node.size}")
    return sum
}

fun filterSize(node: Node?, limit: Int): Int {
    if (node == null) {
        return 0
    }
    var sum = 0
    if (node.isDir) {
        sum += if (node.size <= limit) node.size else 0
    }
    for (child in node.children) {
        sum += filterSize(child, limit)
    }
    return sum
}

fun filterNode(node: Node?, toRelease: Int, hasSpaceOf: Int): Int {
    if (node == null || !node.isDir) {
        return MAX_VALUE
    }
    var newRelease = if (node.size in hasSpaceOf..toRelease) node.size else toRelease
    for (child in node.children) {
        if (child.isDir) {
            newRelease = min(newRelease, filterNode(child, newRelease, hasSpaceOf))
        }
    }
    return newRelease
}

fun buildTree(input: String): Node {
    var tree = Node("/", 0, true, null, mutableSetOf())
    val root = tree
    input.split("\n").forEach {
        if (it.startsWith("$")) {
            val commandList = it.split(" ")
            val command = commandList[1]
            val parentPath = if (tree.path == "/") "" else tree.path
            when (command) {
                "ls" -> {}
                "cd" -> {
                    val destination = commandList[2]
                    if (destination == "..") {
                        tree = tree.parent!!
                    } else {
                        if (destination != "/") {
                            tree = tree.children.find { it.path == "$parentPath/$destination" }!!
                        }
                    }
                }

                else -> {}
            }
        } else {
            val commandList = it.split(" ")
            val sizeOrDir = commandList[0]
            val name = commandList[1]
            val parentPath = if (tree.path == "/") "" else tree.path
            val parentSize = tree.size
            when (sizeOrDir) {
                "dir" -> {
                    tree.children.add(Node("$parentPath/$name", parentSize, true, tree, mutableSetOf()))
                }

                else -> {
                    val size = sizeOrDir.toInt()
                    if (tree.children.firstOrNull { it.path == "$parentPath/$name" } == null) {
                        tree.children.add(Node("$parentPath/$name", parentSize + size, false, tree, mutableSetOf()))
                    } else {
                        tree.children.first { it.path == "$parentPath/$name" }.size += size
                    }
                }
            }
        }
    }
    return root
}

fun part1(input: String): Int {
    buildTree(input).let {
        buildSize(it)
        return filterSize(it, 100000)
    }
}

fun part2(input: String): Int {
    buildTree(input).let {
        buildSize(it)
        return if (it.size >= 30000000) {
            filterNode(it, MAX_VALUE, 30000000 - (70000000 - it.size))
        } else {
            0
        }
    }
}

fun main() {
    println(part1(readInput("Day07")))
    println(part2(readInput("Day07")))
}
