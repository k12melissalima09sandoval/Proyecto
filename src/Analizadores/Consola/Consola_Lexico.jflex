/*------------ Codigo de Usuario ---------*/
//------> Paquetes,importaciones
package Analizadores.Consola;
import java_cup.runtime.*;

/*------------ Opciones y Declaraciones ---------*/
%%
%{
   
%}

//-------> Directivas
%public 
%class ConsolaLexico
%cupsym SimbolosConsola
%cup
%char
%column
%full
%ignorecase
%unicode
%line

//------> Expresiones Regulares
digito = [0-9]
entero = {digito}+
decimal={entero}"."{entero}
cadena = "\"" ([^\"]*) "\"" 
caracter = "'" ([^\']) "'"
letras      = [a-zA-ZñÑ]+
id          ={letras}({entero}|"_"|{letras})*

%%
/*------------ Reglas Lexicas ---------*/

//-----> Simbolos


    
    "+"                         {//System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosConsola.mas, yyline, yycolumn, yytext());}
    "-"                         {//System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosConsola.menos, yyline, yycolumn, yytext());}
    "*"                         {//System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosConsola.por, yyline, yycolumn, yytext());}
    "/"                         {//System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosConsola.div, yyline, yycolumn, yytext());}
    "'mod'"                     {//System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosConsola.residuo, yyline, yycolumn, yytext());}
    "'sqrt'"                    {//System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosConsola.sqrt, yyline, yycolumn, yytext());}
    "'pot'"                     {//System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosConsola.pot, yyline, yycolumn, yytext());}

    "="                         {//System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosConsola.igual, yyline, yycolumn, yytext());}
    "$"                         {//System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosConsola.dolar, yyline, yycolumn, yytext());}
    "["                         {//System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosConsola.corAbre, yyline, yycolumn, yytext());}
    "]"                         {//System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosConsola.corCierra, yyline, yycolumn, yytext());}
    "{"                         {//System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosConsola.llaveAbre, yyline, yycolumn, yytext());}
    "}"                         {//System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosConsola.llaveCierra, yyline, yycolumn, yytext());}
    "("                         {//System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosConsola.parenAbre, yyline, yycolumn, yytext());}
    ")"                         {//System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosConsola.parenCierra, yyline, yycolumn, yytext());}
    "%"                         {//System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosConsola.porcentaje, yyline, yycolumn, yytext());}
    "++"                        {//System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosConsola.concatena, yyline, yycolumn, yytext());}
    "!!"                        {//System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosConsola.indice, yyline, yycolumn, yytext());}
    ","                         {//System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosConsola.coma, yyline, yycolumn, yytext());}

    "Succ"                      {//System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosConsola.succ, yyline, yycolumn, yytext());}
    "Decc"                      {//System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosConsola.decc, yyline, yycolumn, yytext());}
    "let"                       {//System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosConsola.let, yyline, yycolumn, yytext());}
    "Min"                       {//System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosConsola.min, yyline, yycolumn, yytext());}
    "Max"                       {//System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosConsola.max, yyline, yycolumn, yytext());}
    "Calcular"                  {//System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosConsola.calcular, yyline, yycolumn, yytext());}

    "sum"                       {//System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosConsola.sum, yyline, yycolumn, yytext());}
    "product"                   {//System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosConsola.product, yyline, yycolumn, yytext());}
    "revers"                    {//System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosConsola.revers, yyline, yycolumn, yytext());}
    "impr"                      {//System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosConsola.impr, yyline, yycolumn, yytext());}
    "par"                       {//System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosConsola.par, yyline, yycolumn, yytext());}
    "asc"                       {//System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosConsola.asc, yyline, yycolumn, yytext());}
    "desc"                      {//System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosConsola.desc, yyline, yycolumn, yytext());}
    "length"                    {//System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosConsola.tam, yyline, yycolumn, yytext());}
 
    {entero}                    {//System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosConsola.entero, yyline, yycolumn, yytext());}
    {decimal}                    {//System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosConsola.entero, yyline, yycolumn, yytext());}
    {cadena}                    {//System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosConsola.cadena, yyline, yycolumn, yytext());}
    {caracter}                  {//System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosConsola.caracter, yyline, yycolumn, yytext());}
    {id}                        {//System.out.println("Reconocio: "+yytext());  
                                   return new Symbol(SimbolosConsola.id, yyline, yycolumn, yytext());}

//------> Espacios
    (\r\n | \n )+               {  }
    (" " | \t)+                 {  }

//------> Errores Lexicos
 .                              {System.out.println("ErrorLexico: "+yytext()+"Linea: "+yyline+" Columna: "+yycolumn);
                              
                                }
