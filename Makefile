.PHONY: run
run:
	make -j 2 run-db run-app

.PHONY: run-db
run-db:
	docker-compose up db

.PHONY: run-app
run-app:
	./gradlew bootRun

.PHONY: npm-install
npm-install:
	./gradlew npm_install
