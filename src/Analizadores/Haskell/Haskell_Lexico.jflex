/*------------ Codigo de Usuario ---------*/
//------> Paquetes,importaciones
package Analizadores.Haskell;
import java_cup.runtime.Symbol;
import java.util.LinkedList;

import Analizadores.Errores;

/*------------ Opciones y Declaraciones ---------*/
%%
%{
    //----> Codigo de usuario en sintaxis java
    public static LinkedList<Analizadores.Errores> Err = new LinkedList<Analizadores.Errores>();
%}

//-------> Directivas
%public 
%class HaskellLexico
%cupsym SimbolosHaskell
%cup
%char
%column
%full
%ignorecase
%unicode
%line
//%debug

//------> Expresiones Regulares
digito = [0-9]
entero = {digito}+
decimal={entero}"."{entero}
cadena = "\"" ([^\"]*)"\""
caracter = "'" ([^\']) "'"
letras      = [a-zA-ZñÑ]+
id          ={letras}({entero}|"_"|{letras})*

%%
/*------------ Reglas Lexicas ---------*/

//-----> Simbolos


    
    "+"                         {//System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.mas, yyline, yycolumn, yytext());}
    "-"                         {//System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.menos, yyline, yycolumn, yytext());}
    "*"                         {//System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.por, yyline, yycolumn, yytext());}
    "/"                         {//System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.div, yyline, yycolumn, yytext());}
    "'mod'"                     {//System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.residuo, yyline, yycolumn, yytext());}
    "'sqrt'"                    {//System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.sqrt, yyline, yycolumn, yytext());}
    "'pot'"                     {//System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.pot, yyline, yycolumn, yytext());}

    "||"                        {//System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.or, yyline, yycolumn, yytext());}
    "&&"                        {//System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.and, yyline, yycolumn, yytext());}
    
    ">"                         {//System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.mayorq, yyline, yycolumn, yytext());}
    "<"                         {//System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.menorq, yyline, yycolumn, yytext());}
    ">="                        {//System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.mayorigualq, yyline, yycolumn, yytext());}
    "<="                        {//System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.menorigualq, yyline, yycolumn, yytext());}
    "=="                        {//System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.igualigual, yyline, yycolumn, yytext());}
    "!="                        {//System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.diferente, yyline, yycolumn, yytext());}

    "="                         {//System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.igual, yyline, yycolumn, yytext());}
    "$"                         {//System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.dolar, yyline, yycolumn, yytext());}
    "["                         {//System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.corAbre, yyline, yycolumn, yytext());}
    "]"                         {//System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.corCierra, yyline, yycolumn, yytext());}
    "{"                         {//System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.llaveAbre, yyline, yycolumn, yytext());}
    "}"                         {//System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.llaveCierra, yyline, yycolumn, yytext());}
    "("                         {//System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.parenAbre, yyline, yycolumn, yytext());}
    ")"                         {//System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.parenCierra, yyline, yycolumn, yytext());}
    "++"                        {//System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.concatena, yyline, yycolumn, yytext());}
    "!!"                        {//System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.indice, yyline, yycolumn, yytext());}
    ":"                         {//System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.dospuntos, yyline, yycolumn, yytext());}
    ","                         {//System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.coma, yyline, yycolumn, yytext());}
    ";"                         {//System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.puntoycoma, yyline, yycolumn, yytext());}

    "Succ"                      {//System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.succ, yyline, yycolumn, yytext());}
    "Decc"                      {//System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.decc, yyline, yycolumn, yytext());}
    "let"                       {//System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.let, yyline, yycolumn, yytext());}
    "Min"                       {//System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.min, yyline, yycolumn, yytext());}
    "Max"                       {//System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.max, yyline, yycolumn, yytext());}
    "Calcular"                  {//System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.calcular, yyline, yycolumn, yytext());}

    "sum"                       {//System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.sum, yyline, yycolumn, yytext());}
    "product"                   {//System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.product, yyline, yycolumn, yytext());}
    "revers"                    {//System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.revers, yyline, yycolumn, yytext());}
    "impr"                      {//System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.impr, yyline, yycolumn, yytext());}
    "par"                       {//System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.par, yyline, yycolumn, yytext());}
    "asc"                       {//System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.asc, yyline, yycolumn, yytext());}
    "desc"                      {//System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.desc, yyline, yycolumn, yytext());}
    "length"                    {//System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.tam, yyline, yycolumn, yytext());}

    "if"                        {//System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.si, yyline, yycolumn, yytext());}
    "then"                      {//System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.entonces, yyline, yycolumn, yytext());}
    "else"                      {//System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.sino, yyline, yycolumn, yytext());}
    "Case"                      {//System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.caso, yyline, yycolumn, yytext());}
    "end"                       {//System.out.println("Reconocio: end");  
                                   return new Symbol(SimbolosHaskell.fin, yyline, yycolumn, yytext());}

    {entero}                    {//System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.entero, yyline, yycolumn, yytext());}
    {decimal}                    {//System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.entero, yyline, yycolumn, yytext());}
    {cadena}                    {//System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.cadena, yyline, yycolumn, yytext());}
    {caracter}                  {//System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.caracter, yyline, yycolumn, yytext());}
    {id}                        {//System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosHaskell.id, yyline, yycolumn, yytext());}

//------> Espacios

    (\r\n | \n )+               {  }
    (" " | \t)+                 {  }

//------> Errores Lexicos
 .                              {System.out.println("ErrorLexico: "+yytext()+"Linea: "+yyline+" Columna: "+yycolumn);
                                Analizadores.Errores err = new Analizadores.Errores("Lexico: ","No es parte del lenguaje ",yytext(),yyline,yycolumn);
                                Err.add(err);

                                Errores.ErrorSemantico("Lexico: No es parte del Lenguaje Haskell -" +yytext()+ "- ", 0, 0);
                                }
