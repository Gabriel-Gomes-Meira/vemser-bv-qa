package client;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.PessoaModel;
import utils.Authentication;

import static io.restassured.RestAssured.*;

public class PessoaClient {

    public static Response listarPessoas() {
        return given()
                .header("Authorization", Authentication.getTokenAdmin())
        .when()
                .get("/pessoa")
        ;
    }

    public static Response listarPessoas(Integer page, Integer size) {
        return given()
                .header("Authorization", Authentication.getTokenAdmin())
                .queryParam("pagina", page)
                .queryParam("tamanhoDasPaginas", size)
        .when()
                .get("/pessoa")
        ;
    }

    public static Response listarPessoasPorNome(String nome) {
        return given()
                    .header("Authorization", Authentication.getTokenAdmin())
                    .queryParam("nome", nome)
                .when()
                    .get("/pessoa/byname")
        ;
    }

    public static Response buscarPessoaPorCpf(String cpf) {
        return given()
                .header("Authorization", Authentication.getTokenAdmin())
                .pathParam("cpf", cpf)
                .when()
                .get("/pessoa/{cpf}/cpf")
                ;
    }


    public static Response cadastrarPessoa(PessoaModel pessoa) {
        return given()
                    .header("Authorization", Authentication.getTokenAdmin())
                    .header("Content-Type", ContentType.JSON)
                    .body(pessoa)
                .when()
                    .post("/pessoa")
        ;
    }


    public static Response atualizarPessoa(Object idPessoa, PessoaModel pessoa) {
        return given()
                    .header("Authorization", Authentication.getTokenAdmin())
                    .header("Content-Type", ContentType.JSON)
                    .pathParam("idPessoa", idPessoa)
                    .body(pessoa)
                .when()
                    .put("/pessoa/{idPessoa}")
        ;
    }


    public static Response deletarPessoa(Object idPessoa) {
        return given()
                    .header("Authorization", Authentication.getTokenAdmin())
                    .pathParam("idPessoa", idPessoa)
                .when()
                    .delete("/pessoa/{idPessoa}")
        ;
    }
}
