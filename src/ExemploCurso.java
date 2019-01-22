import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ExemploCurso{
    public static void main(String[]args){
        List<Curso> cursos = new ArrayList<>();
        cursos.add(new Curso("Python", 45));
        cursos.add(new Curso("JavaScript", 150));
        cursos.add(new Curso("Java 8", 113));
        cursos.add(new Curso("C", 55));

        cursos.sort(Comparator.comparing(Curso::getAlunos));
        //cursos.forEach(c -> System.out.println(c.getNome()));

        //filtrando cursos com mais de 110 alunos
        final int sum = cursos.stream() //stream: interface fluente, lembrando que, o filter não altera a colecao.
                .filter(c -> c.getAlunos() > 100)
                .mapToInt(Curso::getAlunos)
                .sum();

        //System.out.println(sum);

        //Optional: nova classe para evitar verificacoes null.
        final Optional<Curso> curso = cursos
                .stream() //stream: interface fluente, lembrando que, o filter não altera a colecao.
                .filter(c -> c.getAlunos() > 100)
                .findAny();

        //ifpresent: verifica se tem um objeto, se for null, não faz nada
        //curso.ifPresent(c -> System.out.println(c.getNome()));

        //recuperando objetos filtrados
        final List<Curso> list = cursos
                .stream() //stream: interface fluente, lembrando que, o filter não altera a colecao.
                .filter(c -> c.getAlunos() > 100)
                .collect(Collectors.toList());

        //objetosFiltrados.forEach(o -> System.out.println(o.getNome()));

        //recuperando objetos filtrados e transformando em Map
        cursos
            .stream() //stream: interface fluente, lembrando que, o filter não altera a colecao.
            .filter(c -> c.getAlunos() > 100)
            .collect(Collectors.toMap(c -> c.getNome(), c -> c.getAlunos()))
            .forEach((nome, alunos) -> System.out.println(nome + " tem " + alunos + " alunos"));


        
    }
}
class Curso {
      private String nome;
      private int alunos;

    public Curso(final String nome, final int alunos) {
        this.nome = nome;
        this.alunos = alunos;
    }

    public String getNome() {
        return nome;
    }

    public int getAlunos() {
        return alunos;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder()//
                .append("Curso [")//
                .append("nome=\"")//
                .append(nome).append("\"")//
                .append(",alunos=")//
                .append(alunos)//
                .append("]");
        return builder.toString();
    }
}


