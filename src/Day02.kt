enum class Shape(val worth: Int) {
    ROCK(1),
    PAPER(2),
    SCISSORS(3)
}

fun main() {

    fun charsFromLine(line: String): Pair<Char, Char> {
        val tokens = line.split(' ')
        return Pair(tokens[0][0], tokens[1][0])
    }

    fun toShape(c: Char): Shape {
        return when (c) {
            'A', 'X' -> Shape.ROCK
            'B', 'Y' -> Shape.PAPER
            'C', 'Z' -> Shape.SCISSORS
            else -> throw RuntimeException("invalid input")
        }
    }

    fun calcRound(opponent: Shape, mine: Shape): Int {
        return mine.worth + when (opponent) {
            Shape.ROCK -> when (mine) {
                Shape.ROCK -> 3
                Shape.PAPER -> 6
                Shape.SCISSORS -> 0
            }
            Shape.PAPER -> when (mine) {
                Shape.ROCK -> 0
                Shape.PAPER -> 3
                Shape.SCISSORS -> 6
            }
            Shape.SCISSORS -> when (mine) {
                Shape.ROCK -> 6
                Shape.PAPER -> 0
                Shape.SCISSORS -> 3
            }
        }
    }

    fun toWin(opponent: Shape): Shape {
        return when(opponent) {
            Shape.ROCK -> Shape.PAPER
            Shape.PAPER -> Shape.SCISSORS
            Shape.SCISSORS -> Shape.ROCK
        }
    }

    fun toLose(opponent: Shape): Shape {
        return when(opponent) {
            Shape.ROCK -> Shape.SCISSORS;
            Shape.PAPER -> Shape.ROCK;
            Shape.SCISSORS -> Shape.PAPER
        }
    }

    fun part1(input: List<String>): Int {
        var sum = 0

        for (line in input) {
            val chars = charsFromLine(line)
            sum += calcRound(toShape(chars.first), toShape(chars.second));
        }

        return sum
    }

    fun part2(input: List<String>): Int {
        var sum = 0;

        for (line in input) {
            val chars = charsFromLine(line);
            val opponent = toShape(chars.first)
            val mine = when (chars.second) {
                'X' -> toLose(opponent)
                'Y' -> opponent
                'Z' -> toWin(opponent)
                else -> throw RuntimeException("invalid input")
            }
            sum += calcRound(opponent, mine);
        }

        return sum;
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 15)
    check(part2(testInput) == 12)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
