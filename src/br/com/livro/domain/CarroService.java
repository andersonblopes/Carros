package br.com.livro.domain;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarroService {

	private CarroDAO dao = new CarroDAO();

	public List<Carro> getCarros() {
		List<Carro> carros;
		try {
			carros = dao.getCarros();
			return carros;
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<Carro>();
		}
	}

	public Carro getCarro(Long id) {
		try {
			return dao.getCarroById(id);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean delete(Long id) {
		try {
			return dao.deleteCarro(id);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean save(Carro c) {
		try {
			dao.saveCarro(c);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<Carro> findByName(String nome) {
		try {
			return dao.findByName(nome);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Carro> findByTipo(String tipo) {
		try {
			return dao.findByTipo(tipo);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
