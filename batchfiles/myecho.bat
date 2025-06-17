rem curl -X POST http://localhost:8080/model/echo -d "Hello there" -w "\nHTTP status: %{http_code}\n"
curl -X POST http://localhost:8080/model/echo -d "Hello Echo Service"