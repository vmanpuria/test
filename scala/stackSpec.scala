import org.scalatest.FlatSpec
import collection.mutable.Stack

class StackSpec extends FlatSpec {
    "A Stack" should "pop values in last-in-first-out order" in {
        val stack = new Stack[Int]
        stack.push(1)
        stack.push(2)
        assert(stack.pop() === 2)
        assert(stack.pop() === 1)
    }
}
