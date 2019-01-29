$ ->
  $.get "/articles", (articles) ->
    $.each articles, (index, article) ->
      $("#articles").append $("<li>").text article.name