package ba.unsa.etf.rpr;

import java.util.EmptyStackException;
import java.util.Stack;

public class ExpressionEvaluator {
    public Double evaluate(String expression){
        Stack<String> ops = new Stack<String> ();
        Stack<Double> vals = new Stack<Double>();
        boolean value = false;
        boolean operator = false;
        int parentheses = 0;
        try{
        for(String s: expression.split(" ")) {
            if (s.equals("(")) parentheses++;
            else if (s.equals(")")) {
                parentheses--;
                String op = ops.pop();
                double val = vals.pop();
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
                        if (val == 0) throw new RuntimeException("Uneseni izraz nije validan, djeljenje s nulom!");
                        val = vals.pop() / val;
                        break;
                    case "sqrt":
                        if (val < 0)
                            throw new RuntimeException("Uneseni izraz nije validan, korjenovanje negativnog broja nije moguće u skupu realnih brojeva!");
                        val = Math.sqrt(val);
                        break;
                }
                vals.push(val);
            } else if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/") || s.toLowerCase().equals("sqrt")) {
                if(operator && s.toLowerCase().equals("sqrt") == false) throw new RuntimeException("Uneseni izraz nije aritmetički validan.");
                operator = true;
                value = false;
                ops.push(s);
            } else {
                if(value) throw new RuntimeException("Uneseni izraz nije aritmetički validan.");
                try {
                    vals.push(Double.parseDouble(s));
                    value = true;
                    operator = false;

                } catch (Exception e) {
                    throw new RuntimeException("Uneseni izraz nije aritmetički validan.");
                }
            }
        }
        }catch(EmptyStackException e){
            throw new RuntimeException("Uneseni izraz nije aritmetički validan.");
        }
        Double results = vals.pop();
        if(vals.isEmpty() == false ||parentheses != 0) throw new RuntimeException("Uneseni izraz nije aritmetički validan.");
        return results;
    }
}
