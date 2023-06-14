package datafactory;

import model.PessoaModel;
import net.datafaker.Faker;

import java.text.SimpleDateFormat;

public class PessoaDataFactory {

    private static Faker faker = new Faker();
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private PessoaDataFactory() {}

    private static PessoaModel gerarNovaPessoa() {
        PessoaModel pessoa = new PessoaModel();
        pessoa.setNome(faker.name().nameWithMiddle());
        pessoa.setDataNascimento(dateFormat.format(faker.date().birthday()));
        pessoa.setCpf(faker.cpf().valid(false));
        pessoa.setEmail(faker.internet().emailAddress());

        return pessoa;
    }

    public static PessoaModel getPessoaValida() {
        return gerarNovaPessoa();
    }


}
