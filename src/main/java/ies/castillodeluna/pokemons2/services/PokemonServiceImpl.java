package ies.castillodeluna.pokemons2.services;

import ies.castillodeluna.pokemons2.models.Pokemon;
import ies.castillodeluna.pokemons2.repositories.PokemonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
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
    public Pokemon createPokemon(Pokemon pokemon) {
        return pokemonRepository.save(pokemon);
    }

    @Override
    public void deletePokemon(Long id) {
        pokemonRepository.deleteById(id);
    }

    @Override
    public Pokemon updatePokemonLevel(Long id, Integer newLevel) {
        return pokemonRepository.findById(id).map(pokemon -> {
            pokemon.setLevel(newLevel);
            return pokemonRepository.save(pokemon);
        }).orElse(null);
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
        List<Pokemon> allPokemons = pokemonRepository.findAll();

        double averageLevel = allPokemons.stream().mapToInt(Pokemon::getLevel).average().orElse(0);
        double averageHitPoints = allPokemons.stream().mapToLong(Pokemon::getHitPoints).average().orElse(0);
        Map<String, Long> countByType = allPokemons.stream().collect(Collectors.groupingBy(Pokemon::getType, Collectors.counting()));

        return Map.of(
            "averageLevel", averageLevel,
            "averageHitPoints", averageHitPoints,
            "countByType", countByType
        );
    }
}
