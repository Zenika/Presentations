package com.zenika.demo.jooq.repository;

import com.zenika.demo.jooq.model.tables.records.ActorRecord;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import static com.zenika.demo.jooq.model.Tables.*;
import java.sql.Timestamp;
import static org.jooq.impl.DSL.*;
/**
 *
 */
@Repository @Transactional
public class ActorRepository {
    @Autowired
    private DSLContext jooq;
    public Result<ActorRecord> findAll() {
        return jooq.selectFrom(ACTOR).fetch();
    }
    public Result<ActorRecord> findByLastNameLike(String lastName) {
        return jooq.selectFrom(ACTOR).where(ACTOR.LAST_NAME.like(lastName)).fetch();
    }
    public ActorRecord findById(Short id) {
        return jooq.selectFrom(ACTOR).where(ACTOR.ACTOR_ID.eq(id)).fetchOne();
    }
    public ActorRecord insert(String firstName, String lastName) {
        return jooq.insertInto(ACTOR, ACTOR.FIRST_NAME, ACTOR.LAST_NAME, ACTOR.LAST_UPDATE)
                .values(val(firstName), val(lastName), currentTimestamp())
                .returning().fetchOne();
//        ActorRecord actorRecord=new ActorRecord(null, firstName, lastName, new Timestamp(System.currentTimeMillis()));
//        jooq.attach(actorRecord);
//        actorRecord.insert();
//        return actorRecord;
    }
    public void deleteById(Short id) {
        jooq.delete(ACTOR).where(ACTOR.ACTOR_ID.eq(id)).execute();
    }
}
