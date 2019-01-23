package br.com.estudos.livro.casadocodigo.cap9;

import br.com.estudos.livro.casadocodigo.cap2.Usuario;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Capitulo9 {

    Usuario user1 = new Usuario("Paulo Silveira", 150, true);

    Usuario user2 = new Usuario("Rodrigo Turini", 120, true);

    Usuario user3 = new Usuario("Guilherme Silveira", 90);

    Usuario user4 = new Usuario("SergioLopes", 120);

    Usuario user5 = new Usuario("AdrianoAlmeida", 100);

    List<Usuario> usuarios = Arrays.asList(user1, user2, user3, user4, user5);

    public static void main(String[] args) {
        partition(new Capitulo9().usuarios);
    }

    private static void countLinesOfFile() {

        Map<Path, Long> linesPerFile = null;

        try {
            linesPerFile = Files.list(
                    Paths.get("/home/s2it_asilva/workspace/estudos-java-8/src/br/com/estudos/livro/casadocodigo/cap8"))
                    .filter(p -> p.toString().endsWith(".txt"))
                    .collect(Collectors.toMap(
                            Function.identity(), //Function.identity() é gial a p -> p
                            p -> lines(p).count()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(linesPerFile);
    }

    private static Stream<String> lines(Path p) {
        try {
            return Files.lines(p);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private static void mappingUser(List<Usuario> usuarios) {

        Map<Integer, List<Usuario>> pontuacao = usuarios
                .stream()
                .collect(Collectors.groupingBy(Usuario::getPontos));

        System.out.println(pontuacao);
    }

    private static void partition(List<Usuario> usuarios) {

        //O partitioningBy nada mais é do que uma versão mais
        //eficiente para ser usada ao agrupar booleans

        //particionar usuarios por que é moderador ou não
        Map<Boolean, List<Usuario>> moderadores = usuarios
                .stream()
                .collect(
                        Collectors.partitioningBy(Usuario::isModerador));
        System.out.println(moderadores);

        //or

        Map<Boolean, List<String>> nomesPorTipo = usuarios
                .stream()
                .collect(
                        Collectors.partitioningBy(
                                Usuario::isModerador,
                                Collectors.mapping(Usuario::getNome,
                                        Collectors.toList())));

        System.out.println(nomesPorTipo);

        //para somar os pontos dos usuarios
        Map<Boolean, Integer> pontuacaoPorTipo = usuarios
                .stream()
                .collect(
                        Collectors.partitioningBy(
                                Usuario::isModerador,
                                Collectors.summingInt(Usuario::getPontos)));
        System.out.println(pontuacaoPorTipo);

        //concatenar nomes
        String nomes = usuarios
                .stream()
                .map(Usuario::getNome)
                .collect(Collectors.joining(","));

        System.out.println(nomes);

        //Importante observar que não ultilizamos mais loops, somente colletors

    }

}
