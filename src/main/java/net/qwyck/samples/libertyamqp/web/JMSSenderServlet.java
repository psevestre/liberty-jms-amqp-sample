package net.qwyck.samples.libertyamqp.web;

import java.io.IOException;

import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class JMSSenderServlet
 */
@WebServlet("/JMSSender")
public class JMSSenderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String user = "guest";
	private static final String pass = System.getenv("RMQ_PASSWORD");

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public JMSSenderServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
      InitialContext ctx = new InitialContext();

      QueueConnectionFactory factory = (QueueConnectionFactory) ctx.lookup("jms/RMQConnectionFactory");
      QueueConnection conn = factory.createQueueConnection(user, pass);
      Queue queue = (Queue) ctx.lookup("jms/RMQQueue");

      conn.start();

      QueueSession session = conn.createQueueSession(false, javax.jms.Session.AUTO_ACKNOWLEDGE);
      QueueSender sender = session.createSender(queue);
      TextMessage message = session.createTextMessage();

      message.setText("Hello World");
      sender.send(message);

      System.out.println("Message sent to jms/RMQQueue successfully.");

      if (conn != null) {
				try {
					conn.close();
				} catch (UnsupportedOperationException e) {
					System.out.println("Caught UnsupportedOperationException whilst closing connection to RabbitMQ.");
				}
      }
		} catch (Exception e) {
			System.out.println("An unexpected error occurred, check the error log for more details.");
			e.printStackTrace();
		}
	}
}
