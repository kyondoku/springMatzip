# springMatzip

[스프링 라이브러리]

pom.xml -> <dependencies>안에 

아래의 라이브러리들을 추가한다.


https://mvnrepository.com/artifact/org.springframework/spring-core

- maven mybatis
https://mvnrepository.com/artifact/org.mybatis/mybatis

- maven mybatis-spring
https://mvnrepository.com/artifact/org.mybatis/mybatis-spring

- maven hikaricp
https://mvnrepository.com/artifact/com.zaxxer/HikariCP

- maven jackson-databind
https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind/2.11.2

- JBCrypt
https://mvnrepository.com/artifact/org.mindrot/jbcrypt/0.4

- Commons IO
https://mvnrepository.com/artifact/commons-io/commons-io

- Commons FileUpload
https://mvnrepository.com/artifact/commons-fileupload/commons-fileupload/1.4


- web.xml 에 추가

	<filter>
		<filter-name>encodingFilter</filter-name>
		<filter-class>
			org.springframework.web.filter.CharacterEncodingFilter
		</filter-class>
		<init-param>
			<param-name>encoding</param-name>
			<param-value>UTF-8</param-value>
		</init-param>
		<init-param>
			<param-name>forceEncoding</param-name>
			<param-value>true</param-value>
		</init-param>
	</filter>
	<filter-mapping>
		<filter-name>encodingFilter</filter-name>
		<url-pattern>/*</url-pattern>
	</filter-mapping>
