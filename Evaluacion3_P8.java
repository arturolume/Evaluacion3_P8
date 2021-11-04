import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.xml.transform.Result;
import java.util.Scanner;

public class Evaluacion3_P8 {
 
	public static void main(String[] args) throws Exception {
		
		int codigo;
		String apellido, nombre, dni, direccion;
		
        Connection conn = null;
     
        String driver   = "com.mysql.cj.jdbc.Driver";
        String db       = "evaluacion3";
        String url      = "jdbc:mysql://localhost:3306/" + db;
        String user     = "root";
        String pass     = "Reyes562";
        System.out.println("Connected to database : " + db);

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url,user,pass);
            System.out.println("Connected to database : " + db);
            Statement st=conn.createStatement();

            Scanner sc = new Scanner(System.in);

            boolean salir = false;
            while (!salir){
                System.out.println("1- Registrar alumno");
                System.out.println("2- Mostrar alumnos");
		System.out.println("3- Buscar alumno por DNI");
                System.out.println("4- Salir");
                System.out.println("Ingrese una de las opciones");
                int opcion = sc.nextInt();
                switch(opcion){
                    case 1:
                    	System.out.println("REGISTRAR ALUMNO");
                        System.out.println("Ingresa el apellido del alumno");
                        apellido = sc.next();
                        System.out.println("Ingresa el nombre del alumno");
                        nombre = sc.next();                  
                        System.out.println("Ingresa el DNI del alumno");
                        dni = sc.next();
                        System.out.println("Ingresa la direccion del alumno");
                        direccion = sc.next();
                        String insercion = "insert into alumnos(codigo, apellidos, nombres, dni, direccion) values("
                        		+ "default, '"+apellido+"','"+nombre+"','"+dni+"', '"+direccion+"')";
                        int result = st.executeUpdate(insercion);

                        System.out.println("\nTerminado\n");
                        break;
                    
                    case 2:
                        System.out.println("MOSTRAR ALUMNOS");
                        
                        ResultSet rs = st.executeQuery("select * from alumnos");
                        
                        while (rs.next()) {
                        	System.out.println("Codigo:" + rs.getString(1)
            				+ "\nApellido: "+rs.getString(2)
            				+"\nNombre: "+rs.getString(3)
            				+"\nDNI: "+rs.getString(4)
            				+"\nDireccion: "+rs.getString(5)+"\n");
                        }
                        System.out.println("\nTerminado\n");
                        break;
		    case 3:
                    	System.out.println("BUSCAR ALUMNO POR DNI");
                    	System.out.println("Ingrese el DNI del alumno a buscar");
                    	dni = sc.next();
                    	ResultSet rs1 = st.executeQuery("select * from alumnos where dni ='"+dni+"'");
                    	if (rs1.next()) {
                    		System.out.println("Codigo:" + rs1.getString(1)
            				+ "\nApellido: "+rs1.getString(2)
            				+"\nNombre: "+rs1.getString(3)
            				+"\nDNI: "+rs1.getString(4)
            				+"\nDireccion: "+rs1.getString(5)+"\n");                   	
                        }
                    	System.out.println("\nTerminado\n");
                    	break;
                    case 4:
                        salir = true;
                        System.out.println("\nSaliendo del menu\nQue tenga un buen dia.");
                }
            }
        } catch (SQLException e) {
            System.out.println("SQLException: "+e.getMessage());
            System.out.println("SQLState: "+e.getSQLState());
            System.out.println("VendorError: "+e.getErrorCode());
        }
     
    }
}