package AnalisadorLexico;

import java.util.ArrayList;



public class GyhLex {
	ArrayList<Token> arraytoken = new ArrayList<Token>();
	ArrayList<String> arraylinhas = new ArrayList<String>();
	public int cont_linhas = 0;
	public LeitorArquivo ldat;
	
	public GyhLex() {
		//constructor
	}
	
	public void ident_tipotoken(String arquivo) {
		LeitorArquivo novoarq = new LeitorArquivo();
		this.arraylinhas = novoarq.lerLinhas(arquivo); //This array is getting the entire file
		String lexema = "";
		String flexoes = "";
		
		for(int j = 0; j < this.arraylinhas.size(); j++) {
			System.out.println(this.arraytoken);
			cont_linhas++;
			flexoes = this.arraylinhas.get(j);//take first line of file
			for(int i = 0; i < flexoes.length(); i++) {//Start for
				if(i != flexoes.length() - 1 && flexoes.charAt(i+1) == '=') {//if
					if(flexoes.charAt(i) == '=') {
						this.arraytoken.add(new Token(TipoToken.OpRelIgual, "==", this.cont_linhas));
					}else if(flexoes.charAt(i) == ':') {
						this.arraytoken.add(new Token(TipoToken.Atrib, ":=", this.cont_linhas));
					}else if(flexoes.charAt(i) == '!') {
						this.arraytoken.add(new Token(TipoToken.OpRelDif, "!=", this.cont_linhas));
					}else if(flexoes.charAt(i) == '>') {
						this.arraytoken.add(new Token(TipoToken.OpRelMaiorIgual, ">=", this.cont_linhas));
					}else if(flexoes.charAt(i) == '<') {
						this.arraytoken.add(new Token(TipoToken.OpRelMenorIgual, "<=", this.cont_linhas));
					}
				}//end if
				else if(flexoes.charAt(i) == '<') {
					this.arraytoken.add(new Token(TipoToken.OpRelMenor, "<", this.cont_linhas));
				}else if(flexoes.charAt(i) == '>') {
					this.arraytoken.add(new Token(TipoToken.OpRelMaior, ">", this.cont_linhas));
				}else if(flexoes.charAt(i) == '*') {
					this.arraytoken.add(new Token(TipoToken.OpAritMult, "*", this.cont_linhas));
				}else if(flexoes.charAt(i) == '/') {
					this.arraytoken.add(new Token(TipoToken.OpAritDiv, "/", this.cont_linhas));
				}else if(flexoes.charAt(i) == '+') {
					this.arraytoken.add(new Token(TipoToken.OpAritSoma, "+", this.cont_linhas));
				}else if(flexoes.charAt(i) == '-') {
					this.arraytoken.add(new Token(TipoToken.OpAritSub, "-", this.cont_linhas));
				}else if(flexoes.charAt(i) == ':') {
					this.arraytoken.add(new Token(TipoToken.Delim, ":", this.cont_linhas));
				}else if(flexoes.charAt(i) == '(') {
					this.arraytoken.add(new Token(TipoToken.AbrePar, "(", this.cont_linhas));
				}else if(flexoes.charAt(i) == ')') {
					this.arraytoken.add(new Token(TipoToken.FechaPar, ")", this.cont_linhas));
				}else if(flexoes.charAt(i) == '#') { //fiz esse agora para o coment
					continue;
				}else if(flexoes.charAt(i) >= 48 && flexoes.charAt(i) <= 57) {//numbers
					int contadorPontos = 0;
					while(i < flexoes.length()) {
						if(flexoes.charAt(i) >= 48 && flexoes.charAt(i) <= 57) {
							lexema = lexema.concat(Character.toString(flexoes.charAt(i)));
							i++;
						}else if(flexoes.charAt(i) == '.') {
							if(contadorPontos < 2) {
								lexema = lexema.concat(Character.toString(flexoes.charAt(i)));
								i++;
								contadorPontos++;
							}else {
								System.out.println("erro mane");
								System.exit(1);
							}
						}else break;
						i--;
						
					}//while()
					if(lexema.contains(".")) {//real numbers
						this.arraytoken.add(new Token(TipoToken.NumReal, lexema, this.cont_linhas));
						lexema = "";
					}else {//integers numbers
						this.arraytoken.add(new Token(TipoToken.NumInt, lexema, this.cont_linhas));
						lexema = "";
					}
				}//end if numbers
				else if(flexoes.charAt(i) == '"') {//chain
					while(i < flexoes.length()) {
						if(flexoes.charAt(i + 1) != '"') {
							lexema = lexema.concat(Character.toString(flexoes.charAt(i+1)));
							i++;
						}
					}
					this.arraytoken.add(new Token(TipoToken.Cadeia, lexema, this.cont_linhas));
					lexema = "";
					//end chain
				}else if(flexoes.charAt(i) >= 97 && flexoes.charAt(i) >= 122) {//variables 
					while(i < flexoes.length()) {
						if(flexoes.charAt(i) >= 97 && flexoes.charAt(i) >= 122) {
							lexema = lexema.concat(Character.toString(flexoes.charAt(i)));
						}else if(flexoes.charAt(i) >= 65 && flexoes.charAt(i) <= 90) {
							lexema = lexema.concat(Character.toString(flexoes.charAt(i)));
						}else if(flexoes.charAt(i) >= 48 && flexoes.charAt(i) <= 57) {
							lexema = lexema.concat(Character.toString(flexoes.charAt(i)));
						}else break;
						i++;
					}//end while
					i--;
					this.arraytoken.add(new Token(TipoToken.Var, lexema, this.cont_linhas));
					lexema = "";
				}//end variables
				else if(flexoes.charAt(i) >= 65 && flexoes.charAt(i) >= 90) {//reserved words
					
					while(i < flexoes.length()) {
						
						if(flexoes.charAt(i) >= 65 && flexoes.charAt(i) >= 90) {
							lexema = lexema.concat(Character.toString(flexoes.charAt(i)));
							i++;
							System.out.println(lexema);
						}else break;
					}//while
					i--;
					if(lexema.equals("E")) {
						this.arraytoken.add(new Token(TipoToken.OpBoolE, lexema, this.cont_linhas));
					}else if(lexema.equals("OU")) {
						this.arraytoken.add(new Token(TipoToken.OpBoolOu, lexema, this.cont_linhas));
					}else if(lexema.equals("DEC")) {
						this.arraytoken.add(new Token(TipoToken.PCDec, lexema, this.cont_linhas));
					}else if(lexema.equals("PROG")) {
						this.arraytoken.add(new Token(TipoToken.PCProg, lexema, this.cont_linhas));
					}else if(lexema.equals("INT")) {
						this.arraytoken.add(new Token(TipoToken.PCInt, lexema, this.cont_linhas));
					}else if(lexema.equals("LER")) {
						this.arraytoken.add(new Token(TipoToken.PCLer, lexema, this.cont_linhas));
					}else if(lexema.equals("REAL")) {
						this.arraytoken.add(new Token(TipoToken.PCReal, lexema, this.cont_linhas));
					}else if(lexema.equals("IMPRIMIR")) {
						this.arraytoken.add(new Token(TipoToken.PCImprimir, lexema, this.cont_linhas));
					}else if(lexema.equals("SE")){
						this.arraytoken.add(new Token(TipoToken.PCSe, lexema, this.cont_linhas));
					}else if(lexema.equals("ENTAO")) {
						this.arraytoken.add(new Token(TipoToken.PCEntao, lexema, this.cont_linhas));
					}else if(lexema.equals("ENQTO")) {
						this.arraytoken.add(new Token(TipoToken.PCEnqto, lexema, this.cont_linhas));
					}else if(lexema.equals("INI")) {
						this.arraytoken.add(new Token(TipoToken.PCIni, lexema, this.cont_linhas));
					}else if(lexema.equals("FIM")) {
						this.arraytoken.add(new Token(TipoToken.PCFim, lexema, this.cont_linhas));
					}
					lexema = "";
				}//end reserved words
				
			}//end for
		}//end forzão
		System.out.println("oi Andsu");
	}		
}


