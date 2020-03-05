import gzip
import json
import random

from .models import Movie, MovieView, ViewCategory, MovieLike, Sentiment


def _load_movies():
    with gzip.open("files/movie_ids_03_01_2020.json.gz", "rb") as movies:
        for row in movies.readlines():
            movie = json.loads(row.decode("utf-8").rstrip())
            yield Movie(_id=movie["id"], title=movie["original_title"])


movies = [m for m in _load_movies()]
available_view_cats = [v for v in ViewCategory]
available_sentiments = [s for s in Sentiment]


def generate_view():
    movie = movies[random.randint(0, len(movies) - 1)]
    return MovieView(
        _id=movie._id,
        title=movie.title,
        view_category=available_view_cats[
            random.randint(0, len(available_view_cats) - 1)
        ],
    )


def generate_like():
    movie = movies[random.randint(0, len(movies) - 1)]
    return MovieLike(
        _id=movie._id,
        sentiment=available_sentiments[random.randint(0, len(available_sentiments) - 1)],
        score=random.randint(0, 5),
    )