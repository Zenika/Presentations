package com.zenika.demo.jooq.repository;

import com.zenika.demo.jooq.config.TestConfiguration;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author gquintana
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
public class ActorFilmRepositoryTest {
    @Autowired private ActorFilmRepository actorFilmRepository;
    @Test
    public void testGetRichestActors() {
        List<ActorFilms> actorFilms = actorFilmRepository.getRichestActors();
        assertEquals(10, actorFilms.size());
        assertEquals("GINA", actorFilms.get(0).getFirstName());
        assertEquals("DEGENERES", actorFilms.get(0).getLastName());
        assertEquals(42, actorFilms.get(0).getFilmNumber());
    }
	@Test
	public void testGetFilmInStock() {
		Integer films = actorFilmRepository.getFilmInStock(1,1);
		assertEquals(4, films.intValue());
	}

}
