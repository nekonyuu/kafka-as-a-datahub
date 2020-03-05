import json
import time
import random

import faust

from .models import MovieView, MovieLike
from .source import movies, generate_view, generate_like
     

app = faust.App('movies-views-injector', broker='kafka://localhost')

views_topic = app.topic('views', key_type=str, value_type=MovieView)
likes_topic = app.topic('likes', key_type=str, value_type=MovieLike)

@app.timer(interval=0.05)
async def view_sender(app):
    view = generate_view()
    await views_topic.send(
        key=str(view._id),
        value=view
    )


@app.timer(interval=0.05)
async def like_sender(app):
    like = generate_like()
    await likes_topic.send(
        key=str(like._id),
        value=like
    )

