FROM openjdk:22-jdk-slim AS build

RUN apt-get update && apt-get install -y --no-install-recommends \
    wget \
    unzip \
    && rm -rf /var/lib/apt/lists/*

ENV MAVEN_VERSION=4.0.0-beta-3
RUN wget https://dlcdn.apache.org/maven/maven-4/4.0.0-beta-3/binaries/apache-maven-4.0.0-beta-3-bin.zip -O /tmp/maven.zip \
    && unzip /tmp/maven.zip -d /opt/ \
    && mv /opt/apache-maven-4.0.0-beta-3 /opt/maven \
    && rm /tmp/maven.zip

ENV MAVEN_HOME=/opt/maven
ENV PATH=$MAVEN_HOME/bin:$PATH

WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
COPY testng.xml .
RUN mvn clean test -DsuiteXmlFile=testng.xml
CMD ["mvn", "test", "-DsuiteXmlFile=testng.xml"]