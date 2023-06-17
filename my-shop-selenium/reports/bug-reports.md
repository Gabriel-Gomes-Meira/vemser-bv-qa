
### Bug ao adicionar um produto ao carrinho

#### Pré-requisitos
1. Ter um produto cadastrado

#### Passos
1. Acessar a lista de produtos
2. Visualizar detalhes do produto
3. Click no botão "Add to cart"
4. Aguardar a mensagem de sucesso

![image](https://github.com/Gabriel-Gomes-Meira/vemser-bv-qa/blob/main/my-shop-selenium/images/bugs/add_to_cart_16:50_17-06-2023.png?raw=true)
___


### Bug ao adicionar um produto sem quantidade ao carrinho

#### Pré-requisitos
1. Ter um produto cadastrado
2. Limpar campo de quantidade

#### Passos
1. Acessar a lista de produtos
2. Visualizar detalhes do produto
3. Limpar campo de quantidade
4. Click no botão "Add to cart"
5. Aguardar a mensagem de erro

![antes](https://github.com/Gabriel-Gomes-Meira/vemser-bv-qa/blob/main/my-shop-selenium/images/bugs/add_to_cart_without_quantity_17:04_17-06-2023%20(1).png?raw=true)
![depois](https://github.com/Gabriel-Gomes-Meira/vemser-bv-qa/blob/main/my-shop-selenium/images/bugs/add_to_cart_without_quantity_17:04_17-06-2023%20(2).png?raw=true)
___


### Bug Aumentar a quantidade de um produto no carrinho

#### Pré-requisitos
1. Ter um produto no carrinho
2. Ter estoque disponível

#### Passos
1. Acessar a tela do carrinho
2. Aumentar a quantidade do produto
3. Aguardar atualização da quantidade

![antes](https://github.com/Gabriel-Gomes-Meira/vemser-bv-qa/blob/main/my-shop-selenium/images/bugs/increment_quantity_of_product_on_cart_17:28_17-06-2023%20(1).png?raw=true)
![depois](https://github.com/Gabriel-Gomes-Meira/vemser-bv-qa/blob/main/my-shop-selenium/images/bugs/increment_quantity_of_product_on_cart_17:28_17-06-2023%20(2).png?raw=true)
![pos-refresh](https://github.com/Gabriel-Gomes-Meira/vemser-bv-qa/blob/main/my-shop-selenium/images/bugs/increment_quantity_of_product_on_cart_17:28_17-06-2023%20(3).png?raw=true)
___


### Bug Reduzir a quantidade de um produto no carrinho

#### Pré-requisitos
1. Ter um produto no carrinho com quantidade maior que 1

#### Passos
1. Acessar a tela do carrinho
2. Aumentar a quantidade do produto
3. Aguardar atualização da quantidade

![antes](https://github.com/Gabriel-Gomes-Meira/vemser-bv-qa/blob/main/my-shop-selenium/images/bugs/decrement_quantity_of_product_on_cart_17:29_17-06-2023%20(1)%20.png?raw=true)
![depois](https://github.com/Gabriel-Gomes-Meira/vemser-bv-qa/blob/main/my-shop-selenium/images/bugs/decrement_quantity_of_product_on_cart_17:29_17-06-2023%20(2).png?raw=true)
![pos-refresh](https://github.com/Gabriel-Gomes-Meira/vemser-bv-qa/blob/main/my-shop-selenium/images/bugs/decrement_quantity_of_product_on_cart_17:29_17-06-2023%20(3).png?raw=true)
___