package one.digitalinnovation.heroesapi;

import one.digitalinnovation.heroesapi.repository.HeroesRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

@AutoConfigureWebTestClient
@SpringBootTest
class HeroesApiApplicationTests {

	@Autowired
	private WebTestClient webTestClient;

	@Autowired
	private HeroesRepository heroesRepository;

	private static final String HEROES_ENDPOINT_LOCAL = "/api/v1/heroes";

	@Test
	public void getOneHeroById() {
		webTestClient.get()
			.uri(HEROES_ENDPOINT_LOCAL.concat("/{id}"), "1")
			.exchange()
			.expectStatus().isOk()
			.expectBody();
	}

	@Test
	public void getOneHeroByIdNotFound() {
		webTestClient.get()
			.uri(HEROES_ENDPOINT_LOCAL.concat("/{id}"), "10")
			.exchange()
			.expectStatus().isNotFound();
	}

	@Test
	public void deleteHeroById() {
		webTestClient.delete()
			.uri(HEROES_ENDPOINT_LOCAL.concat("/{id}"), "5")
			.accept(MediaType.APPLICATION_JSON)
			.exchange()
			.expectStatus().isNoContent()
			.expectBody(Void.class);
	}

	@Test
	public void deleteHeroByIdNotFound() {
		webTestClient.delete()
			.uri(HEROES_ENDPOINT_LOCAL.concat("/{id}"), "10")
			.accept(MediaType.APPLICATION_JSON)
			.exchange()
			.expectStatus().isNotFound()
			.expectBody(Void.class);
	}
}
