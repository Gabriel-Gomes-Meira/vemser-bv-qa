
### Logar com credencias inexistents [:arrow_right:](http://www.automationpractice.pl/index.php?controller=authentication&back=my-account)

>**DADO** que eu esteja na tela de login<br>
>**E** informe credenciais não cadastradas <br>
>**QUANDO** clicar no botão Sign in <br>
>**ENTÃO** devo ver a mensagem de erro "Authentication failed."

### Logar com email em branco [:arrow_right:](http://www.automationpractice.pl/index.php?controller=authentication&back=my-account)

>**DADO** que eu esteja na tela de login<br>
> **E** informe credenciais em branco <br>
> **QUANDO** clicar no botão Sign in <br>
> **ENTÃO** devo ver a mensagem de erro "An email address required."

### Logar com senha em branco [:arrow_right:](http://www.automationpractice.pl/index.php?controller=authentication&back=my-account)

>**DADO** que eu esteja na tela de login<br>
>**E** informe email válido e senha em branco <br>
>**QUANDO** clicar no botão Sign in <br>
>**ENTÃO** devo ver a mensagem de erro "Password is required."