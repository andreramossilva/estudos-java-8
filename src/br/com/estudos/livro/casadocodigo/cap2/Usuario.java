package br.com.estudos.livro.casadocodigo.cap2;

public class Usuario {

    private String nome;

    private int pontos;

    private boolean moderador;

    public Usuario() {
    }

    public Usuario(final String nome, final int pontos, final boolean moderador) {
        this.pontos = pontos;
        this.nome = nome;
        this.moderador = moderador;
    }

    public Usuario(final String nome) {
        this.nome = nome;
    }

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
        return nome;
    }
}
