// connect to H2 database
connection = JdbcConnection("jdbc:h2:mem:test", "sa", "sa");

// create a table
connection.execute("create table test (id int primary key, name varchar(255))");

// insert some data
connection.execute("insert into test (id, name) values (1, 'foo'), (2, 'bar'), (3, 'baz')");