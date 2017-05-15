import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.runBlocking
import java.math.BigInteger
import java.util.*
import java.util.stream.IntStream
import java.util.stream.Stream


fun fromHeights(vararg heights: Long): BigInteger {
    val reverseOffset = heights.size - 1

    val maxRightFuture = async(CommonPool) {
        val maxRight = heights.copyOf()
        Arrays.parallelPrefix(maxRight, Math::max)
        maxRight
    }

    val maxLeftFuture = async(CommonPool) {
        val maxLeft = LongArray(heights.size)
        Arrays.parallelSetAll(maxLeft) { index -> heights[reverseOffset - index] }
        Arrays.parallelPrefix(maxLeft, Math::max)
        maxLeft
    }

    fun Stream<BigInteger>.sum() = this.reduce(BigInteger.ZERO, BigInteger::add)

    return runBlocking {
        val maxRight = maxRightFuture.await()
        val maxLeft = maxLeftFuture.await()
        IntStream.range(0, heights.size)
                .parallel()
                .mapToObj { index -> BigInteger.valueOf(Math.min(maxRight[index], maxLeft[reverseOffset - index]) - heights[index]) }
                .sum()
    }
}
