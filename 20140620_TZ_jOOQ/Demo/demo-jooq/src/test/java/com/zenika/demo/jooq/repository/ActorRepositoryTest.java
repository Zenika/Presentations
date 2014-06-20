package com.zenika.demo.jooq.repository;

import com.zenika.demo.jooq.config.TestConfiguration;
import com.zenika.demo.jooq.model.tables.records.ActorRecord;
import org.jooq.Result;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
public class ActorRepositoryTest {
    
    @Autowired private ActorRepository actorRepository;
    @Test
    public void testFindAll() {
        Result<ActorRecord> actors= actorRepository.findAll();
        assertEquals(200, actors.size());
    }

    @Test
    public void testFindByLastNameLike() {
        Result<ActorRecord> actors= actorRepository.findByLastNameLike("R%");
        assertEquals(2, actors.size());
    }
    @Test
    public void testFindById() {
        ActorRecord actor= actorRepository.findById((short) 9);
        assertEquals("JOE", actor.getFirstName());
        assertEquals("SWANK", actor.getLastName());
    }
    @Test
    public void testInsert() {
        ActorRecord actorRecord= actorRepository.insert("Vincent", "Cassel");
        assertNotNull(actorRecord.getActorId());
        actorRepository.deleteById(actorRecord.getActorId());
    }
    
}
