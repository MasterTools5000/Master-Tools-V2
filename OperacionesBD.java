package test;
import beans.Herramienta;
import connection.DBConnection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;



public class OperacionesBD {
    public static void main(String[] args) {
        int select;
        String herramienta;
        //listarHerramienta();
        //actualizarHerramienta(5,"Union");
        select=Integer.parseInt(JOptionPane.showInputDialog("Por favor ingrese el id de la herramienta que desea actualizar"));
        herramienta=JOptionPane.showInputDialog("Por favor digite el tipo de herramienta a cambiar");
        actualizarHerramienta(select,herramienta);
    }
    public static void actualizarHerramienta(int id, String tipo) {
        DBConnection con = new DBConnection();
        String sql = "UPDATE herramienta SET tipo = '" + tipo + "'WHERE id = " + id;
        
        try {
            Statement st = con.getConnection().createStatement();
            st.executeUpdate(sql);            
        }catch(Exception ex){
            System.out.println(ex.getMessage());            
        } 
        
        finally {
            con.desconectar();
            
        }
    }
    public static void listarHerramienta(){
        DBConnection con = new DBConnection();
        String sql = "SELECT * FROM herramienta";
        
        try {
            Statement st = con.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                int id = rs.getInt("id");
                String titulo = rs.getString("titulo");
                String tipo = rs.getString("tipo");
                String marca = rs.getString("marca");
                int copias = rs.getInt("copias");
                boolean novedad = rs.getBoolean("novedad");
                Herramienta herramienta = new Herramienta(id, titulo, tipo, marca, copias, novedad);
                System.out.println(herramienta.toString()); 
                
            }
        st.executeQuery(sql);
        }catch(Exception ex){
            System.out.println(ex.getMessage());            
        } 
        
        finally {
            con.desconectar();   
        }
    }
}
