# 1단계: 애플리케이션 빌드 (Gradle 사용)
FROM gradle:8.7-jdk17 AS build
WORKDIR /home/gradle/project
COPY . .
RUN gradle clean bootJar --no-daemon

# 2단계: 실행 이미지 생성 (OpenJDK 기반)
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /home/gradle/project/build/libs/zb-weather-diary.jar ./zb-weather-diary.jar
EXPOSE 8081
CMD ["java", "-jar", "zb-weather-diary.jar"]
