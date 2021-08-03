package bbs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BbsDAO {
	private Connection conn;
	private ResultSet rs;
	
	public BbsDAO() {
		try {
			String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
			String user = "yoonseo";
			String password ="0000";
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("BbsDAO Exception!");
		}
	}//BbsDAO()
	
	public String getDate() {
		String sql = "select to_char(sysdate, 'yyyymmdd') from dual";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {
				return rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("getDate Exception!");
		}
		return "";	//DB 호출
	}
	
	public int getNext() {
		String sql = "select bbsID from BBS order by bbsID DESC";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {
				return rs.getInt(1) + 1;
			}
			return 1;	// 첫번째 게시물인 경우
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("getDate Exception!");
		}
		return -1;	//오류
	}

	public int write(String bbsTitle, String userID, String bbsContent) {
		String sql = "insert into BBS values(?,?,?,?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, getNext());
			ps.setString(2, bbsTitle);
			ps.setString(3, userID);
			ps.setString(4, getDate());
			ps.setString(5, bbsContent);
			ps.setInt(6, 1);
			return ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("getDate Exception!");
		}
		return -1;	//오류
	}
	
	public ArrayList<Bbs> getList(){
		String sql = "select * from BBS where bbsAvailable=1 order by bbsID desc";
		ArrayList<Bbs> list = new ArrayList<Bbs>();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				Bbs bbs = new Bbs();
				bbs.setBbsID(rs.getInt(1));
				bbs.setBbsTitle(rs.getString(2));
				bbs.setUserID(rs.getString(3));
				bbs.setBbsDate(rs.getString(4));
				bbs.setBbsContent(rs.getString(5));
				bbs.setBbsAvailable(rs.getInt(6));
				list.add(bbs);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("getList Exception!");
		}
		return list;	
	}
	
	/*public boolean nextPage(int pageNumber) {
		String sql = "select * from BBS where bbsID < ? and bbsAvailable = 1";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, getNext() - (pageNumber -1 ) * 10);
			rs = ps.executeQuery();
			if(rs.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("getList Exception!");
		}
		return false;
	}*/
}











