package ba.unsa.etf.rpr;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ExpressionEvaluatorTest {
    @Test
    void evaluateTest(){
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        assertEquals(101, evaluator.evaluate("( 1 + ( ( 2 + 3 ) * ( 4 * 5 ) ) )"));
    }
    @Test
    void evaluateMinusTest(){
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        assertEquals(1 , evaluator.evaluate("( sqrt ( 5 - 4 ) )"));
    }
    @Test
    void evaluateDivisionTest(){
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        assertEquals(2, evaluator.evaluate("( ( ( ( 5 / 1 ) / 5 ) * 8 ) + -6 )"));
        assertEquals(1.5 , evaluator.evaluate("( 1 / ( 2 / 3 ) )"));
    }
    @Test
    void evaluateDivisionExceptionTest(){
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        Exception e = assertThrows(RuntimeException.class, ()-> evaluator.evaluate("( 5 / ( 4 - ( 2 * 2 ) ) )"));
        assertTrue(e.getMessage().contains("djeljenje s nulom"));
    }
    @Test
    void evaluateNegNumTest(){
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        assertEquals(99, evaluator.evaluate("( -1 + ( ( 2 + 3 ) * ( 4 * 5 ) ) )"));
    }
    @Test
    void evaluateDoubleTest(){
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        assertEquals(99, evaluator.evaluate("( -1 + ( ( 1.5 + 3.5 ) * ( 4.00 * 5 ) ) )"));
    }
    @Test
    void evaluateSqrtTest(){
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        assertEquals(101, evaluator.evaluate("( sqrt ( 1 ) + ( ( 2 + 3 ) * ( 4 * 5 ) ) )"));
        assertEquals(101, evaluator.evaluate("( ( ( 2 + 3 ) * ( 4 * 5 ) ) + sqrt ( 1 ) )"));
        assertEquals(4, evaluator.evaluate("( 2 + sqrt ( sqrt ( ( 32 / 2 ) ) ) )"));
    }
    @Test
    void evaluateSqrtExceptionTest(){
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        assertThrows(RuntimeException.class, () -> {evaluator.evaluate("( sqrt ( -1 ) + ( ( 2 + 3 ) * ( 4 * 5 ) ) )");});
    }
    @Test
    void evaluateValidityTest(){
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        assertThrows(RuntimeException.class, () -> {evaluator.evaluate("( 4+ ( 1 + 2 ) )");});
        assertThrows(RuntimeException.class, () -> {evaluator.evaluate("( 1 + 1 );"); });
        assertThrows(RuntimeException.class, () -> {evaluator.evaluate("( 1 + 1A );"); });
        assertThrows(RuntimeException.class, () -> {evaluator.evaluate("( 4 + ( 1 ++ 2 ) )");});
        assertThrows(RuntimeException.class, () -> {evaluator.evaluate("( 4 + ( 1 + 2 ) ))");});
    }
    @Test
    void evaluateValidityDoubleSignTest(){
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        assertThrows(RuntimeException.class, () -> {evaluator.evaluate("( 4 + ( 1 + 2 ) ) )");});
        assertThrows(RuntimeException.class, () -> {evaluator.evaluate("( 4 + 1 + 2 )");});
        assertThrows(RuntimeException.class, () -> {evaluator.evaluate("( 4 * 1 + 2 )");});
        assertThrows(RuntimeException.class, () -> {evaluator.evaluate("( 4 + ( 1 + + 2 ) )");});
        assertThrows(RuntimeException.class, () -> {evaluator.evaluate("( 4 + ( 1 + 2 2 ) )");});
        assertThrows(RuntimeException.class, () -> {evaluator.evaluate("( ( 4 + ( 1 + 2 ) )");});
    }
    @Test
    void evaluateValidityEmptyStackTest(){
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        assertThrows(RuntimeException.class, () -> {evaluator.evaluate("( 1 + )"); });
    }
}
