package tests.endereco;

import client.EnderecoClient;
import client.PessoaClient;
import com.fasterxml.jackson.core.type.TypeReference;
import model.EnderecoModel;
import model.PaginacaoModel;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.base.BaseTest;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.UUID;

public class ListarEnderecoTest extends BaseTest {

    @Test
    @DisplayName("Deve listar endereços cadastrados com paginação")
    public void testListarEnderecosCadastradosComPaginacao() {
        Integer paginaDefault = 0;
        Integer tamanhoDasPaginasDefault = 20;

        Type type = new TypeReference<PaginacaoModel<EnderecoModel>>() {}.getType();
        PaginacaoModel<EnderecoModel> paginacaoEnderecos = EnderecoClient.listarEnderecos()
                .then()
                    .statusCode(200)
                    .extract().as(type);

        Assertions.assertEquals(paginaDefault, paginacaoEnderecos.getPage());
        Assertions.assertEquals(tamanhoDasPaginasDefault, paginacaoEnderecos.getSize());

        // Pode ocorrer do número de registros retornados ser menor que o tamanho estabelecido
        // Isso ocorre apenas na última página. Caso não seja a última, vale a pena validar o tamanho
        if (paginacaoEnderecos.getTotalPages() > paginaDefault) {
            Assertions.assertEquals(paginacaoEnderecos.getContent().size(), tamanhoDasPaginasDefault);
        }
    }

    @Test
    @DisplayName("Deve falhar listar sem autenticação")
    public void testFalharListarSemAutenticacao() {
        EnderecoClient.listarSemAutenticao()
                .then()
                    .statusCode(403)
        ;
    }

    @Test
    @DisplayName("Deve listar endereços por pais")
    public void testListarEnderecosPorPais() {

        // DADO que eu tenha pelo menos um endereço cadastrado
        EnderecoModel endereco = CadastrarEnderecoTest.criarEndereco()
                .then().extract().as(EnderecoModel.class);

        // E parametrize a requisição com o pais do endereço
        // QUANDO eu listar endereços por pais
        EnderecoModel[] enderecosPorPais = EnderecoClient.listarEnderecosPorPais(endereco.getPais())
                .then()
                    .statusCode(200)
                    .extract().as(EnderecoModel[].class);

        Arrays.stream(enderecosPorPais).forEach(enderecoPorPais -> {
            Assertions.assertEquals(endereco.getPais(), enderecoPorPais.getPais());});


        // Limpar massa de dados
        EnderecoClient.deletarEndereco(endereco.getIdEndereco());
        PessoaClient.deletarPessoa(endereco.getIdPessoa());
    }

    @Test
    @DisplayName("Deve falhar listar endereços por pais inexistente")
    public void testFalharListarEnderecosEmPaisInexistente() {

        // DADO que eu parametrize a requisição com um país não existente
        // QUANDO eu listar endereços por pais
        EnderecoClient.listarEnderecosPorPais(UUID.randomUUID().toString())
                // ENTÃO a lista de endereços deve estar vazia
                .then()
                    .statusCode(200)
                    .body("content", org.hamcrest.Matchers.empty())
        ;
    }

    @Test
    @DisplayName("Deve listar endereços por pessoa")
    public void testListarEnderecosPorPessoas() {

        // DADO que eu tenha pelo menos um endereço cadastrado
        EnderecoModel endereco1 = CadastrarEnderecoTest.criarEndereco()
                .then().extract().as(EnderecoModel.class);
        EnderecoModel endereco2 = CadastrarEnderecoTest.criarEndereco(endereco1.getIdPessoa());

        // E parametrize a requisição com o id da pessoa do endereço
        // QUANDO eu listar endereços por pessoa
        EnderecoModel[] enderecosPorPessoa = EnderecoClient.listarEnderecosPorPessoa(endereco1.getIdPessoa())
                .then()
                    .statusCode(200)
                    .extract().as(EnderecoModel[].class);


        Assertions.assertEquals(endereco1.getIdEndereco(), enderecosPorPessoa[0].getIdEndereco());
        Assertions.assertEquals(endereco2.getIdEndereco(), enderecosPorPessoa[1].getIdEndereco());


        // Limpar massa de dados
        EnderecoClient.deletarEndereco(endereco1.getIdEndereco());
        EnderecoClient.deletarEndereco(endereco2.getIdEndereco());
        PessoaClient.deletarPessoa(endereco1.getIdPessoa());
    }

    @Test
    @DisplayName("Deve falhar listar endereços por pessoa inexistente")
    public void testFalharListarEnderecosPorPessoaInexistente() {

        Integer idPessoaInexistente = -1000;

        // DADO que eu parametrize a requisição com um id de pessoa não existente
        // QUANDO eu listar endereços por pessoa
        EnderecoClient.listarEnderecosPorPessoa(idPessoaInexistente)
                // ENTÃO a lista de endereços deve estar vazia
                .then()
                    .statusCode(200)
                    .body("content", org.hamcrest.Matchers.empty())
        ;
    }

    @Test
    @DisplayName("Deve buscar endereço pelo id")
    public void testBuscarEnderecoPeloId() {

        // DADO que eu tenha um endereço cadastrado
        EnderecoModel endereco = CadastrarEnderecoTest.criarEndereco()
                .then().extract().as(EnderecoModel.class);

        // QUANDO eu recuperar o endereço pelo id
        EnderecoModel enderecoRecuperado = EnderecoClient.buscarEnderecoPorId(endereco.getIdEndereco())
                .then()
                    .statusCode(200)
                    .extract().as(EnderecoModel.class);

        // ENTÃO o endereço recuperado deve ser igual ao endereço cadastrado
        Assertions.assertEquals(endereco.getIdEndereco(), enderecoRecuperado.getIdEndereco());

        // Limpar massa de dados
        EnderecoClient.deletarEndereco(endereco.getIdEndereco());
        PessoaClient.deletarPessoa(endereco.getIdPessoa());
    }

    @Test
    @DisplayName("Deve falhar ao recuperar endereço por id inexistente")
    public void testFalharRecuperarEnderecoPorIdInexistente() {

        // DADO que eu tenha um id de endereço não cadastrado
        Integer idEnderecoInexistente = -1000;

        // QUANDO eu recuperar o endereço pelo id
        EnderecoClient.buscarEnderecoPorId(idEnderecoInexistente)
                // ENTÃO deve retornar status 404
                .then()
                    .statusCode(404)
                    .body("message", Matchers.equalTo("{idEndereco} nao encontrado"))
        ;
    }

}
