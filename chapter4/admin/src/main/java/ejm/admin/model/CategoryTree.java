package ejm.admin.model;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * @author Ken Finnigan
 */
@Entity
@Table(name = "category")
public class CategoryTree {

    @Id
    private Integer id;

    private String name;

    private String header;

    private Boolean visible;

    @Column(name = "image_path", length = 120)
    private String imagePath;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "parent_id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private Category parent;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "parent")
    private Collection<CategoryTree> children = new HashSet<>();

    private LocalDateTime created = LocalDateTime.now();

    private LocalDateTime updated;

    @Version
    private Integer version = 0;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String isHeader() {
        return header;
    }

    public Boolean getVisible() {
        return visible;
    }

    public String getImagePath() {
        return imagePath;
    }

    public Category getParent() {
        return parent;
    }

    public Collection<CategoryTree> getChildren() {
        return children;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public Integer getVersion() {
        return version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryTree category = (CategoryTree) o;
        return Objects.equals(id, category.id) &&
                Objects.equals(name, category.name) &&
                Objects.equals(header, category.header) &&
                Objects.equals(visible, category.visible) &&
                Objects.equals(imagePath, category.imagePath) &&
                (parent == null ? category.parent == null : Objects.equals(parent.getId(), category.parent.getId())) &&
                Objects.equals(children, category.children) &&
                Objects.equals(created, category.created) &&
                Objects.equals(updated, category.updated) &&
                Objects.equals(version, category.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, header, visible, imagePath, parent == null ? null : parent.getId(), children, created, updated, version);
    }
}
