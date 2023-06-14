
Validar deleção de endereço

DADO que eu tenha um endereço cadastrado
E esteja autenticado
QUANDO eu deletar o endereço
ENTÃO deve retornar o status 204
E o endereço não deve estar mais acessível.
____________________________________________________________________________________

Validar falha na deleção de endereço com id inválido

DADO que eu informe o id de um endereço inexistente
E esteja autenticado
QUANDO eu deletar o endereço
ENTÃO deve retornar o status 404.