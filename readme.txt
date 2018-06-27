

1./ Template project: yarn install xong chay yarn start
2./ webapp: 
	+ cài yarn 
	+ xong chạy yarn install
	+ start web: yarn starn
3./ server:
	+ cài postgresql voi DB name: nextogix/user/pass
	+ change config trong:../nextLogix\nextlogix-center\src\main\resources\config\application-dev.yml
	+ chạy mvnw.bat
	
-- DROP table and run liquibase update database structure
mvn liquibase:dropAll liquibase:update

http://localhost:9010/#/admin/user-group
pass admin admin
http://localhost:9010/#/admin/elasticsearch-reindex
thêm trong db thì chạy link này
để nó reindex lên