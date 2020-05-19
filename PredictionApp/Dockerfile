FROM anapsix/alpine-java
COPY target/PredictionApp-0.1.0.jar /home/prediction.jar
CMD ["java", "-jar", "/home/prediction.jar", "http://rbkapp:8060/dollarsMax", "http://weatherapp:8070/weather"]