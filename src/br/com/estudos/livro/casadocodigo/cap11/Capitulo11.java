package br.com.estudos.livro.casadocodigo.cap11;

import static java.util.Arrays.asList;

import java.math.BigDecimal;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Capitulo11 {

    Customer paulo = new Customer("Paulo Silveira");

    Customer rodrigo = new Customer("Rodrigo Turini");

    Customer guilherme = new Customer("Guilherme Silveira");

    Customer adriano = new Customer("Adriano Almeida");

    Product bach = new Product("Bach Completo",
            Paths.get("/music/bach.mp3"),
            new BigDecimal(100)
    );

    Product poderosas = new Product("Poderosas Anita",
            Paths.get("/music/poderosas.mp3"),
            new BigDecimal(90
            ));

    Product bandeira = new Product("Bandeira Brasil",
            Paths.get("/images/brasil.jpg"),
            new BigDecimal(50
            ));

    Product beauty = new Product("Beleza Americana",
            Paths.get("beauty.mov"),
            new BigDecimal(150));

    Product vingadores = new Product("Os Vingadores",
            Paths.get("/movies/vingadores.mov"),
            new BigDecimal(200));

    Product amelie = new Product("Amelie Poulain",
            Paths.get("/movies/amelie.mov"),
            new BigDecimal(100));

    LocalDateTime today = LocalDateTime.now();

    LocalDateTime yesterday = today.minusDays(1);

    LocalDateTime lastMonth = today.minusMonths(1);

    Payment payment1 = new Payment(asList(bach, poderosas), today, paulo);

    Payment payment2 = new Payment(asList(bach, bandeira, amelie), yesterday, rodrigo);

    Payment payment3 = new Payment(asList(beauty, vingadores, bach), today, adriano);

    Payment payment4 = new Payment(asList(bach, poderosas, amelie), lastMonth, guilherme);

    Payment payment5 = new Payment(asList(beauty, amelie), yesterday, paulo);

    List<Payment> payments = asList(payment1, payment2, payment3, payment4, payment5);

    BigDecimal monthlyFee = new BigDecimal("99.90");

    Subscription s1 = new Subscription(monthlyFee, yesterday.minusMonths(5), paulo);

    Subscription s2 = new Subscription(monthlyFee, yesterday.minusMonths(8), today.minusMonths(1), rodrigo);

    Subscription s3 = new Subscription(monthlyFee, yesterday.minusMonths(5), today.minusMonths(2), adriano);

    List<Subscription> subscriptions = Arrays.asList(s1, s2, s3);

    public static void main(String[] args) {
        final Capitulo11 capitulo11 = new Capitulo11();
        capitulo11.start();
    }

    private void start() {
        subscribesTotalPaid();
    }

    private void subscribesTotalPaid() {
        BigDecimal totalPaid = subscriptions
                .stream()
                .map(Subscription::getTotalPaid)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println(totalPaid);
    }

    private void groupingValuesByYearMonth() {
        Map<YearMonth, BigDecimal> paymentsValuePerMonth =
                payments.stream()
                        .collect(Collectors.groupingBy(
                                p -> YearMonth.from(p.getDate()),
                                Collectors.reducing(BigDecimal.ZERO,
                                        p -> p.getProducts().stream()
                                                .map(Product::getPrice)
                                                .reduce(BigDecimal.ZERO, BigDecimal::add),
                                        BigDecimal::add)));
        paymentsValuePerMonth.entrySet().forEach(System.out::println);
    }

    private void groupingByYearMonth() {
        payments
                .stream()
                .collect(Collectors.groupingBy(p -> YearMonth.from(p.getDate())))
                .entrySet()
                .forEach(System.out::println);
    }

    private void mostValueCustomer() {

        Function<Payment, BigDecimal> paymentToTotal =
                p -> p.getProducts().stream()
                        .map(Product::getPrice)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);

        final Map<Customer, BigDecimal> mostValue = payments
                .stream()
                .collect(Collectors.groupingBy(Payment::getCustomer,
                        Collectors.reducing(BigDecimal.ZERO, paymentToTotal, BigDecimal::add)));

        mostValue
                .entrySet()
                .stream()
                .sorted(Comparator.comparing(e -> e.getValue()))
                .forEach(System.out::println);

        System.out.println("\n");

        //recuperando o cliente que mais comprou
        final Map.Entry<Customer, BigDecimal> mostValueCosutmer = mostValue
                .entrySet()
                .stream()
                .max(Comparator.comparing(e -> e.getValue()))
                .get();

        System.out.println(mostValueCosutmer);
    }

    private void groupCustomersByPayments() {
        final Map<Customer, List<List<Product>>> customerToProductsList = payments
                .stream()
                .collect(Collectors.groupingBy(Payment::getCustomer,
                        Collectors.mapping(Payment::getProducts, Collectors.toList())));

        customerToProductsList
                .entrySet()
                .stream()
                .sorted(Comparator.comparing(e -> e.getKey().getName()))
                .forEach(System.out::println);

        System.out.println("\n");

        //Achatando
        final Map<Customer, List<Product>> customerToProducts2steps = customerToProductsList
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue()
                        .stream()
                        .flatMap(List::stream)
                        .collect(Collectors.toList())));

        customerToProducts2steps
                .entrySet()
                .stream()
                .sorted(Comparator.comparing(e -> e.getKey().getName()))
                .forEach(System.out::println);

        System.out.println("\n");

        //com reducing
        Map<Customer, List<Product>> customerToProducts =
                payments.stream()
                        .collect(Collectors.groupingBy(Payment::getCustomer,
                                Collectors.reducing(Collections.emptyList(),
                                        Payment::getProducts,
                                        (l1, l2) -> l2))); //l1 é sempre vazio: Collections.emtpyList(), l2 é a lista de produtos
        customerToProducts
                .entrySet()
                .stream()
                .sorted(Comparator.comparing(e -> e.getKey().getName()))
                .forEach(System.out::println);
    }

    private void summingValuesByProduct() {
        final Map<Product, BigDecimal> totalValuePerProduct = payments.stream()
                .flatMap(p -> p.getProducts().stream())
                .collect(Collectors.groupingBy(
                        Function.identity(), Collectors.reducing(BigDecimal.ZERO, Product::getPrice, BigDecimal::add)));

        totalValuePerProduct
                .entrySet()
                .stream()
                .sorted(Comparator.comparing(Map.Entry::getValue))
                .forEach(System.out::println);

    }

    private void mappingMostSellerProducts() {

        final Map<Product, Long> topProducts = payments.stream()
                .flatMap(p -> p.getProducts().stream())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        topProducts.entrySet().stream().max(Comparator.comparing(Map.Entry::getValue)).ifPresent(System.out::println);
    }

    private void calculatingAllValues() {

        final BigDecimal total = payments.stream()
                .map(p -> p.getProducts().stream()
                        .map(Product::getPrice)
                        .reduce(BigDecimal.ZERO, BigDecimal::add))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.println(total);

        //or

        payments.stream()
                .flatMap(p -> p.getProducts().stream()
                        .map(Product::getPrice))
                .reduce(BigDecimal::add)
                .ifPresent(System.out::println);

        //or
        Function<Payment, Stream<BigDecimal>> mapper =
                p -> p.getProducts().stream().map(Product::getPrice);
        payments.stream()
                .flatMap(mapper)
                .reduce(BigDecimal::add)
                .ifPresent(System.out::println);

    }

    private void calculatingValeuOfPayment1() {
        payment1.getProducts().stream()
                .map(Product::getPrice)
                .reduce(BigDecimal::add)
                .ifPresent(System.out::println);

        //or

        final BigDecimal value = payment1.getProducts().stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.println(value);
    }

    private void orderingPayments() {
        payments.stream()
                .sorted(Comparator.comparing(Payment::getDate)).
                forEach(System.out::println);
    }

}
