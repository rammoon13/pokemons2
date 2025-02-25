package ies.castillodeluna.pokemons2.models;

import jakarta.persistence.*;

@Entity
@Table(name = "pokemons")
public class Pokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private String type;
    
    @Column(name="hit_points", nullable = false)
    private Long hitPoints;
    
    @Column(nullable = false)
    private Integer level;
    
    @Column(nullable = false)
    private Boolean isShiny;
    
    @Column(nullable = false)
    private Boolean hasMegaEvolution;
    
    @Column(nullable = false)
    private Boolean isStarter;
    
    @Column(nullable = false)
    private Integer generation;

    public Pokemon() {}

    public Pokemon(Long id, String name, String type, Long hitPoints, Integer level, Boolean isShiny, Boolean hasMegaEvolution, Boolean isStarter, Integer generation) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.hitPoints = hitPoints;
        this.level = level;
        this.isShiny = isShiny;
        this.hasMegaEvolution = hasMegaEvolution;
        this.isStarter = isStarter;
        this.generation = generation;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public Long getHitPoints() { return hitPoints; }
    public void setHitPoints(Long hitPoints) { this.hitPoints = hitPoints; }

    public Integer getLevel() { return level; }
    public void setLevel(Integer level) { this.level = level; }
    
    public Boolean getIsShiny() { return isShiny; }
    public void setIsShiny(Boolean isShiny) { this.isShiny = isShiny; }
    
    public Boolean getHasMegaEvolution() { return hasMegaEvolution; }
    public void setHasMegaEvolution(Boolean hasMegaEvolution) { this.hasMegaEvolution = hasMegaEvolution; }
    
    public Boolean getIsStarter() { return isStarter; }
    public void setIsStarter(Boolean isStarter) { this.isStarter = isStarter; }
    
    public Integer getGeneration() { return generation; }
    public void setGeneration(Integer generation) { this.generation = generation; }
}
