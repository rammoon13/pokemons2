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

    @Query("SELECT AVG(p.hitPoints) FROM Pokemon p")
    Double findAverageHitPoints();

    @Query("SELECT MAX(p.level) FROM Pokemon p")
    Integer findMaxLevel();
}
