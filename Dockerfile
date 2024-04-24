FROM openjdk:17

WORKDIR /app

COPY target/wishlist-0.0.1-SNAPSHOT.jar /app/wishlist.jar

CMD ["java", "-jar", "wishlist.jar"]
