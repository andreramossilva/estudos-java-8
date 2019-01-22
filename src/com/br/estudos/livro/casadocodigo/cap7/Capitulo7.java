package com.br.estudos.livro.casadocodigo.cap7;

import com.br.estudos.livro.casadocodigo.cap2.Usuario;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

public class Capitulo7 {

    Usuario user1 = new Usuario("Paulo  Silveira", 150);

    Usuario user2 = new Usuario("Rodrigo  Turini", 120);

    Usuario user3 = new Usuario("Guilherme  Silveira", 190);

    List<Usuario> usuarios = Arrays.asList(user1, user2, user3);

    public static void main(String[] args) {
        final Capitulo7 capitulo7 = new Capitulo7();
        optionalLazyModo(capitulo7);
    }

    private static void filter(final Capitulo7 capitulo7) {
        final List<Usuario> list = capitulo7.usuarios.stream().filter(u -> u.getPontos() > 150)
                .collect(Collectors.toList());
        list.forEach(System.out::println);

        //se quiser somente ver o resultado nao precisa da lista
        capitulo7.usuarios.stream().filter(u -> u.getPontos() > 150).forEach(System.out::println);
    }

    private static void map(final Capitulo7 capitulo7) {
        final List<Integer> list = capitulo7.usuarios.stream().map(Usuario::getPontos).collect(Collectors.toList());
        list.forEach(System.out::println);
        
        //para evitar autoboxing, utilizar mapToInt
        final double asDouble = capitulo7.usuarios.stream().mapToInt(Usuario::getPontos).average().getAsDouble();
        System.out.println(asDouble);

    }

    private static void optionalDouble(final Capitulo7 capitulo7) {
        final OptionalDouble average = capitulo7.usuarios.stream().mapToInt(Usuario::getPontos).average();
        System.out.println(average.orElse(0.0));

        final double average1 = capitulo7.usuarios.stream().mapToInt(Usuario::getPontos).average()
                .orElseThrow(IllegalArgumentException::new);

        System.out.println(average1);

    }

    private static void optionalIfPresent(final Capitulo7 capitulo7) {
        final OptionalDouble average = capitulo7.usuarios.stream().mapToInt(Usuario::getPontos).average();
        average.ifPresent(a -> System.out.println(average.orElse(0.0)));
    }

    private static void optionalLazyModo(final Capitulo7 capitulo7) {
        final Optional<String> optional = capitulo7.usuarios.stream().max(Comparator.comparingInt(Usuario::getPontos))
                .map(Usuario::getNome);
        optional.ifPresent(a -> System.out.println(a));
    }
}
