package br.com.itengine.score.entity;//
// This file was generated by the JPA Modeler
//

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class Team implements Serializable {

    private String address;

    @OneToMany(targetEntity = Player.class, mappedBy = "team")
    @JsonManagedReference
    private List<Player> players;

    @ManyToOne(optional = false, targetEntity = League.class)
    @JsonBackReference
    private League league;

    @Column(unique = true)
    private String name;

    private String logo;

    @ManyToOne(targetEntity = User.class)
    private User teamAdmin;

    @Id
    @GeneratedValue(generator = "id", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "id")
    private Integer id;

    public Team() {

    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Player> getPlayers() {
        return this.players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public League getLeague() {
        return this.league;
    }

    public void setLeague(League league) {
        this.league = league;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return this.logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public User getTeamAdmin() {
        return this.teamAdmin;
    }

    public void setTeamAdmin(User teamAdmin) {
        this.teamAdmin = teamAdmin;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
