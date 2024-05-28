A Wallet Management web app, using Springboot, Thymeleaf and MySQL.

I am also going to include my server's configuration in order to be able to replicate the project in the future without
having to endure and go through from what I did again. It was painful.

---

## Server configuration

Tools needed for the application: Maven, JDK (get the defaul-jdk from ubuntu), Tomcat, PostgreSQL.
Tools for the server: Apache2 and Certbot (for the certification for your SSL connection).

Since I am using Cloudflare as a DNS, that means the client connects securely (https) to Cloudflare first before reaching the server. The SSL is Strict, which means both client-cloudflare and cloudflare-server communications are over https and not http.

Apache2 handles the requests and internally, between tomcat and apache2 the communication is over http. You will only need SSL and the certifications on apache2 configurations.

That means, keep your springboot application clean from anything that has to do with SSL and the script or hrefs should be done over http. Only your apache2 should have ssl configuration.
