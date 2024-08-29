package sintatico;

import lexico.Classe;
import lexico.Lexico;
import lexico.Token;

public class Sintatico{

    private Lexico lexico;
    private String aquivoLeitura;
    private Token token;



    public Sintatico(String arquivo){
        this.aquivoLeitura = arquivo;
        lexico = new Lexico(arquivo);
    }



    public void analisar(){
        token = lexico.nextToken();
        programa();
    }



    //<programa> ::= program id {A01} ; <corpo> • {A45}
    public void programa(){
        if (token.getClasse() == Classe.palavraReservada && token.getValor().getTexto().equalsIgnoreCase("program")) {
            token = lexico.nextToken();
            if (token.getClasse() == Classe.indentificador) {
                token = lexico.nextToken();
                // {A01}
                if (token.getClasse() == Classe.pontoEVirgula) {
                    token = lexico.nextToken();
                    corpo();
                    if (token.getClasse() == Classe.ponto) {
                        token = lexico.nextToken();
                        // {A45}
                    } else{
                        System.err.println(token.getLinha() + "," + token.getColuna() + " - " + "Erro sintático: faltou ponto final no programa (.)");
                    }
                } else{
                    System.err.println(token.getLinha() + "," + token.getColuna() + " - " + "Erro sintático: faltou ponto e vírgula ( ; ) depois do nome do programa");
                }
            } else{
                System.err.println(token.getLinha() + "," + token.getColuna() + " - " + "Erro sintático: faltou po nome do programa");
            }            
        }else{
            System.err.println(token.getLinha() + "," + token.getColuna() + " - " + "Erro sintático: faltou começar o programa com PROGRAM");
        }
    }



    // <corpo> ::= <declara> <rotina> {A44} begin <sentencas> end {A46}
    public void corpo(){
        declara();
        rotina();
        // {A44}
        if (token.getClasse() == Classe.palavraReservada && token.getValor().getTexto().equalsIgnoreCase("begin")) {
            token = lexico.nextToken();
            sentencas();
            if (token.getClasse() == Classe.palavraReservada && token.getValor().getTexto().equalsIgnoreCase("end")){
                token = lexico.nextToken();
            }else{
                System.err.println(token.getLinha() + "," + token.getColuna() + " - " + "Erro sintático: faltou terminar o corpo doprograma com END");
            }
        }else{
            System.err.println(token.getLinha() + "," + token.getColuna() + " - " + "Erro sintático: faltou começar o corpo do programa com BEGIN");
        }
    }



    //<declara> ::= var<dvar> <mais_dc> | E
    public void declara() {
        if (token.getClasse() == Classe.palavraReservada && token.getValor().getTexto().equalsIgnoreCase("var")) {
            token = lexico.nextToken();
            dvar();
            mais_dc();
        }
    }



    //<dvar> ::= <variaveis> : <tipo_var> {A02}
    public void dvar() {
        variaveis();
        if (token.getClasse() == Classe.doisPontos) {
            token = lexico.nextToken();
            tipo_var();
        } else {
            System.err.println(token.getLinha() + "," + token.getColuna() + " - " + "Erro sintático: faltou dois pontos (:) depois da declaração de variáveis");
        }
        // {A02}
    }



    public void variaveis() {
        if (token.getClasse() == Classe.palavraReservada && token.getValor().getTexto().equalsIgnoreCase("id")) {
            token = lexico.nextToken();
            // {A03}
            mais_var();
        }
    }



    public void mais_var() {
        if (token.getClasse() == Classe.virgula) {
            token = lexico.nextToken();
            variaveis();
        } else {
            System.err.println(token.getLinha() + "," + token.getColuna() + " - " + "Erro sintático: faltou vírgula (,) no final da atribuição do valor da variável");
        }
    }



    public void tipo_var() {
        if (token.getClasse() == Classe.palavraReservada && token.getValor().getTexto().equalsIgnoreCase("integer")) {
            token = lexico.nextToken();
        }
    }



    //<mais_dc> ::= ; <cont_dc>
    public void mais_dc() {
        if (token.getClasse() == Classe.pontoEVirgula) {
            token = lexico.nextToken();
            cont_dc();
        } else {
            System.err.println(token.getLinha() + "," + token.getColuna() + " - " + "Erro sintático: faltou ponto e vírgula (;) no final de uma declaração de variáveis");
        }
    }



    //<cont_dc> ::= <dvar> <mais_dc> | ε
    public void cont_dc() {
        dvar();
        mais_dc();
    }



    //s<rotina> ::= <procedimento> | <funcao> | ε
    public void rotina() {
        procedimento();
        funcao();
    }



    //<procedimento> ::= procedure id {A04} <parametros> {A48}; <corpo> {A56} ; <rotina>
    public void procedimento() {
        if (token.getClasse() == Classe.palavraReservada && token.getValor().getTexto().equalsIgnoreCase("procedure")) {
            token = lexico.nextToken();
            if (token.getClasse() == Classe.palavraReservada && token.getValor().getTexto().equalsIgnoreCase("id")) {
                token = lexico.nextToken();
                // {A04}
                parametros();
                // {A48}
                if (token.getClasse() == Classe.pontoEVirgula) {
                    token = lexico.nextToken();
                    corpo();
                    // {A56}
                    if (token.getClasse() == Classe.pontoEVirgula) {
                        token = lexico.nextToken();
                        rotina();
                    } else {
                        System.err.println(token.getLinha() + "," + token.getColuna() + " - " + "Erro sintático: faltou ponto e vírgula (;) depois do corpo");
                    }
                } else {
                    System.err.println(token.getLinha() + "," + token.getColuna() + " - " + "Erro sintático: faltou ponto e vírgula (;) depois do parametro");
                }
            }
        }
    }



    public void parametros() {

    }



    //<funcao> ::= function id {A05} <parametros> {A48} : <tipo_funcao> {A47} ; <corpo> {A56} ; <rotina>
    public void funcao() {

    }



    public void sentencas() {

    }
}