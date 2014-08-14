package tydic.action;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;
import javax.swing.Timer;
import javax.servlet.ServletException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Date;

public class TimerServlet extends HttpServlet {
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		Timer t = new Timer(1000,new ActionListener()
		{
				public void actionPerformed(ActionEvent e) {
						System.out.println(new Date());
				}
		});			
		t.start();
	}
}
