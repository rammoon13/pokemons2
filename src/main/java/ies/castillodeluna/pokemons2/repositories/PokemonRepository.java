package ies.castillodeluna.pokemons2.repositories;

import ies.castillodeluna.pokemons2.models.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PokemonRepository extends JpaRepository<Pokemon, Long> {
    
    Pokemon findByName(String name);
    
    List<Pokemon> findByType(String type);
    
    List<Pokemon> findTop5ByOrderByHitPointsDesc();
    
    List<Pokemon> findByLevelBetween(int min, int max);
    
    List<Pokemon> findByHitPointsBetween(Long min, Long max);
    
    List<Pokemon> findByGeneration(Integer generation);
    
    List<Pokemon> findByIsStarter(Boolean isStarter);
    
    List<Pokemon> findByHasMegaEvolution(Boolean hasMegaEvolution);
    
    List<Pokemon> findByIsShiny(Boolean isShiny);

    @Query("SELECT AVG(p.hitPoints) FROM Pokemon p")
    Double findAverageHitPoints();

    @Query("SELECT MAX(p.level) FROM Pokemon p")
    Integer findMaxLevel();

    List<Pokemon> findByNameContainingIgnoreCase(String name);

    @Query("SELECT p.type, COUNT(p) FROM Pokemon p GROUP BY p.type")
    List<Object[]> countPokemonsByType();

    @Query("SELECT p.isShiny, COUNT(p) FROM Pokemon p WHERE p.isShiny IS TRUE GROUP BY p.isShiny")
    List<Object[]> countPokemonByShiny();

    @Query("SELECT p.hasMegaEvolution, COUNT(p) FROM Pokemon p WHERE p.hasMegaEvolution IS TRUE GROUP BY p.hasMegaEvolution")
    List<Object[]> countPokemonByMegaEvolution();

    @Query("SELECT p.generation, COUNT(p) FROM Pokemon p GROUP BY p.generation")
    List<Object[]> countPokemonsByGeneration();
}
