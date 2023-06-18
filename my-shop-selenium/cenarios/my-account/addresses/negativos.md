
### Cadastrar endereço sem preencher os campos obrigatórios [:arrow_right:](http://www.automationpractice.pl/index.php?controller=address)

>**DADO** que eu esteja na tela de endereços <br>
>**E** e acesse o cadastro de um novo endereço <br>
>**E** não preencha os campos obrigatórios <br>
>**QUANDO** clicar no botão Save <br>
>**ENTÃO** devo visualizar uma mensagem de erro para cada campo obrigatório

### Cadastrar endereço duplicando apelido [:arrow_right:](http://www.automationpractice.pl/index.php?controller=address)

>**DADO** que eu esteja na tela de endereços <br>
>**E** e acesse o cadastro de um novo endereço <br>
>**E** preencha todos os campos obrigatórios <br>
>**E** preencha o campo "Alias" com um valor já existente <br>
>**QUANDO** clicar no botão Save <br>
>**ENTÃO** devo visualizar uma mensagem de erro para o campo "Alias"