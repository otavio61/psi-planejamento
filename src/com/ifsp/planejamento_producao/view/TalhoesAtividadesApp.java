package com.ifsp.planejamento_producao.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TalhoesAtividadesApp extends JFrame {
    private JPanel mainPanel;
    private List<TalhaoPanel> talhoes;

    public TalhoesAtividadesApp() {
        initializeUI();
        criarTalhoesExemplo();
    }

    private void initializeUI() {
        setTitle("Sistema de Gestão de Talhões");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);

        // Painel principal com scroll vertical
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(240, 240, 240));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        // Cabeçalho
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        headerPanel.setBackground(new Color(70, 130, 180));
        headerPanel.setPreferredSize(new Dimension(getWidth(), 70));
        JLabel headerLabel = new JLabel("Sistema de Gestão de Talhões", JLabel.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 28));
        headerLabel.setForeground(Color.WHITE);
        headerPanel.add(headerLabel);

        // Layout principal
        setLayout(new BorderLayout());
        add(headerPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void criarTalhoesExemplo() {
        talhoes = new ArrayList<>();

        // Criar alguns talhões de exemplo
        String[] nomesTalhoes = { "Talhão Norte", "Talhão Sul", "Talhão Leste", "Talhão Oeste", "Talhão Central" };
        String[][] atividadesTalhoes = {
                { "Preparação do solo", "Plantio de milho", "Aplicação de fertilizante" },
                { "Colheita de soja", "Rotação de cultura", "Irrigação" },
                { "Monitoramento de pragas", "Podas", "Adubação verde" },
                { "Plantio de trigo", "Controle de ervas daninhas", "Análise de solo" },
                { "Colheita de café", "Secagem", "Armazenamento" }
        };

        for (int i = 0; i < nomesTalhoes.length; i++) {
            TalhaoPanel talhao = new TalhaoPanel(nomesTalhoes[i], i + 1);
            for (String atividade : atividadesTalhoes[i]) {
                talhao.adicionarAtividade("Tarefa " + (talhao.getAtividadesCount() + 1), atividade);
            }
            mainPanel.add(talhao);
            mainPanel.add(Box.createRigidArea(new Dimension(0, 15))); // Espaço entre talhões
            talhoes.add(talhao);
        }
    }

    // Classe interna para representar cada painel de talhão
    class TalhaoPanel extends JPanel {
        private String nome;
        private int numero;
        private JPanel headerPanel;
        private JLabel expandIcon;
        private JPanel atividadesPanel;
        private JPanel postItContainer;
        private List<Color> postItColors;
        private boolean expanded;

        public TalhaoPanel(String nome, int numero) {
            this.nome = nome;
            this.numero = numero;
            this.expanded = false;
            initializeUI();
            setupPostItColors();
        }

        private void initializeUI() {
            setLayout(new BorderLayout());
            setBackground(Color.WHITE);
            setBorder(BorderFactory.createCompoundBorder(
                    new LineBorder(new Color(200, 200, 200), 1, true),
                    new EmptyBorder(0, 0, 0, 0)));
            setMaximumSize(new Dimension(Integer.MAX_VALUE, 300)); // Altura máxima quando recolhido

            // Cabeçalho do talhão (clicável)
            headerPanel = new JPanel(new BorderLayout(10, 0));
            headerPanel.setBackground(new Color(230, 240, 250));
            headerPanel.setBorder(new EmptyBorder(15, 20, 15, 20));
            headerPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            // Ícone de expandir/recolher
            expandIcon = new JLabel("▶");
            expandIcon.setFont(new Font("Arial", Font.BOLD, 16));
            expandIcon.setForeground(new Color(80, 80, 80));
            expandIcon.setPreferredSize(new Dimension(20, 20));

            // Título do talhão
            JLabel titleLabel = new JLabel(nome + " (Nº " + numero + ")");
            titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
            titleLabel.setForeground(new Color(60, 60, 60));

            // Botão para adicionar nova atividade
            JButton addButton = new JButton("+ Nova Atividade");
            addButton.setFont(new Font("Arial", Font.PLAIN, 12));
            addButton.setPreferredSize(new Dimension(140, 30));
            addButton.addActionListener(e -> adicionarNovaAtividade());

            headerPanel.add(expandIcon, BorderLayout.WEST);
            headerPanel.add(titleLabel, BorderLayout.CENTER);
            headerPanel.add(addButton, BorderLayout.EAST);

            // Painel de atividades (inicialmente oculto)
            atividadesPanel = new JPanel();
            atividadesPanel.setLayout(new BorderLayout());
            atividadesPanel.setBackground(new Color(245, 245, 245));
            atividadesPanel.setVisible(false);

            // Container dos post-its
            postItContainer = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 15));
            postItContainer.setBackground(new Color(245, 245, 245));

            // ScrollPane horizontal para as atividades
            JScrollPane scrollPane = new JScrollPane(postItContainer);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
            scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 15, 10));

            atividadesPanel.add(scrollPane, BorderLayout.CENTER);

            add(headerPanel, BorderLayout.NORTH);
            add(atividadesPanel, BorderLayout.CENTER);

            // Adicionar listener para expandir/recolher
            headerPanel.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    toggleExpansion();
                }
            });
        }

        private void setupPostItColors() {
            postItColors = new ArrayList<>();
            postItColors.add(new Color(255, 249, 179)); // Amarelo post-it
            postItColors.add(new Color(202, 255, 191)); // Verde claro
            postItColors.add(new Color(255, 207, 207)); // Rosa claro
            postItColors.add(new Color(207, 226, 255)); // Azul claro
            postItColors.add(new Color(230, 207, 255)); // Roxo claro
        }

        private void toggleExpansion() {
            expanded = !expanded;
            atividadesPanel.setVisible(expanded);
            expandIcon.setText(expanded ? "▼" : "▶");

            if (expanded) {
                setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
            } else {
                setMaximumSize(new Dimension(Integer.MAX_VALUE, 300));
            }

            revalidate();
            repaint();

            // Rolagem suave para o talhão expandido
            SwingUtilities.invokeLater(() -> {
                Rectangle rect = getBounds();
                rect.setLocation(0, (int) rect.getY());
                scrollRectToVisible(rect);
            });
        }

        private JPanel criarPostIt(String titulo, String conteudo, Color corFundo) {
            JPanel postIt = new JPanel();
            postIt.setLayout(new BorderLayout(5, 5));
            postIt.setBackground(corFundo);

            // Borda com efeito de sombra
            postIt.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(180, 180, 180), 1),
                    BorderFactory.createEmptyBorder(8, 8, 12, 12)));

            // Título do post-it
            JLabel titleLabel = new JLabel(titulo);
            titleLabel.setFont(new Font("Arial", Font.BOLD, 12));
            titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));

            // Conteúdo do post-it
            JTextArea contentArea = new JTextArea(conteudo);
            contentArea.setEditable(false);
            contentArea.setLineWrap(true);
            contentArea.setWrapStyleWord(true);
            contentArea.setBackground(corFundo);
            contentArea.setFont(new Font("Arial", Font.PLAIN, 11));
            contentArea.setBorder(null);

            // Adiciona componentes ao post-it
            postIt.add(titleLabel, BorderLayout.NORTH);
            postIt.add(new JScrollPane(contentArea), BorderLayout.CENTER);

            // Tamanho fixo para os post-its
            postIt.setPreferredSize(new Dimension(140, 120));

            return postIt;
        }

        public void adicionarAtividade(String titulo, String conteudo) {
            Random random = new Random();
            JPanel postIt = criarPostIt(
                    titulo,
                    conteudo,
                    postItColors.get(random.nextInt(postItColors.size())));
            postItContainer.add(postIt);
            postItContainer.revalidate();
            postItContainer.repaint();
        }

        private void adicionarNovaAtividade() {
            // Diálogo para adicionar nova atividade
            JDialog dialog = new JDialog(TalhoesAtividadesApp.this, "Nova Atividade", true);
            dialog.setLayout(new BorderLayout());
            dialog.setSize(400, 250);
            dialog.setLocationRelativeTo(TalhoesAtividadesApp.this);

            JPanel formPanel = new JPanel(new GridBagLayout());
            formPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.weightx = 1.0;

            JLabel titleLabel = new JLabel("Adicionar Nova Atividade para " + nome);
            titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 2;
            formPanel.add(titleLabel, gbc);

            gbc.gridwidth = 1;
            gbc.gridy = 1;
            formPanel.add(new JLabel("Título:"), gbc);

            JTextField titleField = new JTextField(20);
            gbc.gridx = 1;
            formPanel.add(titleField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 2;
            formPanel.add(new JLabel("Descrição:"), gbc);

            JTextArea descArea = new JTextArea(3, 20);
            descArea.setLineWrap(true);
            JScrollPane scroll = new JScrollPane(descArea);
            gbc.gridx = 1;
            formPanel.add(scroll, gbc);

            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JButton cancelButton = new JButton("Cancelar");
            JButton saveButton = new JButton("Salvar");

            cancelButton.addActionListener(e -> dialog.dispose());
            saveButton.addActionListener(e -> {
                String titulo = titleField.getText().trim();
                String descricao = descArea.getText().trim();

                if (!titulo.isEmpty() && !descricao.isEmpty()) {
                    adicionarAtividade(titulo, descricao);
                    dialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(dialog,
                            "Preencha todos os campos!",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE);
                }
            });

            buttonPanel.add(cancelButton);
            buttonPanel.add(saveButton);

            gbc.gridx = 0;
            gbc.gridy = 3;
            gbc.gridwidth = 2;
            formPanel.add(buttonPanel, gbc);

            dialog.add(formPanel, BorderLayout.CENTER);
            dialog.setVisible(true);
        }

        public int getAtividadesCount() {
            return postItContainer.getComponentCount();
        }

        public String getNome() {
            return nome;
        }

        public int getNumero() {
            return numero;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TalhoesAtividadesApp app = new TalhoesAtividadesApp();
            app.setVisible(true);
        });
    }
}