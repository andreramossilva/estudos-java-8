package br.com.estudos.livro.casadocodigo.cap12;

import java.security.PrivilegedAction;
import java.util.function.Supplier;

public class TestingAmbiguous {

    public void supplier(Supplier<String> supplier){
        System.out.println(supplier.get());
    }

    public void ambiguous(Supplier<String> supplier){
        System.out.println(supplier.get());
    }

    public void ambiguous(PrivilegedAction<String> privilegedAction){
        System.out.println(privilegedAction.run());
    }

}
