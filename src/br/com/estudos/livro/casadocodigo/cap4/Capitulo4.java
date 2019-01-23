package br.com.estudos.livro.casadocodigo.cap4;

import br.com.estudos.livro.casadocodigo.cap2.Usuario;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Capitulo4 {

    Usuario user1 = new Usuario("Paulo  Silveira", 150);
    Usuario user2 = new Usuario("Rodrigo  Turini", 120);
    Usuario user3 = new Usuario("Guilherme  Silveira", 190);

    List<Usuario> usuarios = new ArrayList<>();

    public static void main(String[] args) {

        final Capitulo4 capitulo4 = new Capitulo4();

        capitulo4.usuarios.add(capitulo4.user1);
        capitulo4.usuarios.add(capitulo4.user2);
        capitulo4.usuarios.add(capitulo4.user3);
        
        removeIfMethod(capitulo4);
    }

    private static void andThenMethod(final Capitulo4 capitulo4){
        Consumer<Usuario> mostraMensagem = u -> System.out.println("antes de imprimir os nomes");
        Consumer<Usuario> imprimeNome = u -> System.out.println(u.getNome());
        capitulo4.usuarios.forEach(mostraMensagem.andThen(imprimeNome));
    }

    private static void removeIfMethod (final Capitulo4 capitulo4){
        capitulo4.usuarios.removeIf(u->u.getPontos()>160);
        capitulo4.usuarios.forEach(u-> System.out.println(u.getNome()));
    }


}
