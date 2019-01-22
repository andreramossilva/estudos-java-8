package com.br.estudos.livro.casadocodigo.cap2;

public class Usuario {

    private String nome;

    private int pontos;

    private boolean moderador;

    public Usuario(String nome, int pontos) {
        this.pontos = pontos;
        this.nome = nome;
        this.moderador = false;
    }

    public String getNome() {
        return nome;
    }

    public int getPontos() {
        return pontos;
    }

    public void tornaModerador() {
        this.moderador = true;
    }

    public boolean isModerador() {
        return moderador;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder()//
                .append("Usuario [")//
                .append("nome=\"")//
                .append(nome).append("\"")//
                .append(",pontos=")//
                .append(pontos)//
                .append(",moderador=")//
                .append(moderador)//
                .append("]");
        return builder.toString();
    }
}
