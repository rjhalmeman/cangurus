package Departamento;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.table.DefaultTableModel;
import tools.JanelaPesquisar;
import tools.ManipulaArquivo;
import tools.ManipulaImagem;

public class GUIDepartamento extends JFrame {

    private Container cp;
    private JLabel lbIdDepartamendo = new JLabel("ID");
    private JTextField tfIdDepartamendo = new JTextField(20);
    private JLabel lbNome = new JLabel("Nome");
    private JTextField tfNome = new JTextField(20);
    private JLabel lbSigla = new JLabel("Sigla");
    private JTextField tfSigla = new JTextField(20);
    private JButton btAdicionar = new JButton("Adicionar");
    private JButton btListar = new JButton("Listar");
    private JButton btBuscar = new JButton("Buscar");
    private JButton btLocalizar = new JButton("Localizar");
    private JButton btAlterar = new JButton("Alterar");
    private JButton btExcluir = new JButton("Excluir");
    private JButton btSalvar = new JButton("Salvar");
    private JButton btCancelar = new JButton("Cancelar");
    private JButton btCarregarDados = new JButton("Carregar");
    private JButton btGravar = new JButton("Gravar");
    private JToolBar toolBar = new JToolBar();
    private JPanel painelNorte = new JPanel();
    private JPanel painelCentro = new JPanel();
    private JPanel painelSul = new JPanel();
    private JTextArea texto = new JTextArea();
    private JScrollPane scrollTexto = new JScrollPane();
    private JScrollPane scrollTabela = new JScrollPane();
    private String acao = "";
    private String chavePrimaria = "";
    private ControleDepartamento controle = new ControleDepartamento();
    private Departamento departamento = new Departamento();
    private ManipulaImagem manipulaImagem = new ManipulaImagem();
    String[] colunas = new String[]{"IdDepartamendo", "Nome", "Sigla"};

    String[][] dados = new String[0][4];
    DefaultTableModel model = new DefaultTableModel(dados, colunas);
    JTable tabela = new JTable(model);
    private JPanel painel1 = new JPanel(new GridLayout(1, 1));
    private JPanel painel2 = new JPanel(new GridLayout(1, 1));
    private CardLayout cardLayout;

    public GUIDepartamento() {
        String caminhoENomeDoArquivo = "DadosDepartamento.csv";
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setTitle("CRUD Canguru - V6a");
        cp = getContentPane();

        ImageIcon icon = manipulaImagem.criaIcon("/icones/retrieve.png", 30, 30);
        btBuscar = manipulaImagem.insereBotao(icon, "Buscar");

        icon = manipulaImagem.criaIcon("/icones/list.png", 30, 30);
        btListar = manipulaImagem.insereBotao(icon, "Listar");

        icon = manipulaImagem.criaIcon("/icones/retrieve_1.png", 30, 30);
        btLocalizar = manipulaImagem.insereBotao(icon, "Localizar");

        icon = manipulaImagem.criaIcon("/icones/delete_1.png", 30, 30);
        btExcluir = manipulaImagem.insereBotao(icon, "Excluir");

        icon = manipulaImagem.criaIcon("/icones/update.png", 35, 30);
        btAlterar = manipulaImagem.insereBotao(icon, "Alterar");

        icon = manipulaImagem.criaIcon("/icones/save-as.png", 30, 30);
        btSalvar = manipulaImagem.insereBotao(icon, "Salvar");

        icon = manipulaImagem.criaIcon("/icones/newCancelar.png", 30, 30);
        btCancelar = manipulaImagem.insereBotao(icon, "Cancelar");

        icon = manipulaImagem.criaIcon("/icones/save.png", 30, 30);
        btGravar = manipulaImagem.insereBotao(icon, "Gravar");

        icon = manipulaImagem.criaIcon("/icones/create_1.png", 30, 30);
        btAdicionar = manipulaImagem.insereBotao(icon, "Adicionar");

        cp.setLayout(new BorderLayout());
        cp.add(painelNorte, BorderLayout.NORTH);
        cp.add(painelCentro, BorderLayout.CENTER);
        cp.add(painelSul, BorderLayout.SOUTH);
        cardLayout = new CardLayout();
        painelSul.setLayout(cardLayout);
        painel1.add(scrollTexto);
        painel2.add(scrollTabela);
        texto.setText("\n\n\n\n\n\n");
        scrollTexto.setViewportView(texto);
        painelSul.add(painel1, "Avisos");
        painelSul.add(painel2, "Listagem");
        painelNorte.setLayout(new GridLayout(1, 1));
        painelNorte.add(toolBar);
        painelCentro.setLayout(new GridLayout(2, 2));
        painelCentro.add(lbNome);
        painelCentro.add(tfNome);
        painelCentro.add(lbSigla);
        painelCentro.add(tfSigla);
        toolBar.add(lbIdDepartamendo);
        toolBar.add(tfIdDepartamendo);
        toolBar.add(btAdicionar);
        toolBar.add(btBuscar);
        toolBar.add(btLocalizar);
        toolBar.add(btListar);
        toolBar.add(btAlterar);
        toolBar.add(btExcluir);
        toolBar.add(btSalvar);
        toolBar.add(btCancelar);
        btAdicionar.setVisible(false);
        btAlterar.setVisible(false);
        btExcluir.setVisible(false);
        btSalvar.setVisible(false);
        btCancelar.setVisible(false);
        tfNome.setEditable(false);
        tfSigla.setEditable(false);
        texto.setEditable(false);
        btCarregarDados.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
                if (manipulaArquivo.existeOArquivo(caminhoENomeDoArquivo)) {
                    String aux[];
                    Departamento t;
                    List<String> listaStringCsv = manipulaArquivo.abrirArquivo(caminhoENomeDoArquivo);
                    for (String linha : listaStringCsv) {
                        aux = linha.split(";");
                        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                        try {
                            t = new Departamento(Integer.valueOf(aux[0]), String.valueOf(aux[1]), String.valueOf(aux[2]));
                            controle.adicionar(t);
                        } catch (Exception err) {
                            System.out.println("Deu ruim " + err);
                        }
                    }
                    cardLayout.show(painelSul, "Listagem");
                }
            }
        });
        btGravar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Departamento> listaDepartamento = controle.listar();
                List<String> listaDepartamentoEmFormatoStringCSV = new ArrayList<>();
                for (Departamento t : listaDepartamento) {
                    listaDepartamentoEmFormatoStringCSV.add(t.toString());
                }
                new ManipulaArquivo().salvarArquivo(caminhoENomeDoArquivo, listaDepartamentoEmFormatoStringCSV);
                System.out.println("gravou");
            }
        });
        btBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btAdicionar.setVisible(false);
                cardLayout.show(painelSul, "Avisos");
                scrollTexto.setViewportView(texto);
                if (tfIdDepartamendo.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(cp, "IDDEPARTAMENDO nâo pode ser vazio");
                    tfIdDepartamendo.requestFocus();
                    tfIdDepartamendo.selectAll();
                } else {
                    chavePrimaria = tfIdDepartamendo.getText();
                    departamento = controle.buscar(tfIdDepartamendo.getText());
                    if (departamento == null) {
                        btAdicionar.setVisible(true);
                        btAlterar.setVisible(false);
                        btExcluir.setVisible(false);
                        tfNome.setText("");
                        tfSigla.setText("");
                        texto.setText("Não encontrou na lista - pode Adicionar\n\n\n");
                    } else {
                        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                        texto.setText("Encontrou na lista - pode Alterar ou Excluir\n\n\n");
                        btAlterar.setVisible(true);
                        btExcluir.setVisible(true);
                        tfNome.setText(String.valueOf(departamento.getNome()));
                        tfNome.setEditable(false);
                        tfSigla.setText(String.valueOf(departamento.getSigla()));
                        tfSigla.setEditable(false);
                    }
                }
            }
        });
        btAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                acao = "adicionar";
                tfIdDepartamendo.setText(chavePrimaria);
                tfNome.setEditable(true);
                tfSigla.setEditable(true);
                tfIdDepartamendo.setEditable(false);
                btSalvar.setVisible(true);
                btCancelar.setVisible(true);
                btBuscar.setVisible(false);
                btLocalizar.setVisible(false);
                btListar.setVisible(false);
                btAlterar.setVisible(false);
                btExcluir.setVisible(false);
                btAdicionar.setVisible(false);
                texto.setText("Preencha os atributos\n\n\n\n\n");
            }
        });
        btAlterar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                acao = "alterar";
                tfIdDepartamendo.setText(chavePrimaria);
                tfIdDepartamendo.setEditable(false);
                tfNome.requestFocus();
                btSalvar.setVisible(true);
                btCancelar.setVisible(true);
                btBuscar.setVisible(false);
                btLocalizar.setVisible(false);
                btListar.setVisible(false);
                btAlterar.setVisible(false);
                btExcluir.setVisible(false);
                texto.setText("Preencha os atributos\n\n\n\n\n");
                tfNome.setEditable(true);
                tfSigla.setEditable(true);
            }
        });
        btCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btSalvar.setVisible(false);
                btCancelar.setVisible(false);
                btBuscar.setVisible(true);
                btLocalizar.setVisible(true);
                btListar.setVisible(true);
                tfIdDepartamendo.setEditable(true);
                tfNome.setText("");
                tfNome.setEditable(false);
                tfSigla.setText("");
                tfSigla.setEditable(false);
                tfIdDepartamendo.requestFocus();
                tfIdDepartamendo.selectAll();
                texto.setText("Cancelou\n\n\n\n\n");
            }
        });
        btSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (acao.equals("alterar")) {
                    Departamento departamentoAntigo = departamento;
                    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                    SimpleDateFormat sdfEua = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        departamento.setNome(String.valueOf(tfNome.getText()));
                        departamento.setSigla(String.valueOf(tfSigla.getText()));
                        controle.alterar(departamento, departamentoAntigo);
                        texto.setText("Registro alterado\n\n\n\n\n");
                    } catch (Exception err) {
                        System.out.println("Deu ruim " + err);
                    }
                } else {
                    departamento = new Departamento();
                    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                    SimpleDateFormat sdfEua = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        departamento.setIdDepartamendo(Integer.valueOf(tfIdDepartamendo.getText()));
                        departamento.setNome(String.valueOf(tfNome.getText()));
                        departamento.setSigla(String.valueOf(tfSigla.getText()));
                        controle.adicionar(departamento);
                        texto.setText("Foi adicionado um novo registro\n\n\n\n\n");
                        btAlterar.setVisible(true);
                        btExcluir.setVisible(true);
                    } catch (Exception err) {
                        System.out.println("Deu ruim " + err);
                    }
                }
                btSalvar.setVisible(false);
                btCancelar.setVisible(false);
                btBuscar.setVisible(true);
                btLocalizar.setVisible(true);
                btListar.setVisible(true);
                tfIdDepartamendo.setEditable(true);
                tfIdDepartamendo.requestFocus();
                tfIdDepartamendo.selectAll();
                tfNome.setText("");
                tfSigla.setText("");
                tfNome.setEditable(false);
                tfSigla.setEditable(false);
            }
        });
        btListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Departamento> lt = controle.listar();
                String[] colunas = {"Id", "Nome", "Sigla"};
                Object[][] dados = new Object[lt.size()][colunas.length];
                String aux[];
                for (int i = 0; i < lt.size(); i++) {
                    aux = lt.get(i).toString().split(";");
                    for (int j = 0; j < colunas.length; j++) {
                        dados[i][j] = aux[j];
                    }
                }
                cardLayout.show(painelSul, "Listagem");
                scrollTabela.setPreferredSize(tabela.getPreferredSize());
                painel2.add(scrollTabela);
                scrollTabela.setViewportView(tabela);
                model.setDataVector(dados, colunas);

                btAlterar.setVisible(false);
                btExcluir.setVisible(false);
                btAdicionar.setVisible(false);
            }
        });
        btExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfIdDepartamendo.setText(chavePrimaria);
                if (JOptionPane.YES_OPTION
                        == JOptionPane.showConfirmDialog(null,
                                "Confirma a exclusão do registro <Nome = " + departamento.getNome() + ">?", "Confirm",
                                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                    controle.excluir(departamento);
                    texto.setText("Excluiu o registro de " + departamento.getIdDepartamendo() + " - " + departamento.getNome() + "\n\n\n\n\n");
                } else {
                    texto.setText("Não excluiu o registro de " + departamento.getIdDepartamendo() + " - " + departamento.getNome() + "\n\n\n\n\n");
                }
                btBuscar.setVisible(true);
                btLocalizar.setVisible(true);
                btListar.setVisible(true);
                btExcluir.setVisible(false);
                btAlterar.setVisible(false);
                tfIdDepartamendo.requestFocus();
                tfIdDepartamendo.selectAll();
                tfIdDepartamendo.setText("");
                tfNome.setText("");
                tfSigla.setText("");
            }
        });

        btLocalizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> listaAuxiliar = controle.listStrings();
                if (listaAuxiliar.size() > 0) {//se a lista não estiver vazia abre a janela
                    Point lc = btLocalizar.getLocationOnScreen();//precisa mexer aqui para centralizar a janela// o ponto onde a janela vai abrir
                    lc.x = lc.x + btLocalizar.getWidth();//um pouco para frente do botão localizar
                    String selectedItem = new JanelaPesquisar(listaAuxiliar,
                            lc.x,
                            lc.y).getValorRetornado();////matriz x coluna y é linha - posicionamento da janela
                    if (!selectedItem.equals("")) {//pega toda a linha 
                        String[] aux = selectedItem.split(";");//divide no ponto e virgula
                        tfIdDepartamendo.setText(aux[0]);//pega so cpf e preenche no textfield cpf
                        btBuscar.doClick();//faca um clique,  faz a mesma coisa que o buscar clicando automaticamente
                    } else {
                        tfIdDepartamendo.requestFocus();
                        tfIdDepartamendo.selectAll();
                    }
                }
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                //antes de sair, salvar a lista em disco
                btGravar.doClick();
                // Sai da classe
                dispose();
            }
        });
        btCarregarDados.doClick();
        setVisible(true);
    }
}
