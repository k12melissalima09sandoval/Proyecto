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
texto       = "\""([^\"]*)"\""
caracter = "'" ([^\']) "'"
numero      = [0-9]+
letras      = [a-zA-ZñÑ]+
id          ={letras}({numero}|"_"|{letras})*
decimal     ={numero}"."{numero}
nombreArchivo = {id}".gk"
comentarios = "#/" ([^\#]*) "/#" 
comentario  = "#" ([^\#]*)


%%
/*------------ Reglas Lexicas ---------*/

//-----> Simbolos


    

"entero"        {   System.out.println("Entro entero");
                    return new Symbol(SimbolosGraphik.Tentero, yyline, yycolumn, new String(yytext()));
                }
"decimal"       {   System.out.println("Entro decimal");
                    return new Symbol(SimbolosGraphik.Tdecimal, yyline, yycolumn, new String(yytext()));
                }
"caracter"      {   System.out.println("Entro caracter");
                    return new Symbol(SimbolosGraphik.Tcaracter, yyline, yycolumn, new String(yytext()));
                }
"cadena"        {   System.out.println("Entro cadena");
                    return new Symbol(SimbolosGraphik.Tcadena, yyline, yycolumn, new String(yytext()));
                }
"bool"          {   System.out.println("Entro bool");
                    return new Symbol(SimbolosGraphik.Tbool, yyline, yycolumn, new String(yytext()));
                }
"ALS"           {   System.out.println("Entro ALS");
                    return new Symbol(SimbolosGraphik.Tals, yyline, yycolumn, new String(yytext()));
                }
"vacio"         {   System.out.println("Entro vacio");
                    return new Symbol(SimbolosGraphik.Tvacio, yyline, yycolumn, new String(yytext()));
                }

"publico"       {   System.out.println("Entro publico");
                    return new Symbol(SimbolosGraphik.publico, yyline, yycolumn, new String(yytext()));
                }
"protegido"     {   System.out.println("Entro protegido");
                    return new Symbol(SimbolosGraphik.protegido, yyline, yycolumn, new String(yytext()));
                }
"privado"       {   System.out.println("Entro privado");
                    return new Symbol(SimbolosGraphik.privado, yyline, yycolumn, new String(yytext()));
                }

"importar"      {   System.out.println("Entro importar");
                    return new Symbol(SimbolosGraphik.importar, yyline, yycolumn, new String(yytext()));
                }
"hereda"        {   System.out.println("Entro hereda");
                    return new Symbol(SimbolosGraphik.hereda, yyline, yycolumn, new String(yytext()));
                }
"var"           {   System.out.println("Entro var");
                    return new Symbol(SimbolosGraphik.var, yyline, yycolumn, new String(yytext()));
                }
"nuevo"         {   System.out.println("Entro nuevo");
                    return new Symbol(SimbolosGraphik.nuevo, yyline, yycolumn, new String(yytext()));
                }
"retornar"      {   System.out.println("Entro retornar");
                    return new Symbol(SimbolosGraphik.retornar, yyline, yycolumn, new String(yytext()));
                }
"llamar"        {   System.out.println("Entro llamar");
                    return new Symbol(SimbolosGraphik.llamar, yyline, yycolumn, new String(yytext()));
                }
"inicio"        {   System.out.println("Entro inicio");
                    return new Symbol(SimbolosGraphik.inicio, yyline, yycolumn, new String(yytext()));
                }
"incluir_HK"    {   System.out.println("Entro incluir_HK");
                    return new Symbol(SimbolosGraphik.incluirHK, yyline, yycolumn, new String(yytext()));
                }
"llamarHK"      {   System.out.println("Entro llamarHK");
                    return new Symbol(SimbolosGraphik.llamarHK, yyline, yycolumn, new String(yytext()));
                }
"Si"            {   System.out.println("Entro Si");
                    return new Symbol(SimbolosGraphik.si, yyline, yycolumn, new String(yytext()));
                }
"Sino"          {   System.out.println("Entro Sino");
                    return new Symbol(SimbolosGraphik.sino, yyline, yycolumn, new String(yytext()));
                }
"Seleccion"     {   System.out.println("Entro seleccion");
                    return new Symbol(SimbolosGraphik.seleccion, yyline, yycolumn, new String(yytext()));
                }
"Caso"          {   System.out.println("Entro caso");
                    return new Symbol(SimbolosGraphik.caso, yyline, yycolumn, new String(yytext()));
                }
"Defecto"       {   System.out.println("Entro Defecto");
                    return new Symbol(SimbolosGraphik.defecto, yyline, yycolumn, new String(yytext()));
                }
"Para"          {   System.out.println("Entro Para");
                    return new Symbol(SimbolosGraphik.para, yyline, yycolumn, new String(yytext()));
                }
"Mientras"      {   System.out.println("Entro Mientras");
                    return new Symbol(SimbolosGraphik.mientras, yyline, yycolumn, new String(yytext()));
                }
"Hacer"         {   System.out.println("Entro Hacer");
                    return new Symbol(SimbolosGraphik.hacer, yyline, yycolumn, new String(yytext()));
                }
"Continuar"     {   System.out.println("Entro Continuar");
                    return new Symbol(SimbolosGraphik.continuar, yyline, yycolumn, new String(yytext()));
                }
"Datos"         {   System.out.println("Entro Datos");
                    return new Symbol(SimbolosGraphik.datos, yyline, yycolumn, new String(yytext()));
                }
"Terminar"      {   System.out.println("Entro Terminar");
                    return new Symbol(SimbolosGraphik.terminar, yyline, yycolumn, new String(yytext()));
                }
"graphikar_funcion" {   System.out.println("Entro graphikar_funcion");
                        return new Symbol(SimbolosGraphik.graphikar, yyline, yycolumn, new String(yytext()));
                    }
"columna"       {   System.out.println("Entro Columna");
                    return new Symbol(SimbolosGraphik.columna, yyline, yycolumn, new String(yytext()));
                }
"procesar"      {   System.out.println("Entro Procesar");
                    return new Symbol(SimbolosGraphik.procesar, yyline, yycolumn, new String(yytext()));
                }
"donde"         {   System.out.println("Entro Donde");
                    return new Symbol(SimbolosGraphik.donde, yyline, yycolumn, new String(yytext()));
                }
"dondecada"     {   System.out.println("Entro DondeCada");
                    return new Symbol(SimbolosGraphik.dondecada, yyline, yycolumn, new String(yytext()));
                }
"DondeTodo"     {   System.out.println("Entro DondeTodo");
                    return new Symbol(SimbolosGraphik.dondetodo, yyline, yycolumn, new String(yytext()));
                }
"imprimir"      {   System.out.println("Entro imprimir");
                    return new Symbol(SimbolosGraphik.imprimir, yyline, yycolumn, new String(yytext()));
                }

//exp
"verdadero"     {   System.out.println("Entro verdadero");
                    return new Symbol(SimbolosGraphik.verdadero, yyline, yycolumn, new String(yytext()));
                }
"falso"         {   System.out.println("Entro falso");
                    return new Symbol(SimbolosGraphik.falso, yyline, yycolumn, new String(yytext()));
                }
{nombreArchivo} {   System.out.println("Entro nombreArchivo");
                    return new Symbol(SimbolosGraphik.nombreArchivo, yyline, yycolumn, new String(yytext()));
                }
{texto}         {   System.out.println("Entro texto");
                    return new Symbol(SimbolosGraphik.cad, yyline, yycolumn, new String(yytext()));
                }
{numero}        {   System.out.println("Entro numero");
                    return new Symbol(SimbolosGraphik.num, yyline, yycolumn, new String(yytext()));
                }
{id}            {   System.out.println("Entro "+yytext());
                    return new Symbol(SimbolosGraphik.id, yyline, yycolumn, new String(yytext()));
                }
{decimal}       {   System.out.println("Entro decimal");
                    return new Symbol(SimbolosGraphik.dec, yyline, yycolumn, new String(yytext()));
                }
{caracter}      {
                    System.out.println("Entro Caracter");
                    return new Symbol(SimbolosGraphik.carac, yyline, yycolumn, new String(yytext()));
                }
{comentarios}   {}
{comentario}    {}


//OPERADORES VALIDOS
"("             {   System.out.println("Entro (");
                    return new Symbol(SimbolosGraphik.parenAbre, yyline, yycolumn, new String(yytext()));
                }
")"             {   System.out.println("Entro )");
                    return new Symbol(SimbolosGraphik.parenCierra, yyline, yycolumn, new String(yytext()));
                }
"["             {   System.out.println("Entro [");
                    return new Symbol(SimbolosGraphik.corAbre, yyline, yycolumn, new String(yytext()));
                }
"]"             {   System.out.println("Entro ]");
                    return new Symbol(SimbolosGraphik.corCierra, yyline, yycolumn, new String(yytext()));
                }
"{"             {   System.out.println("Entro {");
                    return new Symbol(SimbolosGraphik.llaveAbre, yyline, yycolumn, new String(yytext()));
                }
"}"             {   System.out.println("Entro }");
                    return new Symbol(SimbolosGraphik.llaveCierra, yyline, yycolumn, new String(yytext()));
                }
"+"             {   System.out.println("Entro +");
                    return new Symbol(SimbolosGraphik.mas, yyline, yycolumn, new String(yytext()));
                }
"-"             {   System.out.println("Entro -");
                    return new Symbol(SimbolosGraphik.menos, yyline, yycolumn, new String(yytext()));
                }
"/"             {   System.out.println("Entro /");
                    return new Symbol(SimbolosGraphik.div, yyline, yycolumn, new String(yytext()));
                }
"^"             {   System.out.println("Entro *");
                    return new Symbol(SimbolosGraphik.potencia, yyline, yycolumn, new String(yytext()));
                }
"*"             {   System.out.println("Entro *");
                    return new Symbol(SimbolosGraphik.por, yyline, yycolumn, new String(yytext()));
                }
","             {   System.out.println("Entro ,");
                    return new Symbol(SimbolosGraphik.coma, yyline, yycolumn, new String(yytext()));
                }
"?"             {   System.out.println("Entro ?");
                    return new Symbol(SimbolosGraphik.fin, yyline, yycolumn, new String(yytext()));
                }
"||"            {   System.out.println("Entro ||");
                    return new Symbol(SimbolosGraphik.or, yyline, yycolumn, new String(yytext()));
                }
"&&"            {   System.out.println("Entro &&");
                    return new Symbol(SimbolosGraphik.and, yyline, yycolumn, new String(yytext()));
                }
"&|"            {   System.out.println("Entro &|");
                    return new Symbol(SimbolosGraphik.xor, yyline, yycolumn, new String(yytext()));
                }
"!"             {   System.out.println("Entro !");
                    return new Symbol(SimbolosGraphik.not, yyline, yycolumn, new String(yytext()));
                }
">"             {   System.out.println("Entro >");
                    return new Symbol(SimbolosGraphik.mayorq, yyline, yycolumn, new String(yytext()));
                }
"<"             {   System.out.println("Entro <");
                    return new Symbol(SimbolosGraphik.menorq, yyline, yycolumn, new String(yytext()));
                }
">="            {   System.out.println("Entro >=");
                    return new Symbol(SimbolosGraphik.mayorigualq, yyline, yycolumn, new String(yytext()));
                }
"<="            {   System.out.println("Entro <=");
                    return new Symbol(SimbolosGraphik.menorigualq, yyline, yycolumn, new String(yytext()));
                }
"=="            {   System.out.println("Entro ==");
                    return new Symbol(SimbolosGraphik.igualigual, yyline, yycolumn, new String(yytext()));
                }
"!="            {   System.out.println("Entro !=");
                    return new Symbol(SimbolosGraphik.diferente, yyline, yycolumn, new String(yytext()));
                }
"++"            {   System.out.println("Entro ++");
                    return new Symbol(SimbolosGraphik.incremento, yyline, yycolumn, new String(yytext()));
                }
"--"            {   System.out.println("Entro --");
                    return new Symbol(SimbolosGraphik.decremento, yyline, yycolumn, new String(yytext()));
                }
"="             {   System.out.println("Entro =");
                    return new Symbol(SimbolosGraphik.igual, yyline, yycolumn, new String(yytext()));
                }
":"             {   System.out.println("Entro :");
                    return new Symbol(SimbolosGraphik.dospuntos, yyline, yycolumn, new String(yytext()));
                }
"."             {   System.out.println("Entro .");
                    return new Symbol(SimbolosGraphik.punto, yyline, yycolumn, new String(yytext()));
                }




//------> Espacios
    (\r\n | \n )+               {}
    (" " | \t)+                 { }

//------> Errores Lexicos
 .                              {System.out.println("ErrorLexico: "+yytext()+"Linea: "+yyline+" Columna: "+yycolumn);
                                Analizadores.Errores err = new Analizadores.Errores("Lexico: ","No es parte del lenguaje ",yytext(),yyline,yycolumn);
                                Err.add(err);
                                }
