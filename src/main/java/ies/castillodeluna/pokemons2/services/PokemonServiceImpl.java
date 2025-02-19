package ies.castillodeluna.pokemons2.services;

import ies.castillodeluna.pokemons2.models.Pokemon;
import ies.castillodeluna.pokemons2.repositories.PokemonRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PokemonServiceImpl implements PokemonService {

    private final PokemonRepository pokemonRepository;

    public PokemonServiceImpl(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    @Override
    public List<Pokemon> getAllPokemons() {
        return pokemonRepository.findAll();
    }

    @Override
    public Pokemon getPokemonById(Long id) {
        return pokemonRepository.findById(id).orElse(null);
    }

    @Override
    public Pokemon getPokemonByName(String name) {
        return pokemonRepository.findByName(name);
    }

    @Override
    public List<Pokemon> getTopPokemonByHP() {
        return pokemonRepository.findTop5ByOrderByHitPointsDesc();
    }

    @Override
    public List<Pokemon> getPokemonsByLevelRange(int min, int max) {
        return pokemonRepository.findByLevelBetween(min, max);
    }

    @Override
    public Pokemon createPokemon(Pokemon pokemon) {
        return pokemonRepository.save(pokemon);
    }

    @Override
    public List<Pokemon> createMultiplePokemons(List<Pokemon> pokemons) {
        return pokemonRepository.saveAll(pokemons);
    }

    @Override
    public Pokemon updatePokemonLevel(Long id, int newLevel) {
        Optional<Pokemon> optionalPokemon = pokemonRepository.findById(id);
        if (optionalPokemon.isPresent()) {
            Pokemon pokemon = optionalPokemon.get();
            pokemon.setLevel(newLevel);
            return pokemonRepository.save(pokemon);
        }
        return null;
    }

    @Override
    public Pokemon updatePokemonType(Long id, String newType) {
        Optional<Pokemon> optionalPokemon = pokemonRepository.findById(id);
        if (optionalPokemon.isPresent()) {
            Pokemon pokemon = optionalPokemon.get();
            pokemon.setType(newType);
            return pokemonRepository.save(pokemon);
        }
        return null;
    }

    @Override
    public Pokemon updatePokemonName(Long id, String newName) {
        Optional<Pokemon> optionalPokemon = pokemonRepository.findById(id);
        if (optionalPokemon.isPresent()) {
            Pokemon pokemon = optionalPokemon.get();
            pokemon.setName(newName);
            return pokemonRepository.save(pokemon);
        }
        return null;
    }

    @Override
    public List<Pokemon> levelUpPokemonsByType(String type) {
        List<Pokemon> pokemons = pokemonRepository.findByType(type);
        pokemons.forEach(pokemon -> pokemon.setLevel(pokemon.getLevel() + 1));
        return pokemonRepository.saveAll(pokemons);
    }

    @Override
    public Pokemon evolvePokemon(Long id, String newName, String newType, int newHP) {
        Optional<Pokemon> optionalPokemon = pokemonRepository.findById(id);
        if (optionalPokemon.isPresent()) {
            Pokemon pokemon = optionalPokemon.get();
            pokemon.setName(newName);
            pokemon.setType(newType);
            pokemon.setHitPoints((long) newHP);
            return pokemonRepository.save(pokemon);
        }
        return null;
    }

    @Override
    public void deletePokemon(Long id) {
        pokemonRepository.deleteById(id);
    }

    @Override
    public List<Pokemon> filterByType(String type) {
        return pokemonRepository.findByType(type);
    }

    @Override
    public List<Pokemon> filterByHitPoints(Long min, Long max) {
        return pokemonRepository.findByHitPointsBetween(min, max);
    }

    @Override
public Map<String, Object> getPokedexStats() {
    Map<String, Object> stats = new HashMap<>();
    stats.put("total_pokemons", pokemonRepository.count());
    stats.put("average_hp", pokemonRepository.findAverageHitPoints());
    stats.put("max_level", pokemonRepository.findMaxLevel());

    // Obtener el conteo de Pokémon por tipo
    Map<String, Long> typeCounts = pokemonRepository.countPokemonsByType()
        .stream()
        .collect(Collectors.toMap(
            data -> (String) data[0],
            data -> (Long) data[1]
        ));
    
    stats.put("pokemon_by_type", typeCounts);
    return stats;
}

@Override
public List<Pokemon> searchPokemonByName(String name) {
    return pokemonRepository.findByNameContainingIgnoreCase(name);
}
}
