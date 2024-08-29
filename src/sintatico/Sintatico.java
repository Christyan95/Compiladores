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
}