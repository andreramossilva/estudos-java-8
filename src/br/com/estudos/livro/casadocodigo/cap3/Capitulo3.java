package br.com.estudos.livro.casadocodigo.cap3;

public class Capitulo3 {

    public static void main(String[] args) {

        //com lambda
        Validador<String> validaCEP = valor -> {
            final boolean result = valor.matches("[0-9]{5}-[0-9]{3}");
            System.out.println(result);
            return result;
        };
        validaCEP.valida("14811-162");

        //com classe anonima
        Validador<String> validaCEP1 = new Validador<String>() {
            @Override
            public boolean valida(final String valor) {
                final boolean result = valor.matches("[0-9]{5}-[0-9]{3}");
                System.out.println(result);
                return result;
            }
        };

        validaCEP.valida("14811-162");
    }
}