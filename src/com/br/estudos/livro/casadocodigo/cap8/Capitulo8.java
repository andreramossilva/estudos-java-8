package com.br.estudos.livro.casadocodigo.cap8;

import com.br.estudos.livro.casadocodigo.cap2.Usuario;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.IntBinaryOperator;
import java.util.function.IntSupplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Capitulo8 {

    Usuario user1 = new Usuario("Paulo  Silveira", 150);

    Usuario user2 = new Usuario("Rodrigo  Turini", 120);

    Usuario user3 = new Usuario("Guilherme  Silveira", 190);

    List<Usuario> usuarios = Arrays.asList(user1, user2, user3);

    public static void main(String[] args) {
        final Capitulo8 capitulo8 = new Capitulo8();
        generateAInfiniteStream(capitulo8.usuarios);
    }

    private static void generateAInfiniteStream(final List<Usuario> usuarios) {

        //OBS: nos casos de Stream infinito, é sempre aconselhável usar
        // uma estratégia de curto-circuito (método que corta a iteração, produz um Stream finito),
        // porque pode gerar overhead.

        //Lembre - se:trabalhar com Streams infinitos
        //pode ser perigoso, mesmo que você utilize operações de curto-circuito.

        Random random = new Random(0);
        List<Integer> list = IntStream
                .generate(random::nextInt)
                .limit(100) //curto-circuito
                .boxed() //para fazer boxing e poder acessar .collect e obter um List<Integer>,
                // sem isso seria necessário fazer IntStream.toArray ,
                // ou então de chamar o collect que recebe três argumentos
                .collect(Collectors.toList());

        //outro exemplo de Stream infinito
        IntStream.generate(new Fibonacci())
                .limit(10)//curto-circuito
                .forEach(System.out::println);

        //filtrando: tomar cuidade ao filtrar, pois se não houver um
        // elemento para filtrar, poderia rodar infinitamente sem passar pelo curto-circuito
        int maiorQue100 = IntStream
                .generate(new Fibonacci())
                .filter(f -> f > 100)
                .findFirst()//curto-circuito
                .getAsInt();
        System.out.println(maiorQue100);
    }

    private static void limitMethod(final List<Usuario> usuarios) {
        usuarios.stream()
                .limit(2)
                .forEach(System.out::println);
    }

    private static void skipMethod(final List<Usuario> usuarios) {
        usuarios.stream()
                .skip(1)
                .forEach(System.out::println);
    }

    private static void sortedMethod(final List<Usuario> usuarios) {
        usuarios.stream()
                .filter(u -> u.getPontos() > 120)
                .sorted(Comparator.comparing(Usuario::getNome))
                .forEach(System.out::println);
    }

    private static void peekMethod(final List<Usuario> usuarios) {
        usuarios.stream()
                .filter(u -> u.getPontos() > 120)
                .peek(System.out::println)
                .findAny();

        usuarios.stream()
                .sorted(Comparator.comparing(Usuario::getNome))
                .peek(System.out::println)
                .findAny();
    }

    private static void maxMethod(final List<Usuario> usuarios) {
        Optional<Usuario> max = usuarios.stream()
                .max(Comparator.comparing(Usuario::getPontos));
        Usuario maximaPontuacao = max.get();
        System.out.println(maximaPontuacao);
    }

    private static void anyMatchMethod(final List<Usuario> usuarios) {
        boolean hasModerator = usuarios.stream()
                .anyMatch(Usuario::isModerador);
        System.out.println(hasModerator);
    }

    private static void sumMethod(final List<Usuario> usuarios) {

        int total = usuarios.stream()
                .mapToInt(Usuario::getPontos)
                .sum();

        System.out.println(total);
    }

    private static void streamItarator(final List<Usuario> usuarios) {
        usuarios.stream()
                .iterator()
                .forEachRemaining(System.out::println);
    }

    private static void reduceMethod(final List<Usuario> usuarios) {

        int valorInicial = 0;
        IntBinaryOperator operacao = (a, b) -> a + b;

        int total = usuarios.stream()
                .mapToInt(Usuario::getPontos)
                .reduce(valorInicial, operacao);

        System.out.println(total);

        //or

        total = usuarios.stream()
                .mapToInt(Usuario::getPontos)
                .reduce(0, (a, b) -> a + b);

        System.out.println(total);

        //or

        total = usuarios.stream()
                .mapToInt(Usuario::getPontos)
                .reduce(0, Integer::sum); //metodo Integer.sum soma dois inteiros

        System.out.println(total);

        int multiplicacao = usuarios.stream()
                .mapToInt(Usuario::getPontos)
                .reduce(1, (a, b) -> a * b);

        System.out.println(multiplicacao);

        //reduce sem map
        total = usuarios.stream()
                .reduce(0, (atual, u) -> atual + u.getPontos(), Integer::sum);

        System.out.println(total);
    }

    static class Fibonacci implements IntSupplier {

        private int anterior = 0;

        private int proximo = 1;

        public int getAsInt() {
            proximo = proximo + anterior;
            anterior = proximo - anterior;
            return anterior;
        }
    }

}
