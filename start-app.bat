@echo off
java -jar ./artifacts/aqa-shop.jar ^
  --spring.datasource.url=jdbc:postgresql://localhost:5432/app ^
  --spring.datasource.username=app ^
  --spring.datasource.password=pass ^
  --spring.jpa.hibernate.ddl-auto=update ^
  --spring.jpa.show-sql=true ^
  --logging.level.org.hibernate.SQL=DEBUG ^
  --logging.level.org.hibernate.type=TRACE
pause