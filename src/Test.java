import java.util.ArrayList;
public class Test {
     public static void main(String[] args) {
        //System.out.println("Hell");
        ArrayList<Integer> test= new ArrayList<Integer>();
        test.add(2);
        String test2 = "yo my name \"Jeff\"";
      test2=  test2.replaceAll("\"", "");
        System.out.println(test2);
        java.util.regex.Pattern.compile("\"").matcher(test2).replaceAll("ok");
        System.out.println("----------\n"+ test2);
     }
}