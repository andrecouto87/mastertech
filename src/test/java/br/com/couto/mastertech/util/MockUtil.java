package br.com.couto.mastertech.util;

import br.com.couto.mastertech.entity.Usuario;
import br.com.couto.mastertech.model.UsuarioModel;

import java.util.Calendar;

public class MockUtil {

    public static UsuarioModel getMockedUsuarioModel() {
        return new UsuarioModel((long) 1, "José Pereira", "36910240867", "pereirajose@email.com", Calendar.getInstance().getTime());
    }

    public static Usuario getMockedUsuarioEntity() {
        return new Usuario((long) 2, "Maria Mendonça", "78909878976", "mariam@email.com", null);
    }
}
