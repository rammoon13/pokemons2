package ies.castillodeluna.pokemons2.services;

import ies.castillodeluna.pokemons2.models.Pokemon;
import ies.castillodeluna.pokemons2.repositories.PokemonRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
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

        Map<String, Long> typeCounts = pokemonRepository.countPokemonsByType()
            .stream()
            .collect(Collectors.toMap(
            data -> (String) data[0],
            data -> (Long) data[1]
            ));
        
        stats.put("pokemon_by_type", typeCounts);

        Map<String, Long> generationCounts = pokemonRepository.countPokemonsByGeneration()
            .stream()
            .collect(Collectors.toMap(
            data -> "Generacion " + data[0],
            data -> (Long) data[1]
            ));
        
        stats.put("pokemon_by_generation", generationCounts);
        stats.put("pokemon_shinys", pokemonRepository.countPokemonByShiny());
        return stats;
    }

    @Override
    public List<Pokemon> searchPokemonByName(String name) {
        return pokemonRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public List<Pokemon> getPokemonsByGeneration(Integer generation) {
        return pokemonRepository.findByGeneration(generation);
    }

    @Override
    public List<Pokemon> getStarterPokemons() {
        return pokemonRepository.findByIsStarter(true);
    }

    @Override
    public List<Pokemon> getMegaEvolutionPokemons() {
        return pokemonRepository.findByHasMegaEvolution(true);
    }

    @Override
    public List<Pokemon> getShinyPokemons() {
        return pokemonRepository.findByIsShiny(true);
    }

    @Override
    public Pokemon updatePokemonShiny(Long id, boolean isShiny) {
        Optional<Pokemon> optionalPokemon = pokemonRepository.findById(id);
        if (optionalPokemon.isPresent()) {
            Pokemon pokemon = optionalPokemon.get();
            pokemon.setShiny(isShiny);
            return pokemonRepository.save(pokemon);
        }
        return null;
    }

    @Override
    public Pokemon updatePokemonMega(Long id, boolean hasMegaEvolution) {
        Optional<Pokemon> optionalPokemon = pokemonRepository.findById(id);
        if (optionalPokemon.isPresent()) {
            Pokemon pokemon = optionalPokemon.get();
            pokemon.setMegaEvolution(hasMegaEvolution);
            return pokemonRepository.save(pokemon);
        }
        return null;
    }

    @Override
    public Pokemon updatePokemonStarter(Long id, boolean isStarter) {
        Optional<Pokemon> optionalPokemon = pokemonRepository.findById(id);
        if (optionalPokemon.isPresent()) {
            Pokemon pokemon = optionalPokemon.get();
            pokemon.setStarter(isStarter);
            return pokemonRepository.save(pokemon);
        }
        return null;
    }

    @Override
    public List<Pokemon> createRandomPokemons(int count) {
        List<Pokemon> randomPokemons = new ArrayList<>();
        String[] types = {"Fuego", "Agua", "Planta", "Eléctrico", "Roca", "Volador"};
        Random random = new Random();

        for (int i = 0; i < count; i++) {
            Pokemon pokemon = new Pokemon();
            pokemon.setName("Pokemon" + random.nextInt(1000));
            pokemon.setType(types[random.nextInt(types.length)]);
            pokemon.setHitPoints((long) (30 + random.nextInt(71))); // Entre 30 y 100
            pokemon.setLevel(1 + random.nextInt(50)); // Entre 1 y 50
            pokemon.setIsShiny(random.nextBoolean());
            pokemon.setHasMegaEvolution(random.nextBoolean());
            pokemon.setIsStarter(random.nextBoolean());
            pokemon.setGeneration(1 + random.nextInt(8)); // Entre 1 y 8
            randomPokemons.add(pokemon);
        }

        return pokemonRepository.saveAll(randomPokemons);
    }

    @Override
    public Pokemon duplicatePokemon(Long id) {
        Optional<Pokemon> optionalPokemon = pokemonRepository.findById(id);
        if (optionalPokemon.isPresent()) {
            Pokemon original = optionalPokemon.get();
            Pokemon duplicate = new Pokemon(null, original.getName() + " Clone",
                    original.getType(), original.getHitPoints(), original.getLevel(),
                    original.getIsShiny(), original.getHasMegaEvolution(),
                    original.getIsStarter(), original.getGeneration());

            return pokemonRepository.save(duplicate);
        }
        return null;
    }

    @Override
    public Pokemon makePokemonLegendary(Long id) {
        Optional<Pokemon> optionalPokemon = pokemonRepository.findById(id);
        if (optionalPokemon.isPresent()) {
            Pokemon pokemon = optionalPokemon.get();
            pokemon.setName("Legendary " + pokemon.getName());
            pokemon.setHitPoints(pokemon.getHitPoints() + 50);
            pokemon.setLevel(pokemon.getLevel() + 10);
            return pokemonRepository.save(pokemon);
        }
        return null;
    }

    @Override
    public void deletePokemonsByType(String type) {
        List<Pokemon> pokemons = pokemonRepository.findByType(type);
        pokemonRepository.deleteAll(pokemons);
    }

    @Override
    public void deletePokemonsUnderLevel(int level) {
        List<Pokemon> pokemons = pokemonRepository.findByLevelBetween(1, level);
        pokemonRepository.deleteAll(pokemons);
    }

    @Override
    public void deleteLegendaryPokemons() {
        List<Pokemon> legendaryPokemons = pokemonRepository.findByNameContainingIgnoreCase("Legendary");
        pokemonRepository.deleteAll(legendaryPokemons);
    }

    @Override
    public void deleteAllPokemons() {
        pokemonRepository.deleteAll();
    }

    @Override
    public void deleteWeakPokemons(int hitPoints, int level) {
        List<Pokemon> weakPokemons = pokemonRepository.findAll().stream()
                .filter(pokemon -> pokemon.getHitPoints() < hitPoints && pokemon.getLevel() < level)
                .collect(Collectors.toList());

        pokemonRepository.deleteAll(weakPokemons);
    }


    @Override
    public void deletePokemonByGeneration(int generation) {
        List<Pokemon> pokemons = pokemonRepository.findByGeneration(generation);
        pokemonRepository.deleteAll(pokemons);
    }


}
