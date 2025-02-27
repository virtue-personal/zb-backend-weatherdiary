# OpenJDK 17 이미지를 기반으로 합니다.
FROM openjdk:17-jdk-slim

# JAR 파일을 /app 디렉토리에 복사합니다.
COPY build/libs/zb-weather-diary.jar /app/zb-weather-diary.jar

# 컨테이너가 실행될 때 애플리케이션을 실행합니다.
CMD ["java", "-jar", "/app/zb-weather-diary.jar"]

# 애플리케이션이 사용하는 포트를 열어줍니다.
EXPOSE 8081
