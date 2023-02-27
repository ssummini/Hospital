package com.hospital.dao;

import java.sql.*;
import java.util.ArrayList;

import com.hospital.vo.MemberVO;

public class MemberDAO {

	private static MemberDAO dao = new MemberDAO();
	private MemberDAO(){}

	public static MemberDAO getInstance() {
		return dao;
	}

	public Connection connect() {
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "scott", "tiger");
		} catch (Exception ex) {
			System.out.println("오류 발생: " + ex);
		}
		return conn;
	}

	public void close(Connection conn, PreparedStatement ps, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception ex) {
				System.out.println("오류발생 : " + ex);				
			}
		}
		close(conn, ps);
	} // close

	public void close(Connection conn, PreparedStatement ps) {
		if (ps != null) {
			try {
				ps.close();
			} catch (Exception ex) {
				System.out.println("오류발생 : " + ex);				
			}
		}

		if (conn != null) {
			try {
				conn.close();
			} catch (Exception ex) {
				System.out.println("오류발생 : " + ex);				
			}
		}
	} // close

	public void memberInsert(MemberVO pa) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = connect();
			pstmt = conn.prepareStatement("insert into pa values(?,?,?,?)");
			pstmt.setString(1, pa.getId());
			pstmt.setString(2, pa.getName());
			pstmt.setString(3, pa.getBirthday());
			pstmt.setString(4, pa.getPhone());
			pstmt.executeUpdate();
		} catch (Exception ex) {
			System.out.println("오류발생 : " + ex);
		} finally {
			close(conn, pstmt);
		}
	}

	public MemberVO memberSearch(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		MemberVO member = null;

		try {
			conn = connect();
			pstmt = conn.prepareStatement("select * from pa where id=?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				member = new MemberVO();
				member.setId(rs.getString(1));
				member.setName(rs.getString(2));
				member.setBirthday(rs.getString(3));
				member.setPhone(rs.getString(4));
			}

		} catch (Exception ex) {
			System.out.println("오류발생 : " + ex);
		} finally {
			close(conn, pstmt, rs);
		}

		return member;
	}

	public void memberUpdate(MemberVO pa) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = connect();
			pstmt = conn.prepareStatement("update member set name=?,birthday=?,phone=? where id=?");
			pstmt.setString(1, pa.getName());
			pstmt.setString(2, pa.getBirthday());
			pstmt.setString(3, pa.getPhone());
			pstmt.setString(4, pa.getId());
			pstmt.executeUpdate();

		} catch (Exception ex) {
			System.out.println("오류발생 : " + ex);
		} finally {
			close(conn, pstmt);
		}

	}

	public void memberDelete(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = connect();
			pstmt = conn.prepareStatement("delete from member where id=?");
			pstmt.setString(1, id);
			pstmt.executeUpdate();

		} catch (Exception ex) {
			System.out.println("오류발생 : " + ex);
		} finally {
			close(conn, pstmt);
		}
	}

	public ArrayList<MemberVO> memberList() {

		ArrayList<MemberVO> list = new ArrayList<MemberVO>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		MemberVO member = null;

		try {
			conn = connect();
			pstmt = conn.prepareStatement("select * from pa");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				member = new MemberVO();
				member.setId(rs.getString(1));
				member.setName(rs.getString(2));
				member.setBirthday(rs.getString(3));
				member.setPhone(rs.getString(4));
				list.add(member);
			}

		} catch (Exception ex) {
			System.out.println("오류발생 : " + ex);
		} finally {
			close(conn, pstmt, rs);
		}

		return list;
	}

}
