package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import modelo.dominio.Evento;
import modelo.dominio.Participantes;
import modelo.dominio.Usuario;

public class ParticipantesDao {
	
	private Statement statement;
	 private PreparedStatement preparedStatement;
	 private Connection con = null;
	
	public ParticipantesDao() {
		ConnectionFactory factory = new ConnectionFactory();
		con = factory.getConnection();
	}
	
	public String salvarParticipacao(Participantes participantes) throws SQLException {
		String salvo = "";
		String sqlSalvarParticipantes = "INSERT INTO GestaoEventos.participantes("
				+ "dataCompra, quantidadeIngressos, id_usuario, id_evento)" 
				+ "values(?,?,?,?)";
		
		try {
            con.setAutoCommit(false);
            preparedStatement = con.prepareStatement(sqlSalvarParticipantes);
            preparedStatement.setDate(1, new java.sql.Date(participantes.getDataCompra().getTime()));
            preparedStatement.setInt(2, participantes.getQuantidadeIngressos());
            preparedStatement.setInt(3, participantes.getIdUsuario());
            preparedStatement.setInt(4, participantes.getIdEvento());

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
