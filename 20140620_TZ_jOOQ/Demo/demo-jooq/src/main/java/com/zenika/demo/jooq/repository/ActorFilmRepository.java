package com.zenika.demo.jooq.repository;

import com.zenika.demo.jooq.model.Routines;
import org.jooq.DSLContext;
import static org.jooq.impl.DSL.*;
import org.jooq.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import static com.zenika.demo.jooq.model.Tables.*;
import com.zenika.demo.jooq.model.tables.*;
import java.util.List;
import org.jooq.Record4;
import org.jooq.Record5;
import org.jooq.Select;
import org.jooq.SelectHavingStep;
import org.jooq.util.mysql.MySQLDSL;
/**
 *
 */
@Repository
public class ActorFilmRepository {
    @Autowired private DSLContext jooq;
    public List<ActorFilms> getRichestActors() {
        Actor a=ACTOR.as("a");
        FilmActor fa=FILM_ACTOR.as("fa");
        Film f=FILM.as("f");
        final Select<Record5<Short, String, String, Integer, String>> subSelect =
                select(a.ACTOR_ID, a.FIRST_NAME, a.LAST_NAME,
                        count(fa.FILM_ID).as("film_number"),
                        MySQLDSL.groupConcat(f.TITLE).separator(",").as("film_titles"))
                .from(a)
                .join(fa).on(a.ACTOR_ID.eq(fa.ACTOR_ID))
                        .join(f).on(f.FILM_ID.eq(fa.FILM_ID))
                .groupBy(a.ACTOR_ID);
        return jooq.select(subSelect.fields())
                .from(subSelect)
                .orderBy(subSelect.field("film_number").desc())
                .limit(10)
                .fetchInto(ActorFilms.class);
    }
	public Integer getFilmInStock(Integer filmId, Integer storeId) {
		return Routines.filmInStock(jooq.configuration(), filmId, storeId);
	}
}
