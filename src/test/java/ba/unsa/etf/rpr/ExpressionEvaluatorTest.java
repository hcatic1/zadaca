package ba.unsa.etf.rpr;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/**
 * Test class for ExpressionEvaluator
 * @author Hana Catic
 * @version 1.0
 * */

public class ExpressionEvaluatorTest {
    /**
     * Test 1
     * Testing basic computations
     * */
    @Test
    void evaluateTest(){
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        assertEquals(101, evaluator.evaluate("( 1 + ( ( 2 + 3 ) * ( 4 * 5 ) ) )"));
    }
    /**
     * Test 2
     * Testing basic computations
     * */
    @Test
    void evaluateMinusTest(){
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        assertEquals(1 , evaluator.evaluate("( sqrt ( 5 - 4 ) )"));
    }
    /**
     * Test 3
     * Testing basic computations with division and negative numbers
     * */
    @Test
    void evaluateDivisionTest(){
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        assertEquals(2, evaluator.evaluate("( ( ( ( 5 / 1 ) / 5 ) * 8 ) + -6 )"));
        assertEquals(1.5 , evaluator.evaluate("( 1 / ( 2 / 3 ) )"));
    }
    /**
     * Test 4
     * Testing exception in case of zero divider
     * */
    @Test
    void evaluateDivisionExceptionTest(){
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        Exception e = assertThrows(RuntimeException.class, ()-> evaluator.evaluate("( 5 / ( 4 - ( 2 * 2 ) ) )"));
    }
    /**
     * Test 5
     * Testing computation with negative numbers
     * */
    @Test
    void evaluateNegNumTest(){
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        assertEquals(99, evaluator.evaluate("( -1 + ( ( 2 + 3 ) * ( 4 * 5 ) ) )"));
    }
    /**
     * Test 6
     * Testing parsing and computation with doubles
     * */
    @Test
    void evaluateDoubleTest(){
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        assertEquals(99, evaluator.evaluate("( -1 + ( ( 1.5 + 3.5 ) * ( 4.00 * 5 ) ) )"));
    }

    /**
     * Test 7
     * Testing computations with unary operator sqrt, as a first and second operand, multiple unary operators
     * */
    @Test
    void evaluateSqrtTest(){
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        assertEquals(101, evaluator.evaluate("( sqrt ( 1 ) + ( ( 2 + 3 ) * ( 4 * 5 ) ) )"));
        assertEquals(101, evaluator.evaluate("( ( ( 2 + 3 ) * ( 4 * 5 ) ) + sqrt ( 1 ) )"));
        assertEquals(4, evaluator.evaluate("( 2 + sqrt ( sqrt ( ( 32 / 2 ) ) ) )"));
        assertEquals(1, evaluator.evaluate("( sqrt ( -2 / -2 ) )"));
    }
    /**
     * Test 8
     * Testing exception with unary operator sqrt and negative numbers
     * */
    @Test
    void evaluateSqrtExceptionTest(){
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        assertThrows(RuntimeException.class, () -> {evaluator.evaluate("( sqrt ( -1 ) + ( ( 2 + 3 ) * ( 4 * 5 ) ) )");});
    }
    /**
     * Test 9
     * Testing invalid expressions, invalid strings
     * */
    @Test
    void evaluateValidityTest(){
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        assertThrows(RuntimeException.class, () -> {evaluator.evaluate("( 4+ ( 1 + 2 ) )");});
        assertThrows(RuntimeException.class, () -> {evaluator.evaluate("( 1 + 1 );"); });
        assertThrows(RuntimeException.class, () -> {evaluator.evaluate("( 1 + 1A );"); });
        assertThrows(RuntimeException.class, () -> {evaluator.evaluate("( 4 + ( 1 ++ 2 ) )");});
        assertThrows(RuntimeException.class, () -> {evaluator.evaluate("( 4 + ( 1 + 2 ) ))");});
    }
    /**
     * Test 10
     * Testing invalid expressions, missing or extra parentheses, double operands and operators
     * */
    @Test
    void evaluateValidityDoubleSignTest(){
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        assertAll(
                ()->assertThrows(RuntimeException.class, () -> {evaluator.evaluate("( 4 + ( 1 + 2 ) ) )");}),
                ()->assertThrows(RuntimeException.class, () -> {evaluator.evaluate("( 4 + 1 + 2 )");}),
                ()->assertThrows(RuntimeException.class, () -> {evaluator.evaluate("( 4 * 1 + 2 )");}),
                ()->assertThrows(RuntimeException.class, () -> {evaluator.evaluate("( 4 + ( 1 + + 2 ) )");}),
                ()->assertThrows(RuntimeException.class, () -> {evaluator.evaluate("( 4 + ( 1 + 2 2 ) )");}),
                ()->assertThrows(RuntimeException.class, () -> {evaluator.evaluate("( ( 4 + ( 1 + 2 ) )");}),
                ()->assertThrows(RuntimeException.class, () -> {evaluator.evaluate("( 4 * 5 + ( sqrt ( ( 32 / 2 ) ) ) )");})
        );
    }
    /**
     * Test 11
     * Testing missing operand and missing operator
     * */
    @Test
    void evaluateValidityEmptyStackTest(){
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        assertThrows(RuntimeException.class, () -> {evaluator.evaluate("( 1 + )"); });
        assertThrows(RuntimeException.class, () -> {evaluator.evaluate("( 1 1 )"); });
    }
}
