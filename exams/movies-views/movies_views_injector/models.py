from faust import Record
import json
from enum import Enum


class Sentiment(str, Enum):
    Like = "like"
    Dislike = "dislike"


class ViewCategory(str, Enum):
    StartOnly = "start_only"
    Half = "half"
    Full = "full"


class Movie(Record):
    _id: int
    title: str


class MovieView(Record):
    _id: int
    title: str
    view_category: ViewCategory


class MovieLike(Record):
    _id: int
    sentiment: Sentiment
    score: int