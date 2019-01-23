package br.com.estudos.livro.casadocodigo.cap10;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.MonthDay;
import java.time.Period;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class Capitulo10 {

    public static void main(String[] args) {
        duration();
    }

    private static void duration() {
        LocalDateTime agora = LocalDateTime.now();
        LocalDateTime daquiAUmaHora = LocalDateTime.now().plusHours(1
        );
        Duration duration = Duration.between(agora, daquiAUmaHora);
        if (duration.isNegative()) {
            duration = duration.negated();
        }
        System.out.printf("%s horas, %s minutos e %s segundos",
                duration.toHours(), duration.toMinutes(),
                duration.getSeconds());
    }

    //considera somente periodos de datas sem hora
    private static void period() {
        LocalDate agora = LocalDate.now();
        LocalDate outraData = LocalDate.of(1989, Month.JANUARY, 25);
        Period periodo = Period.between(outraData, agora);
        System.out.printf("%s dias, %s meses e %s anos",
                periodo.getDays(),
                periodo.getMonths(), periodo.getYears());

        System.out.println("\n");

        // valores negativos
        LocalDate agora1 = LocalDate.now();
        LocalDate outraData1 = LocalDate.of(2050, Month.JANUARY, 25);
        Period periodo1 = Period.between(outraData1, agora1);

        System.out.printf("%s dias,%s meses e %s anos",
                periodo1.getDays(), periodo1.getMonths(), periodo1.getYears(
                ));

        System.out.println("\n");

        if (periodo1.isNegative()) {
            periodo1 = periodo1.negated();
        }

        System.out.printf("%s dias,%s meses e %s anos",
                periodo1.getDays(), periodo1.getMonths(), periodo1.getYears(
                ));
    }

    private static void chronoUnit() {
        LocalDate agora = LocalDate.now();
        LocalDate outraData = LocalDate.of(1989, Month.JANUARY, 25);

        long dias = ChronoUnit.DAYS.between(outraData, agora);
        System.out.println(dias);
    }

    private static void invalidHour() {
        LocalDateTime horaInvalida = LocalDate.now().atTime(25, 0);
    }

    private static void invalidDate() {
        LocalDate.of(2014, Month.FEBRUARY, 30);
    }

    private static void parse() {
        LocalDateTime agora = LocalDateTime.now();
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String resultado = agora.format(formatador);
        LocalDate agoraEmData = LocalDate.parse(resultado, formatador);
        System.out.println(agoraEmData);
    }

    private static void format() {
        LocalDateTime agora = LocalDateTime.now();
        String resultado = agora.format(DateTimeFormatter.ISO_LOCAL_TIME);
        System.out.println(resultado);

        LocalDateTime agora1 = LocalDateTime.now();
        System.out.println(agora1.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    }

    private static void dayOfWeek() {
        Locale pt = new Locale("pt");
        System.out.println(LocalDate.now().getDayOfWeek().getDisplayName(TextStyle.FULL, pt));
        System.out.println(LocalDate.now().getDayOfWeek().getDisplayName(TextStyle.SHORT, pt));
    }

    private static void getDisplayName() {
        Locale pt = new Locale("pt");
        System.out.println(Month.DECEMBER.getDisplayName(TextStyle.FULL, pt));
        System.out.println(Month.DECEMBER.getDisplayName(TextStyle.SHORT, pt));

    }

    private static void firstMonthOfQuarter() {
        System.out.println(Month.DECEMBER.firstMonthOfQuarter());
        System.out.println(Month.DECEMBER.plus(2));
        System.out.println(Month.DECEMBER.minus(1));
    }

    private static void yearMonth() {
        YearMonth ym = YearMonth.from(LocalDateTime.now());
        System.out.println(ym.getMonth() + " " + ym.getYear());
    }

    private static void monthDay() {
        System.out.println("hoje é dia:" + MonthDay.now().getDayOfMonth());
    }

    private static void isEqual() {
        ZonedDateTime tokyo = ZonedDateTime
                .of(2011, 5, 2, 10, 30, 0, 0, ZoneId.of("Asia/Tokyo"));
        ZonedDateTime saoPaulo = ZonedDateTime
                .of(2011, 5, 2, 10, 30, 0, 0, ZoneId.of("America/Sao_Paulo"));

        tokyo = tokyo.plusHours(12); //adicionando fuso

        System.out.println(tokyo.isEqual(saoPaulo));
    }

    private static void is() {
        LocalDate hoje = LocalDate.now();
        LocalDate amanha = LocalDate.now().plusDays(1);
        System.out.println(hoje.isBefore(amanha));
        System.out.println(hoje.isAfter(amanha));
        System.out.println(hoje.isEqual(amanha));
    }

    private static void with() {
        LocalDate dataDoPassado = LocalDate.now().withYear(1988);
        System.out.println(dataDoPassado);
    }

    private static void atTimeAndAtZone() {
        LocalTime agora = LocalTime.now();
        LocalDate hoje = LocalDate.now();
        LocalDateTime dataEhora = hoje.atTime(agora);
        System.out.println(dataEhora);

        ZonedDateTime dataComHoraETimezone =
                dataEhora.atZone(ZoneId.of("America/Sao_Paulo"));
        System.out.println(dataComHoraETimezone);
    }
}
