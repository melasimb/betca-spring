package es.upm.miw.persistence.jpa.daos.library;

import es.upm.miw.TestConfig;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class LibraryIT {

    @Autowired
    private DaosLibraryService daosLibraryServiceIntegrationTest;

    @Autowired
    private BookDao bookDao;

    @Autowired
    private AuthorDao authorDao;

    @Autowired
    private StyleDao styleDao;

    @BeforeEach
    public void populate() {
        daosLibraryServiceIntegrationTest.seedDb();
    }

    @Test
    public void testPopulate() {
        assertTrue(3 == bookDao.count());
    }

    @Test
    public void testFindByStyle() {
        assertEquals(2, authorDao.findByStyle(styleDao.findByNameIgnoreCase("Infantil")).size());
    }

    @Test
    public void testFindNameByStyleName() {
        assertEquals(2, authorDao.findNameByStyleName("Infantil").size());
        assertArrayEquals(new String[]{"Jesús", "Cris"}, authorDao.findNameByStyleName("Infantil").toArray());
    }

    @Test
    public void testFindDistinctNameByAnyBook() {
        assertEquals(3, authorDao.findDistinctNameByAnyBook().size());
        assertArrayEquals(new String[]{"Ana", "Cris", "Jesús"}, authorDao.findDistinctNameByAnyBook().toArray());
    }

    @Test
    public void testFindNameByThemeName() {
        assertEquals(2, authorDao.findNameByThemeName("Suspense").size());
        assertArrayEquals(new String[]{"Cris", "Ana"}, authorDao.findNameByThemeName("Suspense").toArray());
    }

    @AfterEach
    public void deleteAll() {
        daosLibraryServiceIntegrationTest.deleteDb();
    }

}
