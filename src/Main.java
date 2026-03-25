import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String url="jdbc:oracle:thin:@localhost:1521:xe";
        String user="RIBERA";
        String password="ribera";
        Scanner sc=new Scanner(System.in);
        Connection conn=null;
        try {
            conn = DriverManager.getConnection(url,user,password);
            conn.setAutoCommit(false);
            String sql="INSERT INTO empleado2 (id, nombre, salario) VALUES (?, ?, ?)";

            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setInt(1,1);
            preparedStatement.setString(2, "Dona");
            preparedStatement.setDouble(3, 3000.0);
            preparedStatement.executeUpdate();

            preparedStatement.setInt(1,12);
            preparedStatement.setString(2, "DON");
            preparedStatement.setDouble(3, 1000.0);
            preparedStatement.executeUpdate();

            conn.commit();
            System.out.println("Todos los empleados añadidos correctamente");
        }catch (SQLException e){
            System.out.println("ERROR: "+e.getMessage());
            try{
                if(conn!=null){
                    conn.rollback();
                    System.out.println("Rollback finalizado");
                }
            }catch (SQLException ex){
                System.out.println("ERROR: "+ex.getMessage());
            }
        }
    }
}
