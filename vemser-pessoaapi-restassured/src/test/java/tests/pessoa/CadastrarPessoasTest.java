package tests.pessoa;

import client.PessoaClient;
import datafactory.PessoaDataFactory;
import model.PessoaModel;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.base.BaseTest;

public class CadastrarPessoasTest extends BaseTest {


    @Test
    @DisplayName("Deve cadastrar pessoa com sucesso")
    public void testCadastrarPessoaComSucesso() {

        PessoaModel pessoa = PessoaDataFactory.getPessoaValida();
        PessoaModel pessoaCriada = PessoaClient.cadastrarPessoa(pessoa)
                .then()
                    .statusCode(201) // Status Code padrão para POST bem sucedido
                    .extract()
                        .as(PessoaModel.class);;

        Assertions.assertEquals(pessoa.getNome(), pessoaCriada.getNome());
        Assertions.assertEquals(pessoa.getCpf(), pessoaCriada.getCpf());
        Assertions.assertEquals(pessoa.getEmail(), pessoaCriada.getEmail());
        Assertions.assertEquals(pessoa.getDataNascimento(), pessoaCriada.getDataNascimento());
        Assertions.assertTrue(pessoaCriada.getIdPessoa() > 0);

        PessoaModel pessoaBuscada = PessoaClient.buscarPessoaPorCpf(pessoa.getCpf())
                .then()
                .statusCode(200)
                .extract().as(PessoaModel.class);
        Assertions.assertNotNull(pessoaBuscada);

        // Limpar dados
        PessoaClient.deletarPessoa(pessoaCriada.getIdPessoa());
    }

    @Test
    @DisplayName("Não deve criar pessoa com campos obrigatórios vazios")
    public void testFalharCadastrarPessoaSemCamposObrigatorios() throws NoSuchFieldException, IllegalAccessException {
        String[] requiredFields = {
                "nome",
                "dataNascimento",
                "cpf",
                "email"
        };

        for (String field : requiredFields) {
            PessoaModel pessoa = PessoaDataFactory.getPessoaValida(); // Sempre buscando uma pessoa válida
            java.lang.reflect.Field pessoaField = pessoa.getClass().getDeclaredField(field);
            pessoaField.setAccessible(true);
            pessoaField.set(pessoa, "");  // Dado que forme uma pessoa com quaisquer um dos campos obrigatórios vazios

            String emptyErrorMessage = String.format("%s: must not be blank", field);
            String nullErrorMessage = String.format("%s: must not be null", field);

            // Quando eu tento cadastrar essa pessoa
            PessoaClient.cadastrarPessoa(pessoa).then()
                    .assertThat()
                    // Então o sistema deve retornar um erro de campo obrigatório
                    .statusCode(400)
                    .body("errors",
                            Matchers.hasItem(
                                    Matchers.either(Matchers.equalTo(emptyErrorMessage))
                                    .or(Matchers.equalTo(nullErrorMessage))));
        }
    }
}
