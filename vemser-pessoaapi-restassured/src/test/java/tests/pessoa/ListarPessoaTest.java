package tests.pessoa;

import client.PessoaClient;
import datafactory.PessoaDataFactory;
import model.PaginacaoModel;
import model.PessoaModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.base.BaseTest;

import java.util.Arrays;

import static org.hamcrest.Matchers.lessThan;


public class ListarPessoaTest extends BaseTest {

    @Test
    @DisplayName("Deve listar pessoas com sucesso")
    public void testListarPessoasComSucesso() {
        PessoaClient.listarPessoas()
                                .then()
                                    .statusCode(200);
    }

    @Test
    @DisplayName("Deve listar pessoas com query")
    public void testListarPessoasComSucessoComQuery() {
        Integer page = 1;
        Integer size = 100;

        PaginacaoModel response = PessoaClient.listarPessoas(page, size)
                .then()
                .statusCode(200)
                .extract().as(PaginacaoModel.class);

        Assertions.assertEquals(page, response.getPage());
        Assertions.assertEquals(size, response.getSize());
    }

    @Test
    @DisplayName("Deve falhar paginação com query negativa")
    public void testFalharPaginacaoComTamanhoNegativoOuPaginaNegativa() {
        Integer page = 0;
        Integer size = -1;

        PessoaClient.listarPessoas(page, size)
                .then()
                .statusCode(400);

        page = -1;
        size = 10;

        PessoaClient.listarPessoas(page, size)
                .then()
                .statusCode(400);
    }

    @Test
    @DisplayName("Deve buscar pessoa pelo cpf")
    public void testBuscarPessoaPorCPFComSucesso() {
        // Dado que há uma pessoa cadastrada
        PessoaModel pessoaCriada = PessoaClient.cadastrarPessoa(PessoaDataFactory.getPessoaValida())
                            .then().extract().as(PessoaModel.class);

        // Quando eu busco essa pessoa pelo cpf
        PessoaModel pessoaBuscada = PessoaClient.buscarPessoaPorCpf(pessoaCriada.getCpf())
                // Então eu posso a encontrar com todos os seus dados
                .then()
                    .statusCode(200)
                    .extract().as(PessoaModel.class);

        Assertions.assertEquals(pessoaCriada.getNome(), pessoaBuscada.getNome());
        Assertions.assertEquals(pessoaCriada.getCpf(), pessoaBuscada.getCpf());
        Assertions.assertEquals(pessoaCriada.getEmail(), pessoaBuscada.getEmail());
        Assertions.assertEquals(pessoaCriada.getDataNascimento(), pessoaBuscada.getDataNascimento());
        Assertions.assertEquals(pessoaCriada.getIdPessoa(), pessoaBuscada.getIdPessoa());

        // Limpar massa
        PessoaClient.deletarPessoa(pessoaBuscada.getIdPessoa());
    }

    @Test
    @DisplayName("Deve falhar ao buscar pessoa com cpf em branco")
    public void testFalharBuscarCPFEmBranco() {
        PessoaClient.buscarPessoaPorCpf(" ")
                .then()
                    .statusCode(400);
    }

    @Test
    @DisplayName("Deve listar pessoas por nome com sucesso")
    public void testListarPessoasPorNomeComSucesso() {
        PessoaModel pessoaModel = PessoaDataFactory.getPessoaValida();
        String primeiroNome = pessoaModel.getNome().split(" ")[0];

        // Dado que há ao menos uma pessoa com cadastrada
        pessoaModel = PessoaClient.cadastrarPessoa(pessoaModel)
                .then()
                    .extract().as(PessoaModel.class);

        // Quando eu buscar pessoas com o primeiro nome da pessoa cadastrada
        PessoaModel[] pessoas = PessoaClient.listarPessoasPorNome(primeiroNome)
                .then()
                .statusCode(200)
                .time(lessThan(1000L))
                .extract().as(PessoaModel[].class);

        // Então deve retornar a lista de pessoas cujo o nome contenha o primeiro nome da pessoa cadastrada
        Assertions.assertTrue(pessoas.length > 0);
        Arrays.stream(pessoas).forEach(pessoa -> {
            Assertions.assertTrue(pessoa.getNome().contains(primeiroNome));
        });

        // Limpar massa
        PessoaClient.deletarPessoa(pessoaModel.getIdPessoa());
    }

    @Test
    @DisplayName("Deve falhar listar pessoas sem nome")
    public void testFalharListarPessoasSemNome() { // Visto que a documentaçao aponta que nome é obrigatório

        PessoaClient.listarPessoasPorNome("")
                .then()
                .statusCode(400);
    }



}
