
### Trocar para senha fraca [:arrow_right:](http://www.automationpractice.pl/index.php?controller=identity)

>**DADO** que eu esteja na tela de minha conta <br>
>**E** acesse a visualização dos dados pessoais <br>
>**E** preencha todos os campos obrigatórios com os dados atuais <br>
>**E** preencha o campo New Password com uma senha fraca e confirme <br>
>**QUANDO** clicar no botão Save <br>
>**ENTÃO** devo receber a mensagem de erro


### Trocar senha sem confirmar [:arrow_right:](http://www.automationpractice.pl/index.php?controller=identity)

>**DADO** que eu esteja na tela de minha conta <br>
>**E** acesse a visualização dos dados pessoais <br>
>**E** preencha todos os campos obrigatórios com os dados atuais <br>
>**E** preencha o campo New Password com uma senha nova 
>**E** não confirme <br>
>**QUANDO** clicar no botão Save <br>
>**ENTÃO** devo receber a mensagem de erro