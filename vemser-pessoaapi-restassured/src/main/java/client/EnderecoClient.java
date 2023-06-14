package client;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.EnderecoModel;
import utils.Authentication;

import static io.restassured.RestAssured.given;

public class EnderecoClient {

    public static Response listarEnderecos() {
        return  given()
                    .header("Authorization", Authentication.getTokenAdmin())
                .when()
                    .get("/endereco")
        ;
    }

    public static Response listarEnderecos(Integer page, Integer size) {
        return  given()
                    .header("Authorization", Authentication.getTokenAdmin())
                    .queryParam("pagina", page)
                    .queryParam("tamanhoDasPaginas", size)
                .when()
                    .get("/endereco")
        ;
    }

    public static Response listarSemAutenticao() {
        return  given()
                .when()
                    .get("/endereco")
        ;
    }

    public static Response listarEnderecosPorPais(String pais) {
        return given()
                    .header("Authorization", Authentication.getTokenAdmin())
                    .queryParam("País", pais)
                .when()
                    .get("/endereco/retorna-por-pais")
        ;
    }

    public static Response listarEnderecosPorPessoa(Integer idPessoa) {
        return  given()
                    .header("Authorization", Authentication.getTokenAdmin())
                    .queryParam("idPessoa", idPessoa)
                .when()
                    .get("/endereco/retorna-por-id-pessoa")
        ;
    }

    public static Response buscarEnderecoPorId(Integer idEndereco) {
        return given()
                    .header("Authorization", Authentication.getTokenAdmin())
                    .pathParam("idEndereco", idEndereco)
                .when()
                    .get("/endereco/{idEndereco}")
        ;
    }


    public static Response cadastrarEndereco(Integer idPessoa, EnderecoModel endereco) {
        return given()
                    .header("Authorization", Authentication.getTokenAdmin())
                    .header("Content-Type", ContentType.JSON)
                    .pathParam("idPessoa", idPessoa)
                    .queryParam("idPessoa", idPessoa)
                    .body(endereco)
                .when()
                    .post("/endereco/{idPessoa}")
        ;
    }


    public static Response atualizarEndereco(Object idEndereco, EnderecoModel endereco) {
        return given()
                    .header("Authorization", Authentication.getTokenAdmin())
                    .header("Content-Type", ContentType.JSON)
                    .pathParam("idEndereco", idEndereco)
                    .body(endereco)
                .when()
                    .put("/endereco/{idEndereco}")
        ;
    }


    public static Response deletarEndereco(Integer idEndereco) {
        return given()
                    .header("Authorization", Authentication.getTokenAdmin())
                    .pathParam("idEndereco", idEndereco)
                .when()
                    .delete("/endereco/{idEndereco}")
        ;
    }
}
