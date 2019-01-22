import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author s2it_asilva
 * @version $Revision: $<br/>
 * $Id: $
 * @since 18/01/19 15:25
 */
public class TestingFunctions {

    public static void main(String[] args) {
        testListOfIntegers();
    }

    private static void testListOfIntegers() {
        List<Integer> numbers = Arrays.asList(1,2,3,4,5);
        Consumer<Integer> impressor = i -> System.out.println(multiply(i));
        numbers.forEach(impressor);
    }

    public static Integer multiply(Integer i){
        return i * 2;
    }
}
