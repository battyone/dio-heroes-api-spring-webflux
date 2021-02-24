package one.digitalinnovation.heroesapi.service;

import one.digitalinnovation.heroesapi.document.Heroes;
import one.digitalinnovation.heroesapi.repository.HeroesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class HeroesService {

  @Autowired
  private HeroesRepository heroesRepository;

  public Flux<Heroes> findAllHeroes() {
    return Flux.fromIterable(this.heroesRepository.findAll());
  }

  public Mono<Heroes> findHeroById(String id) throws EmptyResultDataAccessException {
    return Mono.justOrEmpty(this.heroesRepository.findById(id))
      .switchIfEmpty(Mono.error(new EmptyResultDataAccessException(1)));
  }

  public Mono<Heroes> saveHero(Heroes heroes) {
    return Mono.justOrEmpty(this.heroesRepository.save(heroes));
  }

  public Mono<Boolean> deleteHeroById(String id) {
    this.heroesRepository.deleteById(id);
    return Mono.just(true);
  }
}
