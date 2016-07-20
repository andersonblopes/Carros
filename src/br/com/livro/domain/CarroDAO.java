package br.com.livro.domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CarroDAO extends BaseDAO {

	public Carro getCarroById(long id) throws SQLException {
		Connection con = null;
		PreparedStatement smt = null;

		try {
			con = getConnection();
			smt = con.prepareStatement("SELECT * FROM carro WHERE id=?");
			smt.setLong(1, id);
			ResultSet rs = smt.executeQuery();
			if (rs.next()) {
				Carro c = createCarro(rs);
				rs.close();
				return c;
			}
		} finally {
			if (smt != null) {
				smt.close();
			}
			if (con != null) {
				con.close();
			}
		}
		return null;

	}

	public List<Carro> findByName(String nome) throws SQLException {
		List<Carro> carros = new ArrayList<>();
		Connection con = null;
		PreparedStatement smt = null;
		try {
			con = getConnection();
			smt = con.prepareStatement("SELECT * FROM carro WHERE lower(nome) like ?");
			smt.setString(1, "%" + nome.toLowerCase() + "%");
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				Carro c = createCarro(rs);
				carros.add(c);
			}
			rs.close();
		} finally {
			if (smt != null) {
				smt.close();
			}
			if (con != null) {
				con.close();
			}
		}
		return carros;
	}

	public List<Carro> findByTipo(String tipo) throws SQLException {
		List<Carro> carros = new ArrayList<>();
		Connection con = null;
		PreparedStatement smt = null;
		try {
			con = getConnection();
			smt = con.prepareStatement("SELECT * FROM carro WHERE tipo = ?");
			smt.setString(1, tipo);
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				Carro c = createCarro(rs);
				carros.add(c);
			}
			rs.close();
		} finally {
			if (smt != null) {
				smt.close();
			}
			if (con != null) {
				con.close();
			}
		}
		return carros;
	}

	public List<Carro> getCarros() throws SQLException {
		List<Carro> carros = new ArrayList<>();
		Connection con = null;
		PreparedStatement smt = null;
		try {
			con = getConnection();
			smt = con.prepareStatement("SELECT * FROM carro");
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				Carro c = createCarro(rs);
				carros.add(c);
			}
			rs.close();
		} finally {
			if (smt != null) {
				smt.close();
			}
			if (con != null) {
				con.close();
			}
		}
		return carros;
	}

	public Carro createCarro(ResultSet rs) throws SQLException {
		Carro c = new Carro();
		c.setId(rs.getLong("id"));
		c.setNome(rs.getString("nome"));
		c.setDescricao(rs.getString("descricao"));
		c.setUrlFoto(rs.getString("urlfoto"));
		c.setUrlVideo(rs.getString("urlvideo"));
		c.setLatitude(rs.getString("latitude"));
		c.setLongitude(rs.getString("longitude"));
		c.setTipo(rs.getString("tipo"));
		return c;
	}

	public void saveCarro(Carro c) throws SQLException {
		Connection con = null;
		PreparedStatement smt = null;

		try {
			con = getConnection();
			if (c.getId() == null) {
				smt = con.prepareStatement(
						"INSERT INTO carro (nome, descricao, urlfoto, urlvideo, latitude, longitude, tipo) VALUES (?,?,?,?,?,?,?)",
						Statement.RETURN_GENERATED_KEYS);
			} else {
				smt = con.prepareStatement(
						"UPDATE carro SET nome=?, descricao=?, urlfoto=?, urlvideo=?, latitude=?, longitude=?, tipo=? WHERE id=?");
			}
			smt.setString(1, c.getNome());
			smt.setString(2, c.getDescricao());
			smt.setString(3, c.getUrlFoto());
			smt.setString(4, c.getUrlVideo());
			smt.setString(5, c.getLatitude());
			smt.setString(6, c.getLongitude());
			smt.setString(7, c.getTipo());

			if (c.getId() != null) {
				smt.setLong(8, c.getId());
			}

			int count = smt.executeUpdate();
			if (count == 0) {
				throw new SQLException("Erro ao inserir o carro");
			}
			if (c.getId() == null) {
				Long id = getGenerateId(smt);
				c.setId(id);
			}

		} finally {
			if (smt != null) {
				smt.close();
			}
			if (con != null) {
				con.close();
			}
		}
	}

	public static Long getGenerateId(PreparedStatement smt) throws SQLException {
		ResultSet rs = smt.getGeneratedKeys();
		if (rs.next()) {
			Long id = rs.getLong(1);
			return id;
		}
		return 0L;
	}

	public boolean deleteCarro(Long id) throws SQLException {
		Connection con = null;
		PreparedStatement smt = null;
		try {
			con = getConnection();
			smt = con.prepareStatement("DELETE FROM carro WHERE id = ?");
			smt.setLong(1, id);
			int count = smt.executeUpdate();
			boolean ok = count > 0;
			return ok;
		} finally {
			if (smt != null) {
				smt.close();
			}
			if (con != null) {
				con.close();
			}
		}
	}

}
