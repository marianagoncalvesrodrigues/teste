package AnalisadorLexico;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class LeitorArquivo{

  public LeitorArquivo(){
  }

  public ArrayList<String> lerLinhas(String nome){

    ArrayList<String> linhas = new ArrayList<String>();
    
    try{
        Scanner scan = new Scanner(new File(nome));

        while(scan.hasNextLine()){
             linhas.add(scan.nextLine());
        }
      }catch(Exception e){
        System.out.println(e);
      }
        return linhas;
  }
  
}