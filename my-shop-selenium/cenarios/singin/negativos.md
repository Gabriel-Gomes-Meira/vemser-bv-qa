
### Cadastrar usuário com email já cadastrado [:arrow_right:](http://www.automationpractice.pl/index.php?controller=authentication&back=my-account)

>**DADO** que eu esteja na tela de cadastro de usuário <br>
>**E** preencha com um email de usuários já cadastrado <br>
>**QUANDO** clicar no botão "Create an Account" <br>
>**ENTÃO** devo ver a mensagem uma mensagem de erro

## Cadastrar usuário sem preencher formulário [:arrow_right:](http://www.automationpractice.pl/index.php?controller=authentication&back=my-account)

>**DADO** que eu esteja no formulário de cadastro de usuário <br>
>**E** não preenchi campos obrigatórios <br>
>**QUANDO** clicar no botão "Register" <br>
>**ENTÃO** devo ver a mensagem de erro para cada campo 
