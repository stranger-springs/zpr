#Requierments
- docker-compose
- build-essential

To build database run:
`docker-compose up`

To run client module add VM option to run configuration:
`-Djava.library.path="./client/native/linux_x86_64"`