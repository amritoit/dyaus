package models;

import play.db.jpa.JPAApi;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;
import java.util.stream.Stream;
import models.Article;

import static java.util.concurrent.CompletableFuture.supplyAsync;

/**
 * Provide JPA operations running inside of a thread pool sized to the connection pool
 */
public class JPAPersonRepository implements ArticleRepository {

    private final JPAApi jpaApi;
    private final DatabaseExecutionContext executionContext;

    @Inject
    public JPAPersonRepository(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
        this.jpaApi = jpaApi;
        this.executionContext = executionContext;
    }

    @Override
    public CompletionStage<Article> add(Article article) {
        return supplyAsync(() -> wrap(em -> insert(em, article)), executionContext);
    }

    @Override
    public CompletionStage<Stream<Article>> list() {
        return supplyAsync(() -> wrap(em -> list(em)), executionContext);
    }

    private <T> T wrap(Function<EntityManager, T> function) {
        return jpaApi.withTransaction(function);
    }

    private Article insert(EntityManager em, Article article) {
        em.persist(article);
        return article;
    }

    private Stream<Article> list(EntityManager em) {
        List<Article> articles = em.createQuery("select a from Article a", Article.class).getResultList();
        return articles.stream();
    }
}
