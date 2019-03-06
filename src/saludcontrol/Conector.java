/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saludcontrol;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jpalazzesi
 */
public class Conector {
    String url = System.getProperty("user.dir")+"\\salud.db";
    Connection connect;
   
    public void connect(){
        try {
             Class.forName("org.sqlite.JDBC");
        }catch (Exception e){
            System.out.println("Error llamando al Jar SQLite: "+e);
        }
    try {
        connect = DriverManager.getConnection("jdbc:sqlite:"+url);
        if (connect!=null) {
            System.out.println("Conectado");
        }
    }catch (SQLException ex) {
        System.err.println("No se ha podido conectar a la base de datos\n"+ex.getMessage());
    }
   }
    public void close(){
           try {
               connect.close();
           } catch (SQLException ex) {
               Logger.getLogger(Conector.class.getName()).log(Level.SEVERE, null, ex);
               System.out.println("Error al Cerrar: "+ex);
           }
    }
    
    public String getFecha(){
        Calendar fecha = new GregorianCalendar();
        //Obtenemos el valor del año, mes, día,
        //hora, minuto y segundo del sistema
        //usando el método get y el parámetro correspondiente
        int año = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        int hora = fecha.get(Calendar.HOUR_OF_DAY);
        int minuto = fecha.get(Calendar.MINUTE);
        int segundo = fecha.get(Calendar.SECOND);
        String jo;
        jo = dia + "/" + (mes+1) + "/" + año;
        //String hora = hora+":"+ minuto+":"+ segundo;
        return jo;
    }
    
    //Métodos Guardar
    public void setDiario(String edad, String peso, String altura, String cintura, String cuello, String cadera, String imc, String tmb, String gc, String mm, String usuario){
        try {
            PreparedStatement st = connect.prepareStatement("insert into diario(fecha, edad, peso, altura, cintura, cuello, cadera, imc, tmb, gc, mm, user) values (?,?,?,?,?,?,?,?,?,?,?,?)");
            st.setString(1, getFecha());
            st.setString(2, edad);
            st.setString(3, peso);
            st.setString(4, altura);
            st.setString(5, cintura);
            st.setString(6, cuello);
            st.setString(7, cadera);
            st.setString(8, imc);
            st.setString(9, tmb);
            st.setString(10, gc);
            st.setString(11, mm);
            st.setString(12, usuario);
            st.execute();
        } catch (SQLException ex) {
            System.err.println("Error al grabar: "+ex.getMessage());
        }
    }
    //Métodos pedir archivos
    public void getDiario(JTable tabla, String usuario){
        try{
            PreparedStatement st = connect.prepareStatement("select * from diario where user = ?");
            st.setString(1, usuario);
            ResultSet re = st.executeQuery();
                DefaultTableModel mo;
                String titulos[] = {"ID", "Fecha", "Edad", "Peso", "Altura", "Cintura", "Cuello", "Cadera", "IMC", "TMB", "GC", "MM"};
                mo = new DefaultTableModel(null, titulos);
                String fila[] = new String[13];
                while(re.next()){
                    fila[0] = re.getString("id");
                    fila[1] = re.getString("fecha");
                    fila[2] = re.getString("edad");
                    fila[3] = re.getString("peso");
                    fila[4] = re.getString("altura");
                    fila[5] = re.getString("cintura");
                    fila[6] = re.getString("cuello");
                    fila[7] = re.getString("cadera");
                    fila[8] = re.getString("imc");
                    fila[9] = re.getString("tmb");
                    fila[10] = re.getString("gc");
                    fila[11] = re.getString("mm");
                    mo.addRow(fila);
                }
                tabla.setModel(mo);
        }catch (SQLException ex) {
            System.out.println("Error al obtener: "+ex);
        }
    }
    //método para obtener usuarios
        public void getUsuarios(JComboBox combo){
            combo.removeAllItems();
        try{
            PreparedStatement st = connect.prepareStatement("select * from usuarios");
            ResultSet re = st.executeQuery();
                while(re.next()){
                    combo.addItem(re.getString("user"));
                }
        }catch (SQLException ex) {
            System.out.println("Error al obtener: "+ex);
        }
    }
    
    //Método para eliminar
    public void delDiario(int id){
        try {
            PreparedStatement st = connect.prepareStatement("delete from diario where id = ?");
            st.setInt(1, id);
            st.execute();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    //Método para Actualizar
    public void setUsuario(String user){
        try {
            PreparedStatement st = connect.prepareStatement("insert into usuarios(user) values(?)");
            st.setString(1, user);
            st.execute();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }


}
