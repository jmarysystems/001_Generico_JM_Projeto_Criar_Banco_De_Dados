/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package criar_banco_de_dados;

import br.com.jmary.home.Home;
import br.com.jmary.home.imagens.Imagens_Internas;
import br.com.jmary.home.jpa.DB;
import br.com.jmary.utilidades.Exportando;
import br.com.jmary.utilidades.JOPM;
import java.awt.Color;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
/*
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
*/

/**
 *
 * @author NewUser
 */
public class Tabela_JM extends javax.swing.JPanel {
    
    private ListSelectionModel lsmPesquisa;
    public DefaultTableModel   tmPesquisa;
    
    Exportando Exportando;
    
    Home Home;
    String query;
            
    /**
     * Creates new form Email_Mensagens_Por_Contato
     * @param Home2
     * @param query2
     */
    public Tabela_JM( Home Home2, String query2 ) {
        initComponents();
        
        Home = Home2;
        query = query2;
                
        tabelaInicio();
        
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 );
            tabelaResultSet( query );
        } catch( InterruptedException e ){  } } }.start();
    }
    
    public void setar_linha_na_tabela( Object[] rowData ){
        try{      
            
            tmPesquisa.addRow( rowData );     
            //TableColumn TColuna = tbPesquisa.getColumnModel().getColumn( 0 );
            //TColuna.setCellRenderer( coluna0 ); 
            
        } catch( Exception e ){}
    }
    
    private void home_setar_Banco_de_Dados_properties() {                                         
////////////////////////////////////////////////////////////////////////////////  
        try{                
            String nomeArquivoPropertiesASerCriado = "db_conf_derby";
            
            Properties props = new Properties();                        
            FileInputStream in = new FileInputStream( nomeArquivoPropertiesASerCriado + ".properties" );
            props.loadFromXML(in);
            in.close();  
            
            Home.setar_Banco_de_Dados(props);
            
        }catch( Exception e ){
            e.printStackTrace();
        }
//////////////////////////////////////////////////////////////////////////////// 
    }    
    
    private void tabelaResultSet( String query ){ try {   
        while ( tmPesquisa.getRowCount() > 0){ tmPesquisa.removeRow(0); }      

////////////////////////////////////////////////////////////////////////////////  
        home_setar_Banco_de_Dados_properties();
//////////////////////////////////////////////////////////////////////////////// 

        DB DB = new DB();
        Connection con = null; try{ con = DB.derby();       }catch(Exception e){}    
        Statement s = null; try{ s = con.createStatement(); }catch(Exception e){}        
        ResultSet rs = s.executeQuery(query);
        ResultSetMetaData rsMetaData = rs.getMetaData(); 
  
        int columnCount = rsMetaData.getColumnCount();
        String[] columnNames = new String[columnCount];
        
        int contador = 0;
        for (int i = 0; i < columnCount; i++) {
            contador = 1 + i;
            columnNames [i] = rsMetaData.getColumnName(contador);
        }
            
        tmPesquisa = new DefaultTableModel( null, columnNames );
        tbPesquisa.setModel(tmPesquisa);
        
        while( rs.next() ){
            Object[] objects = new Object[ columnCount ];
            
            for(int i=0;i<columnCount;i++){
                objects[i]=rs.getObject(i+1);
            }
            tmPesquisa.addRow( objects );
        }
        
        //setar_DefaultTableModel_tbPreferedSize(1);
        
        Class<Imagens_Internas> clazzHome = Imagens_Internas.class;
        JOPM JOptionPaneMod = new JOPM( 1, "CONSULTA SQL\n"
                + "\nSTATUS DA CONSULTA:"
                + "\nCONSULTA FINALIZADA COM SUCESSO!\n"
                + "\nOK Para Prosseguir"
                ,"Class: " + this.getClass().getName(), 
                new ImageIcon( clazzHome.getResource("logocangaco2.png")) );

    } catch( SQLException e ){ JOPM JOptionPaneMod = new JOPM( 2, "CONSULTA SQL, \n" + "\n", e.getMessage() ); } }   
            
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        tbPesquisa = new javax.swing.JTable();
        jp_opcoes_da_tabela = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        lbFiltro1 = new javax.swing.JLabel();
        lbFiltro2 = new javax.swing.JLabel();
        lbExcluirColuna = new javax.swing.JLabel();
        lbExcluirLinha = new javax.swing.JLabel();
        lb_Impressora = new javax.swing.JLabel();
        lb_Exportar_Exel = new javax.swing.JLabel();
        lbLinha_Tabela = new javax.swing.JLabel();
        lbColuna_Tabela = new javax.swing.JLabel();
        lb_Exportar_Html = new javax.swing.JLabel();
        lbSelecionar = new javax.swing.JLabel();

        jScrollPane2.setBorder(null);

        jScrollPane2.setViewportView(tbPesquisa);

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home_controle_menus_norte/imagens/zoom_out.png"))); // NOI18N
        jLabel9.setToolTipText("PROCURAR E EXCLUIR");
        jLabel9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel9MousePressed(evt);
            }
        });

        lbFiltro1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home_controle_menus_norte/imagens/filtro2.png"))); // NOI18N
        lbFiltro1.setToolTipText("FILTRAR COLUNA - PELO ITEM SELECIONADO");
        lbFiltro1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbFiltro1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbFiltro1MousePressed(evt);
            }
        });

        lbFiltro2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home_controle_menus_norte/imagens/filtro2reverso.png"))); // NOI18N
        lbFiltro2.setToolTipText("EXCLUIR TODOS DA COLUNA = ITEM SELECIONADO");
        lbFiltro2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbFiltro2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbFiltro2MousePressed(evt);
            }
        });

        lbExcluirColuna.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbExcluirColuna.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home_controle_menus_norte/imagens/tabela_vertical_red.png"))); // NOI18N
        lbExcluirColuna.setToolTipText("EXCLUIR COLUNA SELECIONADA");
        lbExcluirColuna.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbExcluirColuna.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbExcluirColunaMousePressed(evt);
            }
        });

        lbExcluirLinha.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbExcluirLinha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home_controle_menus_norte/imagens/tabela_horizontal_red.png"))); // NOI18N
        lbExcluirLinha.setToolTipText("EXCLUIR LINHA SELECIONADA");
        lbExcluirLinha.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbExcluirLinha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbExcluirLinhaMousePressed(evt);
            }
        });

        lb_Impressora.setForeground(new java.awt.Color(0, 102, 0));
        lb_Impressora.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home_controle_menus_norte/imagens/printv.png"))); // NOI18N
        lb_Impressora.setToolTipText("IMPRIMIR ");
        lb_Impressora.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lb_Impressora.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lb_ImpressoraMousePressed(evt);
            }
        });

        lb_Exportar_Exel.setForeground(new java.awt.Color(0, 102, 0));
        lb_Exportar_Exel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home_controle_menus_norte/imagens/xlsx.png"))); // NOI18N
        lb_Exportar_Exel.setToolTipText("EXPORTAR PARA EXCEL");
        lb_Exportar_Exel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lb_Exportar_Exel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lb_Exportar_ExelMousePressed(evt);
            }
        });

        lbLinha_Tabela.setForeground(new java.awt.Color(0, 102, 0));
        lbLinha_Tabela.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home_controle_menus_norte/imagens/tabela_horizontal.png"))); // NOI18N
        lbLinha_Tabela.setToolTipText("SELECIONAR HORIZONTALMENTE");
        lbLinha_Tabela.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbLinha_Tabela.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbLinha_TabelaMousePressed(evt);
            }
        });

        lbColuna_Tabela.setForeground(new java.awt.Color(0, 102, 0));
        lbColuna_Tabela.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home_controle_menus_norte/imagens/tabela_vertical.png"))); // NOI18N
        lbColuna_Tabela.setToolTipText("SELECIONAR VERTICALMENTE");
        lbColuna_Tabela.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbColuna_Tabela.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbColuna_TabelaMousePressed(evt);
            }
        });

        lb_Exportar_Html.setForeground(new java.awt.Color(0, 102, 0));
        lb_Exportar_Html.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home_controle_menus_norte/icons_32_x_32/large-text-file-viewer-icon-32.png"))); // NOI18N
        lb_Exportar_Html.setToolTipText("EXPORTAR PARA HTML");
        lb_Exportar_Html.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lb_Exportar_Html.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lb_Exportar_HtmlMousePressed(evt);
            }
        });

        lbSelecionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home_controle_menus_norte/icons_32_x_32/1clipboard-icon-32.png"))); // NOI18N
        lbSelecionar.setToolTipText("Visualizar");
        lbSelecionar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbSelecionar.setEnabled(false);
        lbSelecionar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbSelecionarMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jp_opcoes_da_tabelaLayout = new javax.swing.GroupLayout(jp_opcoes_da_tabela);
        jp_opcoes_da_tabela.setLayout(jp_opcoes_da_tabelaLayout);
        jp_opcoes_da_tabelaLayout.setHorizontalGroup(
            jp_opcoes_da_tabelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_opcoes_da_tabelaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbLinha_Tabela)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbColuna_Tabela)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbFiltro1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbFiltro2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbExcluirColuna, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbExcluirLinha, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lb_Impressora)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lb_Exportar_Exel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lb_Exportar_Html, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbSelecionar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jp_opcoes_da_tabelaLayout.setVerticalGroup(
            jp_opcoes_da_tabelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbSelecionar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbLinha_Tabela, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbColuna_Tabela, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbFiltro1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbFiltro2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbExcluirColuna, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lb_Impressora, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lb_Exportar_Exel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbExcluirLinha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lb_Exportar_Html, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jp_opcoes_da_tabela, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jp_opcoes_da_tabela, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lbLinha_TabelaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbLinha_TabelaMousePressed
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 );

            if ( tbPesquisa.getSelectedRow() != -1){
                tabelaModoDeSelecao( "Column Selection",true, false, false );
            }
            else{

                Class<Imagens_Internas> clazzHome = Imagens_Internas.class;
                JOPM JOptionPaneMod = new JOPM( 1, "ALTERAR MODO DE SELEÇÃO\n"
                    + "\nPARA ALTERAR O MODO DE SELEÇÃO\n"
                    + "\nPRIMEIRO SELECIONE UMA CÉLULA\n"
                    + "\nOK Para Prosseguir"
                    ,"Class: " + this.getClass().getName(),
                    new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
            }

        } catch( InterruptedException e ){  } } }.start();
    }//GEN-LAST:event_lbLinha_TabelaMousePressed

    private void lbColuna_TabelaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbColuna_TabelaMousePressed
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 );

            if ( tbPesquisa.getSelectedRow() != -1){
                tabelaModoDeSelecao( "Row Selection",false, true, false );
            }
            else{

                Class<Imagens_Internas> clazzHome = Imagens_Internas.class;
                JOPM JOptionPaneMod = new JOPM( 1, "ALTERAR MODO DE SELEÇÃO\n"
                    + "\nPARA ALTERAR O MODO DE SELEÇÃO\n"
                    + "\nPRIMEIRO SELECIONE UMA CÉLULA\n"
                    + "\nOK Para Prosseguir"
                    ,"Class: " + this.getClass().getName(),
                    new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
            }

        } catch( InterruptedException e ){  } } }.start();
    }//GEN-LAST:event_lbColuna_TabelaMousePressed

    private void lbExcluirColunaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbExcluirColunaMousePressed
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 );

            if ( tbPesquisa.getSelectedRow() != -1){
                int coluna = tbPesquisa.getSelectedColumn();
                TableColumn TColuna = tbPesquisa.getColumnModel().getColumn( coluna );
                tbPesquisa.removeColumn( TColuna );
            }
            else{

                Class<Imagens_Internas> clazzHome = Imagens_Internas.class;
                JOPM JOptionPaneMod = new JOPM( 1, "EXCLUIR COLUNA DA TABELA\n"
                    + "\nPARA EXCLUIR UMA COLUNA\n"
                    + "\nPRIMEIRO SELECIONE UMA\n"
                    + "\nOK Para Prosseguir"
                    ,"Class: " + this.getClass().getName(),
                    new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
            }

        } catch( InterruptedException e ){  } } }.start();
    }//GEN-LAST:event_lbExcluirColunaMousePressed

    private void jLabel9MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MousePressed
        /*
        if ( tbPesquisa.getSelectedRow() != -1){
            new Thread() {   @Override public void run() { try { Thread.sleep( 1 );

                Exportando = new Exportando("Procurando e Excluindo");
                Exportando.setVisible(true);
                Exportando.pbg.setMinimum(0);
                Exportando.pbg.setMaximum( 100 );
                ////////////////////////////////////////////////////////////////
                String contentsX = Classe_Externa_Recebida.jTextPane1.getText().trim();

                if(!contentsX.equals("")){

                    StringTokenizer st1=new StringTokenizer(contentsX,"\n");

                    int contadorElenco;
                    for (int i = 0; st1.hasMoreTokens(); i++) {
                        contadorElenco = 0;

                        String rowstring = st1.nextToken();
                        //              //System.out.println( "rowstring - " + rowstring );
                        try{
                            String[] dados = rowstring.split("\t");
                            contadorElenco++;
                            if(contadorElenco < 2){
                                String material_procurado = dados[0].trim();
                                Exportando.pbg.setValue( tmPesquisa.getRowCount() );
                                ///////////////////////////////////////////////////////////////////////////////////////////////////////////
                                for( int L_i=0; L_i < tmPesquisa.getRowCount(); L_i++ ){
                                    Exportando.pbg.setValue(L_i);

                                    for( int C_i=0; C_i < tmPesquisa.getColumnCount(); C_i++ ){ try{
                                        String strn = tmPesquisa.getColumnName(C_i);

                                        if( strn.trim().equalsIgnoreCase("ID") ){
                                            String sap = String.valueOf( tmPesquisa.getValueAt(L_i, C_i) ).trim();

                                            if( material_procurado.equals(sap) ){

                                                tmPesquisa.removeRow( L_i );
                                            }
                                        }
                                    } catch( Exception e ){  } }
                                }
                                ///////////////////////////////////////////////////////////////////////////////////////////////////////////
                            }
                        } catch( Exception e ){ e.printStackTrace(); }

                    }

                    Exportando.fechar();
                    Class<Imagens_Internas> clazzHome = Imagens_Internas.class;
                    //ImageIcon icon = new ImageIcon( clazzHome.getResource( imgURLIcon ) );
                    JOPM JOptionPaneMod = new JOPM( 1, "EXCLUSÃO DE DADOS\n"
                        + "\nSTATUS DA EXCLUSÃO:"
                        + "\nFINALIZADA COM SUCESSO!\n"
                        + "\nOK Para Prosseguir"
                        ,"Class: " + this.getClass().getName(),
                        new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
                }
                else{

                    Exportando.fechar();

                    Class<Imagens_Internas> clazzHome = Imagens_Internas.class;
                    //ImageIcon icon = new ImageIcon( clazzHome.getResource( imgURLIcon ) );
                    JOPM JOptionPaneMod = new JOPM( 1, "EXCLUSÃO DE DADOS\n"
                        + "\nSTATUS DA EXCLUSÃO:"
                        + "\nNÃO HÁ DADOS INFORMADO PARA PROCURA!\n"
                        + "\nOK Para Prosseguir"
                        ,"Class: " + this.getClass().getName(),
                        new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
                }
            } catch( InterruptedException e ){ Exportando.fechar(); } } }.start();
        }
        else{

            Exportando.fechar();

            Class<Imagens_Internas> clazzHome = Imagens_Internas.class;
            JOPM JOptionPaneMod = new JOPM( 1, "EXCLUSÃO DE DADOS\n"
                + "\nPARA EXCLUIR DADOS DA TABELA\n"
                + "\nPRIMEIRO INFORME OS NÚMEROS DOS MATERIAIS\n"
                + "\nOK Para Prosseguir"
                ,"Class: " + this.getClass().getName(),
                new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
        }
        */
    }//GEN-LAST:event_jLabel9MousePressed

    private void lbFiltro1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbFiltro1MousePressed
        if ( tbPesquisa.getSelectedRow() != -1){
            
            new Thread() {   @Override public void run() { try {
                
                int coluna = tbPesquisa.getSelectedColumn();

                String filtro = String.valueOf( tbPesquisa.getValueAt(tbPesquisa.getSelectedRow(), tbPesquisa.getSelectedColumn()) );
            
                Exportando Exportando = new Exportando("APLICANDO O FILTRO...");
                Exportando.setVisible(true);
                Exportando.pbg.setMinimum(0);
            
                int numFila = tmPesquisa.getRowCount();
            
                Exportando.pbg.setMaximum( numFila );
            
                Thread.sleep( 1 );
                                                
                for (int i = 0; i < numFila; i++) {     Exportando.pbg.setValue( i );
                
                    try{
                    
                        String str = String.valueOf( tmPesquisa.getValueAt(i, coluna) ).trim();  
                    
                        if( !str.equals(filtro) ){
                        
                            tmPesquisa.removeRow( i );       
                            i = -1;
                            //System.out.println( str + "  -------------  " + i + "  " + coluna);
                        }
                    } catch( Exception e ){ }
                    //System.out.println( "++++++++++++++  " + i + "  " + coluna);
                }

                Exportando.fechar();
                
            } catch( Exception e ){ System.out.println("Exportar - "); e.printStackTrace(); } } }.start(); 
        }
        else{

            Class<Imagens_Internas> clazzHome = Imagens_Internas.class;
            JOPM JOptionPaneMod = new JOPM( 1, "FILTRA TABELA\n"

                + "\nPara filtrar dados da tabela 1º selecione uma célula\n"
                + "\nOK Para Prosseguir"
                ,"Class: " + this.getClass().getName(),
                new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
        }
    }//GEN-LAST:event_lbFiltro1MousePressed

    private void lbFiltro2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbFiltro2MousePressed
        if ( tbPesquisa.getSelectedRow() != -1){
            
            new Thread() {   @Override public void run() { try {
                
                int colunaReverso = tbPesquisa.getSelectedColumn();

                String filtroReverso = String.valueOf( tbPesquisa.getValueAt(tbPesquisa.getSelectedRow(), tbPesquisa.getSelectedColumn()) );
                
                Exportando Exportando = new Exportando("APLICANDO O FILTRO...");
                Exportando.setVisible(true);
                Exportando.pbg.setMinimum(0);
            
                int numFila=tmPesquisa.getRowCount();
            
                Exportando.pbg.setMaximum( numFila );
            
                Thread.sleep( 1 );
                                                
                for (int i = 0; i < numFila; i++) {     Exportando.pbg.setValue( i );
                
                    try{
                    
                        String str = String.valueOf( tmPesquisa.getValueAt(i, colunaReverso) ).trim();  
                    
                        if( str.equals(filtroReverso) ){
                        
                            tmPesquisa.removeRow( i );       
                            i = -1;
                            //System.out.println( str + "  -------------  " + i + "  " + coluna);
                        }
                    } catch( Exception e ){ }
                    //System.out.println( "++++++++++++++  " + i + "  " + coluna);
                }

                Exportando.fechar();
            
            } catch( Exception e ){ System.out.println("Exportar - "); e.printStackTrace(); } } }.start();  
        }
        else{

            Class<Imagens_Internas> clazzHome = Imagens_Internas.class;
            JOPM JOptionPaneMod = new JOPM( 1, "FILTRO REVERSO TABELA\n"

                + "\nPara filtrar dados da tabela 1º selecione uma célula\n"
                + "\nOK Para Prosseguir"
                ,"Class: " + this.getClass().getName(),
                new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
        }
    }//GEN-LAST:event_lbFiltro2MousePressed
    
    private void lb_ImpressoraMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_ImpressoraMousePressed
        /*
        if( lb_Impressora.isEnabled() == true ){

            new Thread() { @Override public void run() { try{

                Object[] options = {
                    "IMPRIMIR",
                    "CANCELAR"
                };

                int n = JOptionPane.showOptionDialog(null,
                    "Confirme a Impressão.",
                    "Opção de Imprimir",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);
                if(n==0){
                    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                    Exportando = new Exportando("IMPRIMINDO...");
                    Exportando.setVisible(true);Exportando.pbg.setMinimum(0);
                    Exportando.pbg.setMaximum( 100 );
                    Exportando.pbg.setValue( 50 );

                    Produtos_Por_Ean_Imprimir_01_Controle t1 = new Produtos_Por_Ean_Imprimir_01_Controle( tbPesquisa );
                    t1.setName("ControleThread_Print");
                    t1.start();

                    try{ Thread.sleep( 1000 ); } catch( Exception e ){ }
                    Exportando.fechar();
                }
                /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            }catch( Exception e ){ } } }.start();

        }
        */
    }//GEN-LAST:event_lb_ImpressoraMousePressed

    private void lb_Exportar_ExelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_Exportar_ExelMousePressed
        /*
        if( lb_Exportar_Exel.isEnabled() == true ){

            new Thread() { @Override public void run() { try{

                Object[] options = {
                    "EXPORTAR PARA EXCEL",
                    "CANCELAR"
                };

                int n = JOptionPane.showOptionDialog(null,
                    "Confirme a Exportação.",
                    "Opção Exportação",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);
                if(n==0){
                    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                    int numLinhas = -1; try{ numLinhas = tbPesquisa.getRowCount();    } catch( Exception e ){}
                    int numColuna = -1; try{ numColuna = tbPesquisa.getColumnCount(); } catch( Exception e ){}
                    if ( numLinhas != -1){
                        try {
                            Arquivo_Ou_Pasta.criarPasta(System.getProperty("user.dir"), "Arquivos_Exportados");
                            Thread.sleep( 1 );
                            /////////////////////////////////////////////////////////////////////////////////////
                            //String entrada = System.getProperty("user.dir") + "//"+ "Arquivos" + "//" +
                            Class<Produtos_Por_Ean_Excel_Modelo> clazz_Entrada_Excel = Produtos_Por_Ean_Excel_Modelo.class;
                            ////////////////////////////////////////////////////////////////////////////////////
                            String saida = System.getProperty("user.dir") + "//"+ "Arquivos_Exportados" + "//" +
                            "estabelecimento_Excel" + ".xlsx";
                            ///////////////////////////////////////////////////////////////////////////////////
                            Arquivo_Ou_Pasta.deletar(new File(saida));
                            Thread.sleep( 1 );

                            Exportando = new Exportando("EXPORTANDO DADOS...");
                            Exportando.setVisible(true);Exportando.pbg.setMinimum(0);

                            //FileInputStream fileIn = null;
                            FileOutputStream fileOut = null;

                            try{

                                //fileIn = new FileInputStream( entrada );
                                //fileIn = new FileInputStream( clazzHome.getResourceAsStream("usuarios_do_sistema_excel_modelo.xlsx") );
                                //XSSFWorkbook wb = new XSSFWorkbook(fileIn);
                                XSSFWorkbook wb = new XSSFWorkbook(clazz_Entrada_Excel.getResourceAsStream("estabelecimento_excel_modelo.xlsx"));
                                XSSFSheet aba = wb.getSheetAt(0);

                                Exportando.pbg.setMaximum( numLinhas );

                                XSSFCell cell;
                                for (int i = -1; i < numLinhas; i++) {
                                    int linhaParaescrever = i+2;
                                    XSSFRow linha = aba.getRow(linhaParaescrever);
                                    //XSSFRow linha = aba.createRow(i+1);

                                    for (int j = 0; j < numColuna ; j++) {

                                        cell = linha.getCell(j);
                                        if (cell == null) cell = linha.createCell(j);

                                        try{ cell.setCellType(XSSFCell.CELL_TYPE_STRING); }catch(Exception e){}
                                        try{ cell.setCellValue(XSSFCell.CELL_TYPE_STRING); }catch(Exception e){}

                                        if(i==-1){
                                            cell.setCellValue(String.valueOf(tbPesquisa.getColumnName(j)));
                                        }else{

                                            cell.setCellValue(String.valueOf(tbPesquisa.getValueAt(i, j)));
                                        }
                                    }

                                    Exportando.pbg.setValue( i );
                                }

                                wb.setForceFormulaRecalculation(true);
                                fileOut = new FileOutputStream( saida );
                                wb.write(fileOut);

                                try{
                                    fileOut.close();
                                    //fileIn.close();
                                } catch(Exception e) {}

                                java.awt.Desktop.getDesktop().open( new File( saida ) );
                            } catch(Exception e) {}

                            Exportando.fechar();
                        }catch( Exception e ){ e.printStackTrace(); }
                    }
                    else{

                        Class<Imagens_Internas> clazzHome = Imagens_Internas.class;
                        //ImageIcon icon = new ImageIcon( clazzHome.getResource( imgURLIcon ) );
                        JOPM JOptionPaneMod = new JOPM( 1, "EXPORTAR PARA EXCEL\n"
                            + "\nSTATUS DA EXPORTAÇÃO:"
                            + "\nPARA EXPORTAR É NECESSÁRIO QUE A TABELA NÃO ESTEJA VAZIA!\n"
                            + "\nOK Para Prosseguir"
                            ,"Class: " + this.getClass().getName(),
                            new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
                    }
                    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                }
            }catch( Exception e ){ } } }.start();

        }
        */
    }//GEN-LAST:event_lb_Exportar_ExelMousePressed

    private void lb_Exportar_HtmlMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_Exportar_HtmlMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_lb_Exportar_HtmlMousePressed

    private void lbSelecionarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbSelecionarMousePressed
        /*
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 );

            if ( tbPesquisa.getSelectedRow() != -1){
                Exportando = new Exportando("ABRINDO...");
                Exportando.setVisible(true);Exportando.pbg.setMinimum(0);
                Exportando.pbg.setMaximum( 100 );
                Exportando.pbg.setValue( 50 );

                if( lbSelecionar.isEnabled() == true ){

                    List<ProdutosPorEan>  List_Estabelecimento = null;
                    int id = 0;

                    try{
                        int linhaSelecionada = tbPesquisa.getSelectedRow();//pegar a linha selecionada
                        id = (int) tbPesquisa.getValueAt(linhaSelecionada, 0);//pegar os valores da linha e coluna
                    }catch(Exception e){}

                    System.out.println("ID DA TABELA SELECIONADA: " + id);

                    List<ProdutosPorEan> List_2_Estabelecimento = null;
                    try{
                        Query q2 = JPAUtil.em().createNativeQuery("SELECT * FROM " + Banco_Ctrl_Tabela_BD.get() + " WHERE ID = ?", ProdutosPorEan.class );
                        q2.setParameter( 1, id );
                        List_2_Estabelecimento = q2.getResultList();
                    }catch(Exception e){}

                    String rbusca = ""; try{ rbusca = List_2_Estabelecimento.get(0).getDescricao(); }catch( Exception e ){}
                    if( !rbusca.equals("") ){

                        if( Classe_Externa_Recebida.nome_da_classe_externa_a_consultar.equals( "Familia_02_Cadastrar_Visualizar - Estabelecimento" ) ){

                            //Familia_02_Cadastrar_Visualizar_externa_recebido.tf_Externo_Selecionado_Estabelecimento.setText( List_2_Estabelecimento.get(0).getNomeOuFantasia());
                            //Familia_02_Cadastrar_Visualizar_externa_recebido.EstabelecimentoQueVaiAcessar = List_2_Estabelecimento.get(0);

                            //Home.ControleTabs.removerTabSemControleSelecionado(jTabselecionar_externo_recebido);
                        }
                    }

                    Exportando.fechar();
                }
                else{

                    Class<Imagens_Internas> clazzHome = Imagens_Internas.class;
                    JOPM JOptionPaneMod = new JOPM( 1, "SELECIONAR ITEM SELECIONADO\n"
                        + "\nPARA SELECIONAR UM ITEM\n"
                        + "\nPRIMEIRO SELECIONE UMA CÉLULA\n"
                        + "\nOK Para Prosseguir"
                        ,"Class: " + this.getClass().getName(),
                        new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
                }
            }

        } catch( InterruptedException e ){ Exportando.fechar(); e.printStackTrace(); } } }.start();
        */
    }//GEN-LAST:event_lbSelecionarMousePressed

    private void lbExcluirLinhaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbExcluirLinhaMousePressed
        new Thread() {   @Override public void run() { try {   
                    
            int r = tbPesquisa.getSelectedRow();
            int c = tbPesquisa.getSelectedColumn();
            
            //if( c==0 ){
                    
                Object[] options = { "Confirmar", "Cancelar" };
                int n = JOptionPane.showOptionDialog(null,
                    "Confirme a exclusão da linha: \n"+ r,
                    "Confirmar Exclusão",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null, options, options[0] 
                );
                
                if(n==0){
                        
                    tmPesquisa.removeRow( tbPesquisa.getSelectedRow() );
                }
            //}            
        } catch( Exception e ){  } } }.start();
    }//GEN-LAST:event_lbExcluirLinhaMousePressed

    /*
    DefaultTableCellRenderer coluna0 = new DefaultTableCellRenderer() {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){ 
            
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);  
            
            JLabel label = (JLabel)super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                        
            if (value instanceof ImageIcon) { 
                //System.out.println("if (value instanceof ImageIcon) { ");
                
                Rectangle cellRect = table.getCellRect(row, column, false);                
                
                Image image;
                ImageIcon imageIcon;
                try {  
                    imageIcon = (ImageIcon)value;
                    image = imageIcon.getImage();
                    
                    double x = cellRect.getWidth();
                    
                    Icon icon = 
                            new ImageIcon(image.getScaledInstance(
                            (int) x, (int) cellRect.getHeight(), 
                            Image.SCALE_DEFAULT) );
                                                                        
                    label.setIcon( icon );
                    
                    //setText(" ");
                    
                    setToolTipText( "Excluir Linha" );

                }catch(Exception ex){}
                
                if(row == linhaX && column == colunaX){
                    this.setBackground(Color.GREEN);
                }
                else{
                    this.setBackground(table.getBackground());
                }
                
            } else if (value instanceof String) {
                //System.out.println("} else if (value instanceof String) {");
                
                setText((String) value);  
            }  
            
            return this;  
        } 
    };
    */
    
    int linhaX, colunaX;
    class CellListener extends MouseMotionAdapter{
      
      @Override
      public void mouseMoved(MouseEvent e){
  	JTable tb = (JTable)e.getSource();
        linhaX = tb.rowAtPoint(e.getPoint());
        colunaX = tb.columnAtPoint(e.getPoint());
        tb.repaint();
      }
      
      public void mouseClick(MouseEvent e){
  	JTable tb = (JTable)e.getSource();
        linhaX = tb.rowAtPoint(e.getPoint());
        colunaX = tb.columnAtPoint(e.getPoint());
        tb.repaint();
      }
    }
    
    private void tabelaInicio(){
        /*new Thread() {   @Override public void run() {*/ try { //Thread.sleep( 1 ); 
            
            ////////////////////////////////////////////////////////////////
            lbLinha_Tabela.setEnabled(false);
            lbColuna_Tabela.setEnabled(false);
            jLabel9.setEnabled(false);
            lbFiltro1.setEnabled(false);
            lbFiltro2.setEnabled(false);
            lbExcluirColuna.setEnabled(false);
            lbExcluirLinha.setEnabled(false);
            lb_Impressora.setEnabled(false);
            lb_Exportar_Exel.setEnabled(false);
            lb_Exportar_Html.setEnabled(false);
            
            jp_opcoes_da_tabela.setVisible(false);
            ////////////////////////////////////////////////////////////////
        
            tmPesquisa = cabecalho_1; 
            
            lsmPesquisa = tbPesquisa.getSelectionModel();
            setar_Cabecalho_da_Tabela(1); 
            setar_DefaultTableModel_tbPreferedSize(1); 
                                
            // quero exibir imagens ao lado do texto da primeira coluna
            //TableCellRenderer tcr = new Imagem_Da_Tabela( tmPesquisa, tbPesquisa );
            //TableColumn column = tbPesquisa.getColumnModel().getColumn(0);
	    //column.setCellRenderer(tcr);
        
            //TableColumn TColuna = tbPesquisa.getColumnModel().getColumn( 0 );
            //TColuna.setCellRenderer( coluna0 ); 
            
            tbPesquisa.addMouseMotionListener(new CellListener());
        
            tbPesquisa.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            
            tbPesquisa.setAutoCreateRowSorter(true);

            tabelaModoDeSelecao( "Multiple Interval Selection", false, false, false );                        
            
            ////////////////////////////////////////////////////////////////////
            lsmPesquisa.addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    if ( !e.getValueIsAdjusting() ){
                        tbPesquisaLinhaSelecionada();
                    }
                }
                public void mouseClicked(MouseEvent e) {

                }
            });
            ////////////////////////////////////////////////////////////////////
                                    
        } catch( Exception e ){ JOPM JOptionPaneMod = new JOPM( 2, "tabelaInicio(), \n"
                + e.getMessage() + "\n", this.getClass().getSimpleName() ); } //} }.start();              
    }
    
    public void tabelaModoDeSelecao( String command, boolean linhaCheck, boolean colunaCheck, boolean selecionar_celula ) {
        
        if ("Row Selection".equals(command)) {
            
            tbPesquisa.setRowSelectionAllowed(linhaCheck);
            //In MIS mode, column selection allowed must be the
            //opposite of row selection allowed.
            if (!selecionar_celula) {
                tbPesquisa.setColumnSelectionAllowed(!linhaCheck);
            }
            
        } else if ("Column Selection".equals(command)) {
            
            tbPesquisa.setColumnSelectionAllowed(colunaCheck);
            //In MIS mode, row selection allowed must be the
            //opposite of column selection allowed.
            if (!selecionar_celula) {
                tbPesquisa.setRowSelectionAllowed(!colunaCheck);
            }

        } else if ("Cell Selection".equals(command)) {
            
            tbPesquisa.setCellSelectionEnabled(selecionar_celula);
            
        } else if ("Multiple Interval Selection".equals(command)) {
            
            tbPesquisa.setSelectionMode(
                    ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
            //If cell selection is on, turn it off.
            if (selecionar_celula) {
                selecionar_celula = false;
                tbPesquisa.setCellSelectionEnabled(false);
            }
            //And don't let it be turned back on.
            selecionar_celula = false;
            
        } else if ("Single Interval Selection".equals(command)) {
            
            tbPesquisa.setSelectionMode(
                    ListSelectionModel.SINGLE_INTERVAL_SELECTION);
            //Cell selection is ok in this mode.
            selecionar_celula = true;
            
        } else if ("Single Selection".equals(command)) { 
            
            tbPesquisa.setSelectionMode(
                    ListSelectionModel.SINGLE_SELECTION);
            //Cell selection is ok in this mode.
            selecionar_celula = true;                       
        }
    }
                            
    String controltbPesquisaLinhaSelecionada = "";
    public void tbPesquisaLinhaSelecionada() { 
        try{
            
            //Se houver uma célula selecionada.
            if ( tbPesquisa.getSelectedRow() != -1){               

                /*
                Classe_Externa_Recebida.lbEditar        .setEnabled(true);
                Classe_Externa_Recebida.lbDesativar     .setEnabled(true);
                Classe_Externa_Recebida.lbVisualizar    .setEnabled(true);
                
                Classe_Externa_Recebida.lbSelecionar.setEnabled(true);
                */
                
                ////////////////////////////////////////////////////////////////
                lbLinha_Tabela.setEnabled(true);
                lbColuna_Tabela.setEnabled(true);
                jLabel9.setEnabled(true);
                lbFiltro1.setEnabled(true);
                lbFiltro2.setEnabled(true);
                lbExcluirColuna.setEnabled(true);
                lbExcluirLinha.setEnabled(true);
                lb_Impressora.setEnabled(true);
                lb_Exportar_Exel.setEnabled(true);
                lb_Exportar_Html.setEnabled(true); 
                
                jp_opcoes_da_tabela.setVisible(true);
                ////////////////////////////////////////////////////////////////

                //excluir_linha();
            } 
            else{
                /*
                Classe_Externa_Recebida.lbEditar     .setEnabled(false);
                Classe_Externa_Recebida.lbDesativar .setEnabled(false);
                Classe_Externa_Recebida.lbVisualizar.setEnabled(false);
                
                Classe_Externa_Recebida.lbSelecionar.setEnabled(false);
                */
                
                ////////////////////////////////////////////////////////////////
                lbLinha_Tabela.setEnabled(false);
                lbColuna_Tabela.setEnabled(false);
                jLabel9.setEnabled(false);
                lbFiltro1.setEnabled(false);
                lbFiltro2.setEnabled(false);
                lbExcluirColuna.setEnabled(false);
                lbExcluirLinha.setEnabled(false);
                lb_Impressora.setEnabled(false);
                lb_Exportar_Exel.setEnabled(false);
                lb_Exportar_Html.setEnabled(false);
                
                jp_opcoes_da_tabela.setVisible(false);
                ////////////////////////////////////////////////////////////////
                                
            }
        } catch( Exception e ) {}
    }
    
    private void excluir_linha(){
      
        new Thread() {   @Override public void run() { try {   
            //pegando texto da coluna 1 e da linha selecionada
            //String nome = (String) tbPesquisa.getValueAt(tbPesquisa.getSelectedRow(), 1);
            //pegando texto da coluna 2 e da linha selecionada
            //String email = (String) tbPesquisa.getValueAt(tbPesquisa.getSelectedRow(), 2);
                    
            int r = tbPesquisa.getSelectedRow();
            int c = tbPesquisa.getSelectedColumn();
            
            //System.out.println("int c = tbPesquisa.getSelectedColumn(); - " + c);
            
            if( c==0 ){
                    
                Object[] options = { "Confirmar", "Cancelar" };
                int n = JOptionPane.showOptionDialog(null,
                    "Confirme a exclusão da linha: \n"+ r,
                    "Confirmar Exclusão",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null, options, options[0] 
                );
                
                if(n==0){
                        
                    tmPesquisa.removeRow( tbPesquisa.getSelectedRow() );
                }
                                                
                //tmPesquisa.removeRow(tbPesquisa.getSelectedRow());
                //tbPesquisa.repaint();
                //tbPesquisa.validate();
            }            
        } catch( Exception e ){  } } }.start();
  }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel jp_opcoes_da_tabela;
    private javax.swing.JLabel lbColuna_Tabela;
    private javax.swing.JLabel lbExcluirColuna;
    private javax.swing.JLabel lbExcluirLinha;
    public javax.swing.JLabel lbFiltro1;
    public javax.swing.JLabel lbFiltro2;
    private javax.swing.JLabel lbLinha_Tabela;
    public javax.swing.JLabel lbSelecionar;
    public javax.swing.JLabel lb_Exportar_Exel;
    public javax.swing.JLabel lb_Exportar_Html;
    public javax.swing.JLabel lb_Impressora;
    public javax.swing.JTable tbPesquisa;
    // End of variables declaration//GEN-END:variables
        
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////   
    public DefaultTableModel cabecalho_1 = new DefaultTableModel( null, new String[]{ 
        "", "ID", "CODIGO AUXILIAR", "EAN", "DESCRIÇÃO DO PRODUTO"
    } );
    
    public void setar_Cabecalho_da_Tabela(int opcao){  
        try{
            
            switch(opcao){
                
                case 1:
                    while ( tmPesquisa.getRowCount() > 0){ tmPesquisa.removeRow(0); }                     
                    tmPesquisa = cabecalho_1;                    
                    tbPesquisa.setModel(tmPesquisa);
                break;    
                
            }            
        } catch( Exception e ){ e.printStackTrace(); }
    }
    
    public void setar_DefaultTableModel_tbPreferedSize(int opcao){ 
        try{
            
            switch(opcao){
                
                case 1:
                    DefaultTableCellRenderer rendererCentro = new DefaultTableCellRenderer();
                    rendererCentro.setHorizontalAlignment(SwingConstants.CENTER);
                    
                    DefaultTableCellRenderer rendererDireita = new DefaultTableCellRenderer();
                    rendererDireita.setHorizontalAlignment(SwingConstants.RIGHT);
                    
                    int count = 0;
                    
                    tbPesquisa.getColumnModel().getColumn(count).setPreferredWidth(30);
                    tbPesquisa.getColumnModel().getColumn(count).setResizable(true);
                    tbPesquisa.getColumnModel().getColumn(count).setCellRenderer( rendererCentro );
                    count++;
                    
                    tbPesquisa.getColumnModel().getColumn(count).setPreferredWidth(60);
                    tbPesquisa.getColumnModel().getColumn(count).setResizable(true);
                    tbPesquisa.getColumnModel().getColumn(count).setCellRenderer( rendererCentro );
                    count++;
                    
                    tbPesquisa.getColumnModel().getColumn(count).setPreferredWidth(120);
                    tbPesquisa.getColumnModel().getColumn(count).setResizable(true);
                    tbPesquisa.getColumnModel().getColumn(count).setCellRenderer( rendererCentro );
                    count++;
                                                    
                    tbPesquisa.getColumnModel().getColumn(count).setPreferredWidth(120);
                    tbPesquisa.getColumnModel().getColumn(count).setResizable(true);
                    count++;
                    
                    tbPesquisa.getColumnModel().getColumn(count).setPreferredWidth(450);
                    tbPesquisa.getColumnModel().getColumn(count).setResizable(true);
                    //tbPesquisa.getColumnModel().getColumn(count).setCellRenderer( rendererCentro );

                    tbPesquisa.setRowHeight(30);
                    tbPesquisa.setSelectionBackground(Color.YELLOW);
                    tbPesquisa.setSelectionForeground(Color.BLUE);
                    
                    tbPesquisa.getTableHeader().setReorderingAllowed(true);
                    //tbPesquisa.getTableHeader().setResizingAllowed(true);            
                    tbPesquisa.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);

                    //tbPesquisa.grabFocus();
                    //tbPesquisa.getTableHeader().setReorderingAllowed(true);
                    //tbPesquisa.getTableHeader().setResizingAllowed(false);            
                    //tbPesquisa.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
                break;    
                
            }            
        } catch( Exception e ){ e.printStackTrace(); }
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    
        
}