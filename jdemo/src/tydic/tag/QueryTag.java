package tydic.tag;

import javax.servlet.jsp.tagext.TagSupport;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.PageContext;
import java.io.IOException;
import java.io.Writer;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class QueryTag extends TagSupport {
	
	//标签类的属性
	private String driver;
	private String url;
	private String username;
	private String passwd;
	private String sql;
	
	//执行数据库访问的对象
	private Connection con = null;
	private Statement st = null;
	private ResultSet rs =null;
	private ResultSetMetaData rsmd = null;
	
	//标签属性的setter和getter方法
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public void setUsername(String name) {
		this.username = name;
	}
	public void setPasswd(String pwd) {
		this.passwd = pwd;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	
	public String getDriver() {
		return driver;
	}
	public String getUrl() {
		return url;
	}
	public String getUsername() {
		return username;
	}
	public String getPasswd() {
		return passwd;
	}
	public String getSql() {
		return sql;
	}
	
	//标签处理
	public int doEndTag() throws JspTagException {
		try {
			//注册驱动
			Class.forName(driver);
			//获取连接
			con = DriverManager.getConnection(url,username,passwd);
			//创建Statement
			st = con.createStatement();
			//执行查询
			rs = st.executeQuery(sql);			
			rsmd = rs.getMetaData();
			//获取列数目
			int columnCount = rsmd.getColumnCount();
			//获取页面输出流
			Writer out = pageContext.getOut();//JspWriter继承java.io.Writer
			//在页面输出表格
			out.write("<table border='1' bgColor='9999cc'>");
			//遍历结果集
			while(rs.next()) {
				out.write("<tr>");
				//逐列输出查询到的数据
				for(int i=1; i<=columnCount; i++) {
					out.write("<td>"+rs.getString(i)+"</td>");
				}
				out.write("</tr>");
			}
			
		} catch(Exception ex) {
				ex.printStackTrace();
				throw new JspTagException("Error! 出错了...");
		}
		return EVAL_PAGE;
	}
	
	//销毁标签前调用的方法,释放资源
	public void destory() {
		if(rs != null) {
			try {
				rs.close();
			} catch(SQLException ex) {
					ex.printStackTrace();
			}
		}
		if(st != null) {
			try {
				st.close();
			} catch(SQLException ex) {
					ex.printStackTrace();
			}
		}
		if(con != null) {
			try {
				con.close();
			} catch(SQLException ex) {
					ex.printStackTrace();
			}
		}
	}
}
