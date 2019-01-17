package ejm.admin.model;

import java.time.LocalDateTime;

import org.fest.assertions.Assertions;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

/**
 * @author Ken Finnigan
 */
public class CategoryTest {

    @Test
    public void categoriesAreEqual() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        Category cat1 = createCategory(1, "Top", Boolean.TRUE, now);
        Category cat2 = createCategory(1, "Top", Boolean.TRUE, now);

        Assertions.assertThat(cat1).isEqualTo(cat2);
        assertThat(cat1.equals(cat2)).isTrue();
        assertThat(cat1.hashCode()).isEqualTo(cat2.hashCode());
    }

    @Test
    public void categoriesAreNotEqual() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        Category cat1 = createCategory(2, "Top", Boolean.TRUE, now);
        Category cat2 = createCategory(1, "Top", Boolean.TRUE, now);

        Assertions.assertThat(cat1).isNotEqualTo(cat2);
        assertThat(cat1.equals(cat2)).isFalse();
        assertThat(cat1.hashCode()).isNotEqualTo(cat2.hashCode());
    }

    @Test
    public void categoryModification() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        Category cat1 = createCategory(1, "Top", Boolean.TRUE, now);
        Category cat2 = createCategory(1, "Top", Boolean.TRUE, now);

        Assertions.assertThat(cat1).isEqualTo(cat2);
        assertThat(cat1.equals(cat2)).isTrue();
        assertThat(cat1.hashCode()).isEqualTo(cat2.hashCode());

        cat1.setVisible(Boolean.FALSE);

        Assertions.assertThat(cat1).isNotEqualTo(cat2);
        assertThat(cat1.equals(cat2)).isFalse();
        assertThat(cat1.hashCode()).isNotEqualTo(cat2.hashCode());
    }

    @Test
    public void categoriesWithIdenticalParentAreEqual() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        Category parent1 = createParentCategory(1, "Top", LocalDateTime.now());
        Category cat1 = createCategory(5, "Top", Boolean.TRUE, now, parent1);
        Category cat2 = createCategory(5, "Top", Boolean.TRUE, now, parent1);

        Assertions.assertThat(cat1).isEqualTo(cat2);
        assertThat(cat1.equals(cat2)).isTrue();
        assertThat(cat1.hashCode()).isEqualTo(cat2.hashCode());
    }

    //Uncomment @Test when code fixed from reading chapter 4
    //@Test
    public void categoriesWithIdenticalParentIdAreEqual() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        Category parent1 = createParentCategory(1, "Top", now);
        Category parent2 = createParentCategory(1, "Tops", now);
        Category cat1 = createCategory(5, "Top", Boolean.TRUE, now, parent1);
        Category cat2 = createCategory(5, "Top", Boolean.TRUE, now, parent2);

        Assertions.assertThat(cat1).isEqualTo(cat2);
        assertThat(cat1.equals(cat2)).isTrue();
        assertThat(cat1.hashCode()).isEqualTo(cat2.hashCode());
    }

    @Test
    public void categoriesWithDifferentParentNotEqual() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        Category parent1 = createParentCategory(1, "Top", now);
        Category parent2 = createParentCategory(2, "Top", now);
        Category cat1 = createCategory(5, "Top", Boolean.TRUE, now, parent1);
        Category cat2 = createCategory(5, "Top", Boolean.TRUE, now, parent2);

        Assertions.assertThat(cat1).isNotEqualTo(cat2);
        assertThat(cat1.equals(cat2)).isFalse();
        assertThat(cat1.hashCode()).isNotEqualTo(cat2.hashCode());
    }

    private Category createParentCategory(Integer id, String name, LocalDateTime created) {
        return createCategory(id, name, Boolean.TRUE, created);
    }

    private Category createCategory(Integer id, String name, Boolean visible, LocalDateTime created) {
        return createCategory(id, name, visible, created, null);
    }

    private Category createCategory(Integer id, String name, Boolean visible, LocalDateTime created, Category parent) {
        return new TestCategoryObject(id, name, null, visible, null, parent, created, null, 1);
    }
}
