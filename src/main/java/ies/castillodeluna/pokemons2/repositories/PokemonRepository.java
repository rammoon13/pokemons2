package ies.castillodeluna.pokemons2.repositories;

import ies.castillodeluna.pokemons2.models.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PokemonRepository extends JpaRepository<Pokemon, Long> {
    List<Pokemon> findByType(String type);
    List<Pokemon> findByHitPointsBetween(Long min, Long max);
}
