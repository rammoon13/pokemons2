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

    /**
     * GET /api/pokemons
     * Obtiene todos los Pokémon.
     * En Postman:
     * - Método: GET
     * - URL: http://localhost:8080/api/pokemons
     */
    @GetMapping // probado
    public List<Pokemon> getAllPokemons() {
        return pokemonService.getAllPokemons();
    }

    /**
     * GET /api/pokemons/{id}
     * Obtiene un Pokémon por su ID.
     * En Postman:
     * - Método: GET
     * - URL: http://localhost:8080/api/pokemons/1
     */
    @GetMapping("/{id}") // probado
    public Pokemon getPokemonById(@PathVariable Long id) {
        return pokemonService.getPokemonById(id);
    }

    /**
     * GET /api/pokemons/name/{name}
     * Obtiene un Pokémon por su nombre.
     * En Postman:
     * - Método: GET
     * - URL: http://localhost:8080/api/pokemons/name/Bulbasaur
     */
    @GetMapping("/name/{name}") // probado
    public Pokemon getPokemonByName(@PathVariable String name) {
        return pokemonService.getPokemonByName(name);
    }

    /**
     * GET /api/pokemons/top-hp
     * Obtiene los 5 Pokémon con más HP.
     * En Postman:
     * - Método: GET
     * - URL: http://localhost:8080/api/pokemons/top-hp
     */
    @GetMapping("/top-hp") // probado
    public List<Pokemon> getTopHP() {
        return pokemonService.getTopPokemonByHP();
    }

    /**
     * GET /api/pokemons/level-range?min=10&max=50
     * Obtiene los Pokémon dentro de un rango de niveles.
     * En Postman:
     * - Método: GET
     * - URL: http://localhost:8080/api/pokemons/level-range?min=10&max=50
     */
    @GetMapping("/level-range") // probado
    public List<Pokemon> getPokemonsByLevelRange(@RequestParam int min, @RequestParam int max) {
        return pokemonService.getPokemonsByLevelRange(min, max);
    }

    /**
     * POST /api/pokemons
     * Crea un nuevo Pokémon.
     * En Postman:
     * - Método: POST
     * - URL: http://localhost:8080/api/pokemons
     * - Body (JSON):
     * {
     *   "name": "Charmander",
     *   "type": "Fuego",
     *   "hitPoints": 39,
     *   "level": 5
     * }
     */
    @PostMapping // probado 
    public Pokemon createPokemon(@RequestBody Pokemon pokemon) {
        return pokemonService.createPokemon(pokemon);
    }

    /**
     * POST /api/pokemons/bulk
     * Crea múltiples Pokémon.
     * En Postman:
     * - Método: POST
     * - URL: http://localhost:8080/api/pokemons/bulk
     * - Body (JSON):
     * [
     *   { "name": "Squirtle", "type": "Agua", "hitPoints": 44, "level": 5 },
     *   { "name": "Pikachu", "type": "Eléctrico", "hitPoints": 35, "level": 5 }
     * ]
     */
    @PostMapping("/bulk") // probado
    public List<Pokemon> createMultiplePokemons(@RequestBody List<Pokemon> pokemons) {
        return pokemonService.createMultiplePokemons(pokemons);
    }

    /**
     * PUT /api/pokemons/{id}/level
     * Actualiza el nivel de un Pokémon.
     * En Postman:
     * - Método: PUT
     * - URL: http://localhost:8080/api/pokemons/1/level
     * - Body (JSON):
     * { "level": 10 }
     */
    @PutMapping("/{id}/level") // probado
    public Pokemon updatePokemonLevel(@PathVariable Long id, @RequestBody Map<String, Integer> levelRequest) {
        return pokemonService.updatePokemonLevel(id, levelRequest.get("level"));
    }

    /**
     * PUT /api/pokemons/{id}/type
     * Actualiza el tipo de un Pokémon.
     * En Postman:
     * - Método: PUT
     * - URL: http://localhost:8080/api/pokemons/1/type
     * - Body (JSON):
     * { "type": "Agua" }
     */
    @PutMapping("/{id}/type") // probado
    public Pokemon updatePokemonType(@PathVariable Long id, @RequestBody Map<String, String> typeRequest) {
        return pokemonService.updatePokemonType(id, typeRequest.get("type"));
    }

    /**
     * PUT /api/pokemons/{id}/name
     * Actualiza el nombre de un Pokémon.
     * En Postman:
     * - Método: PUT
     * - URL: http://localhost:8080/api/pokemons/1/name
     * - Body (JSON):
     * { "name": "Raichu" }
     */
    @PutMapping("/{id}/name") // probado
    public Pokemon updatePokemonName(@PathVariable Long id, @RequestBody Map<String, String> nameRequest) {
        return pokemonService.updatePokemonName(id, nameRequest.get("name"));
    }

    /**
     * PUT /api/pokemons/level-up/type/{type}
     * Sube de nivel todos los Pokémon de un tipo específico.
     * En Postman:
     * - Método: PUT
     * - URL: http://localhost:8080/api/pokemons/level-up/type/Fuego
     */
    @PutMapping("/level-up/type/{type}") // probado (No sirve para los doble tipo)
    public List<Pokemon> levelUpByType(@PathVariable String type) {
        return pokemonService.levelUpPokemonsByType(type);
    }

    /**
     * PUT /api/pokemons/evolve/{id}
     * Evoluciona un Pokémon cambiando su nombre, tipo y HP.
     * En Postman:
     * - Método: PUT
     * - URL: http://localhost:8080/api/pokemons/evolve/1
     * - Body (JSON):
     * {
     *   "newName": "Charizard",
     *   "newType": "Fuego/Volador",
     *   "newHP": "78"
     * }
     */
    @PutMapping("/evolve/{id}") // probado
    public Pokemon evolvePokemon(@PathVariable Long id, @RequestBody Map<String, String> evolutionData) {
        return pokemonService.evolvePokemon(id, evolutionData.get("newName"), evolutionData.get("newType"), Integer.parseInt(evolutionData.get("newHP")));
    }

    /**
     * DELETE /api/pokemons/{id}
     * Elimina un Pokémon por su ID.
     * En Postman:
     * - Método: DELETE
     * - URL: http://localhost:8080/api/pokemons/1
     */
    @DeleteMapping("/{id}") // probado
    public void deletePokemon(@PathVariable Long id) {
        pokemonService.deletePokemon(id);
    }

    /**
     * GET /api/pokemons/filter?type=Agua
     * Filtra Pokémon por tipo.
     * En Postman:
     * - Método: GET
     * - URL: http://localhost:8080/api/pokemons/filter?type=Agua
     */
    @GetMapping("/filter") // probado
    public List<Pokemon> filterByType(@RequestParam String type) {
        return pokemonService.filterByType(type);
    }

    /**
     * GET /api/pokemons/hitpoints?min=30&max=60
     * Filtra Pokémon por rango de HP.
     * En Postman:
     * - Método: GET
     * - URL: http://localhost:8080/api/pokemons/hitpoints?min=30&max=60
     */
    @GetMapping("/hitpoints") // probado
    public List<Pokemon> filterByHitPoints(@RequestParam Long min, @RequestParam Long max) {
        return pokemonService.filterByHitPoints(min, max);
    }

    /**
     * GET /api/pokemons/stats
     * Obtiene estadísticas de la Pokédex.
     * En Postman:
     * - Método: GET
     * - URL: http://localhost:8080/api/pokemons/stats
     */
    @GetMapping("/stats") // probado
    public Map<String, Object> getPokedexStats() {
        return pokemonService.getPokedexStats();
    }

     /**
     * GET /api/pokemons/search?query=Char
     * Obtiene el pokemon que contenga la palabra en el query.
     * En Postman:
     * - Método: GET
     * - URL: http://localhost:8080/api/pokemons/search?query=Char
     */
    @GetMapping("/search") // probado
    public List<Pokemon> searchPokemon(@RequestParam String query) {
        return pokemonService.searchPokemonByName(query);
    }

    /**
     * GET /api/pokemons/filter/shiny
     * Obtiene todos los Pokémon shiny.
     * En Postman:
     * - Método: GET
     * - URL: http://localhost:8080/api/pokemons/filter/shiny
     */
    @GetMapping("/filter/shiny")
    public List<Pokemon> getShinyPokemons() {
        return pokemonService.getShinyPokemons();
    }

    /**
     * GET /api/pokemons/filter/mega
     * Obtiene todos los Pokémon con megaevolución.
     * En Postman:
     * - Método: GET
     * - URL: http://localhost:8080/api/pokemons/filter/mega
     */
    @GetMapping("/filter/mega")
    public List<Pokemon> getMegaEvolutions() {
        return pokemonService.getMegaEvolutionPokemons();
    }

    /**
     * GET /api/pokemons/filter/starter
     * Obtiene todos los Pokémon iniciales.
     * En Postman:
     * - Método: GET
     * - URL: http://localhost:8080/api/pokemons/filter/starter
     */
    @GetMapping("/filter/starter")
    public List<Pokemon> getStarterPokemons() {
        return pokemonService.getStarterPokemons();
    }

    /**
     * GET /api/pokemons/filter/generation/{generation}
     * Obtiene los Pokémon de una generación específica.
     * En Postman:
     * - Método: GET
     * - URL: http://localhost:8080/api/pokemons/filter/generation/1
     */
    @GetMapping("/filter/generation/{generation}")
    public List<Pokemon> getPokemonsByGeneration(@PathVariable int generation) {
        return pokemonService.getPokemonsByGeneration(generation);
    }

    /**
     * PUT /api/pokemons/{id}/shiny
     * Activa o desactiva el estado shiny de un Pokémon.
     * En Postman:
     * - Método: PUT
     * - URL: http://localhost:8080/api/pokemons/{id}/shiny
     * - Body (JSON): { "isShiny": true }
     */
    @PutMapping("/{id}/shiny")
    public Pokemon updatePokemonShiny(@PathVariable Long id, @RequestBody Map<String, Boolean> shinyRequest) {
        return pokemonService.updatePokemonShiny(id, shinyRequest.get("isShiny"));
    }

    /**
     * PUT /api/pokemons/{id}/mega
     * Activa o desactiva la megaevolución de un Pokémon.
     * En Postman:
     * - Método: PUT
     * - URL: http://localhost:8080/api/pokemons/{id}/mega
     * - Body (JSON): { "hasMegaEvolution": true }
     */
    @PutMapping("/{id}/mega")
    public Pokemon updatePokemonMega(@PathVariable Long id, @RequestBody Map<String, Boolean> megaRequest) {
        return pokemonService.updatePokemonMega(id, megaRequest.get("hasMegaEvolution"));
    }

    /**
     * PUT /api/pokemons/{id}/starter
     * Define si un Pokémon es inicial o no.
     * En Postman:
     * - Método: PUT
     * - URL: http://localhost:8080/api/pokemons/{id}/starter
     * - Body (JSON): { "isStarter": true }
     */
    @PutMapping("/{id}/starter")
    public Pokemon updatePokemonStarter(@PathVariable Long id, @RequestBody Map<String, Boolean> starterRequest) {
        return pokemonService.updatePokemonStarter(id, starterRequest.get("isStarter"));
    }
}
