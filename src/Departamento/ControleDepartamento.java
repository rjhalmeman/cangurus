package Departamento;

import java.util.ArrayList;
import java.util.List;

public class ControleDepartamento {

    private List<Departamento> lista = new ArrayList<>();

    public ControleDepartamento() {

    }

    public void limparLista() {
        lista.clear();
    }

    public void adicionar(Departamento departamento) {
        lista.add(departamento);
    }

    public List<Departamento> listar() {
        return lista;
    }

    public Departamento buscar(String IdDepartamendo) {
        for (int i = 0; i < lista.size(); i++) {
            if (String.valueOf(lista.get(i).getIdDepartamendo()).equals(IdDepartamendo)) {
                return lista.get(i);
            }
        }
        return null;
    }

    public void alterar(Departamento departamento, Departamento departamentoAntigo) {
        lista.set(lista.indexOf(departamentoAntigo), departamento);

    }

    public void excluir(Departamento departamento) {
        lista.remove(departamento);
    }

    public List<String> listStrings() {
        List<String> ls = new ArrayList<>();
        for (Departamento t : lista) {//converto trabalhador para uma lista de strings
            ls.add(t.toString());
        }
        return ls;
    }
}
