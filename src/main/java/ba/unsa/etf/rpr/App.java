package ba.unsa.etf.rpr;
/**
 * Main class for application that takes arguments from command prompt and evaluates the expression if valid
 * @author Hana Catic
 * @version 1.0
 * */
public class App{
    /**
     * Main method takes arguments from command prompt, argument needs to be forwarded in double quotation marks otherwise
     * it will be considered invalid input
     * */
    public static void main( String[] args ){
        try{
            if(args.length != 1){
                throw new RuntimeException("Invalid input!");
            }
            else{
                ExpressionEvaluator  evaluator = new ExpressionEvaluator();
                System.out.println(evaluator.evaluate(args[0]));
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }

    }
}
