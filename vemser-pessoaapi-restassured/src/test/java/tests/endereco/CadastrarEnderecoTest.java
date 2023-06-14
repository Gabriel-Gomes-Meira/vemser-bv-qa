package tests.endereco;

import client.EnderecoClient;
import client.PessoaClient;
import com.fasterxml.jackson.core.type.TypeReference;
import datafactory.EnderecoDataFactory;
import datafactory.PessoaDataFactory;
import io.restassured.response.Response;
import model.EnderecoModel;
import model.PaginacaoModel;
import model.PessoaModel;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.base.BaseTest;

import java.lang.reflect.Type;

public class CadastrarEnderecoTest extends BaseTest {

    @Test
    @DisplayName("Deve cadastrar endereço com sucesso")
    public void testCadastrarEnderecoComSucesso() {

        EnderecoModel enderecoCriado = criarEndereco()
                .then()
                .statusCode(201) // Status Code padrão para POST
                .extract().as(EnderecoModel.class);

        // Então o endereço deve ser persistido com sucesso
        EnderecoModel enderecoPersistido = EnderecoClient.buscarEnderecoPorId(enderecoCriado.getIdEndereco())
                .then()
                .statusCode(200)
                .extract().as(EnderecoModel.class);

        Assertions.assertEquals(enderecoCriado.getPais(), enderecoPersistido.getPais());
        Assertions.assertEquals(enderecoCriado.getEstado(), enderecoPersistido.getEstado());
        Assertions.assertEquals(enderecoCriado.getCidade(), enderecoPersistido.getCidade());
        Assertions.assertEquals(enderecoCriado.getNumero(), enderecoPersistido.getNumero());
        Assertions.assertEquals(enderecoCriado.getCep(), enderecoPersistido.getCep());
        Assertions.assertEquals(enderecoCriado.getComplemento(), enderecoPersistido.getComplemento());
        Assertions.assertEquals(enderecoCriado.getIdPessoa(), enderecoPersistido.getIdPessoa());

        // Delete para limpar massa de dados
        EnderecoClient.deletarEndereco(enderecoPersistido.getIdEndereco());
        PessoaClient.deletarPessoa(enderecoCriado.getIdPessoa());
    }

    @Test
    @DisplayName("Deve falhar cadastro de endereço para pessoa inexistente")
    public void testFalharCadastroDeEnderecoParaPessoaInexistente() {

        // Dado que eu informe um id de pessoa inexistente
        PessoaModel pessoaTemporaria = PessoaClient.cadastrarPessoa(PessoaDataFactory.getPessoaValida())
                    .then()
                    .extract().as(PessoaModel.class);
        PessoaClient.deletarPessoa(pessoaTemporaria.getIdPessoa());

        // Quando eu cadastrar um endereço para essa pessoa
        EnderecoModel endereco = EnderecoDataFactory
                .getEnderecoValida();

        EnderecoClient.cadastrarEndereco(pessoaTemporaria.getIdPessoa(), endereco)
                    .then()
                    .log().all()
                    .statusCode(404)
                    .body("message", Matchers.equalTo("ID da pessoa nao encontrada"));
    }

    public static Response criarEndereco() {
        // Dado que eu tenha uma pessoa previamente cadastrada
        PessoaModel pessoaValida = PessoaClient.cadastrarPessoa(PessoaDataFactory.getPessoaValida())
                .then().extract().as(PessoaModel.class);

        // Quando eu cadastrar um endereço para essa pessoa
        EnderecoModel endereco = EnderecoDataFactory
                .getEnderecoValida();

        return EnderecoClient.cadastrarEndereco(pessoaValida.getIdPessoa(), endereco);
    }

    public static EnderecoModel criarEndereco(Integer idPessoa) {

        return EnderecoClient.cadastrarEndereco(
                idPessoa,
                EnderecoDataFactory.getEnderecoValida()
                ).then()
                    .statusCode(200)
                    .extract().as(EnderecoModel.class);
    }



}
