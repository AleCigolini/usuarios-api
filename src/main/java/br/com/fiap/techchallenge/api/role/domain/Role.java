package br.com.fiap.techchallenge.api.role.domain;


import br.com.fiap.techchallenge.api.core.utils.domain.DominioBase;

public class Role extends DominioBase {

    private String role;
    private String descricao;
    private Boolean ativo;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

}

