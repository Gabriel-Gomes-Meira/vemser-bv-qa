package datafactory;

import model.EnderecoModel;
import model.TipoEndereco;
import net.datafaker.Faker;

import java.util.Random;

public class EnderecoDataFactory {

    private static Faker faker = new Faker();

    private EnderecoDataFactory() {}

    private static EnderecoModel gerarNovoEndereco() {
        EnderecoModel endereco = new EnderecoModel();
        int i = new Random().nextInt((TipoEndereco.values().length - 1));
        endereco.setTipo(TipoEndereco.values()[i]);
        endereco.setLogradouro(faker.address().streetName());
        endereco.setNumero(faker.number().numberBetween(1, 1000));
        endereco.setComplemento(faker.address().secondaryAddress());
        endereco.setCep(faker.number().digits(8));
        endereco.setCidade(faker.address().city());
        endereco.setEstado(faker.address().state());
        endereco.setPais(faker.address().country());
        return endereco;
    }

    public static EnderecoModel getEnderecoValida() {
        return gerarNovoEndereco();
    }


}
