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
    operadorDiferente, // <>
    operadorIgual,  // =
    parentesesEsquerdo, // (
    parentesesDireito, // )
    string, //''
    comentario, // {}
    EOF
 }