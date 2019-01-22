import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class OrdenaStrings {

    public static void main(String[] args) {
        List<String> letras = Arrays.asList("c","f","k","m","l","a","d","n","b","j","e","g","i","o","h","p","t","s","u","r","v","z","x","y","w","q");
        List<String> palavras = Arrays.asList("alura","caelum","andre","andre ramos","andre ramos da silva","analy","analy Stalhberg","analy stahlberg ramos","mari","mari stahlberg","mari stahlberg ramos");

        //modoAntigoDeOrdenarComSort(letras);
        //modoNovoDeOrdenarComSort(letras, new ComparadorPorTamanho());

        //modoAntigoDeOrdenarComComparator(palavras, new ComparadorPorTamanho());
        //modoNovoDeOrdenarComSort(palavras, new ComparadorPorTamanho());
        
        //modosDeForeach();

        modosComMethodReferenceSemLambda();
        modosComMethodReferenceComLamgda();
    }

    private static void modosComMethodReferenceSemLambda() {
        List<String> palavras = Arrays.asList("analy", "andre ramos", "analy stahlberg ramos","mari stahlberg");
        
        Function<String, Integer> funcao = new Function<String, Integer>() {

            @Override
            public Integer apply(final String s) {
                return s.length();
            }
        };

        final Comparator<String> comparator = Comparator.comparing(funcao);
        palavras.sort(comparator);
        System.out.println(palavras);
    }

    private static void modosComMethodReferenceComLamgda() {
        List<String> palavras = Arrays.asList("analy", "andre ramos", "analy stahlberg ramos","mari stahlberg");
        palavras.sort(Comparator.comparing(String::length));//Method Reference
        System.out.println(palavras);
    }

    private static void modosDeForeach() {
        List<String> palavras = Arrays.asList("analy", "andre ramos", "analy stahlberg ramos","mari stahlberg");
        modoAntigoForeach(palavras);
        System.out.println("\n");
        modoNovoComClasseImplementandoConsumer(palavras);
        System.out.println("\n");
        modoNovoComClasseDiretoComConsumer(palavras);
        System.out.println("\n");
        modoNovoComLambda(palavras);
        System.out.println("\n");
        modoNovoDeOrdenarComLambdaEComparatorImplementandoMetodo(palavras);
        System.out.println("\n");
        modoNovoDeOrdenarComLambdaEComparator(palavras);
    }

    private static void modoNovoComClasseDiretoComConsumer(final List<String> palavras) {
        palavras.forEach(new Consumer<String>() {
            @Override
            public void accept(final String s) {
                System.out.println(s);
            }
        });
    }

    private static void modoNovoComLambda(final List<String> palavras) {
        palavras.forEach(s->System.out.println(s));
    }

    private static void modoNovoDeOrdenarComLambdaEComparatorImplementandoMetodo(final List<String> palavras) {
        palavras.sort((s1, s2)->{
            if(s1.length() < s2.length()) {
                return -1;
            }
            if(s1.length() > s2.length()){
                return 1;
            }
            return 0;
        });
    }

    private static void modoNovoDeOrdenarComLambdaEComparator(final List<String> palavras) {
        palavras.sort(Comparator.comparingInt(String::length));
    }

    private static void modoNovoComClasseImplementandoConsumer(final List<String> palavras) {
        final Consumer<String> consumer = new ImprimeLinha();
        palavras.forEach(consumer);
    }

    private static void modoAntigoForeach(final List<String> palavras) {
        for(String p : palavras){
            System.out.println(p);
        }
    }

    private static void modoAntigoDeOrdenarComSort(List<String> palavras){
        Collections.sort(palavras);
        System.out.println(palavras);
    }

    private static void modoAntigoDeOrdenarComComparator(List<String> palavras, ComparadorPorTamanho comparador){
        Collections.sort(palavras, comparador);
        System.out.println(palavras);
    }

    private static void modoNovoDeOrdenarComSort(List<String> palavras, ComparadorPorTamanho comparador){
        palavras.sort(comparador);
        System.out.println(palavras);
    }

    static class ComparadorPorTamanho implements Comparator<String>{

        @Override
        public int compare(final String s1, final String s2) {

            if(s1.length() < s2.length()) {
                return -1;
            }

            if(s1.length() > s2.length()){
                return 1;
            }

            return 0;
        }
    }

}
