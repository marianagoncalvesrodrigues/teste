package AnalisadorLexico;

public class Token{
  public TipoToken nome;
  public String lexema;
  public int linha;

  public Token(TipoToken nome, String lexema, int linha){
    this.nome = nome;
    this.lexema = lexema;
    this.linha = linha;
  }

  public String toString(){
    return "<"+nome+","+lexema+","+linha+">";
  }
}