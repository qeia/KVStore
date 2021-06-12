# KVStore
Simple KVStore.
First build and package the code, then run for master:

`java  -jar target/KVStore-1.0-SNAPSHOT.jar server config.yml`

and for replica: 

`java -jar target/KVStore-1.0-SNAPSHOT.jarerver replica.yml`

To test, do:

`curl -H "Content-type: application/json" -XPOST http://localhost:4455/set/key -d ‘“value”’`


`curl -H “Accept: application/json” http://localhost:4466/get/key`
