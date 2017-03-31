/*------------ Codigo de Usuario ---------*/
//------> Paquetes,importaciones
package Analizadores.Graphik;
import java_cup.runtime.*;
import java.util.LinkedList;

/*------------ Opciones y Declaraciones ---------*/
%%
%{
    //----> Codigo de usuario en sintaxis java
    public static LinkedList<Analizadores.Errores> Err = new LinkedList<Analizadores.Errores>();
%}

//-------> Directivas
%public 
%class GraphikLexico
%cupsym SimbolosGraphik
%cup
%char
%column
%full
%ignorecase
%unicode
%line
//%debug


//------> Expresiones Regulares
cadena       = "\""([^\"]*)"\""
caracter = "'" ([^\']) "'"
numero      = [0-9]+
letras      = [a-zA-ZñÑ]+
id          ={letras}({numero}|"_"|{letras})*
decimal     ={numero}"."{numero}


%%
/*------------ Reglas Lexicas ---------*/

//-----> Simbolos


{cadena}         {   System.out.println(" cadena"+yytext());
                    return new Symbol(symCSV.texto, yyline, yycolumn, new String(yytext()));
                }
{numero}        {   System.out.println(" numero");
                    return new Symbol(symCSV.numero, yyline, yycolumn, new String(yytext()));
                }
{decimal}       {   System.out.println(" decimal");
                    return new Symbol(symCSV.decimal, yyline, yycolumn, new String(yytext()));
                }

"["             {   System.out.println(" [");
                    return new Symbol(SimbolosGraphik.corAbre, yyline, yycolumn, new String(yytext()));
                }
"]"             {   System.out.println(" ]");
                    return new Symbol(SimbolosGraphik.corCierra, yyline, yycolumn, new String(yytext()));
                }
"{"             {   System.out.println(" {");
                    return new Symbol(SimbolosGraphik.llaveAbre, yyline, yycolumn, new String(yytext()));
                }
"}"             {   System.out.println(" }");
                    return new Symbol(SimbolosGraphik.llaveCierra, yyline, yycolumn, new String(yytext()));
                }
","             {   System.out.println(" ,");
                    return new Symbol(SimbolosGraphik.coma, yyline, yycolumn, new String(yytext()));
                }




//------> Espacios
    (\r\n | \n )+               {}
    (" " | \t)+                 { }

//------> Errores Lexicos
 .                              {System.out.println("ErrorLexico: "+yytext()+"Linea: "+yyline+" Columna: "+yycolumn);
                                Analizadores.Errores err = new Analizadores.Errores("Lexico: ","No es parte del lenguaje ",yytext(),yyline,yycolumn);
                                Err.add(err);
                                }
