package br.com.estudos.livro.casadocodigo.cap6;

import br.com.estudos.livro.casadocodigo.cap2.Usuario;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class Capitulo6 {

    Usuario user1 = new Usuario("Paulo  Silveira", 150);
    Usuario user2 = new Usuario("Rodrigo  Turini", 120);
    Usuario user3 = new Usuario("Guilherme  Silveira", 190);

    List<Usuario> usuarios = new ArrayList<>();

    public static void main(String[] args) {

        final Capitulo6 capitulo6 = new Capitulo6();

        capitulo6.usuarios.add(capitulo6.user1);
        capitulo6.usuarios.add(capitulo6.user2);
        capitulo6.usuarios.add(capitulo6.user3);

        constructorReferenceWithJustOneParameter(capitulo6);
    }

    private static void comparingMethod(final Capitulo6 Capitulo6) {

        Capitulo6.usuarios.sort(
                Comparator.comparingInt(Usuario::getPontos)
                        .thenComparing(Usuario::getNome)
        );

        System.out.println(Capitulo6.usuarios);
    }

    private static void reversedMethod(final Capitulo6 Capitulo6) {

        Capitulo6.usuarios.sort(
                Comparator
                        .comparingInt(Usuario::getPontos)
                        .thenComparing(Usuario::getNome)
                        .reversed()
        );

        System.out.println(Capitulo6.usuarios);
    }

    private static void constructorReference() {
        Supplier<Usuario> criadorDeUsuarios = Usuario::new;
        final Usuario usuario = criadorDeUsuarios.get();
    }

    private static void constructorReferenceWithJustOneParameter(final Capitulo6 capitulo6) {
        Function<String, Usuario> criadorDeUsuarios = Usuario::new;
        final Usuario usuario = criadorDeUsuarios.apply("Andre");
        capitulo6.usuarios.add(usuario);
        capitulo6.usuarios.forEach(System.out::println);
    }

    private static void constructorReferenceWithManyParameter(final Capitulo6 capitulo6) {
        BiFunction<String, Integer, Usuario> criadorDeUsuarios = Usuario::new;
        final Usuario usuario = criadorDeUsuarios.apply("Andre", 290);
        capitulo6.usuarios.add(usuario);
        capitulo6.usuarios.forEach(System.out::println);
    }

}
