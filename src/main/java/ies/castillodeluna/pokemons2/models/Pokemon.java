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

    public Pokemon() {}

    public Pokemon(Long id, String name, String type, Long hitPoints, Integer level) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.hitPoints = hitPoints;
        this.level = level;
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
}
