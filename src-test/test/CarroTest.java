package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import br.com.livro.domain.Carro;
import br.com.livro.domain.CarroService;

public class CarroTest {

	CarroService carroService = new CarroService();

	@Test
	public void listaCarros() {
		List<Carro> carros = carroService.getCarros();
		assertTrue(carros.size() == 30);
		Carro c = new Carro();
		c.setNome("Fusca");
		c.setDescricao("Carro muito caro.");
		c.setTipo("POPULAR");
		carroService.save(c);
		carros = carroService.getCarros();
		assertTrue(carros.size() > 30);
		carroService.delete(c.getId());
		carros = carroService.getCarros();
		assertTrue(carros.size() == 30);
	}

	@Test
	public void insereAtualizaDeletaCarro() {
		Carro c = new Carro();
		c.setNome("FUSCA");
		c.setDescricao("Carro muito caro.");
		c.setTipo("POPULAR");
		carroService.save(c);
		assertNotNull(c.getId());

		Carro c2 = carroService.getCarro(c.getId());
		assertEquals("POPULAR", c2.getTipo());

		c2.setDescricao("Carro barato");
		carroService.save(c2);

		c = carroService.findByName("FUSCA").get(0);
		assertEquals("Carro barato", c2.getDescricao());

		carroService.delete(c.getId());
		carroService.delete(c2.getId());

		assertNull(carroService.getCarro(c.getId()));
		assertNull(carroService.getCarro(c2.getId()));
	}

}
