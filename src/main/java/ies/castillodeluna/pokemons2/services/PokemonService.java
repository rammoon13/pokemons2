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
}
