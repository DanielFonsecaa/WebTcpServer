
<br/>
<div align="center">

<h3 align="center">Web Tcp Server</h3>
<p align="center">
Creation of a Simple Web Tcp Server


  


</p>
</div>

## About The Project

This project involves creating a simple web server using TCP that serves content to a local host browser. 

During the class, there was some misunderstanding regarding the assignment, so I initially created an HTTP server instead of the intended TCP-based server. That version is still there.
## Usage

The server is designed to handle three specific types of requests:

HTML Content:

When navigating to
```
localhost:8081/index.html

```
 localhost:8081/index.html, the server responds by sending the specified HTML content.

Image Content: 

For requests to 
```
localhost:8081/logo.png 
```

the server delivers the PNG image file.

Error Handling:

Any other requests, including misspelled or unsupported URLs, will result in a 404 error indicating that the requested resource could not be found.
