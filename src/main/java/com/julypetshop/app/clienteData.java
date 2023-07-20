package com.julypetshop.app;

public class clienteData {

    //private Integer cli_id;
    private String cli_name;
    private String cli_cpf;
    private String cli_telefone;
    private String cli_cep;
    private String cli_rua;
    private String cli_numero;

    public clienteData(String cli_name, String cli_cpf, String cli_telefone, String cli_cep, String cli_rua, String cli_numero ){
        this.cli_name       = cli_name;
        this.cli_cpf        = cli_cpf;
        this.cli_telefone   = cli_telefone;
        this.cli_cep        = cli_cep;
        this.cli_rua        = cli_rua;
        this.cli_numero     = cli_numero;
    }

    public String getCli_name() {
        return cli_name;
    }

    public String getCli_cpf() {
        return cli_cpf;
    }

    public String getCli_telefone() {
        return cli_telefone;
    }

    public String getCli_cep() {
        return cli_cep;
    }

    public String getCli_rua() {
        return cli_rua;
    }

    public String getCli_numero() {
        return cli_numero;
    }

}
