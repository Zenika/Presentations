# ReadMe Demo

# Etape 1: Génération de code

1. Montrer la configuration Maven
2. Lancer Maven
3. Parcourir le code généré
  * Tables: liste des tables
  * Routines
  * Enums
  * Actor Table: liste des colonnes de la table

# Etape 2: Configuration Spring et CRUD

1. Montrer `DataSourceConfiguration` et `JooqConfiguration`
2. Editer `ActorRepository`
    - findAll: `dslContext.selectFrom(ACTOR).fetch()`
    - findById: `dslContext.selectFrom(ACTOR).where(ACTOR.ACTOR_ID.eq(id)).fetchOne()`
    - findByName: `dslContext.selectFrom(ACTOR).where(ACTOR.LAST_NAME.like(lastName)).fetch()`
    - insert: 2 variantes
        - DSL
        jooq.insertInto(ACTOR, ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
            .values(val(firstName), val(lastName))
            .returning().fetchOne()
        - UpdatableReccord
        ActorRecord actorRecord=new ActorRecord(null, firstName, lastName, null);
        jooq.attach(actorRecord);
        actorRecord.insert();
        return actorRecord;
    - delete: pour que le TU passe: `jooq.delete(ACTOR).where(ACTOR.ACTOR_ID.eq(id)).execute()`
3. Execture et montrer les logs

# Etape 3: Requêtage avancé

1. Commencer par

        Actor a=ACTOR.as("a");
        FilmActor fa=FILM_ACTOR.as("fa");
        return jooq.select(a.ACTOR_ID, a.FIRST_NAME, a.LAST_NAME, 
                count(fa.FILM_ID).as("film_number"))
            .from(a)
            .join(fa).on(a.ACTOR_ID.eq(fa.ACTOR_ID))
            .groupBy(a.ACTOR_ID)

2. Extraire la sous requête et la réinjecter

        Actor a=ACTOR.as("a");
        FilmActor fa=FILM_ACTOR.as("fa");
        final Select<Record4<Short, String, String, Integer>> subSelect = 
                select(a.ACTOR_ID, a.FIRST_NAME, a.LAST_NAME, 
                        count(fa.FILM_ID).as("film_number"))
                .from(a)
                .join(fa).on(a.ACTOR_ID.eq(fa.ACTOR_ID))
                .groupBy(a.ACTOR_ID);
        return jooq.select(subSelect.fields())
                .from(subSelect)
                .orderBy(subSelect.field("film_number").desc())
                .limit(10)
                .fetchInto(ActorFilms.class);

3. Ajouter la jointure sur film et le champ supplémentaire

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

