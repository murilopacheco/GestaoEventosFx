package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
	
	public List<Evento> listarEventos() throws SQLException{
		List<Evento> listaDeEventos = new ArrayList<Evento>();
		ResultSet res = null;
		String sqlListar = "SELECT * FROM EVENTO";
		try {
		preparedStatement = con.prepareStatement(sqlListar);
		res = preparedStatement.executeQuery();
		while (res.next()) {
			Evento evento = new Evento();
			evento.setId(res.getInt("id"));
			evento.setNome(res.getString("nome"));
			evento.setDataEvento(res.getDate("dataEvento"));
			evento.setStatus(res.getString("statusEvento"));
			evento.setVagas(res.getInt("vagas"));
			evento.setValorIngresso(res.getDouble("valorIngresso"));
			evento.setLocal(res.getString("localEvento"));
			evento.setCategoria(res.getString("categoria"));
			
			listaDeEventos.add(evento);
		}
		
		
		}catch (SQLException e){
            System.out.println("Erro na consulta1:" + e.getMessage());
        }
		return listaDeEventos;
	}

}
