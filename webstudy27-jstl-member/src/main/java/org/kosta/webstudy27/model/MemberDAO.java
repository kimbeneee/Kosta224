package org.kosta.webstudy27.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.kosta.webstudy27.model.DataSourceManager;

public class MemberDAO {
	private DataSource dataSource;
	private static MemberDAO instance=new MemberDAO();
	private MemberDAO() {
		this.dataSource=DataSourceManager.getInstance().getDataSource();
	}
	public static MemberDAO getInstance() {
		return instance;
	}
	public void closeAll(PreparedStatement pstmt, Connection con) throws SQLException{
		if(pstmt!=null)
			pstmt.close();
		if(con!=null)
			con.close(); // dbcp로부터 빌려온 컨넥션을 dbcp에 반납한다
	}
	public void closeAll(ResultSet rs,PreparedStatement pstmt,Connection con) throws SQLException {
		closeAll(pstmt,con);
		if(rs!=null)
			rs.close();
	}
	public MemberVO findMemberInfoById(String id) throws SQLException {
		MemberVO vo=null;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=dataSource.getConnection();//dbcp로부터 커녁션을 빌려온다
			String sql="select name,address,to_char(birthday,'YYYY.MM.DD'),to_char(regdate,'YYYY.MM.DD HH24:MM:SS') from mvc_member where id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				vo=new MemberVO(id,rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
			}
		}finally {
			closeAll(rs, pstmt, con);//dbcp로 컨넥션을 반환한다
		}
		return vo;
	}
	public MemberVO login(String id,String password) throws SQLException{
		MemberVO vo=null;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=dataSource.getConnection();  // dbcp로부터 컨넥션을 빌려온다
			StringBuilder sql=new StringBuilder("select name,address,to_char(birthday,'YYYY-MM-DD') ");
			sql.append("from mvc_member where id=? and password=?");
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				vo=new MemberVO(id,password,rs.getString(1),rs.getString(2),rs.getString(3),null);
			}
		}finally {
			closeAll(rs, pstmt, con);
		}
		return vo;
	}
	public ArrayList<MemberVO> findMemberByAddress(String address) throws SQLException {
		ArrayList<MemberVO> list=new ArrayList<MemberVO>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=dataSource.getConnection(); //dbcp로부터 컨텍션을 빌려온다
			StringBuilder sql=new StringBuilder("select id,name,to_char(birthday,'YYYY.MM.DD') from mvc_member where address=?");
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, address);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				MemberVO vo=new MemberVO();
				vo.setId(rs.getString(1));
				vo.setName(rs.getString(2));
				vo.setBirthday(rs.getString(3));
				list.add(vo);
			}
		}finally {
			closeAll(rs, pstmt, con);
		}
		return list;
	}
	public void UpdateMember(MemberVO vo) throws SQLException {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=dataSource.getConnection();
			String sql="update mvc_member set password=?,name=?,address=?,birthday=to_date(?,'YYYY.MM.DD') where id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, vo.getPassword());
			pstmt.setString(2, vo.getName());
			pstmt.setString(3, vo.getAddress());
			pstmt.setString(4, vo.getBirthday());
			pstmt.setString(5, vo.getId());
			pstmt.executeUpdate();
		}finally {
			closeAll(pstmt, con);
		}
	}
	public void registerMember(MemberVO vo) throws SQLException {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=dataSource.getConnection();
			StringBuilder sql=new StringBuilder();
			sql.append("insert into mvc_member(id,password,name,address,birthday,regdate) ");
			sql.append("values(?,?,?,?,TO_DATE(?,'YYYY.MM.DD'),sysdate)"); 
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getAddress());
			pstmt.setString(5, vo.getBirthday());
			pstmt.executeUpdate();
		}finally {
			closeAll(pstmt, con);
		}
	}
	// 아이디가 존재하면 true , 존재하지 않으면 false 를 반환
	public boolean checkId(String id) throws SQLException {
		boolean result=false;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=dataSource.getConnection();
			String sql="select count(*) from mvc_member where id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			if(rs.next()&&rs.getInt(1)==1) {
				result=true;
			}
		}finally {
			closeAll(rs, pstmt, con);
		}
		return result;
	}
}











