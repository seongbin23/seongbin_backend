# Build Stage
FROM openjdk:17-alpine AS builder

# 필요한 파일 복사
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY libs libs
COPY src src

# gradlew 실행 권한 부여
RUN chmod +x ./gradlew

# Gradle 빌드 (테스트 포함)
RUN ./gradlew bootJar --no-daemon

# Run Stage
FROM openjdk:17-alpine

# 컨테이너 포트
EXPOSE 8081

# 빌드된 JAR 복사
COPY --from=builder build/libs/*.jar app.jar

# 실행
ENTRYPOINT ["java", "-jar", "app.jar"]
