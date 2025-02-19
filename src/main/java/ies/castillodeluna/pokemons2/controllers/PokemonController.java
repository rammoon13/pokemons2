package ies.castillodeluna.pokemons2.controllers;

import ies.castillodeluna.pokemons2.models.Pokemon;
import ies.castillodeluna.pokemons2.services.PokemonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/pokemons")
public class PokemonController {

    private final PokemonService pokemonService;

    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping // probado
    // Example: GET http://localhost:8080/api/pokemons
    public List<Pokemon> getAllPokemons() {
        return pokemonService.getAllPokemons();
    }

    @GetMapping("/{id}") // probado
    // Example: GET http://localhost:8080/api/pokemons/1
    public Pokemon getPokemonById(@PathVariable Long id) {
        return pokemonService.getPokemonById(id);
    }

    @PostMapping // probado (se debe poner en POST, no en PUT ni GET, y poner JSON en vez de TEXT)
    // Example: POST http://localhost:8080/api/pokemons
    // Body (raw JSON):
    // {
    //   "name": "Pikachu",
    //   "type": "Electric",
    //   "level": 5,
    //   "hitPoints": 35
    // }
    public Pokemon createPokemon(@RequestBody Pokemon pokemon) {
        return pokemonService.createPokemon(pokemon);
    }

    @DeleteMapping("/{id}") // probado
    // Example: DELETE http://localhost:8080/api/pokemons/1
    public void deletePokemon(@PathVariable Long id) {
        pokemonService.deletePokemon(id);
    }

    @PutMapping("/{id}/level") // probado (se debe poner en PUT, no en POST ni GET, y poner JSON en vez de TEXT)
    // Example: PUT http://localhost:8080/api/pokemons/1/level
    // Body (raw JSON):
    // {
    //   "level": 10
    // }
    public Pokemon updatePokemonLevel(@PathVariable Long id, @RequestBody Map<String, Integer> levelRequest) {
        return pokemonService.updatePokemonLevel(id, levelRequest.get("level"));
    }

    @GetMapping("/filter") // probado
    // Example: GET http://localhost:8080/api/pokemons/filter?type=Electrico
    public List<Pokemon> filterByType(@RequestParam String type) {
        return pokemonService.filterByType(type);
    }

    @GetMapping("/hitpoints") // probado
    // Example: GET http://localhost:8080/api/pokemons/hitpoints?min=20&max=100
    public List<Pokemon> filterByHitPoints(@RequestParam Long min, @RequestParam Long max) {
        return pokemonService.filterByHitPoints(min, max);
    }

    @GetMapping("/stats") // probado
    // Example: GET http://localhost:8080/api/pokemons/stats
    public Map<String, Object> getPokedexStats() {
        return pokemonService.getPokedexStats();
    }
}
