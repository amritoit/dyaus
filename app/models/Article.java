package models;
import javax.persistence.*;

@Entity
@Table(name="articles")
public class Article {

    @Id
    private long id;

    @Column(name="title")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "id="+id+", name="+name;
    }
}
