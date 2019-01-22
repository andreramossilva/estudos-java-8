import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class EstudosStream {

    public static void main(String[] args) {

        List<String> list = Arrays.asList(
                "� importante questionar o quanto o consenso sobre a necessidade de qualifica��o desafia a capacidade de equaliza��o de alternativas �s solu��es ortodoxas",
                "No entanto, n�o podemos esquecer que a crescente influ�ncia da m�dia apresenta tend�ncias no sentido de aprovar a manuten��o de todos os recursos funcionais envolvidos",
                "Gostaria de enfatizar que a percep��o das dificuldades desafia a capacidade de equaliza��o do investimento em reciclagem t�cnica",
                "A certifica��o de metodologias que nos auxiliam a lidar com o desafiador cen�rio globalizado assume importantes posi��es no estabelecimento de todos os recursos funcionais envolvidos",
                "Por conseguinte, a competitividade nas transa��es comerciais n�o pode mais se dissociar dos n�veis de motiva��o departamental",
                "Todavia, o acompanhamento das prefer�ncias de consumo causa impacto indireto na reavalia��o dos relacionamentos verticais entre as hierarquias",
                "No entanto, n�o podemos esquecer que o julgamento imparcial das eventualidades assume importantes posi��es no estabelecimento de alternativas �s solu��es ortodoxas",
                "Pensando mais a longo prazo, a estrutura atual da organiza��o garante a contribui��o de um grupo importante na determina��o das diversas correntes de pensamento",
                "Percebemos, cada vez mais, que a competitividade nas transa��es comerciais possibilita uma melhor vis�o global das novas proposi��es",
                "Todas estas quest�es, devidamente ponderadas, levantam d�vidas sobre se o acompanhamento das prefer�ncias de consumo � uma das consequ�ncias de todos os recursos funcionais envolvidos",
                "No mundo atual, o acompanhamento das prefer�ncias de consumo cumpre um papel essencial na formula��o do sistema de forma��o de quadros que corresponde �s necessidades",
                "Do mesmo modo, a crescente influ�ncia da m�dia representa uma abertura para a melhoria dos relacionamentos verticais entre as hierarquias",
                "Evidentemente, a cont�nua expans�o de nossa atividade causa impacto indireto na reavalia��o das posturas dos �rg�os dirigentes com rela��o �s suas atribui��es");
        test(list);



    }

    private static void filter(final List<String> list) {
        final List<String> l = list.stream().filter(s -> s.contains("n�o")).collect(Collectors.toList());
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
