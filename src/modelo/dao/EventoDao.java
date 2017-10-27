package modelo.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import modelo.dominio.Evento;
import modelo.dominio.Usuario;

public class EventoDao {
	
	 private Statement statement;
	 private PreparedStatement preparedStatement;
	 private Connection con = null;
	
	public EventoDao() {
		ConnectionFactory factory = new ConnectionFactory();
		con = factory.getConnection();
	}
	
	public String solicitarEvento(Usuario usuario, Evento evento) throws SQLException {
		String salvo = "";
		String sqlSalvarEvento = "INSERT INTO GestaoEventos.evento("
				+ "nome,dataEvento,vagas,categoria,valorIngresso,statusEvento," 
				+ "localEvento,id_usuario)" + "values("
						+ "?,?,?,?,?,?,?,?)";
		
		try {
            con.setAutoCommit(false);
            preparedStatement = con.prepareStatement(sqlSalvarEvento);
            preparedStatement.setString(1, evento.getNome());
            preparedStatement.setDate(2, new java.sql.Date(evento.getDataEvento().getTime()));
            preparedStatement.setInt(3, evento.getVagas());
            preparedStatement.setString(4, evento.getCategoria());
            preparedStatement.setDouble(5, evento.getValorIngresso());
            preparedStatement.setString(6, evento.getStatus());
            preparedStatement.setString(7, evento.getLocal());
            preparedStatement.setInt(8, usuario.getId());

            preparedStatement.executeUpdate();

            //Grava as informações se caso de problema os dados não são gravados
            con.commit();
            salvo = "salvo";

        } catch (SQLException e) {
            if (con != null) {
                try {
                    System.err.print("Rollback efetuado na transação" + e.getMessage());
                    con.rollback();
                } catch (SQLException e2) {
                    System.err.print("Erro na transação!" + e2);
                    salvo = "\"Erro na transação!\"+e2";
                }
            }
        } finally {
            if (preparedStatement != null) {
            	preparedStatement.close();
            }
            con.setAutoCommit(true);
        }
		
		return salvo;
	}

}
