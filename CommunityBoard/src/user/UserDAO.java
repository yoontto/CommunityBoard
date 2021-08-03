package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public UserDAO() {
		try {
			String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
			String user = "yoonseo";
			String password ="0000";
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("UserDAO Exception!");
		}
	}//userDAO()
	
	public int login(String userID, String userPassword) {
		String sql = "select userPassword From userinfo where userID = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, userID);
			rs = ps.executeQuery();
			if (rs.next()) {
				if(rs.getString(1).equals(userPassword)) {
					return 1;	// 로그인 성공
				} else {
					return 0; 	// 비밀번호 불일치
				}
			}
			return -1; // 아이디 없음
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("login Exception!");
		}
		return -2;	//데이터베이스 오류
	}//login()
	
	public int join(User user) {	//VO 타입의 객체
		String sql = "insert into userinfo values (?,?,?,?,?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUserID());
			ps.setString(2, user.getUserPassword());
			ps.setString(3, user.getUserName());
			ps.setString(4, user.getUserGender());
			ps.setString(5, user.getUserEmail());
			return ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("join Exception!");
		}
		return -1;	// 데이터베이스 오류
	}//join()
	
	
}
