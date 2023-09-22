LOCAL_UID := $(shell id -u)
UNAME_S := $(shell uname -s)

build:
	mvn clean verify

service-env:
	docker compose up --force-recreate --build

