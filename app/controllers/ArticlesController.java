package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import play.data.FormFactory;
import javax.inject.Inject;
import java.util.concurrent.CompletionStage;
import play.libs.concurrent.HttpExecutionContext;
import models.Article;
import models.ArticleRepository;
import java.util.stream.Collectors;
import static play.libs.Json.toJson;


public class ArticlesController extends Controller{
    private final FormFactory formFactory;
    private final ArticleRepository articleRepository;
    private final HttpExecutionContext ec;

    @Inject
    public ArticlesController(FormFactory formFactory, ArticleRepository articleRepository, HttpExecutionContext ec) {
        this.formFactory = formFactory;
        this.articleRepository = articleRepository;
        this.ec = ec;
    }

    public Result index() {
        return ok(views.html.index.render());
    }

    public CompletionStage<Result> addArticle() {
        Article article = formFactory.form(Article.class).bindFromRequest().get();
        System.out.println("------------------------------------"+article);
        return articleRepository.add(article).thenApplyAsync(p -> {
            return redirect(routes.ArticlesController.index());
        }, ec.current());
    }

    public CompletionStage<Result> getArticle() {
        return articleRepository.list().thenApplyAsync(articleStream -> {
            return ok(toJson(articleStream.collect(Collectors.toList())));
        }, ec.current());
    }
//    public Result index(int article_id) {
//        try {
//            if(article_id <= 0) {
//                throw new Exception("Not a valid articles!!");
//            }
//
//        } catch(Exception ex) {
//            return ok(ex.getMessage());
//        }
//    }
}
