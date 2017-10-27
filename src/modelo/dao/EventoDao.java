package modelo.dao;

import java.sql.Connection;

public class EventoDao {
	
	public void EventoDao() {
		ConnectionFactory factory = new ConnectionFactory();
		Connection con = factory.getConnection();
	}

}
