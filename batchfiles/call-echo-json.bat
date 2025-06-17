@echo off
REM Send JSON string to the /model/echo endpoint

curl -X POST http://localhost:8080/model/echo ^
     -H "Content-Type: application/json" ^
     -d "{\"message\": \"Hello from JSON batch call!\"}" ^
     -w "\nHTTP status: %%{http_code}\n"
