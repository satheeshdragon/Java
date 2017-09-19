// Loading required libraries
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
 
public class DatabaseAccess extends HttpServlet{
    
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
            throws ServletException, IOException
  {
      // JDBC driver name and database URL
     String JDBC_DRIVER="com.mysql.jdbc.Driver";  

      // Set response content type
      response.setContentType("text/html");
	  Connection con = null;
        Statement  stmt = null;
        ResultSet   rs = null;
      PrintWriter out = response.getWriter();
      String title = "Database Result BBBB";
      String docType =
        "<!doctype html public \"-//w3c//dtd html 4.0 " +
         "transitional//en\">\n";
         out.println(docType +
         "<html>\n" +
         "<head><title>" + title + "</title></head>\n" +
         "<body bgcolor=\"#f0f0f0\">\n" +
         "<h1 align=\"center\">" + title + "</h1>\n");
      try{
         // Register JDBC driver
         Class.forName("org.gjt.mm.mysql.Driver");

         // Open a connection
         con = DriverManager.getConnection("jdbc:mysql://localhost/test","root", "");

		 out.println(docType+ 
		 "DATATATATATAT");
         // Execute SQL query
		 
        stmt = con.createStatement();
         String sql;
         sql = "SELECT id, first, last, age FROM Employees";
		 rs = stmt.executeQuery(sql);
		out.println(docType +
			"query"+sql+"END");
		        out.println(docType +
         "<h1 align=\"center\">Query Given</h1>\n");
         //PreparedStatement rs=conn.prepareStatement(sql);
         // Extract data from result set
         while(rs.next()){
         int id  = rs.getInt("id");
         int age = rs.getInt("age");
         String first = rs.getString("first");
         String last = rs.getString("last");

            //Display values
            out.println("ID: " + id + "<br>");
            out.println(", AGE: " + age + "<br>");
            out.println(", FISRT: " + first + "<br>");
            out.println(", LAST: " + last + "<br>");
        
         }
         out.println("</body></html>");

         // Clean-up environment
         rs.close();
         stmt.close();
         con.close();
      }catch(SQLException se){
         //Handle errors for JDBC
         se.printStackTrace();
      }catch(Exception e){
         //Handle errors for Class.forName
         e.printStackTrace();
      }finally{
         //finally block used to close resources
         try{
            if(stmt!=null)
               stmt.close();
         }catch(SQLException se2){
         }// nothing we can do
         try{
            if(con!=null)
            con.close();
         }catch(SQLException se){
            se.printStackTrace();
         }//end finally try
      } //end try
   }
} 