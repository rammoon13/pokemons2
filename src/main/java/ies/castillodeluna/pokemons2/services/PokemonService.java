package ies.castillodeluna.pokemons2.services;

import ies.castillodeluna.pokemons2.models.Pokemon;
import java.util.List;
import java.util.Map;

public interface PokemonService {
    List<Pokemon> getAllPokemons();
    Pokemon getPokemonById(Long id);
    Pokemon createPokemon(Pokemon pokemon);
    void deletePokemon(Long id);
    Pokemon updatePokemonLevel(Long id, Integer newLevel);
    List<Pokemon> filterByType(String type);
    List<Pokemon> filterByHitPoints(Long min, Long max);
    Map<String, Object> getPokedexStats();
}
