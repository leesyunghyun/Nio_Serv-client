import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FIServerData {
	private Connection con;
	private Statement stmt;
	private ResultSet rs = null;
	private String id;
	private String pw;
	private String ip;
	private String name;
	private String birthday;

	public void Dataopen() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager
					.getConnection("jdbc:mysql://localhost/fastinput?user=root&password=0000");
			stmt = con.createStatement();
		} catch (Exception e) {
			// TODO �ڵ� ������ catch ���
			e.printStackTrace();
		}

	}

	public FIServerData() {
		// TODO �ڵ� ������ ������ ����
		Dataopen();
	}
	public void setalloff()
	{
		String str ="update fast_input set onoff ='off'";
		try {
			stmt.executeUpdate(str);
		}
		catch(SQLException e){
		}
	}
	public void setBye(String nicname)
	{
		String str = "update fast_input set onoff ='off' where nicname ='" + nicname + "';";
		try {
			stmt.executeUpdate(str);
		}
		catch(SQLException e){
		}
	}
	public boolean LoginCheck(String id, String pw)
	{
		String str = "Select * from fast_input where id='" + id + "';";
		try {
			rs = stmt.executeQuery(str);
			rs.first();
			if(rs.getString("pw").equals(pw)){
				if(rs.getString("onoff").equals("off"))
				{
					str = "Update fast_input set onoff = 'on' where id = '" + id +"';";
					stmt.executeUpdate(str);
					return true;
				}
			}
			else{
				return false;
			}
		} catch (SQLException e) {
			return false;
		}
		return false;
	}
	public void InsertMember(String id, String pw, String name, String ip,
			String birthday, String nicname) {
		String str = "Insert into fast_input values('" + id + "','" + pw
				+ "','" + name + "','" + ip + "','" + birthday + "','"
				+ nicname + "');";
		
			try {
				stmt.execute(str);
			} catch (SQLException e) {
				// TODO �ڵ� ������ catch ���
				e.printStackTrace();
			}		
	}
	
	public boolean IDCheck(String id)
	{
		String str = "Select * from fast_input where id='" + id + "';";
		try {
			rs = stmt.executeQuery(str);
			rs.first();
			if(rs.getString("id").equals(id)){
				return false;
			}
			else{
				return true;
			}
		} catch (SQLException e) {
			return true;
		}
	}
	
	public boolean NICCheck(String nic)
	{
		String str = "Select * from fast_input where nicname='" + nic + "';";
		try {
			rs = stmt.executeQuery(str);
			rs.first();
			if(rs.getString("nicname").equals(nic)){
				return false;
			}
			else{
				return true;
			}
		} catch (SQLException e) {
			return true;
		}
	}

	public String FindID(String nicname, String pw)
	{
		String str = "Select id from fast_input where nicname='" + nicname + "' and pw = '" + pw  + "';";
		String str2 = "falsefalse"; //���̵� 8���������̶� 10���ڷ� �����ؾ���.
		try {
			rs = stmt.executeQuery(str);
			rs.first();
			str2 = rs.getString("id");
			return str2;
		} catch (SQLException e) {
			return str2;
		}
	}
	
	public String FindPW(String id,String name,String birthday)
	{
		String str = "Select pw from fast_input where id='" + id + "' and name = '" + name  + "' and birthday = '" + birthday + "';";
		String str2 = "falsefalse"; //���̵� 8���������̶� 10���ڷ� �����ؾ���.
		try {
			rs = stmt.executeQuery(str);
			rs.first();
			str2 = rs.getString("pw");
			return str2;
		} catch (SQLException e) {
			return str2;
		}
	}
	
	public String getnicname(String id)
	{
		String str = "Select nicname from fast_input where id='" + id + "';";
		String str2 = "false";
		try {
			rs = stmt.executeQuery(str);
			rs.first();
			str2 = rs.getString("nicname");
			return str2;
		} catch (SQLException e) {
			//str = "getnicname���� ����";
			return str2;
		}
	}
}
