package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UserDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public UserDAO() {
		try {
			String dbURL = "jdbc:mysql://localhost:3306/ajax?serverTimezone=Asia/Seoul&useSSL=false";
			String dbID="root";
			String dbPassword="apmsetup";
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
			//System.out.println("DB연동 성공");
		}catch (Exception e) {
			System.out.println("DB연동 실패");
			e.printStackTrace();
		}
	}
	
	public ArrayList<User> search(String userName){
		String SQL = "SELECT * FROM user WHERE userName LIKE ?";
		ArrayList<User> userList = new ArrayList<User>();
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1,"%"+userName+"%");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setUserName(rs.getString(1));
				user.setUserAge(rs.getInt(2));
				user.setUserGender(rs.getString(3));
				user.setUserEmail(rs.getString(4));
				userList.add(user);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return userList;
	}
}
