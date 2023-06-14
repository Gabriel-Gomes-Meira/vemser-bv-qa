package tests.pessoa;

import client.PessoaClient;
import datafactory.PessoaDataFactory;
import io.restassured.response.Response;
import model.PessoaModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.base.BaseTest;

import static org.hamcrest.Matchers.equalTo;

public class DeletarPessoaTest extends BaseTest {

    @Test
    @DisplayName("Deve deletar pessoa com sucesso")
    public void testDeletarPessoaComSucesso() {
        // Dado que eu tenho uma pessoa cadastrada
        PessoaModel pessoaCriada = PessoaClient.cadastrarPessoa(PessoaDataFactory.getPessoaValida())
                .then().extract().as(PessoaModel.class);

        // Quando eu deleto essa pessoa
        PessoaClient.deletarPessoa(pessoaCriada.getIdPessoa())
                .then()
                .statusCode(204);

        // Então a pessoa é deletada com sucesso
        Response response = PessoaClient.buscarPessoaPorCpf(pessoaCriada.getCpf());

        response.then().statusCode(404);
        Assertions.assertTrue(response.getBody().asString().isEmpty());
    }

    @Test
    @DisplayName("Deve falhar ao tentar deletar pessoa com id inválido")
    public void testFalharDeletarComIdInvalido() {
        // Dado que não tenha pessoa com o id negativo cadastrada
        Integer idPessoa = -1;

        // Quando eu tento deletar essa pessoa
        PessoaClient.deletarPessoa(idPessoa)
                .then()
                .statusCode(404)
                .body("message", equalTo("ID da pessoa nao encontrada"));

        // Dado que não tenha pessoa com o id em caracteres
        // Quando eu tento deletar essa pessoa
        PessoaClient.deletarPessoa("___")
                .then()
                .statusCode(400);
    }
}
