package tests.pessoa;

import client.PessoaClient;
import datafactory.PessoaDataFactory;
import model.PessoaModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.base.BaseTest;

import java.util.Objects;

import static org.hamcrest.Matchers.equalTo;

public class AtualizarPessoaTest extends BaseTest {


    @Test
    @DisplayName("Deve atualizar pessoa com sucesso")
    public void testAtualizarPessoaComSucesso() {
        // Dado que eu tenho uma pessoa cadastrada
        PessoaModel pessoaCriada = PessoaClient.cadastrarPessoa(PessoaDataFactory.getPessoaValida())
                .then().extract().as(PessoaModel.class);

        // Quando eu atualizo essa pessoa
        PessoaModel pessoaAtualizada = PessoaClient.atualizarPessoa(pessoaCriada.getIdPessoa(), PessoaDataFactory.getPessoaValida())
                .then()
                .statusCode(200)
                .extract().as(PessoaModel.class);

        // Então a pessoa tem os dados alterados e persistidos com sucesso
        PessoaModel pessoaSalva = PessoaClient.buscarPessoaPorCpf(pessoaAtualizada.getCpf()).
                then()
                .statusCode(200)
                .extract().as(PessoaModel.class);

        Assertions.assertTrue(Objects.equals(pessoaCriada.getIdPessoa(), pessoaAtualizada.getIdPessoa())
                && Objects.equals(pessoaAtualizada.getIdPessoa(), pessoaSalva.getIdPessoa()));
        Assertions.assertEquals(pessoaAtualizada.getNome(), pessoaSalva.getNome());
        Assertions.assertEquals(pessoaAtualizada.getCpf(), pessoaSalva.getCpf());
        Assertions.assertEquals(pessoaAtualizada.getDataNascimento(), pessoaSalva.getDataNascimento());
        Assertions.assertEquals(pessoaAtualizada.getEmail(), pessoaSalva.getEmail());

        // Limpaar dados
        PessoaClient.deletarPessoa(pessoaAtualizada.getIdPessoa());
    }

    @Test
    @DisplayName("Deve falhar ao tentar atualizar pessoa com id inválido")
    public void testFalharAtualizarComIdInvalido() {
        // Dado que não tenha pessoa com o id negativo cadastrada
        Integer idPessoa = -1000;

        // Quando eu tento atualizar essa pessoa
        PessoaModel pessoaAtualizada = PessoaDataFactory.getPessoaValida();
        PessoaClient.atualizarPessoa(idPessoa, pessoaAtualizada)
                .then()
                .statusCode(404)
                .body("message", equalTo("ID da pessoa nao encontrada"));

        // Dado que não tenha pessoa com o id em caracteres
        // Quando eu tento atualizar essa pessoa
        PessoaClient.atualizarPessoa("___", pessoaAtualizada)
                .then()
                .statusCode(400);

        // Obs: o cenário abrange "idInválido", então poderia também criar um registro e deletar,
        // mas já que um id é universalmente um inteiro positivo, então podemos poupar processamento
    }
}
