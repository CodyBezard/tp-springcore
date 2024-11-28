package fr.diginamic.hello.objets;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="DEPARTMENT")
public class Departement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="CODE", nullable=false,unique = true)
    private String code;
    @Column(name="NAME", nullable=false,unique = true)
    private String name;
    @OneToMany(mappedBy = "departement")
    List<Ville> villes = new ArrayList<>();

    public Departement() {}

    public Departement(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ville> getVilles() {
        return villes;
    }

    public void setVilles(List<Ville> villes) {
        this.villes = villes;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Departement{");
        sb.append("id=").append(id);
        sb.append(", code=").append(code);
        sb.append(", name='").append(name).append('\'');
        sb.append(", villes=").append(villes);
        sb.append('}');
        return sb.toString();
    }
}
