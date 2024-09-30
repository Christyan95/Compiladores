import sintatico.Sintatico;

public class App {
    public static void main(String[] args) throws Exception {
        // Lexico lexico = new Lexico("programaPascal.pas");
        // Token token;
        // do{
        //     token = lexico.nextToken();
        //     System.out.println(token);
        // }while(token.getClasse() != Classe.EOF);

    // System.out.println("----------------------------------------");
    // TabelaSimbolos tabelaSimbolos = lexico.getTabelaSimbolos();
    // System.out.println(tabelaSimbolos);

    Sintatico sintatico = new Sintatico("programaPascal.pas");

    sintatico.analisar();
    }
}