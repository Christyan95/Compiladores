package lexico;

public enum Classe{
    identificador,
    numeroInteiro,
    palavraReservada,
    operadorSoma, // +
    operadorSubtracao, // -
    operadorMultiplicacao, // *
    operadorDivisao, // /
    doisPontos, // :
    pontoEVirgula,  // ;
    virgula, // ,
    ponto, // .
    atribuicao,  // :=
    operadorMaior, // >
    operadorMenor, // <
    operadorMaiorIgual, // >=
    operadorMenorIgual, // <=
    operadorDiferente, // <>s
    operadorIgual,  // =
    operadorE,  // and
    operadorOu,  // or
    operadorNegacao,  // not
    parentesesEsquerdo, // (
    parentesesDireito, // )
    string, //''
    comentario, // {}
    EOF
 }