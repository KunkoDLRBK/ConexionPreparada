/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.conexionbd;
import java.sql.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author kike
 */
public class ConexionBD {
    static final String db_URL="jdbc:mysql://localhost:3306/jcvd";
    static final String USER="root";
    static final String PASS="";
    //static final String QUERY="SELECT Nombre FROM videojuego WHERE Nombre='Fortnite'";
    public static void main(String[] args) {
         Scanner sc = new Scanner(System.in);
       String s="Fortnite";
        System.out.println("Introduce para ver los datos de un juego por nombre");
        String pp=sc.nextLine();
        System.out.println("Metodo buscar Nombre es: "+buscaNombre(pp));
        System.out.println("----------------------------------------------------------------");
        System.out.println("Metodo Lanza Consulta es: ");
        lanzaConsulta(pp);
        System.out.println("----------------------------------------------------------------");
        System.out.println("Metodo Nuevo Registro por parametros: ");
        nuevoRegistro();
        System.out.println("----------------------------------------------------------------");
        System.out.println("Metodo Nuevo Registro: ");
        System.out.println("Nombre del juego.");
        String nombre=sc.nextLine();
        System.out.println("Categoria del juego.");
        String categoria=sc.nextLine();
        System.out.println("Fecha del juego.");
        String fecha=sc.nextLine();
        System.out.println("Compañia del juego.");
        String compañia=sc.nextLine();
        System.out.println("precio del juego.");
        float precio=sc.nextFloat();
        nuevoRegistro(nombre,categoria, fecha, compañia, precio);
        System.out.println("----------------------------------------------------------------");
        System.out.println("Metodo Eliminar Registro: ");
        String eli="Fortnite";
        System.out.println("La eliminacio ha sido: "+eliminarRegistro(eli));
    }
    
    private static boolean buscaNombre(String s){
            boolean x=false;
            String nombretabla="";
            String QUERY="SELECT Nombre FROM videojuego WHERE Nombre= ? ";
            Connection conn;
            try {
                conn = DriverManager.getConnection(db_URL,USER,PASS);
            
            PreparedStatement sentencia= conn.prepareStatement(QUERY);
            sentencia.setString(1,s);
            ResultSet rs= sentencia.executeQuery();
            while(rs.next()){
                nombretabla=rs.getString("Nombre");
                if(nombretabla.equals(s)){
                    x=true;
                }
            }
              sentencia.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
            return x;
    }
    
    private static void lanzaConsulta(String s){
        String QUERY="SELECT * FROM videojuego WHERE Nombre= ? ";
         Connection conn;
        try{
            conn= DriverManager.getConnection(db_URL,USER,PASS);
        PreparedStatement sentencia= conn.prepareStatement(QUERY);
            sentencia.setString(1,s);
        ResultSet rs=sentencia.executeQuery();
        while(rs.next()){
            System.out.println("El juego con id: "+rs.getInt("id"));
            System.out.println("Su nombre es: "+rs.getString("Nombre"));
            System.out.println("El genero es: "+rs.getString("Genero"));
            System.out.println("La fecha es: "+rs.getDate("FechaLanzamiento"));
            System.out.println("La compañia es: "+rs.getString("Compañia"));
            System.out.println("EL precio es: "+rs.getFloat("Precio"));
        }
        sentencia.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    private static void nuevoRegistro(){
     String QUERY="INSERT INTO videojuego (Nombre, Genero, FechaLanzamiento, Compañia, Precio) VALUES (?,?,?,?,?)";
     Connection conn;   
     try{
        conn= DriverManager.getConnection(db_URL,USER,PASS);
        PreparedStatement sentencia= conn.prepareStatement(QUERY);
        sentencia.setString(1,"COD");
        sentencia.setString(2,"Shoter");
        sentencia.setString(3,"2012-11-12");
        sentencia.setString(4,"Treyarch");
        sentencia.setFloat(5,75);
        sentencia.executeUpdate();
            System.out.println("Se ha añadido correctamente el juego.");
        sentencia.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    
     
    private static void nuevoRegistro(String nombre, String genero, String Fecha, String compañia, float precio){
        String QUERY="INSERT INTO videojuego (Nombre, Genero, FechaLanzamiento, Compañia, Precio) VALUES (?,?,?,?,?)";
        Connection conn;
        try{
            conn= DriverManager.getConnection(db_URL,USER,PASS);
        PreparedStatement sentencia= conn.prepareStatement(QUERY);
        sentencia.setString(1,nombre);
        sentencia.setString(2,genero);
        sentencia.setString(3,Fecha);
        sentencia.setString(4,compañia);
        sentencia.setFloat(5,precio);
        sentencia.executeUpdate();
            System.out.println("Se ha añadido correctamente el juego: "+nombre);
        sentencia.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    private static boolean eliminarRegistro(String nombre){
        boolean x=false;
    String QUERY="Delete from videojuego WHERE Nombre= ? ";
    Connection conn;
        try {
            conn= DriverManager.getConnection(db_URL,USER,PASS);
        PreparedStatement stmt= conn.prepareStatement(QUERY);
        stmt.setString(1,nombre);
        stmt.executeUpdate();
        x=true;
        System.out.println("Se ha eliminado correctamente el juego.");
        
        stmt.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    return x;
    }
    
}


