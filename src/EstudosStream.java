import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class EstudosStream {

    public static void main(String[] args) {

        List<String> list = Arrays.asList(
                "É importante questionar o quanto o consenso sobre a necessidade de qualificação desafia a capacidade de equalização de alternativas às soluções ortodoxas",
                "No entanto, não podemos esquecer que a crescente influência da mídia apresenta tendências no sentido de aprovar a manutenção de todos os recursos funcionais envolvidos",
                "Gostaria de enfatizar que a percepção das dificuldades desafia a capacidade de equalização do investimento em reciclagem técnica",
                "A certificação de metodologias que nos auxiliam a lidar com o desafiador cenário globalizado assume importantes posições no estabelecimento de todos os recursos funcionais envolvidos",
                "Por conseguinte, a competitividade nas transações comerciais não pode mais se dissociar dos níveis de motivação departamental",
                "Todavia, o acompanhamento das preferências de consumo causa impacto indireto na reavaliação dos relacionamentos verticais entre as hierarquias",
                "No entanto, não podemos esquecer que o julgamento imparcial das eventualidades assume importantes posições no estabelecimento de alternativas às soluções ortodoxas",
                "Pensando mais a longo prazo, a estrutura atual da organização garante a contribuição de um grupo importante na determinação das diversas correntes de pensamento",
                "Percebemos, cada vez mais, que a competitividade nas transações comerciais possibilita uma melhor visão global das novas proposições",
                "Todas estas questões, devidamente ponderadas, levantam dúvidas sobre se o acompanhamento das preferências de consumo é uma das consequências de todos os recursos funcionais envolvidos",
                "No mundo atual, o acompanhamento das preferências de consumo cumpre um papel essencial na formulação do sistema de formação de quadros que corresponde às necessidades",
                "Do mesmo modo, a crescente influência da mídia representa uma abertura para a melhoria dos relacionamentos verticais entre as hierarquias",
                "Evidentemente, a contínua expansão de nossa atividade causa impacto indireto na reavaliação das posturas dos órgãos dirigentes com relação às suas atribuições");
        test(list);



    }

    private static void filter(final List<String> list) {
        final List<String> l = list.stream().filter(s -> s.contains("não")).collect(Collectors.toList());
        l.forEach(System.out::println);
    }

    private static void mapToInt(final List<String> list){
        final int sum = list.stream().mapToInt(String::length).sum();
        System.out.println(sum);
    }

    private static void allMatch(final List<String> list){
        //Verifica, atraves de um Predicate, se todos os elementos da colecao tem y.
        //Se todos os elementos tiver y retorna true se nao false
        System.out.println(list.stream().allMatch(s -> s.contains("y")));
    }

    private static void anyMatch(final List<String> list){
        //Verifica, atraves de um Predicate, se algum elemento da colecao tem y.
        //Se algum elemento tiver y retorna true se nao false
        System.out.println(list.stream().anyMatch(s -> s.contains("y")));
    }

    private static void flatMapToInt(){
        int[] intArray = {1, 2, 3, 4, 5, 6};

        //1. Stream<int[]>
        Stream<int[]> streamArray = Stream.of(intArray);

        //2. Stream<int[]> -> flatMap -> IntStream
        IntStream intStream = streamArray.flatMapToInt(x -> Arrays.stream(x));

        intStream.forEach(x -> System.out.println(x));
    }

    private static void test(final List<String> list){
        list.stream().peek(System.out::println);
    }

}
