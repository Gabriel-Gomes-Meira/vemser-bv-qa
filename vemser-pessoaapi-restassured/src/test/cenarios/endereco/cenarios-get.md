
Validar listagem de endereços cadastrados com paginação

DADO que eu esteja autenticado
E não parametrize a paginação
QUANDO eu listar os endereços
ENTÂO deve retornar a paginação de todos os endereços cadastrados com página e tamanho padrão (0, 20).
____________________________________________________________________________________

Validar falha na listagem sem autenticação

DADO que eu não esteja autenticado
QUANDO eu listar os endereços
ENTÃO deve retornar o status 403
E não deve retornar a listagem de endereços.
____________________________________________________________________________________

Validar listagem de endereços por pais

DADO que eu tenha pelo menos um endereço cadastrado com certo país
E esteja autenticado
E parametrize a requisição com o país existente
QUANDO eu listar os endereços por país
ENTÃO deve retornar a listagem de todos os endereços cadastrados com o país informado.
____________________________________________________________________________________

Validar falha na listagem de endereços por pais inexistente

DADO que eu parametrize a requisição com um país inexistente
E esteja autenticado
QUANDO eu listar os endereços por país
ENTÃO deve retornar o status 404
E não deve retornar a listagem de endereços.
____________________________________________________________________________________

Validar listagem de endereços por pessoa

DADO que eu tenha pelo menos um endereço cadastrado com certo pessoa
E esteja autenticado
E parametrize a requisição com o id da pessoa existente
QUANDO eu listar os endereços por pessoa
ENTÃO deve retornar a listagem de todos os endereços cadastrados com o id da pessoa informada.
____________________________________________________________________________________

Validar falha na listagem de endereços por pessoa inexistente

DADO que eu parametrize a requisição com um id de pessoa inexistente
E esteja autenticado
QUANDO eu listar os endereços por pessoa
ENTÃO deve retornar o status 404
E não deve retornar a listagem de endereços.
____________________________________________________________________________________

Validar recuperar endereço por id

DADO que eu informe o id de um endereço cadastrado
E esteja autenticado
QUANDO solicitar tal endereço pelo seu id
ENTÃO devo retornar o endereço cadastrado.
____________________________________________________________________________________

Validar falha na recuperação de endereço por id inexistente

DADO que eu informe o id de um endereço inexistente
E esteja autenticado
QUANDO solicitar tal endereço pelo seu id
ENTÃO deve retornar o status 404
E não deve retornar o endereço.