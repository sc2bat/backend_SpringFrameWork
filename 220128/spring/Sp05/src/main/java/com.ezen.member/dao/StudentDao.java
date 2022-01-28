package

import

public class StudentDao{
	String driver="oracle.jdbc.dirver.OracleDriver";
	String url = "jdbc:oracle:thin@localhost:1521:xe";
	String id = "scott";
	String pw = "tiger";
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public Connection getConnection(){
		Connection con = null;
		
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url, id, pw);
		}catch(ClassNotFoundException e){e.printStackTrace();
		}catch(SQLException e){e.printStackTrace();
		}
		return con;
	}
	
	public void close(){
		try{
			if(con != null)con.close();
			if(pstmt != null)pstmt.close();
			if(rs != null)rs.close();
		}catch(SQLException e){e.printStackTrace();
		}
		
	}
	
	public void insertStudent(Student std){
		String sql = "INSERT INTO student(snum, sid, spw, sname, sage, sgender, smajor)"
						+ "VALUES(?, ?, ?, ?, ?, ?, ?)";
		con = getConnection();
		try{
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, std.getsNum());
			pstmt.setString(2, std.getsId());
			pstmt.setString(3, std.getsPw());
			pstmt.setString(4, std.getsName());
			pstmt.setString(5, std.getsAge());
			pstmt.setString(6, std.getsGender());
			pstmt.setString(7, std.getsMajor());
			pstmt.executeUpdate();
		}catch(SQLException e){e.printStackTrace();
		}finally{
			close();
		}
		
		
	}
	
	public ArrayList<Student> selectStudent(){
		ArrayList<Student> list = new ArrayList<Student>();
		String sql = "SELECT * FROM student";
		con = getConnection();
		try{
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				// Student std = new Student();
				// 비어있는 생성자가 없기에 만듬과 동시에 넣어줘야함
				Student std = new Student(
					rs.getString("sNum"),
					rs.getString("sId"),
					rs.getString("sPw"),
					rs.getString("sName"),
					rs.getInt("sAge"),
					rs.getString("sGender"),
					rs.getString("sMajor"),
				);
				list.add(std);
			}
		}catch(SQLException e){e.printStackTrace();
		}finally{
			close();
		}
		
	}
	
	public Student selectStudent(String snum){
		Student std = null;
		String sql = "SELECT * FROM student WHERE sNum=?";
		try{
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, snum);
			rs = pstmt.executeQuery();
			if(rs.next()){
				Student std = new Student(
					rs.getString("sNum"),
					rs.getString("sId"),
					rs.getString("sPw"),
					rs.getString("sName"),
					rs.getInt("sAge"),
					rs.getString("sGender"),
					rs.getString("sMajor"),
				);
			}
		}catch(SQLException e){e.printStackTrace();
		}finally{
			close();
		}
		return std;
	}
	
}