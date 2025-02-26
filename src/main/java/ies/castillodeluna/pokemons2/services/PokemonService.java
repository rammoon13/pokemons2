package ies.castillodeluna.pokemons2.services;

import ies.castillodeluna.pokemons2.models.Pokemon;

import java.util.List;
import java.util.Map;

public interface PokemonService {
    List<Pokemon> getAllPokemons();
    Pokemon getPokemonById(Long id);
    Pokemon getPokemonByName(String name);
    List<Pokemon> getTopPokemonByHP();
    List<Pokemon> getPokemonsByLevelRange(int min, int max);
    Pokemon createPokemon(Pokemon pokemon);
    List<Pokemon> createMultiplePokemons(List<Pokemon> pokemons);
    Pokemon updatePokemonLevel(Long id, int newLevel);
    Pokemon updatePokemonType(Long id, String newType);
    Pokemon updatePokemonName(Long id, String newName);
    List<Pokemon> levelUpPokemonsByType(String type);
    Pokemon evolvePokemon(Long id, String newName, String newType, int newHP);
    void deletePokemon(Long id);
    List<Pokemon> filterByType(String type);
    List<Pokemon> filterByHitPoints(Long min, Long max);
    Map<String, Object> getPokedexStats();
    List<Pokemon> searchPokemonByName(String name);
    List<Pokemon> getPokemonsByGeneration(Integer generation);
    List<Pokemon> getStarterPokemons();
    List<Pokemon> getMegaEvolutionPokemons();
    List<Pokemon> getShinyPokemons();
    Pokemon updatePokemonShiny(Long id, boolean isShiny);
    Pokemon updatePokemonMega(Long id, boolean hasMegaEvolution);
    Pokemon updatePokemonStarter(Long id, boolean isStarter);
    List<Pokemon> createRandomPokemons(int count);
    Pokemon duplicatePokemon(Long id);
    Pokemon makePokemonLegendary(Long id);
    void deletePokemonsByType(String type);
    void deletePokemonsUnderLevel(int level);
    void deleteLegendaryPokemons();
    void deleteAllPokemons();
    void deleteWeakPokemons(int hitPoints, int level);
    void deletePokemonByGeneration(int generation);
}
