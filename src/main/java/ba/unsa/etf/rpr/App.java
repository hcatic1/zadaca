package ba.unsa.etf.rpr;

/**
 * Hello world!
 *
 */
public class App{
    public static void main( String[] args ){
        try{
            if(args.length != 1){
                throw new RuntimeException("Neispravan unos!");
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
