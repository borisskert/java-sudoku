# ----------------------------------------------------------------------------------------------------------------------
# Compiling image
# ----------------------------------------------------------------------------------------------------------------------
FROM ubuntu:20.04 as compiler

MAINTAINER borisskert <boris.skert@gmail.com>
ENV DEBIAN_FRONTEND	noninteractive

RUN apt-get update && \
    apt-get install -y openjdk-11-jdk \
                       maven

COPY . /usr/local/src
WORKDIR /usr/local/src

RUN mvn package

# ----------------------------------------------------------------------------------------------------------------------
# Runtime image
# ----------------------------------------------------------------------------------------------------------------------
FROM ubuntu:20.04

RUN apt-get update && \
    apt-get install -y openjdk-11-jre

COPY --from=compiler /usr/local/src/target/sudoku.jar /usr/local/lib/sudoku.jar

ENTRYPOINT ["java", "-Dfile.encoding=UTF-8", "-jar", "/usr/local/lib/sudoku.jar"]
