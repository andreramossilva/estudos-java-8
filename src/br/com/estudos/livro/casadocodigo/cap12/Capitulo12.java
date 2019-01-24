package br.com.estudos.livro.casadocodigo.cap12;

import br.com.estudos.livro.casadocodigo.cap2.Usuario;
import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Capitulo12 {

    public static void main(String[] args) {
        testingReflaction();
    }

    private static void testingReflaction() {
        Controller controller = new Controller();
        final Role[] roles = controller.getClass().getAnnotationsByType(Role.class);
        Arrays.asList(roles).forEach(a -> System.out.println(a.value()));

        try {
            Constructor<Usuario> constructor = Usuario.class.getDeclaredConstructor(String.class, int.class);
            final Parameter[] parameters = constructor.getParameters();
            Arrays.asList(parameters)
                    .forEach(param -> System.out.println(param.isNamePresent() + ":" + param.getName()));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    private static void testingInterfaceConversion() {
        TestingAmbiguous testingAmbiguous = new TestingAmbiguous();

        //o metodo supplier recebe um Supplier<String>
        //porém ao chamar é executado o método run de PrivilegedAction<String>
        PrivilegedAction<String> action = ()->"executando metodo run";
        testingAmbiguous.supplier(action::run);
    }

    private static void testingAmbiguos() {
        TestingAmbiguous testingAmbiguous = new TestingAmbiguous();

        //erro ao compilar, pois o compilador não sabe resolver
        //a sobrecarga de Supplier<T> e PrivilegedAction<T>
        //testingAmbiguous.ambiguous(()->"string");

        //Nesse caso, devemos "ajudar" o compilador passando
        //o tipo que queremos ultilizar
        testingAmbiguous.ambiguous((PrivilegedAction<String>)()->"string");
    }

    private static void testingDiamondOperators() {
        testingDiamondOperators(new ArrayList<>());
    }

    private static void testingDiamondOperators(final List<String> strings) {
        strings.add("Andre");
        System.out.println(strings);
    }

}
