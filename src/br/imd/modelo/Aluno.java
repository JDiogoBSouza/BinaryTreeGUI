package br.imd.modelo;

public class Aluno {
	
    private int matricula;
    private String nome;

    public Aluno(int mat, String nome) {
        this.matricula = mat;
        this.nome = nome;
    }
    
    public Aluno(Aluno a)
    {
        this.matricula = a.getMatricula();
        this.nome = a.getNome();
    }

    // Getters and Setters
    
    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int mat) {
        this.matricula = mat;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
