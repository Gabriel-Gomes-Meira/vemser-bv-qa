
Validar atualização de endereço

DADO que eu tenha um endereço cadastrado
E informe o id do endereço cadastrado
E esteja autenticado
E infome novos dados válidos
QUANDO eu atualizar o endereço
ENTÃO deve retornar o endereço atualizado
E a alteração deve ser persistido.
____________________________________________________________________________________

Validar falha na atualização de endereço com id inválido

DADO que eu informe o id de um endereço inexistente
E esteja autenticado
E infome novos dados válidos
QUANDO eu atualizar o endereço
ENTÃO deve retornar o status 404.