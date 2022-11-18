package ba.unsa.etf.rpr;

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
