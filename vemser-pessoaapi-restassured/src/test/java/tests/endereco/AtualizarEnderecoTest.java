package tests.endereco;

import client.EnderecoClient;
import client.PessoaClient;
import datafactory.EnderecoDataFactory;
import model.EnderecoModel;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.base.BaseTest;

import java.lang.reflect.Field;

public class AtualizarEnderecoTest extends BaseTest {

    @Test
    @DisplayName("Atualizar endereço")
    public void testAtualizarEndereco() {

        // Dado que eu tenha um endereço cadastrado
        EnderecoModel endereco = CadastrarEnderecoTest.criarEndereco()
                .then().extract().as(EnderecoModel.class);

        // Quando eu atualizar o endereço
        EnderecoModel enderecoAtualizado = EnderecoDataFactory.getEnderecoValida();
        enderecoAtualizado.setIdEndereco(endereco.getIdEndereco());
        enderecoAtualizado.setIdPessoa(endereco.getIdPessoa());
        EnderecoClient.atualizarEndereco(endereco.getIdEndereco(), enderecoAtualizado)
                .then()
                    .statusCode(200);

        // Então o endereço deve ser atualizado e persistido
        EnderecoModel enderecoPersistido = EnderecoClient.buscarEnderecoPorId(endereco.getIdEndereco())
                .then()
                    .statusCode(200)
                    .extract().as(EnderecoModel.class);

        for (Field field : endereco.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                Assertions.assertEquals(field.get(enderecoAtualizado), field.get(enderecoPersistido));
            } catch (IllegalAccessException e) {
                Assertions.fail();
            }
        }

        // Limpar masssa de ados
        EnderecoClient.deletarEndereco(endereco.getIdEndereco());
        PessoaClient.deletarPessoa(endereco.getIdPessoa());
    }

    @Test
    @DisplayName("Atualizar endereço com id inválido")
    public void testAtualizarEnderecoComIdInvalido() {

        // Dado que eu informe um endereço inexistente
        Integer idInvalido = -1000;

        // Quando eu atualizar o endereço com id inválido
        EnderecoClient.atualizarEndereco(idInvalido, EnderecoDataFactory.getEnderecoValida())
                .then()
                    .statusCode(404)
                    .body("message", Matchers.equalTo("{idEndereco} nao encontrado"));
    }
}
