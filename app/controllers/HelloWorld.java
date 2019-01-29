package controllers;

import play.mvc.Result;
import play.mvc.Controller;

public class HelloWorld extends Controller {
    public Result index() {
        return ok("Hello World!!");
    }

    public Result indexName(String name) {
        return ok("Hello "+name + "!!");
    }

    public Result indexNameAge(String name, int age) {
        return ok("Hello "+name + ", you are of age "+age+"!!");
    }
}