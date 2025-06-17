@echo off
REM Send plain text string to the /model/echo endpoint

curl -X POST http://localhost:8080/model/echo ^
     -H "Content-Type: text/plain" ^
     -d "Hello from plain text batch call!" ^
     -w "\nHTTP status: %%{http_code}\n"
