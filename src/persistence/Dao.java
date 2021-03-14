package persistence;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class Dao {

	Connection con;
	PreparedStatement stmt;
	ResultSet rs;
	CallableStatement cs;


	private static final Logger log = Logger.getLogger(Dao.class);

	/*private String pathConfig = System.getProperty("os.name").contains("Windows")?
	"C:\\Projetos\\Eclipse\\cadastrogrupowlrest\\WebContent\\WEB-INF\\config\\config.xml" : "";

public void open_mysql() throws Exception{

Class.forName("com.mysql.jdbc.Driver");
Properties properties = new Properties();
FileInputStream fis = new FileInputStream(pathConfig);
properties.loadFromXML(fis);

String url = properties.getProperty("jdbc.mysql.url");
String username = properties.getProperty("jdbc.mysql.username");
String password = properties.getProperty("jdbc.mysql.password");

con = DriverManager.getConnection(url,username,password);

}*/

	//para rodar no heroku
	public void open_mysql() throws Exception{

		Class.forName("com.mysql.cj.jdbc.Driver");

		String url = "jdbc:mysql://us-cdbr-east-03.cleardb.com:3306/heroku_75a9c868adb575b?useSSL=false";
		String username = "b545792a4ce977";
		String password = "9b543a77";

		con = DriverManager.getConnection(url,username,password);

	}



	public void close() {

		try {
			if(con!=null)
				con.close();
			if(stmt!=null)
				stmt.close();
			if(rs!=null)
				rs.close();
		} catch (SQLException e) {
			log.info("Erro ao fechar conexão: "+ e.getMessage());
		}

	}





	public static void main(String[] args) {

		try {
			new Dao().open_mysql();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
