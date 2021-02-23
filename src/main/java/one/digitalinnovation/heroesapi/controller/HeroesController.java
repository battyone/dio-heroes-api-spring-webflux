package one.digitalinnovation.heroesapi.controller;

import lombok.extern.slf4j.Slf4j;
import one.digitalinnovation.heroesapi.document.Heroes;
import one.digitalinnovation.heroesapi.service.HeroesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController()
@RequestMapping("/api/v1/heroes")
public class HeroesController {

  private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(HeroesController.class);

  @Autowired
  private HeroesService heroesService;

  @GetMapping("")
  public Flux<Heroes> getAll() {
    log.info("requesting all Heroes");
    return this.heroesService.findAllHeroes();
  }

  @GetMapping("/{id}")
  public Mono<Heroes> findById(@PathVariable String id) throws EmptyResultDataAccessException {
    log.info("requesting Hero with id {}", id);
    return this.heroesService.findHeroById(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<Heroes> create(@RequestBody Heroes hero) {
    log.info("creating a new Hero");
    return this.heroesService.createHero(hero);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteById(@PathVariable String id) throws EmptyResultDataAccessException {
    log.info("deleting a Hero with id {}", id);
    this.heroesService.deleteHeroById(id);
  }

  @ExceptionHandler({EmptyResultDataAccessException.class})
  public ResponseEntity handle(EmptyResultDataAccessException ex) {
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
}
