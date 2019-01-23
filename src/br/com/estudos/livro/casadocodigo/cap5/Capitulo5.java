package br.com.estudos.livro.casadocodigo.cap5;

import br.com.estudos.livro.casadocodigo.cap2.Usuario;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.ToIntFunction;

public class Capitulo5 {

    Usuario user1 = new Usuario("Paulo  Silveira", 150);

    Usuario user2 = new Usuario("Rodrigo  Turini", 120);

    Usuario user3 = new Usuario("Guilherme  Silveira", 190);

    List<Usuario> usuarios = Arrays.asList(user1, user2, user3);

    public static void main(String[] args) {

        final Capitulo5 capitulo5 = new Capitulo5();

        withoutAutoBoxingFunction(capitulo5);

    }

    //Usar funtions ToInt evita autoboxing, pode melhorar a performance.
    //Como o variavel ponto do Usuario é um int, ao invocar caparing, acontecerá
    //um autoboxing de Integer para int, isso a cada iteracao de sort, portanto
    //usar comparingInt, nesse caso é bem melhor para evitar autoboxing.
    private static void withoutAutoBoxingFunction(final Capitulo5 capitulo5) {

        ToIntFunction<Usuario> extraiPontos = u -> u.getPontos();
        Comparator<Usuario> comparator = Comparator.comparingInt(extraiPontos);
        capitulo5.usuarios.sort(comparator);
        System.out.println(capitulo5.usuarios);

        //or

        capitulo5.usuarios.sort(Comparator.comparingInt(Usuario::getPontos));
        System.out.println(capitulo5.usuarios);
    }

    private static void sortAndComparator(final Capitulo5 capitulo5) {
        capitulo5.usuarios.sort(Comparator.comparing(Usuario::getNome));
        System.out.println(capitulo5.usuarios);
    }

    private static void naturalOrder() {
        List<String> palavras = Arrays.asList("Casa do Código", "Alura", "Caelum");
        palavras.sort(Comparator.naturalOrder());
        System.out.println(palavras);
    }

    private static void reverseOrder() {
        List<String> palavras = Arrays.asList("Casa do Código", "Alura", "Caelum");
        palavras.sort(Comparator.reverseOrder());
        System.out.println(palavras);
    }

    private static void autoBoxingFunction(final Capitulo5 capitulo5) {
        Function<Usuario, Integer> extraiPontos = u -> u.getPontos();
        Comparator<Usuario> comparator = Comparator.comparing(extraiPontos);
        capitulo5.usuarios.sort(comparator);
        System.out.println(capitulo5.usuarios);
    }
}
