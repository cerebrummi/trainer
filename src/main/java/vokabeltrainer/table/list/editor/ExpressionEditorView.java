package vokabeltrainer.table.list.editor;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListCellRenderer;
import javax.swing.border.TitledBorder;
import javax.swing.text.JTextComponent;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import vokabeltrainer.ApplicationImages;
import vokabeltrainer.BackgroundPanelTiled;
import vokabeltrainer.InfoTextField;
import vokabeltrainer.KeyboardHebrew;
import vokabeltrainer.Settings;
import vokabeltrainer.TextImage;
import vokabeltrainer.WideComboBox;
import vokabeltrainer.common.Common;
import vokabeltrainer.common.Data;
import vokabeltrainer.common.Main;
import vokabeltrainer.editing.ExtraInformationDocument;
import vokabeltrainer.editing.GermanDocument;
import vokabeltrainer.editing.HebrewDocument;
import vokabeltrainer.tonionlayout.TotemLayout;
import vokabeltrainer.tonionlayout.TrainLayout;
import vokabeltrainer.types.Binjan;
import vokabeltrainer.types.Chapter;
import vokabeltrainer.types.Database;
import vokabeltrainer.types.Expression;
import vokabeltrainer.types.ExpressionKind;
import vokabeltrainer.types.Gender;
import vokabeltrainer.types.Numerus;

public class ExpressionEditorView extends JDialog
      implements ExpressionEditorViewConnector
{
   private static final int WIDTH_INFO_PANEL = 240;

   private static final int WIDTH_INPUT_PANEL = Settings.getKeyboardWidth();

   private static final long serialVersionUID = 5853498340870217732L;

   private Expression expression;
   private JTextField german;
   private JTextField hebrewInLatin;
   private InfoTextField hebrew;
   private JComboBox<Gender> genderHebrew;
   private JComboBox<Numerus> numerusHebrew;
   private WideComboBox<ExpressionKind> kind;
   private InfoTextField newSearchwordGerman;
   private JList<String> searchwordsJListGerman;
   private Set<String> searchwordsSetGerman;
   private InfoTextField newSearchwordHebrew;
   private JList<String> searchwordsJListHebrew;
   private Set<String> searchwordsSetHebrew;
   private KeyboardHebrew keyboard;
   private JPanel layout;
   private JButton deleteSearchwordButtonGerman;
   private JButton deleteSearchwordButtonHebrew;
   private boolean save;
   private ExpressionKind oldKind;
   private JButton saveButton;
   private JButton restoreButton;
   private JButton cancelButton;
   private List<JTextComponent> components = new ArrayList<>();
   private String germanTitle = "Deutsch";
   private String hebrewInLatinTitle = "Lautschrift";
   private String searchwordJListGermanTitle = "Deutsche Suchwörter";
   private String searchwordsJListHebrewTitle = "Hebräische Suchwörter";
   private String chapterTitle = "Lektion";
   private JComboBox<String> chapter;
   private JButton infoExpressionKindButton;
   private JComboBox<Binjan> binjan;
   private JPanel swapPanel;
   private CardLayout cardLayout;

   @SuppressWarnings("unused")
   private ExpressionEditorControllerConnector connector;

   private JPanel verticalUmgangssprachePanel;
   private JPanel verticalZahlPanel;
   private JPanel verticalUhrzeitPanel;
   private JPanel verticalWochentagPanel;
   private JPanel verticalVerbPanel;
   private JPanel verticalSubstantivPanel;
   private JPanel verticalRedewendungPanel;
   private JPanel verticalPronomPanel;
   private JPanel verticalPraepositionPanel;
   private JPanel verticalPossesivPronomPanel;
   private JPanel verticalPersonalSuffixPanel;
   private JPanel verticalPersonalPronomPanel;
   private JPanel verticalPersonalPraefixPanel;
   private JPanel verticalPartikelPanel;
   private JPanel verticalOrdnungszahlPanel;
   private JPanel verticalModalVerbPanel;
   private JPanel verticalKontruktPanel;
   private JPanel verticalJahreszeitPanel;
   private JPanel verticalGlueckwunschPanel;
   private JPanel verticalFragewortPanel;
   private JPanel verticalFarbePanel;
   private JPanel verticalEigennamePanel;
   private JPanel verticalDemonstrativPronomPanel;
   private JPanel verticalBindewortPanel;
   private JPanel verticalBerufPanel;
   private JPanel verticalAufrufPanel;
   private JPanel verticalAltersangabePanel;
   private JPanel verticalAdverbPanel;
   private JPanel verticalAdjektivPanel;
   private JPanel verticalUnkownPanel;
   private JTextPane extraInfo;

   private JScrollPane extraInfoScroller;

   public ExpressionEditorView(ExpressionEditorControllerConnector connector)
   {
      super(Common.getjFrame(), "Cerebrummi©",
            Dialog.ModalityType.APPLICATION_MODAL);
      this.connector = connector;
      save = false;
      setSize(1271, 680);
      layout = new BackgroundPanelTiled();
      layout.setBorder(
            BorderFactory.createLineBorder(Settings.getGreen(), 15, false));
      layout.setLayout(new TrainLayout(layout, 15));
      getContentPane().add(layout);

      initGuiFields();
      initInput();
      initInfosLeft();
      initInfosRight();
      initInfosExtra();

      initController();
      Component[] focusList = { german, hebrewInLatin, hebrew,
            newSearchwordGerman, newSearchwordHebrew, saveButton, restoreButton,
            cancelButton };
      this.setFocusTraversalPolicy(
            new CerebrummiFocusTraversalPolicy(focusList));
   }

   private void initGuiFields()
   {
      Font germanfont = Main.getGermanFont(16F);
      Font hebrewfont = Main.getHebrewFont(30F);

      infoExpressionKindButton = new JButton(
            new ImageIcon(ApplicationImages.getInfoButtonIcon()));
      infoExpressionKindButton.setBackground(new Color(0, 0, 0, 0));
      infoExpressionKindButton.setMinimumSize(new Dimension(50, 50));
      infoExpressionKindButton.setMaximumSize(new Dimension(50, 50));

      german = new JTextField();
      german.setBorder(makeBorderBlank(germanTitle));
      german.setFont(germanfont);
      german.setMinimumSize(new Dimension(WIDTH_INPUT_PANEL, 70));
      german.setMaximumSize(new Dimension(WIDTH_INPUT_PANEL, 70));
      german.setDocument(new GermanDocument(true));

      hebrewInLatin = new JTextField();
      hebrewInLatin.setBorder(makeBorderBlank(this.hebrewInLatinTitle));
      hebrewInLatin.setFont(germanfont);
      hebrewInLatin.setMinimumSize(new Dimension(WIDTH_INPUT_PANEL, 70));
      hebrewInLatin.setMaximumSize(new Dimension(WIDTH_INPUT_PANEL, 70));
      hebrewInLatin.setDocument(new GermanDocument(true));

      hebrew = new InfoTextField("Hebräisch", "Bitte hineinklicken oder  ",
            "mit der Tabulatortaste auswählen.  ",
            "Dann hebräische Tastatur benutzen.  ");
      hebrew.setBlankBorder();
      hebrew.setDocument(new HebrewDocument(true));

      genderHebrew = new JComboBox<>();
      genderHebrew.setBorder(new TitledBorder("Geschlecht Hebräisch"));
      genderHebrew.setModel(Gender.getHebrewModel());
      genderHebrew.setFont(germanfont);
      genderHebrew.setEditable(false);
      genderHebrew.setMinimumSize(new Dimension(WIDTH_INFO_PANEL, 50));
      genderHebrew.setMaximumSize(new Dimension(WIDTH_INFO_PANEL, 50));

      numerusHebrew = new JComboBox<>(Numerus.getModelHebrew());
      numerusHebrew.setFocusable(false);
      numerusHebrew.setBorder(new TitledBorder("Numerus Hebräisch"));
      numerusHebrew.setFont(germanfont);
      numerusHebrew.setEditable(false);
      numerusHebrew.setMinimumSize(new Dimension(WIDTH_INFO_PANEL, 50));
      numerusHebrew.setMaximumSize(new Dimension(WIDTH_INFO_PANEL, 50));

      kind = new WideComboBox<>(ExpressionKind.getModel());
      kind.setMaximumRowCount(ExpressionKind.getNumberOfValues() / 2);
      kind.setBorder(new TitledBorder("Wortart"));
      kind.setFont(germanfont);
      kind.setEditable(false);
      kind.setMinimumSize(new Dimension(WIDTH_INFO_PANEL - 50, 50));
      kind.setMaximumSize(new Dimension(WIDTH_INFO_PANEL - 50, 50));

      binjan = new JComboBox<>(Binjan.values());
      binjan.setMaximumRowCount(Binjan.values().length);
      binjan.setBorder(new TitledBorder("Binjan"));
      binjan.setFont(germanfont);
      binjan.setEditable(false);
      binjan.setMinimumSize(new Dimension(WIDTH_INFO_PANEL, 50));
      binjan.setMaximumSize(new Dimension(WIDTH_INFO_PANEL, 50));

      newSearchwordGerman = new InfoTextField("Neues Suchwort Deutsch  ",
            "Bitte je ein Wort eingeben  ", "und dann ENTER drücken!  ");
      newSearchwordGerman.setFont(germanfont);
      newSearchwordGerman.setMinimumSize(new Dimension(WIDTH_INFO_PANEL, 70));
      newSearchwordGerman.setMaximumSize(new Dimension(WIDTH_INFO_PANEL, 70));
      newSearchwordGerman.setDocument(new GermanDocument(false));

      searchwordsJListGerman = new JList<>();
      searchwordsJListGerman.setCellRenderer(new ListCellRenderer<String>()
      {
         @Override
         public Component getListCellRendererComponent(
               JList<? extends String> list, String value, int index,
               boolean isSelected, boolean cellHasFocus)
         {
            FocusTextField listComponent = new FocusTextField(value);
            listComponent.setFont(Main.getGermanFont(16F));
            if (isSelected)
            {
               listComponent.setBackground(Color.WHITE);
            }
            else
            {
               listComponent.setBackground(Settings.getBackgroundGold());
            }
            return listComponent;
         }
      });
      searchwordsJListGerman.setFocusable(false);
      searchwordsJListGerman
            .setBorder(makeBorderBlank(this.searchwordJListGermanTitle));
      searchwordsJListGerman.setBackground(new Color(223, 210, 198));
      searchwordsJListGerman
            .setMinimumSize(new Dimension(WIDTH_INFO_PANEL, 300));
      searchwordsJListGerman
            .setMaximumSize(new Dimension(WIDTH_INFO_PANEL, 400));

      deleteSearchwordButtonGerman = new JButton("lösche Suchwort Deutsch");
      deleteSearchwordButtonGerman.setFocusable(false);
      deleteSearchwordButtonGerman.setFont(Main.getGermanFont(16F));
      deleteSearchwordButtonGerman
            .setMinimumSize(new Dimension(WIDTH_INFO_PANEL, 40));
      deleteSearchwordButtonGerman
            .setMaximumSize(new Dimension(WIDTH_INFO_PANEL, 40));

      newSearchwordHebrew = new InfoTextField("Neues Suchwort Hebräisch  ",
            "Bitte hineinklicken oder  ", "mit der Tabulatortaste auswählen.  ",
            "Dann hebräische Tastatur benutzen.  ", "Danach ENTER drücken!  ");
      newSearchwordHebrew.setFont(hebrewfont);
      newSearchwordHebrew
            .setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
      newSearchwordHebrew.setMinimumSize(new Dimension(WIDTH_INFO_PANEL, 70));
      newSearchwordHebrew.setMaximumSize(new Dimension(WIDTH_INFO_PANEL, 70));
      newSearchwordHebrew.setDocument(new HebrewDocument(false));
      this.components.add(newSearchwordHebrew);

      searchwordsJListHebrew = new JList<>();
      searchwordsJListHebrew.setCellRenderer(new ListCellRenderer<String>()
      {
         @Override
         public Component getListCellRendererComponent(
               JList<? extends String> list, String value, int index,
               boolean isSelected, boolean cellHasFocus)
         {
            FocusTextField listComponent = new FocusTextField(value);
            listComponent.setFont(hebrewfont);
            listComponent
                  .setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
            if (isSelected)
            {
               listComponent.setBackground(Color.WHITE);
            }
            else
            {
               listComponent.setBackground(Settings.getBackgroundGold());
            }
            return listComponent;
         }
      });
      searchwordsJListHebrew.setFocusable(false);
      searchwordsJListHebrew.setFixedCellHeight(50);
      searchwordsJListHebrew
            .setBorder(makeBorderBlank(this.searchwordsJListHebrewTitle));
      searchwordsJListHebrew.setBackground(Settings.getBackgroundGold());
      searchwordsJListHebrew
            .setMinimumSize(new Dimension(WIDTH_INFO_PANEL, 300));
      searchwordsJListHebrew
            .setMaximumSize(new Dimension(WIDTH_INFO_PANEL, 400));

      deleteSearchwordButtonHebrew = new JButton("lösche Suchwort Hebräisch");
      deleteSearchwordButtonHebrew.setFont(Main.getGermanFont(16F));
      deleteSearchwordButtonHebrew
            .setMinimumSize(new Dimension(WIDTH_INFO_PANEL, 40));
      deleteSearchwordButtonHebrew
            .setMaximumSize(new Dimension(WIDTH_INFO_PANEL, 40));

      saveButton = new JButton("anwenden");
      saveButton.setFont(Main.getGermanFont(16F));
      saveButton.setMinimumSize(new Dimension(120, 40));
      saveButton.setMaximumSize(new Dimension(160, 40));

      restoreButton = new JButton("zurücksetzen");
      restoreButton.setFont(Main.getGermanFont(16F));
      restoreButton.setMinimumSize(new Dimension(120, 40));
      restoreButton.setMaximumSize(new Dimension(160, 40));

      cancelButton = new JButton("abbrechen");
      cancelButton.setFont(Main.getGermanFont(16F));
      cancelButton.setMinimumSize(new Dimension(120, 40));
      cancelButton.setMaximumSize(new Dimension(160, 40));

      chapter = new JComboBox<String>();
      chapter.setEditable(true);
      chapter.setMaximumRowCount(20);
      chapter.setBorder(new TitledBorder(this.chapterTitle));
      chapter.setOpaque(false);
      chapter.setBackground(new Color(0, 0, 0, 0));
      chapter.setMinimumSize(new Dimension(WIDTH_INPUT_PANEL, 70));
      chapter.setMaximumSize(new Dimension(WIDTH_INPUT_PANEL, 70));

      cardLayout = new CardLayout();
      swapPanel = new JPanel(cardLayout);
      swapPanel.setMinimumSize(new Dimension(WIDTH_INFO_PANEL, 200));
      swapPanel.setMaximumSize(new Dimension(WIDTH_INFO_PANEL, 200));
      swapPanel.setBorder(BorderFactory.createEmptyBorder());
      swapPanel.add(ExpressionKind.UNKOWN.name(), initUnkownPanel());
      swapPanel.add(ExpressionKind.ADJEKTIV.name(), initAdjektivPanel());
      swapPanel.add(ExpressionKind.ADVERB.name(), initAdverbPanel());
      swapPanel.add(ExpressionKind.ALTERSANGABE.name(),
            initAltersangabePanel());
      swapPanel.add(ExpressionKind.AUSRUF.name(), initAufrufPanel());
      swapPanel.add(ExpressionKind.BERUF.name(), initBerufPanel());
      swapPanel.add(ExpressionKind.BINDEWORT.name(), initBindewortPanel());
      swapPanel.add(ExpressionKind.DEMONSTRATIVPRONOM.name(),
            initDemonstrativPronomPanel());
      swapPanel.add(ExpressionKind.EIGENNAME.name(), initEigennamePanel());
      swapPanel.add(ExpressionKind.FARBE.name(), initFarbePanel());
      swapPanel.add(ExpressionKind.FRAGEWORT.name(), initFragewortPanel());
      swapPanel.add(ExpressionKind.GLUECKWUNSCH.name(),
            initGlueckwunschPanel());
      swapPanel.add(ExpressionKind.JAHRESZEIT.name(), initJahreszeitPanel());
      swapPanel.add(ExpressionKind.KONSTRUKT.name(), initKonstruktPanel());
      swapPanel.add(ExpressionKind.MODALVERB.name(), initModalverbPanel());
      swapPanel.add(ExpressionKind.ORDNUNGSZAHL.name(),
            initOrdnungszahlPanel());
      swapPanel.add(ExpressionKind.PARTIKEL.name(), initPartikelPanel());
      swapPanel.add(ExpressionKind.PERSONALPRAEFIX.name(),
            initPersonalPraefixPanel());
      swapPanel.add(ExpressionKind.PERSONALPRONOM.name(),
            initPersonalPronomPanel());
      swapPanel.add(ExpressionKind.PERSONALSUFFIX.name(),
            initPersonalSuffixPanel());
      swapPanel.add(ExpressionKind.POSSESSIVPRONOM.name(),
            initPossessivPronomPanel());
      swapPanel.add(ExpressionKind.PRAEPOSITION.name(),
            initPraepositionPanel());
      swapPanel.add(ExpressionKind.PRONOM.name(), initPronomPanel());
      swapPanel.add(ExpressionKind.REDEWENDUNG.name(), initRedewendungPanel());
      swapPanel.add(ExpressionKind.SUBSTANTIV.name(), initSubstantivPanel());
      swapPanel.add(ExpressionKind.UHRZEIT.name(), initUhrzeitPanel());
      swapPanel.add(ExpressionKind.UMGANGSPRACHE.name(),
            initUmgangsSprachePanel());
      swapPanel.add(ExpressionKind.VERB.name(), initVerbPanel());
      swapPanel.add(ExpressionKind.WOCHENTAG.name(), initWochentagPanel());
      swapPanel.add(ExpressionKind.ZAHL.name(), initZahlPanel());
      cardLayout.show(swapPanel, ExpressionKind.UNKOWN.name());
      
      extraInfo = new JTextPane();
      extraInfo.setFont(Main.getHebrewFont(30));
      extraInfo.setBorder(BorderFactory.createTitledBorder("Weitere Informationen"));
      extraInfo.setDocument(new ExtraInformationDocument());
      StyledDocument doc = extraInfo.getStyledDocument();
      SimpleAttributeSet style = new SimpleAttributeSet();
      StyleConstants.setForeground(style, Settings.getDarkGold());
      StyleConstants.setFontSize(style, 20);
      StyleConstants.setFontFamily(style, "Serif");
      doc.setParagraphAttributes(0, doc.getLength(), style, true);
      components.add(extraInfo);
      extraInfoScroller = new JScrollPane(extraInfo);
      extraInfoScroller.setMinimumSize(new Dimension(WIDTH_INFO_PANEL, 100));
      extraInfoScroller.setMaximumSize(new Dimension(WIDTH_INFO_PANEL, 400));
      
      keyboard = new KeyboardHebrew(hebrew, components, 70, true);
   }

   private Component initUmgangsSprachePanel()
   {
      verticalUmgangssprachePanel = new JPanel();
      TotemLayout layout = new TotemLayout(verticalUmgangssprachePanel, 15);
      verticalUmgangssprachePanel.setLayout(layout);
      verticalUmgangssprachePanel.setOpaque(true);
      verticalUmgangssprachePanel.setBackground(Settings.getTransparent());
      return verticalUmgangssprachePanel;
   }

   private Component initZahlPanel()
   {
      verticalZahlPanel = new JPanel();
      TotemLayout layout = new TotemLayout(verticalZahlPanel, 15);
      verticalZahlPanel.setLayout(layout);
      verticalZahlPanel.setOpaque(true);
      verticalZahlPanel.setBackground(Settings.getTransparent());
      return verticalZahlPanel;
   }

   private Component initWochentagPanel()
   {
      verticalWochentagPanel = new JPanel();
      TotemLayout layout = new TotemLayout(verticalWochentagPanel, 15);
      verticalWochentagPanel.setLayout(layout);
      verticalWochentagPanel.setOpaque(true);
      verticalWochentagPanel.setBackground(Settings.getTransparent());
      return verticalWochentagPanel;
   }

   private Component initVerbPanel()
   {
      verticalVerbPanel = new JPanel();
      TotemLayout layout = new TotemLayout(verticalVerbPanel, 15);
      verticalVerbPanel.setLayout(layout);
      verticalVerbPanel.setOpaque(true);
      verticalVerbPanel.setBackground(Settings.getTransparent());
      return verticalVerbPanel;
   }

   private Component initUhrzeitPanel()
   {
      verticalUhrzeitPanel = new JPanel();
      TotemLayout layout = new TotemLayout(verticalUhrzeitPanel, 15);
      verticalUhrzeitPanel.setLayout(layout);
      verticalUhrzeitPanel.setOpaque(true);
      verticalUhrzeitPanel.setBackground(Settings.getTransparent());
      return verticalUhrzeitPanel;
   }

   private Component initSubstantivPanel()
   {
      verticalSubstantivPanel = new JPanel();
      TotemLayout layout = new TotemLayout(verticalSubstantivPanel, 15);
      verticalSubstantivPanel.setLayout(layout);
      verticalSubstantivPanel.setOpaque(true);
      verticalSubstantivPanel.setBackground(Settings.getTransparent());
      return verticalSubstantivPanel;
   }

   private Component initRedewendungPanel()
   {
      verticalRedewendungPanel = new JPanel();
      TotemLayout layout = new TotemLayout(verticalRedewendungPanel, 15);
      verticalRedewendungPanel.setLayout(layout);
      verticalRedewendungPanel.setOpaque(true);
      verticalRedewendungPanel.setBackground(Settings.getTransparent());
      return verticalRedewendungPanel;
   }

   private Component initPronomPanel()
   {
      verticalPronomPanel = new JPanel();
      TotemLayout layout = new TotemLayout(verticalPronomPanel, 15);
      verticalPronomPanel.setLayout(layout);
      verticalPronomPanel.setOpaque(true);
      verticalPronomPanel.setBackground(Settings.getTransparent());
      return verticalPronomPanel;
   }

   private Component initPraepositionPanel()
   {
      verticalPraepositionPanel = new JPanel();
      TotemLayout layout = new TotemLayout(verticalPraepositionPanel, 15);
      verticalPraepositionPanel.setLayout(layout);
      verticalPraepositionPanel.setOpaque(true);
      verticalPraepositionPanel.setBackground(Settings.getTransparent());
      return verticalPraepositionPanel;
   }

   private Component initPossessivPronomPanel()
   {
      verticalPossesivPronomPanel = new JPanel();
      TotemLayout layout = new TotemLayout(verticalPossesivPronomPanel, 15);
      verticalPossesivPronomPanel.setLayout(layout);
      verticalPossesivPronomPanel.setOpaque(true);
      verticalPossesivPronomPanel.setBackground(Settings.getTransparent());
      return verticalPossesivPronomPanel;
   }

   private Component initPersonalSuffixPanel()
   {
      verticalPersonalSuffixPanel = new JPanel();
      TotemLayout layout = new TotemLayout(verticalPersonalSuffixPanel, 15);
      verticalPersonalSuffixPanel.setLayout(layout);
      verticalPersonalSuffixPanel.setOpaque(true);
      verticalPersonalSuffixPanel.setBackground(Settings.getTransparent());
      return verticalPersonalSuffixPanel;
   }

   private Component initPersonalPronomPanel()
   {
      verticalPersonalPronomPanel = new JPanel();
      TotemLayout layout = new TotemLayout(verticalPersonalPronomPanel, 15);
      verticalPersonalPronomPanel.setLayout(layout);
      verticalPersonalPronomPanel.setOpaque(true);
      verticalPersonalPronomPanel.setBackground(Settings.getTransparent());
      return verticalPersonalPronomPanel;
   }

   private Component initPersonalPraefixPanel()
   {
      verticalPersonalPraefixPanel = new JPanel();
      TotemLayout layout = new TotemLayout(verticalPersonalPraefixPanel, 15);
      verticalPersonalPraefixPanel.setLayout(layout);
      verticalPersonalPraefixPanel.setOpaque(true);
      verticalPersonalPraefixPanel.setBackground(Settings.getTransparent());

      return verticalPersonalPraefixPanel;
   }

   private Component initPartikelPanel()
   {
      verticalPartikelPanel = new JPanel();
      TotemLayout layout = new TotemLayout(verticalPartikelPanel, 15);
      verticalPartikelPanel.setLayout(layout);
      verticalPartikelPanel.setOpaque(true);
      verticalPartikelPanel.setBackground(Settings.getTransparent());
      return verticalPartikelPanel;
   }

   private Component initOrdnungszahlPanel()
   {
      verticalOrdnungszahlPanel = new JPanel();
      TotemLayout layout = new TotemLayout(verticalOrdnungszahlPanel, 15);
      verticalOrdnungszahlPanel.setLayout(layout);
      verticalOrdnungszahlPanel.setOpaque(true);
      verticalOrdnungszahlPanel.setBackground(Settings.getTransparent());
      return verticalOrdnungszahlPanel;
   }

   private Component initModalverbPanel()
   {
      verticalModalVerbPanel = new JPanel();
      TotemLayout layout = new TotemLayout(verticalModalVerbPanel, 15);
      verticalModalVerbPanel.setLayout(layout);
      verticalModalVerbPanel.setOpaque(true);
      verticalModalVerbPanel.setBackground(Settings.getTransparent());
      return verticalModalVerbPanel;
   }

   private Component initKonstruktPanel()
   {
      verticalKontruktPanel = new JPanel();
      TotemLayout layout = new TotemLayout(verticalKontruktPanel, 15);
      verticalKontruktPanel.setLayout(layout);
      verticalKontruktPanel.setOpaque(true);
      verticalKontruktPanel.setBackground(Settings.getTransparent());
      return verticalKontruktPanel;
   }

   private Component initJahreszeitPanel()
   {
      verticalJahreszeitPanel = new JPanel();
      TotemLayout layout = new TotemLayout(verticalJahreszeitPanel, 15);
      verticalJahreszeitPanel.setLayout(layout);
      verticalJahreszeitPanel.setOpaque(true);
      verticalJahreszeitPanel.setBackground(Settings.getTransparent());
      return verticalJahreszeitPanel;
   }

   private Component initGlueckwunschPanel()
   {
      verticalGlueckwunschPanel = new JPanel();
      TotemLayout layout = new TotemLayout(verticalGlueckwunschPanel, 15);
      verticalGlueckwunschPanel.setLayout(layout);
      verticalGlueckwunschPanel.setOpaque(true);
      verticalGlueckwunschPanel.setBackground(Settings.getTransparent());
      return verticalGlueckwunschPanel;
   }

   private Component initFragewortPanel()
   {
      verticalFragewortPanel = new JPanel();
      TotemLayout layout = new TotemLayout(verticalFragewortPanel, 15);
      verticalFragewortPanel.setLayout(layout);
      verticalFragewortPanel.setOpaque(true);
      verticalFragewortPanel.setBackground(Settings.getTransparent());
      return verticalFragewortPanel;
   }

   private Component initFarbePanel()
   {
      verticalFarbePanel = new JPanel();
      TotemLayout layout = new TotemLayout(verticalFarbePanel, 15);
      verticalFarbePanel.setLayout(layout);
      verticalFarbePanel.setOpaque(true);
      verticalFarbePanel.setBackground(Settings.getTransparent());
      return verticalFarbePanel;
   }

   private Component initEigennamePanel()
   {
      verticalEigennamePanel = new JPanel();
      TotemLayout layout = new TotemLayout(verticalEigennamePanel, 15);
      verticalEigennamePanel.setLayout(layout);
      verticalEigennamePanel.setOpaque(true);
      verticalEigennamePanel.setBackground(Settings.getTransparent());
      return verticalEigennamePanel;
   }

   private Component initDemonstrativPronomPanel()
   {
      verticalDemonstrativPronomPanel = new JPanel();
      TotemLayout layout = new TotemLayout(verticalDemonstrativPronomPanel, 15);
      verticalDemonstrativPronomPanel.setLayout(layout);
      verticalDemonstrativPronomPanel.setOpaque(true);
      verticalDemonstrativPronomPanel.setBackground(Settings.getTransparent());
      return verticalDemonstrativPronomPanel;
   }

   private Component initBindewortPanel()
   {
      verticalBindewortPanel = new JPanel();
      TotemLayout layout = new TotemLayout(verticalBindewortPanel, 15);
      verticalBindewortPanel.setLayout(layout);
      verticalBindewortPanel.setOpaque(true);
      verticalBindewortPanel.setBackground(Settings.getTransparent());
      return verticalBindewortPanel;
   }

   private Component initBerufPanel()
   {
      verticalBerufPanel = new JPanel();
      TotemLayout layout = new TotemLayout(verticalBerufPanel, 15);
      verticalBerufPanel.setLayout(layout);
      verticalBerufPanel.setOpaque(true);
      verticalBerufPanel.setBackground(Settings.getTransparent());
      return verticalBerufPanel;
   }

   private Component initAufrufPanel()
   {
      verticalAufrufPanel = new JPanel();
      TotemLayout layout = new TotemLayout(verticalAufrufPanel, 15);
      verticalAufrufPanel.setLayout(layout);
      verticalAufrufPanel.setOpaque(true);
      verticalAufrufPanel.setBackground(Settings.getTransparent());
      return verticalAufrufPanel;
   }

   private Component initAltersangabePanel()
   {
      verticalAltersangabePanel = new JPanel();
      TotemLayout layout = new TotemLayout(verticalAltersangabePanel, 15);
      verticalAltersangabePanel.setLayout(layout);
      verticalAltersangabePanel.setOpaque(true);
      verticalAltersangabePanel.setBackground(Settings.getTransparent());
      return verticalAltersangabePanel;
   }

   private Component initAdverbPanel()
   {
      verticalAdverbPanel = new JPanel();
      TotemLayout layout = new TotemLayout(verticalAdverbPanel, 15);
      verticalAdverbPanel.setLayout(layout);
      verticalAdverbPanel.setOpaque(true);
      verticalAdverbPanel.setBackground(Settings.getTransparent());
      return verticalAdverbPanel;
   }

   private Component initAdjektivPanel()
   {
      verticalAdjektivPanel = new JPanel();
      TotemLayout layout = new TotemLayout(verticalAdjektivPanel, 15);
      verticalAdjektivPanel.setLayout(layout);
      verticalAdjektivPanel.setOpaque(true);
      verticalAdjektivPanel.setBackground(Settings.getTransparent());
      return verticalAdjektivPanel;
   }

   private Component initUnkownPanel()
   {
      verticalUnkownPanel = new JPanel();
      TotemLayout layout  = new TotemLayout(verticalUnkownPanel, 15);
      verticalUnkownPanel.setLayout(layout);
      verticalUnkownPanel.setOpaque(true);
      verticalUnkownPanel.setBackground(Settings.getTransparent());
      return verticalUnkownPanel;
   }

   private TitledBorder makeBorderBlank(String title)
   {
      return new TitledBorder(title);
   }

   private TitledBorder makeBorderRed(String title)
   {
      return new TitledBorder(BorderFactory.createLineBorder(Color.RED, 3),
            title);
   }

   private void initInput()
   {
      JPanel vertical = new JPanel();
      vertical.setOpaque(false);
      vertical.setLayout(new TotemLayout(vertical, 15));
      vertical.add(chapter);
      vertical.add(german);
      vertical.add(hebrewInLatin);
      vertical.add(keyboard);

      JPanel horizontal = new JPanel();
      horizontal.setOpaque(false);
      horizontal.setLayout(new TrainLayout(horizontal, 15));
      horizontal.add(saveButton);
      horizontal.add(restoreButton);
      horizontal.add(cancelButton);

      vertical.add(horizontal);
      layout.add(vertical);
   }

   private void initInfosLeft()
   {
      JPanel vertical = new JPanel();
      vertical.setOpaque(false);
      vertical.setLayout(new TotemLayout(vertical, 15));
      vertical.add(genderHebrew);
      vertical.add(numerusHebrew);
      vertical.add(newSearchwordGerman);
      JScrollPane scrollPane = new JScrollPane(searchwordsJListGerman);
      scrollPane.setMinimumSize(new Dimension(WIDTH_INFO_PANEL, 220));
      scrollPane.setMaximumSize(new Dimension(WIDTH_INFO_PANEL, 400));
      vertical.add(scrollPane);
      vertical.add(deleteSearchwordButtonGerman);
      layout.add(vertical);
   }

   private void initInfosRight()
   {
      JPanel vertical = new JPanel();
      vertical.setOpaque(false);
      vertical.setLayout(new TotemLayout(vertical, 15));

      JPanel horizontal = new JPanel();
      horizontal.setOpaque(false);
      horizontal.setLayout(new TrainLayout(horizontal));
      horizontal.add(kind);
      horizontal.add(infoExpressionKindButton);

      JPanel filler = new JPanel();
      filler.setMinimumSize(new Dimension(WIDTH_INFO_PANEL, 50));
      filler.setMaximumSize(new Dimension(WIDTH_INFO_PANEL, 50));

      vertical.add(horizontal);
      vertical.add(filler);

      vertical.add(newSearchwordHebrew);
      JScrollPane scrollPane = new JScrollPane(searchwordsJListHebrew);
      scrollPane.setMinimumSize(new Dimension(WIDTH_INFO_PANEL, 220));
      scrollPane.setMaximumSize(new Dimension(WIDTH_INFO_PANEL, 400));
      vertical.add(scrollPane);
      vertical.add(deleteSearchwordButtonHebrew);
      layout.add(vertical);
   }

   private void initInfosExtra()
   {
      JPanel vertical = new JPanel();
      vertical.setOpaque(false);
      vertical.setLayout(new TotemLayout(vertical, 15));

      vertical.add(swapPanel);
      vertical.add(extraInfoScroller);

      layout.add(vertical);
   }

   private void resetAllBorders()
   {
      chapter.setBorder(makeBorderBlank(this.chapterTitle));
      german.setBorder(makeBorderBlank(this.germanTitle));
      hebrewInLatin.setBorder(makeBorderBlank(this.hebrewInLatinTitle));
      hebrew.setBlankBorder();
      searchwordsJListGerman
            .setBorder(makeBorderBlank(this.searchwordJListGermanTitle));
      searchwordsJListHebrew
            .setBorder(makeBorderBlank(this.searchwordsJListHebrewTitle));
   }

   private void initController()
   {
      infoExpressionKindButton.addActionListener(event -> {
         JOptionPane.showMessageDialog(this, "", "Cerebrummi©",
               JOptionPane.INFORMATION_MESSAGE,
               new ImageIcon(TextImage.make(ExpressionKind.getExplanations())));
      });

      infoExpressionKindButton.addMouseListener(new MouseListener()
      {

         @Override
         public void mouseClicked(MouseEvent e)
         {

         }

         @Override
         public void mousePressed(MouseEvent e)
         {

         }

         @Override
         public void mouseReleased(MouseEvent e)
         {

         }

         @Override
         public void mouseEntered(MouseEvent e)
         {
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
         }

         @Override
         public void mouseExited(MouseEvent e)
         {
            setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
         }

      });

      german.addActionListener(event -> {
         if (!german.getText().isEmpty())
         {
            german.setBorder(makeBorderBlank(this.germanTitle));
         }
      });

      hebrewInLatin.addActionListener(event -> {
         if (!hebrewInLatin.getText().isEmpty())
         {
            hebrewInLatin.setBorder(makeBorderBlank(this.hebrewInLatinTitle));
         }
      });

      hebrew.addCaretListener(event -> {
         if (!hebrew.getText().isEmpty())
         {
            hebrew.setBlankBorder();
         }
      });

      newSearchwordGerman.addActionListener(event -> {
         String add = newSearchwordGerman.getText().replaceAll(",", "");
         if (!add.isEmpty())
         {
            searchwordsSetGerman.add(add);
            searchwordsJListGerman.setModel(getSearchwordsModelGerman());
            newSearchwordGerman.setText("");
            newSearchwordGerman.requestFocus();
         }
         if (!searchwordsSetGerman.isEmpty())
         {
            searchwordsJListGerman
                  .setBorder(makeBorderBlank(this.searchwordJListGermanTitle));
         }
      });

      deleteSearchwordButtonGerman.addActionListener(event -> {
         if (searchwordsJListGerman.getSelectedValue() != null)
         {
            searchwordsSetGerman
                  .remove(searchwordsJListGerman.getSelectedValue());
            searchwordsJListGerman.setModel(getSearchwordsModelGerman());
         }
      });

      newSearchwordHebrew.addActionListener(event -> {
         String add = newSearchwordHebrew.getText().replaceAll(",", "");
         if (!add.isEmpty())
         {
            searchwordsSetHebrew.add(add);
            searchwordsJListHebrew.setModel(getSearchwordsModelHebrew());
            newSearchwordHebrew.setText("");
            newSearchwordHebrew.requestFocus();
         }
         if (!searchwordsSetHebrew.isEmpty())
         {
            searchwordsJListHebrew
                  .setBorder(makeBorderBlank(this.searchwordsJListHebrewTitle));
         }
      });

      deleteSearchwordButtonHebrew.addActionListener(event -> {
         if (searchwordsJListHebrew.getSelectedValue() != null)
         {
            searchwordsSetHebrew
                  .remove(searchwordsJListHebrew.getSelectedValue());
            searchwordsJListHebrew.setModel(getSearchwordsModelHebrew());
         }
      });

      saveButton.addActionListener(event -> {
         if (testForCompletness())
         {
            save = true;
            saveExpression();
            this.dispose();
         }
      });

      restoreButton.addActionListener(event -> {
         setExpression(expression);
         resetAllBorders();
      });

      cancelButton.addActionListener(event -> {
         save = false;
         this.dispose();
      });

      kind.addItemListener(event -> {
         
         switch((ExpressionKind) this.kind.getSelectedItem())
         {
         case ADJEKTIV:
            binjan.setSelectedItem(Binjan.NA);
            break;
         case ADVERB:
            binjan.setSelectedItem(Binjan.NA);
            break;
         case ALTERSANGABE:
            binjan.setSelectedItem(Binjan.NA);
            break;
         case AUSRUF:
            binjan.setSelectedItem(Binjan.NA);
            break;
         case BERUF:
            binjan.setSelectedItem(Binjan.NA);
            break;
         case BINDEWORT:
            binjan.setSelectedItem(Binjan.NA);
            break;
         case DEMONSTRATIVPRONOM:
            binjan.setSelectedItem(Binjan.NA);
            break;
         case EIGENNAME:
            binjan.setSelectedItem(Binjan.NA);
            break;
         case FARBE:
            binjan.setSelectedItem(Binjan.NA);
            break;
         case FRAGEWORT:
            binjan.setSelectedItem(Binjan.NA);
            break;
         case GLUECKWUNSCH:
            binjan.setSelectedItem(Binjan.NA);
            break;
         case JAHRESZEIT:
            binjan.setSelectedItem(Binjan.NA);
            break;
         case KONSTRUKT:
            binjan.setSelectedItem(Binjan.NA);
            break;
         case MODALVERB:
            binjan.setSelectedItem(Binjan.UNKOWN);
            this.verticalModalVerbPanel.add(binjan);
            break;
         case ORDNUNGSZAHL:
            binjan.setSelectedItem(Binjan.NA);
            break;
         case PARTIKEL:
            binjan.setSelectedItem(Binjan.NA);
            break;
         case PERSONALPRAEFIX:
            binjan.setSelectedItem(Binjan.NA);
            break;
         case PERSONALPRONOM:
            binjan.setSelectedItem(Binjan.NA);
            break;
         case PERSONALSUFFIX:
            binjan.setSelectedItem(Binjan.NA);
            break;
         case POSSESSIVPRONOM:
            binjan.setSelectedItem(Binjan.NA);
            break;
         case PRAEPOSITION:
            binjan.setSelectedItem(Binjan.NA);
            break;
         case PRONOM:
            binjan.setSelectedItem(Binjan.NA);
            break;
         case REDEWENDUNG:
            binjan.setSelectedItem(Binjan.NA);
            break;
         case SUBSTANTIV:
            binjan.setSelectedItem(Binjan.NA);
            break;
         case UHRZEIT:
            binjan.setSelectedItem(Binjan.NA);
            break;
         case UMGANGSPRACHE:
            binjan.setSelectedItem(Binjan.NA);
            break;
         case UNKOWN:
            binjan.setSelectedItem(Binjan.NA);
            break;
         case VERB:
            binjan.setSelectedItem(Binjan.UNKOWN);
            this.verticalVerbPanel.add(binjan);
            break;
         case WOCHENTAG:
            binjan.setSelectedItem(Binjan.NA);
            break;
         case ZAHL:
            binjan.setSelectedItem(Binjan.NA);
            break;
         default:
            break;
         }
         
         this.cardLayout.show(swapPanel,
               ((ExpressionKind) this.kind.getSelectedItem()).name());
      });

   }

   private boolean testForCompletness()
   {
      boolean result = true;
      if (chapter.getSelectedItem() == null)
      {
         chapter.setBorder(makeBorderRed(this.chapterTitle));
         result = false;
      }
      if (german.getText().isEmpty())
      {
         german.setBorder(makeBorderRed(this.germanTitle));
         result = false;
      }
      if (hebrewInLatin.getText().isEmpty())
      {
         hebrewInLatin.setBorder(makeBorderRed(this.hebrewInLatinTitle));
         result = false;
      }
      if (hebrew.getText().isEmpty())
      {
         hebrew.setRedBorder();
         result = false;
      }
      if (searchwordsSetGerman.isEmpty())
      {
         searchwordsJListGerman
               .setBorder(makeBorderRed(this.searchwordJListGermanTitle));
         result = false;
      }
      if (searchwordsSetHebrew.isEmpty())
      {
         searchwordsJListHebrew
               .setBorder(makeBorderRed(this.searchwordsJListHebrewTitle));
         result = false;
      }
      return result;
   }

   private void saveExpression()
   {
      expression.setGerman(cleanTextWithoutComma(german.getText()));
      expression
            .setHebrewInLatin(cleanTextWithoutComma(hebrewInLatin.getText()));
      expression.setHebrew(cleanTextWithoutComma(hebrew.getText()));
      expression.setGenderHebrew((Gender) genderHebrew.getSelectedItem());
      expression.setNumerusHebrew((Numerus) numerusHebrew.getSelectedItem());
      expression.setBinjan((Binjan) binjan.getSelectedItem());
      expression.setKind((ExpressionKind) kind.getSelectedItem());
      List<String> wordsGerman = new ArrayList<>();
      for (String word : searchwordsSetGerman)
      {
         wordsGerman.add(cleanText(word));
      }
      expression.setSearchwordsGerman(wordsGerman);
      List<String> wordsHebrew = new ArrayList<>();
      for (String word : searchwordsSetHebrew)
      {
         wordsHebrew.add(cleanText(word));
      }
      expression.setSearchwordsHebrew(wordsHebrew);
      Chapter selfChapter = new Chapter();
      selfChapter.setOrigin(Database.SELF);
      selfChapter
            .setName(cleanTextWithoutComma((String) chapter.getSelectedItem()));
      expression.setChapter(selfChapter);
   }

   private String cleanText(String text)
   {
      return text.replaceAll("\t", "").replaceAll("\n", "").replaceAll(",", "");
   }

   private String cleanTextWithoutComma(String text)
   {
      return text.replaceAll("\t", "").replaceAll("\n", "");
   }

   public void setExpression(Expression expression)
   {
      this.save = false;
      this.oldKind = expression.getKind();
      this.expression = expression;
      this.german.setText(expression.getGerman());
      this.hebrewInLatin.setText(expression.getHebrewInLatin());
      this.hebrew.setText(expression.getHebrew());
      this.genderHebrew.setSelectedItem(expression.getGenderHebrew());
      this.numerusHebrew.setSelectedItem(expression.getNumerusHebrew());
      this.kind.setSelectedItem(expression.getKind());
      this.binjan.setSelectedItem(expression.getBinjan());

      this.searchwordsSetGerman = new HashSet<>();
      for (String word : expression.getSearchwordsGerman())
      {
         this.searchwordsSetGerman.add(word);
      }
      this.searchwordsJListGerman.setModel(getSearchwordsModelGerman());

      this.searchwordsSetHebrew = new HashSet<>();
      for (String word : expression.getSearchwordsHebrew())
      {
         this.searchwordsSetHebrew.add(word);
      }
      this.searchwordsJListHebrew.setModel(getSearchwordsModelHebrew());
      this.chapter.setModel(Data.getChapterComboBoxModel());
      if (expression.getChapter().getName().isEmpty())
      {
         chapter.setSelectedIndex(chapter.getItemCount() - 1);
      }
      else
      {
         this.chapter.setSelectedItem(expression.getChapter().getName());
      }
   }

   public Expression getExpression()
   {
      return expression;
   }

   private DefaultComboBoxModel<String> getSearchwordsModelGerman()
   {
      DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
      for (String word : searchwordsSetGerman)
      {
         model.addElement(word);
      }
      return model;
   }

   private DefaultComboBoxModel<String> getSearchwordsModelHebrew()
   {
      DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
      for (String word : searchwordsSetHebrew)
      {
         model.addElement(word);
      }
      return model;
   }

   public boolean isSave()
   {
      return save;
   }

   public void setSave(boolean save)
   {
      this.save = save;
   }

   public boolean isKindChanged()
   {
      return this.oldKind != this.expression.getKind();
   }

   public ExpressionKind getOldKind()
   {
      return oldKind;
   }
}
