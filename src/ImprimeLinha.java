import java.util.function.Consumer;

public class ImprimeLinha implements Consumer<String> {

    @Override
    public void accept(final String s) {
        System.out.println(s);
    }
}
