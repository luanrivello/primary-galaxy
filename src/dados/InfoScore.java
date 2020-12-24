package dados;

import java.io.Serializable;

public class InfoScore implements Serializable{
    private final int ponto;
    private final String nome;

    public InfoScore(String nome, int ponto) {
        this.nome = nome;
        this.ponto = ponto;
        
    }

    public int getPonto() {
        return ponto;
    }

    public String getNome() {
        return nome;
    }

}
