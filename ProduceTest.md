<h1>java向sqlsever数据库存储过程</h1>
<hr/>
--创建一个表<br/>
create table aa(<br/>
Id int identity(1,1) primary key,<br/>
name varchar(40),<br/>
sal int,<br/>
)

--创建存储过程表<br/>
create procedure Pro_aa<br/>
@name varchar(40),<br/>
@sal int,<br/>
@Id int output<br/>
as<br/>
begin<br/>
	insert into aa(name,sal) values (@name,@sal)<br/>
	select @Id=Id from aa<br/>
end

--当执行该过程时，将name，sal保存到数据库中，并返回Id号（其中id在表aa中为主键自增长）

--java代码段

package com.test;

import java.sql.CallableStatement;<br/>
import java.sql.Connection;<br/>
import java.sql.DriverManager;<br/>
import java.sql.PreparedStatement;<br/>
import java.sql.ResultSet;<br/>

import org.junit.Test;<br/>

/*<br/>
 * sql server数据库存储过程测试<br/>
 */<br/>
public class Demo2 {<br/>
	@Test<br/>
	public void fun1(){<br/>
		Connection ct=null;<br/>
		CallableStatement cs=null;<br/>
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			ct=DriverManager.getConnection(
					"jdbc:sqlserver://localhost:1433;DatabaseName=PIM","sa","zhang");

			cs=ct.prepareCall("{call Pro_aa(?,?,?)}");
			cs.setString(1, "zhangruitao");
			cs.setInt(2, 1234567);
			cs.registerOutParameter(3, java.sql.Types.INTEGER);
			cs.execute();
			int i=cs.getInt(3);
			
			System.out.println("得到输出结果："+i);
			cs.close();
			ct.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}