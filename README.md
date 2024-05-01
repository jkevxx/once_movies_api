# MOVIES API REST

This is a simple API REST about movies using SPRING BOOT

---
Docker configuration:

- Download [posgres](https://hub.docker.com/_/postgres) image
```bash
docker pull postgres
```

- Container creation
```bash
docker run --name my-postgres -e POSTGRES_USER=root -e POSTGRES_PASSWORD=admin -e POSTGRES_DB=once_movies -p 5432:5432 -d 9a510ccf1de4
```

- Enter to the database
```bash
psql -U root --password --db once_movies
```

video reference [here](https://youtu.be/hVrKX2RtigQ)