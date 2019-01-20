FROM hseeberger/scala-sbt
WORKDIR /RouletteGo
ADD . /RouletteGo
CMD sbt test