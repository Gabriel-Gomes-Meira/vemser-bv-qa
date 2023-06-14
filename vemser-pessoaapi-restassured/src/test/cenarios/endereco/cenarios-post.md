Validar criação de endereço

DADO que eu informe um endereço válido
E esteja autenticado
E informe o id de uma pessoa cadastrada
QUANDO eu criar o endereço
ENTÃO o mesmo deve ser persistido.
____________________________________________________________________________________

Validar falha na criação de endereço com pessoa inexistente

DADO que eu informe um endereço válido
E esteja autenticado
E informe o id de uma pessoa inexistente
QUANDO eu criar o endereço
ENTÃO deve retornar o status 404
E não deve ser persistido.

