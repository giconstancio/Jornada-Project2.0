package com.copel.Jornada.Demanda.componentes;

public class Regiao {
    
    private String nomeRegiao;
    private int recorrenciaChamados;

    public Regiao(String nomeRegiao) throws IllegalArgumentException {
        if(!nomeRegiao.equalsIgnoreCase("centro") && !nomeRegiao.equalsIgnoreCase("bairro") && !nomeRegiao.equalsIgnoreCase("rural")) {
            throw new IllegalArgumentException("Erro: Nome da região inválido. Opções válidas: 'centro', 'bairro' ou 'rural'.");
        } else {
            this.nomeRegiao = nomeRegiao;
        }
    }

    public void definirRecorrenciaPelaRegiao() {
        if (nomeRegiao.equalsIgnoreCase("centro")) {
            this.recorrenciaChamados = 15;
        } else if (nomeRegiao.equalsIgnoreCase("bairro")) {
            this.recorrenciaChamados = 25;
        } else if (nomeRegiao.equalsIgnoreCase("rural")) {
            this.recorrenciaChamados = 10;
        }
    }

    public int definirPesoPorRecorrencia() {
        int pesoRecorrencia = (int) (recorrenciaChamados / 10);
        return pesoRecorrencia;
    }

    public int definirPesoPorRegiao() {
        if (nomeRegiao.equalsIgnoreCase("centro")) {
            return 10;
        } else if (nomeRegiao.equalsIgnoreCase("bairro")) {
            return 20;
        } else if (nomeRegiao.equalsIgnoreCase("rural")) {
            return 30;
        } return 0;
    }

    public String getNomeRegiao() {
        return nomeRegiao;
    }

    public void setNomeRegiao(String nomeRegiao) {
        this.nomeRegiao = nomeRegiao;
    }

    public int getRecorrenciaChamados() {
        return recorrenciaChamados;
    }

    public void setRecorrenciaChamados(int recorrenciaChamados) {
        this.recorrenciaChamados = recorrenciaChamados;
    }
}
