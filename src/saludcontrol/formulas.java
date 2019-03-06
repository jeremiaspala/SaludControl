/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saludcontrol;

import com.sun.org.apache.bcel.internal.generic.CALOAD;

/**
 *
 * @author jpalazzesi
 */
public class formulas {
    int edad=0;
    double altura=0;
    double peso=0;
    int sexo = 0;
    public formulas(){
        
    }
    
    /*La ecuación para hombres es:
Using Imperial Units (inches)

This is the formula (Hodgdon and Beckett, 1984) that was taken from the Navy website, and is seen elsewhere on the internet. Note, all measurements are in inches (measured to the nearest 1/4 inch), including height.

males: % body fat = 86.010 x log10(abdomen - neck) - 70.041 x log10(height) + 36.76
females: % body fat = 163.205 x log10(waist + hip - neck) - 97.684 x log10(height) - 78.387
    */
    //Obtener el porcentaje de grasa corporal
    public String getGC(String sexo, double cintura, double cuello, double altura, double cadera, int edad){
        double grasa=0;
        String resultado=null;
        if(sexo != null && cintura != 0 && cuello !=0 && altura != 0){
            if(sexo.equals("hombre")){
                 // males: % body fat = 86.010 x log10(abdomen - neck) - 70.041 x log10(height) + 36.76
                   grasa = 86.010 * Math.log10(cintura / 2.4 - cuello / 2.4) - 70.041 * Math.log10(altura/2.4) + 36.76;
                   System.out.print("Grasa Hombre: "+grasa);               
            }else{
                grasa = 163.205 * Math.log10(cintura / 2.4  + cadera / 2.4 - cuello / 2.4) - 97.684 * Math.log10(altura/ 2.4) - 78.387;
                System.out.print("Grasa Mujer: "+grasa);
            }
        }
        resultado = " "+grasa;
        return resultado.substring(0,6);
    }
    //obtener el Indice Masa Corporal
    public String getIMC(int edadi, double alturai, double pesoi){
        double i = 0;
        String resultado = null;
        if(alturai !=0 && pesoi !=0){
            i = pesoi / (alturai * alturai);
            resultado = ""+i;
        }
        return resultado.substring(4,6)+"."+resultado.substring(7, 8);
    }
    //Obtener el Masa Magra
    public String getMM(String sexo, double cintura, double cuello, double altura, double cadera, int edad, double pesoi){
        double grasa=0;
        String resultado=null;
        if(sexo != null && cintura != 0 && cuello !=0 && altura != 0){
            if(sexo.equals("hombre")){
                 // males: % body fat = 86.010 x log10(abdomen - neck) - 70.041 x log10(height) + 36.76
                   grasa = 86.010 * Math.log10(cintura / 2.4 - cuello / 2.4) - 70.041 * Math.log10(altura/2.4) + 36.76;
                   System.out.print("Grasa Hombre: "+grasa);               
            }else{
                grasa = 163.205 * Math.log10(cintura + cadera - cuello) - 97.684 * Math.log10(altura) - 78.387;
                System.out.print("Grasa Mujer: "+grasa);
            }
        }
        double a = ((100 - grasa) * pesoi) / 100;
        resultado = " "+a;
        return resultado.substring(0,6);
    }
    //Obtener la Tasa Metabólica Basal
    public String getTMB(double pesoi, double alturai, double edadi, String sexoi, double actividad){
        String resultado = null;
        double i=0;
        int sexx = 0;
        if(sexoi.equals("hombre")){
            sexx = 5;
        }else{
            sexx = -161;
        }
        if(pesoi != 0 && alturai !=0 && edadi !=0 && sexoi != null){
            i = (10* pesoi) + (6.25 * alturai) - (5 * edadi) + 5 + sexx;
            i = i * actividad;
            System.out.println("Indice + Actividad "+i+" "+actividad);
        }else{
            System.out.println("Faltan Parametros para TMB");
        }
        resultado = " "+i;
        return resultado;
    }
    public String getIca(double altura, double cintura){
        String resultado = null;
        double a = (cintura / altura);
        System.out.println("Resultado ICA: "+a);
        System.out.println("Valores: "+altura +" "+cintura);
        resultado = " "+a;
        return resultado.substring(0, 8);
    }
    public String getInfoCintura(){
        String actividad ="<html><body><ul>\n" +
"<li>Altura - se mide sin zapatos</li>\n" +
"<li>Peso - se mide en la mañana sin ropa, después de ir al baño, y antes de comer o beber.</li>\n" +
"<li>Cintura (Hombres) - se mide horizontalmente, al nivel del ombligo</li>\n" +
"          (Mujeres) - se mide horizontalmente, al nivel de la anchura abdominal mínima</li>\n" +
"<li>Cuello - se mide debajo de la laringe con la cinta inclinada ligeramente hacia el pecho.</li>\n" +
"<li>Cadera (Mujeres solamente) - la mayor circunferencia horizontal alrededor de las caderas</li>" +
                
"	</ul></body></html>";
        return actividad;
    }
    public String getInfoActividad(){
        String actividad = "<html><body><table>\n" +
"	<tbody>\n" +
"		<tr>\n" +
"			<td>Poco o ning&uacute;n ejercicio</td>\n" +
"		</tr>\n" +
"		<tr>\n" +
"			<td>Ejercicio ligero (1-3 d&iacute;as a la semana)</td>\n" +
"		</tr>\n" +
"		<tr>\n" +
"			<td>Ejercicio moderado (3-5 d&iacute;as a la semana)</td>\n" +
"		</tr>\n" +
"		<tr>\n" +
"			<td>Ejercicio fuerte (6-7 d&iacute;as a la semana)</td>\n" +
"		</tr>\n" +
"		<tr>\n" +
"			<td>Ejercicio muy fuerte (dos veces al d&iacute;a, entrenamientos muy duros)</td>\n" +
"		</tr>\n" +
"	</tbody>\n" +
"</table></body></html>";
        return actividad;
    }
    public String getInfoIMC(){
        String actividad = "<html><body><table style=\"width: 100%;font-size:8;\">\n" +
"	<tbody>\n" +
"		<tr>\n" +
"			<td style=\"width: 50.0000%;\">Peso Insuficiente\n" +
"				\n" +
"			</td>\n" +
"			<td style=\"width: 50.0000%;\">&lt; 18.5\n" +
"				\n" +
"			</td>\n" +
"		</tr>\n" +
"		<tr>\n" +
"			<td style=\"width: 50.0000%;\">Peso Normal\n" +
"				\n" +
"			</td>\n" +
"			<td style=\"width: 50.0000%;\">18.5 - 24.9\n" +
"				\n" +
"			</td>\n" +
"		</tr>\n" +
"		<tr>\n" +
"			<td style=\"width: 50.0000%;\">Sobrepeso Grado I\n" +
"				\n" +
"			</td>\n" +
"			<td style=\"width: 50.0000%;\">25 - 26.9\n" +
"				\n" +
"			</td>\n" +
"		</tr>\n" +
"		<tr>\n" +
"			<td style=\"width: 50.0000%;\">Sobrepeso Grado II\n" +
"				\n" +
"			</td>\n" +
"			<td style=\"width: 50.0000%;\">27 - 29.9\n" +
"				\n" +
"			</td>\n" +
"		</tr>\n" +
"		<tr>\n" +
"			<td style=\"width: 50.0000%;\">Obesidad I\n" +
"				\n" +
"			</td>\n" +
"			<td style=\"width: 50.0000%;\">30 - 34.9\n" +
"				\n" +
"			</td>\n" +
"		</tr>\n" +
"		<tr>\n" +
"			<td style=\"width: 50.0000%;\">Obesidad II\n" +
"				\n" +
"			</td>\n" +
"			<td style=\"width: 50.0000%;\">35 - 39.9\n" +
"				\n" +
"			</td>\n" +
"		</tr>\n" +
"		<tr>\n" +
"			<td style=\"width: 50.0000%;\">Obesidad III(M&oacute;rbida)\n" +
"				\n" +
"			</td>\n" +
"			<td style=\"width: 50.0000%;\">40 - 49.9\n" +
"				\n" +
"			</td>\n" +
"		</tr>\n" +
"		<tr>\n" +
"			<td style=\"width: 50.0000%;\">Obesidad IV (Extrema)\n" +
"				\n" +
"			</td>\n" +
"			<td style=\"width: 50.0000%;\">&gt; 50\n" +
"				\n" +
"			</td>\n" +
"		</tr>\n" +
"	</tbody>\n" +
"</table>" +
"</body></html>";
        return actividad;
    }
    public String getInfoCC(){
        String actividad = "<html><body>ICA = circunferencia-cintura (cm) / altura (cm)\n" +
"Relación sana cintura/altura<br>\n" +
"Hombre: inferior a 50% (robusto 53.6% a 58.3%)<br>\n" +
"Mujer: inferior a 42% (robusta 49.2% a 54.1%)<br>Un ICA por encima de 0,5 es crítico<br> y significa un riesgo importante.</body></html>";
        return actividad;
    }
    public String getInfoTMBData(){
        String actividad ="<html><body>\n" +
"La tasa metabólica basal (TMB) es la cantidad de las <br>calorías diarias necesarias para mantener las funciones <br>básicas de su cuerpo.<br>\n" +
"En 1919, James Arthur Harris y Francis Gano Benedict <br>presentaran un método para calcular la TMB, <br>conocido como el método Harris-Benedict.</body></html>";
        return actividad;
    }
    public String getInfoGC(){
        String actividad ="<html><body><p>El porcentaje de grasa corporal se calcula utilizando las f&oacute;rmulas desarrolladas por Hodgdon y Beckett.</p>\n" +
"\n" +
"<table>\n" +
"	<tbody>\n" +
"		<tr>\n" +
"			<td>&nbsp;</td>\n" +
"			<td><strong>Mujeres</strong></td>\n" +
"			<td><strong>Hombres</strong></td>\n" +
"		</tr>\n" +
"		<tr>\n" +
"			<td>Grasa esencial &nbsp;&nbsp;</td>\n" +
"			<td>10-12%</td>\n" +
"			<td>2-4%</td>\n" +
"		</tr>\n" +
"		<tr>\n" +
"			<td>Atleta</td>\n" +
"			<td>14-20%</td>\n" +
"			<td>6-13%</td>\n" +
"		</tr>\n" +
"		<tr>\n" +
"			<td>Fitness</td>\n" +
"			<td>21-24%</td>\n" +
"			<td>14-17%</td>\n" +
"		</tr>\n" +
"		<tr>\n" +
"			<td>Aceptable</td>\n" +
"			<td>25-31%</td>\n" +
"			<td>18-25%</td>\n" +
"		</tr>\n" +
"		<tr>\n" +
"			<td>Obesidad</td>\n" +
"			<td>32% o m&aacute;s</td>\n" +
"			<td>26% o m&aacute;s</td>\n" +
"		</tr>\n" +
"	</tbody>\n" +
"</table></body></html>";
        return actividad;
    }
    public String getInfoMM(){
        String actividad ="<html><body>Masa corporal magra - Se deriva restando el <br>peso de la grasa corporal del peso total.</body></html>";
        return actividad;
    }
}
