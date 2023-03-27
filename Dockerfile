FROM gradle:8.0.2-jdk17

WORKDIR /app

COPY /app .

RUN gradle installDist

CMD ./build/install/app/bin/app