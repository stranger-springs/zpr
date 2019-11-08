#Requierments
- docker-compose
- build-essential

To build database run:
`docker-compose up`

To run application add VM option to run configuration:
`-Djava.library.path="./native/linux_x86_64"`