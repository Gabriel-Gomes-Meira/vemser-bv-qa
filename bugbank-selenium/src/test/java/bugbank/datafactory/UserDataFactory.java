package bugbank.datafactory;

import bugbank.model.UserModel;
import bugbank.pages.RegisterPage;
import net.datafaker.Faker;
import org.testng.annotations.DataProvider;

import java.util.List;
import java.util.Objects;
import java.util.UUID;


public class UserDataFactory {

    private static final Faker faker = new Faker();

    public static UserModel getPessoaValida() {
        String senha = faker.internet().password();
        return new UserModel(
                faker.internet().emailAddress(),
                faker.name().fullName(),
                senha,
                senha
        );
    }

    @DataProvider(name = "usuarioComEmailInvalido")
    public static Object[][] getUsuarioComEmailInvalido() {
        UserModel usuario = getPessoaValida();

        return new Object[][] {{
            UUID.randomUUID().toString(),
            usuario.getName(),
            usuario.getPassword(),
            usuario.getPasswordConfirm()
        }};
    }

    @DataProvider(name = "usuarioCamposVazios")
    public static Object[][] getUsuarioCamposVazios() {
        return new Object[][] {
            {   "", "", "", "", 0 },
            {   "", "Random Person", "12345678", "12345678", 1 },
            {   "anyone@email.com", "", "12345678", "12345678", 2  },
            {   "anyone@email.com", "Random Person", "", "12345678", 3 },
            {   "anyone@email.com", "Random Person", "12345678", "", 4 }
        };
    }

    public static Boolean validarMensagensDeErroEmCamposVazios(int index) {
         return List.of(
                (   Objects.equals(RegisterPage.getMensagemDeEmail(), "É campo obrigatório") &&
                        Objects.equals(RegisterPage.getMensagemDeName(), "É campo obrigatório") &&
                        Objects.equals(RegisterPage.getMensagemDePassword(), "É campo obrigatório") &&
                        Objects.equals(RegisterPage.getMensagemDePasswordConfirm(), "É campo obrigatório")),
                (   Objects.equals(RegisterPage.getMensagemDeEmail(), "É campo obrigatório")),
                (   Objects.equals(RegisterPage.getMensagemDeName(), "É campo obrigatório")),
                (   Objects.equals(RegisterPage.getMensagemDePassword(), "É campo obrigatório")),
                (   Objects.equals(RegisterPage.getMensagemDePasswordConfirm(), "É campo obrigatório"))
        ).get(index);
    }
}
