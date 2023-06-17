
### Adicionar 0 produto ao carrinho [:arrow_right:](http://www.automationpractice.pl/index.php?id_product=1&controller=product)

>**DADO** que eu esteja na tela de detalhes do produto <br>
>**E** informe uma quantidade 0 <br>
>**QUANDO** clicar no botão Add to cart <br>
>**ENTÃO** o produto não deve ser adicionado ao carrinho
>**E** deve ser exibida uma mensagem de erro

### Adicionar produto sem quantidade [:arrow_right:](http://www.automationpractice.pl/index.php?id_product=1&controller=product)

>**DADO** que eu esteja na tela de detalhes do produto <br>
>**E** não informe uma quantidade <br>
>**QUANDO** clicar no botão Add to cart <br>
>**ENTÃO** o produto não deve ser adicionado ao carrinho <br>
>**E** deve ser exibida uma mensagem de erro

### Adicionar mais produtos do que em estoque [:arrow_right:](http://www.automationpractice.pl/index.php?id_product=1&controller=product)

>**DADO** que eu esteja na tela de detalhes do produto <br>
>**E** informe uma quantidade maior que o estoque <br>
>**QUANDO** clicar no botão Add to cart <br>
>**ENTÃO** o produto não deve ser adicionado ao carrinho <br>
>**E** deve ser exibida uma mensagem de erro