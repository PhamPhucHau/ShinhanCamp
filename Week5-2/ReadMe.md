
# Spring Data JPA hoạt động
![](https://github.com/PhamPhucHau/ShinhanCamp/blob/main/Week5-2/SpringJPADiagram.jpg)
# ORM: Là giải pháp giúp ánh xạ các class POJO với các Entity trong Database thông qua các ORM vendor như Hibernate... 
#  JPA: Java Persistence Api: Nó là nguyên lý, kiến trúc giúp ánh xạ trực tiếp và quản lý truy vấn các lớp POJO trong java tương ứng với các entity trong database thông qua các annotation=> Giúp cho việc config đỡ cực hơn và quản lý được tốt hơn.
# Hibernate là một triển khai của ORM và JPA giúp cho việc ánh xạ các đối tượng thông qua các configuration và cung cấp cho người dùng các đối tượng cơ bản để thao tác với database thông qua các biến transaction, session, query...
# Spring Data JPA: Là một sự trừu tượng giúp tự tạo ra các DAO giúp truy vấn các đối tượng khi có đối tượng nào triển khai nó.
# Data Flow
Service gọi tới tầng repository.
Tầng Repository sẽ gọi tới các hàm quen thuộc bao gồm CRUD và một số câu query đơn giản trên đối tượng đã được triển khai bằng JpaRepository. 
Tiếp đến các câu lệnh CRUD sẽ thông qua  Spring Data JPA để sinh ra code để truy cập vào database và thực hiện các chức năng. Nhưng trước khi truy cập vào được nó phải thông qua Hibernate để có thể mapping được các lớp java với các entity trong databse thông qua các annotation và nhờ các annotation và application.properties hoặc application.yml này sẽ sinh ra code xml để có thể kết nối với JDBC interface, 
Hibernate sẽ sinh ra code kết nối với database và sử dụng một số JDBC Basic APIs: CommonDataSource,DataSource,PooledConnection,
RowSet,RowSetReader,RowSetWriter... để thực hiện một số chức năng DAO.
Cuối cùng thông qua JDBC Driver để kết nối với cơ sở dữ liệu và thực thi các code mà hibernate sinh ra thông qua JDBC Basic APIs. 
