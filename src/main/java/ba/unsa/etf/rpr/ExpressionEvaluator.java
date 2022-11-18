package ba.unsa.etf.rpr;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 * Class for expression evaluation using Dijkstra's Algorithm
 * @author Hana Catic
 * @version 1.0
 * */
public class ExpressionEvaluator {
    /**
     * Evaluate using Dijkstra's Algorithm
     * @param expression
     * @return evaluated expression after calculations
     * */
    public Double evaluate(String expression){
        Stack<String> ops = new Stack<String> (); //stack of operators
        Stack<Double> vals = new Stack<Double>(); //stack of values
        boolean value = false; //keeps information about previous element, whether it was a number
        boolean operator = false; //keeps information about previous element, whether it was an operator
        boolean sqrtOp = false; //keeps information about unary operator
        int parentheses = 0; //number of parentheses, every open parentheses needs to be closed, if on open parentheses is increased, and on closed decreased in the end it needs to be zero
        try{
        for(String s: expression.split(" ")) {
            if (s.equals("(")) {
                parentheses = parentheses + 1; //according to above-mentioned
                operator = false; //after an operator either follows an open parentheses or a value and a closed parantheses, if that condition is satisfied value operator can go back to false
                sqrtOp = false; //condition that sqrt needs to be followed by open parentheses
            }
            else if (s.equals(")")) { //mathematical operations are conducted on closed parentheses
                parentheses = parentheses - 1;  //keeping track of parentheses, them being correctly opened and closed
                String op = ops.pop(); //taking the last added operator from stack
                operator = false;
                if(sqrtOp) throw new RuntimeException("Invalid input."); //unary operator needs to be followed by an open parentheses
                double val = vals.pop(); //taking the last value from stack
                //depending on the operator op different operations are calculated
                switch (op) {
                    case "+":
                        val = vals.pop() + val;
                        break;
                    case "-":
                        val = vals.pop() - val;
                        break;
                    case "*":
                        val = vals.pop() * val;
                        break;
                    case "/":
                        //dividing with zero is not allowed
                        if (val == 0) throw new RuntimeException("Invalid input!");
                        val = vals.pop() / val;
                        break;
                    case "sqrt":
                        //square root of a negative number does not have a solution in the set of real numbers
                        if (val < 0)
                            throw new RuntimeException("Invalid input!");
                        val = Math.sqrt(val);
                        break;
                }
                vals.push(val); //add result of operation back to stack
                sqrtOp = false;
            } else if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/") || s.toLowerCase().equals("sqrt")) {
                //if there are consecutive operators with no parentheses between them, it does not include any of the binary operators when one of the operands is result of unary operator sqrt
                if( operator && s.toLowerCase().equals("sqrt") == false ) throw new RuntimeException("Invalid input");
                operator = true;
                value = false;
                ops.push(s);
                if(s.equals("sqrt")) sqrtOp = true;
            } else {
                //if there are two consecutive numbers with no operator between them
                if(value) throw new RuntimeException("Invalid input.");
                try { //in case of invalid input Double.parseDouble will throw an exception which means that the input was invalid
                    vals.push(Double.parseDouble(s));
                    value = true;

                } catch (Exception e) {
                    throw new RuntimeException("Invalid input.");
                }
            }
        }
        //if there are more parentheses than operands emptyStackException will be thrown, catch it and throw runTimeException
        }catch(EmptyStackException e){
            throw new RuntimeException("Invalid input.");
        }
        Double results = vals.pop();
        //after taking out the result, if the stack is not empty it means that there were more operands than parentheses, or if all opened parentheses were not closed or all closed were not opened
        if(vals.isEmpty() == false || parentheses != 0) throw new RuntimeException("Invalid input");
        return results; //return evaluated expression
    }
}
