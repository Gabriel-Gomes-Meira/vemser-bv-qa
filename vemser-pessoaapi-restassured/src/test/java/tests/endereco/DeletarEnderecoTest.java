package tests.endereco;

import client.EnderecoClient;
import client.PessoaClient;
import model.EnderecoModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.base.BaseTest;

import java.util.UUID;

public class DeletarEnderecoTest extends BaseTest {

    @Test
    @DisplayName("Deve deletar um endereço com sucesso")
    public void testDeletarEnderecoComSucesso() {

        // Dado que eu tenha um endereco cadastrado
        EnderecoModel endereco = CadastrarEnderecoTest.criarEndereco()
                .then().extract().as(EnderecoModel.class);

        // Quando eu deleto o endereco
        EnderecoClient.deletarEndereco(endereco.getIdEndereco())
                .then()
                .statusCode(204);

        // Então o endereco não é accessível
        EnderecoClient.buscarEnderecoPorId(endereco.getIdEndereco())
                .then()
                .statusCode(404);

        // Limpar massa de dados
        PessoaClient.deletarPessoa(endereco.getIdPessoa());
    }

    @Test
    @DisplayName("Deve falhar deleção um endereço inexistente")
    public void testFalharDelecaoComEnderecoInexistente() {

        //DADO que eu informe o id de um endereço inexistente
        Integer idInvalido = -1000;

        //QUANDO eu deletar o endereço
        EnderecoClient.deletarEndereco(idInvalido)
                //ENTÃO deve retornar o status 404.
                .then()
                    .statusCode(404);
    }
}
