package models;

import com.google.inject.ImplementedBy;

import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

@ImplementedBy(JPAPersonRepository.class)
public interface ArticleRepository {

    CompletionStage<Article> add(Article article);

    CompletionStage<Stream<Article>> list();
}
