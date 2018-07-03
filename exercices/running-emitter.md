# Lancer votre propre émetteur de messages

Un émetteur de messages d'exemple est fourni dans le cadre des exercices de ce cours.

Si vous avez besoin de le lancer sur votre machine, Docker doit être installé, puis:

```bash
cd simple-emitter && docker build -t simple-emitter .
docker run -d simple-emitter
```
