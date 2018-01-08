package com.zucc.demo.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.stereotype.Repository;
import com.zucc.demo.dao.UserDAO;
import com.zucc.demo.model.UserVO;


@Repository
public class UserDAOImpl implements UserDAO{

	@Override
	public UserVO Login(String uname) {
		UserVO User = new UserVO();
        try {
            Connection con = getConnection();
            String sql="SELECT id,pwd FROM User Where uname=?";
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setString(1,uname);
            ResultSet rs=pst.executeQuery();          
            while(rs.next()){
                int id = rs.getInt("id");
                String pwd = rs.getString("pwd") ; // 此方法比较高效
                User.setUname(uname);
                User.setId(id);
                User.setPwd(pwd);                
            }
				rs.close();
				pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return User;
        
      
	}
	
	
    private Connection getConnection(){
        try{
            //加载MySql的驱动类
            Class.forName("com.mysql.jdbc.Driver") ;
        }catch(ClassNotFoundException e){
            System.out.println("找不到驱动程序类 ，加载驱动失败！");
            e.printStackTrace() ;
        }
        //连接MySql数据库，用户名和密码都是root
        String url = "jdbc:mysql://localhost:3306/ChatRoom" ;
        String username = "root" ;
        String password = "Dmm961104cc." ;
        try{
            Connection con =
                    DriverManager.getConnection(url , username , password ) ;
            return con;
        }catch(SQLException se){
            System.out.println("数据库连接失败！");
            se.printStackTrace() ;
        }
        return null;
    }
}
