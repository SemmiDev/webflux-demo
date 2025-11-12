.PHONY: run compose

run:
	mvn spring-boot:run

compose:
	podman-compose up -d --build
