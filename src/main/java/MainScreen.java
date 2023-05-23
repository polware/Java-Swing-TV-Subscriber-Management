import models.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by IntelliJ IDEA
 * User: Paul H. Vargas P.
 * Date: 20/05/2023
 * Time: 11:04 a. m.
 */
public class MainScreen extends JFrame {
    Subscriber subscriber;
    Subscription subscription;
    SubscriptionCycle subscriptionCycle;
    int packagesSelectedPrice = 0;
    int totalValue = 0;
    File fileSubscribers;
    // Panel 1
    JPanel jPanelSubscriber;
    JTextField textFieldSubName;
    JTextField textFieldSubLastName;
    JTextField textFieldMobile;
    JTextField textFieldCity;
    JLabel labelSubName;
    JLabel labelSubLastName;
    JLabel labelMobile;
    JLabel labelCity;
    // Panel 2
    JPanel jPanelCycle;
    JTextField textFieldStartCycle;
    JTextField textFieldEndCycle;
    JTextField textFieldTvNumber;
    JLabel labelDate;
    JLabel labelStartCycle;
    JLabel labelEndCycle;
    JLabel labelTvNumber;
    SimpleDateFormat dateFormat;
    Date currentDate;
    // Panel 3
    JPanel jPanelPackages;
    JCheckBox jCheckBoxSports;
    JCheckBox jCheckBoxMovies;
    JCheckBox jCheckBoxDocumentaries;
    JLabel labelPackages;
    JButton buttonAddPackage;
    // Panel 4
    JPanel jPanelChannelsArea;
    JTextArea jTextAreaSports;
    JTextArea jTextAreaMovies;
    JTextArea jTextAreaDocumentaries;
    // Panel 5
    JPanel jPanelPayment;
    JLabel labelInstall;
    JLabel labelPackagePrice;
    JLabel labelTotal;
    // Panel 6
    JPanel jPanelTable;
    JTable jTableSubscribers;
    DefaultTableModel tableModel;
    JScrollPane jScrollPane;
    // Panel 7
    JPanel jPanelDataActions;
    JButton buttonSave;
    JButton buttonNew;

    public MainScreen() {
        // Panel 1
        jPanelSubscriber = new JPanel();
        Border panelSubsTitle = BorderFactory.createTitledBorder("models.Subscriber Details");
        jPanelSubscriber.setBorder(panelSubsTitle);
        jPanelSubscriber.setBounds(15, 15, 300, 210);
        jPanelSubscriber.setLayout(new GridLayout(4, 2));

        labelSubName = new JLabel("First Name:");
        labelSubLastName = new JLabel("Last Name:");
        labelMobile = new JLabel("Mobile:");
        labelCity = new JLabel("City:");
        textFieldSubName = new JTextField();
        textFieldSubLastName = new JTextField();
        textFieldMobile = new JTextField();
        textFieldCity = new JTextField();

        jPanelSubscriber.add(labelSubName);
        jPanelSubscriber.add(textFieldSubName);
        jPanelSubscriber.add(labelSubLastName);
        jPanelSubscriber.add(textFieldSubLastName);
        jPanelSubscriber.add(labelMobile);
        jPanelSubscriber.add(textFieldMobile);
        jPanelSubscriber.add(labelCity);
        jPanelSubscriber.add(textFieldCity);

        // Panel 2
        jPanelCycle = new JPanel();
        jPanelCycle.setBounds(15, 240, 300, 300);
        jPanelCycle.setLayout(new GridLayout(7, 1));
        Border panelCycleTitle = BorderFactory.createTitledBorder("Cycle Details");
        jPanelCycle.setBorder(panelCycleTitle);

        labelDate = new JLabel();
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        currentDate = new Date();
        labelDate.setText("Today (DD/MM/YYYY): "+dateFormat.format(currentDate));
        labelStartCycle = new JLabel("Start Date:");
        textFieldStartCycle = new JTextField();
        textFieldStartCycle.setOpaque(false);
        labelEndCycle = new JLabel("End Date:");
        textFieldEndCycle = new JTextField();
        textFieldEndCycle.setOpaque(false);
        labelTvNumber = new JLabel("Number of TV ($10 extra per TV):");
        textFieldTvNumber = new JTextField();
        textFieldTvNumber.setOpaque(false);
        jPanelCycle.add(labelDate);
        jPanelCycle.add(labelStartCycle);
        jPanelCycle.add(textFieldStartCycle);
        jPanelCycle.add(labelEndCycle);
        jPanelCycle.add(textFieldEndCycle);
        jPanelCycle.add(labelTvNumber);
        jPanelCycle.add(textFieldTvNumber);

        // Panel 3
        jPanelPackages = new JPanel();
        jPanelPackages.setBounds(330, 15, 300, 200);
        jPanelPackages.setLayout(new GridLayout(5, 1));
        Border panelPackagesTitle = BorderFactory.createTitledBorder("Channel Packages");
        jPanelPackages.setBorder(panelPackagesTitle);

        labelPackages = new JLabel("Select your package:");
        jCheckBoxSports = new JCheckBox("Sports Package");
        jCheckBoxMovies = new JCheckBox("Movies Package");
        jCheckBoxDocumentaries = new JCheckBox("Documentaries Package");
        buttonAddPackage = new JButton("Add Package(s)");

        jPanelPackages.add(labelPackages);
        jPanelPackages.add(jCheckBoxSports);
        jPanelPackages.add(jCheckBoxMovies);
        jPanelPackages.add(jCheckBoxDocumentaries);
        jPanelPackages.add(buttonAddPackage);

        // Checkbox listeners
        jCheckBoxSports.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (jCheckBoxSports.isSelected()) {
                    DisplaySportsChannels();
                }
                else {
                    jTextAreaSports.setText("");
                }
            }
        });

        jCheckBoxMovies.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (jCheckBoxMovies.isSelected()) {
                    DisplayMoviesChannels();
                }
                else {
                    jTextAreaMovies.setText("");
                }
            }
        });

        jCheckBoxDocumentaries.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (jCheckBoxDocumentaries.isSelected()) {
                    DisplayDocumentariesChannels();
                }
                else {
                    jTextAreaDocumentaries.setText("");
                }
            }
        });

        buttonAddPackage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    GetSubscriberData();
                }
                catch (Exception exception) {
                    System.out.println("");
                }
            }
        });

        // Panel 4
        jPanelChannelsArea = new JPanel();
        jPanelChannelsArea.setBounds(330, 240, 300, 400);
        jPanelChannelsArea.setLayout(new GridLayout(3 , 1));
        Border panelChannelsTitle = BorderFactory.createTitledBorder("Available Channels");
        jPanelChannelsArea.setBorder(panelChannelsTitle);

        jTextAreaSports = new JTextArea(5, 1);
        jTextAreaSports.setEditable(false);
        jTextAreaSports.setOpaque(false);
        jTextAreaSports.setLineWrap(true);
        jTextAreaMovies = new JTextArea(5, 1);
        jTextAreaMovies.setEditable(false);
        jTextAreaMovies.setOpaque(false);
        jTextAreaMovies.setLineWrap(true);
        jTextAreaDocumentaries = new JTextArea(5, 1);
        jTextAreaDocumentaries.setEditable(false);
        jTextAreaDocumentaries.setOpaque(false);
        jTextAreaDocumentaries.setLineWrap(true);

        jPanelChannelsArea.add(jTextAreaSports);
        jPanelChannelsArea.add(jTextAreaMovies);
        jPanelChannelsArea.add(jTextAreaDocumentaries);

        // Panel 5
        jPanelPayment = new JPanel();
        jPanelPayment.setBounds(645, 15, 200, 200);
        jPanelPayment.setLayout(new GridLayout(3, 1));
        Border panelPaymentTitle = BorderFactory.createTitledBorder("Payment");
        jPanelPayment.setBorder(panelPaymentTitle);

        labelInstall = new JLabel("Installation Price:");
        labelPackagePrice = new JLabel("Package(s) Price:");
        labelTotal = new JLabel("Total Value:");
        jPanelPayment.add(labelInstall);
        jPanelPayment.add(labelPackagePrice);
        jPanelPayment.add(labelTotal);

        // Panel 6
        jPanelTable = new JPanel();
        jPanelTable.setBounds(645, 240, 500, 400);
        jPanelTable.setLayout(new GridLayout(3, 1));
        Border panelTableTitle = BorderFactory.createTitledBorder("Customers");
        jPanelTable.setBorder(panelTableTitle);

        tableModel = new DefaultTableModel();
        jTableSubscribers = new JTable(tableModel);
        tableModel.addColumn("First Name");
        tableModel.addColumn("Last Name");
        tableModel.addColumn("Phone Number");
        tableModel.addColumn("Start Cycle");
        tableModel.addColumn("End Cycle");
        tableModel.addColumn("Total Price");
        jScrollPane = new JScrollPane(jTableSubscribers);
        jPanelTable.add(jScrollPane);

        // Panel 7
        jPanelDataActions = new JPanel();
        jPanelDataActions.setBounds(860, 15, 280, 200);
        jPanelDataActions.setLayout(new GridLayout(3, 1));
        Border panelActionTitle = BorderFactory.createTitledBorder("Data Operations");
        jPanelDataActions.setBorder(panelActionTitle);

        buttonSave = new JButton("Save Subscription");
        buttonNew = new JButton("New Subscription");

        jPanelDataActions.add(buttonSave);
        jPanelDataActions.add(buttonNew);

        buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SaveSubscription();
            }
        });

        buttonNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewSubscription();
            }
        });

        setLayout(null);
        add(jPanelSubscriber);
        add(jPanelCycle);
        add(jPanelPackages);
        add(jPanelChannelsArea);
        add(jPanelPayment);
        add(jPanelTable);
        add(jPanelDataActions);
    }
    private int DisplaySportsChannels() {
        SportChannel channel1 = new SportChannel("Win Sports", "ES", "SP", 3);
        SportChannel channel2 = new SportChannel("ESPN Plus", "ES", "SP", 4);
        SportChannel channel3 = new SportChannel("ESPN 2", "ES", "SP", 4);
        SportChannel channel4 = new SportChannel("MLB Extra", "ES", "SP", 3);
        ArrayList<SportChannel> sportChannels = new ArrayList<>();
        sportChannels.add(channel1);
        sportChannels.add(channel2);
        sportChannels.add(channel3);
        sportChannels.add(channel4);
        String channelString = "";
        int packagePrice = 0;
        for (SportChannel sportChannel : sportChannels) {
            channelString += "       " + sportChannel.getChannelName()
                    + "       " + sportChannel.getLanguage()
                    + "       " + sportChannel.getPrice()
                    + "\n";
            packagePrice += sportChannel.getPrice();
        }
        jTextAreaSports.setText(channelString);
        return packagePrice;
    }

    private int DisplayMoviesChannels() {
        MovieChannel channel1 = new MovieChannel("HBO Plus", "EN", "Mov", 4);
        MovieChannel channel2 = new MovieChannel("Cinemax", "ES", "Mov", 3);
        MovieChannel channel3 = new MovieChannel("Cinecanal", "ES", "Mov", 3);
        MovieChannel channel4 = new MovieChannel("Universal +", "EN", "Mov", 4);
        ArrayList<MovieChannel> movieChannels = new ArrayList<>();
        movieChannels.add(channel1);
        movieChannels.add(channel2);
        movieChannels.add(channel3);
        movieChannels.add(channel4);
        String channelString = "";
        int packagePrice = 0;
        for (MovieChannel movieChannel : movieChannels) {
            channelString += "       " + movieChannel.getChannelName()
                    + "       " + movieChannel.getLanguage()
                    + "       " + movieChannel.getPrice()
                    + "\n";
            packagePrice += movieChannel.getPrice();
        }
        jTextAreaMovies.setText(channelString);
        return packagePrice;
    }

    private int DisplayDocumentariesChannels() {
        DocumentaryChannel channel1 = new DocumentaryChannel("National GEO", "ES", "DOC", 3);
        DocumentaryChannel channel2 = new DocumentaryChannel("History CH.", "EN", "DOC", 2);
        DocumentaryChannel channel3 = new DocumentaryChannel("Discovery CH.", "ES", "DOC", 3);
        DocumentaryChannel channel4 = new DocumentaryChannel("NHK World", "EN", "DOC", 2);
        DocumentaryChannel channel5 = new DocumentaryChannel("Channel D", "ES", "DOC", 2);
        ArrayList<DocumentaryChannel> documentaryChannels = new ArrayList<>();
        documentaryChannels.add(channel1);
        documentaryChannels.add(channel2);
        documentaryChannels.add(channel3);
        documentaryChannels.add(channel4);
        documentaryChannels.add(channel5);
        String channelString = "";
        int packagePrice = 0;
        for (DocumentaryChannel documentaryChannel : documentaryChannels) {
            channelString += "       " + documentaryChannel.getChannelName()
                    + "       " + documentaryChannel.getLanguage()
                    + "       " + documentaryChannel.getPrice()
                    + "\n";
            packagePrice += documentaryChannel.getPrice();
        }
        jTextAreaDocumentaries.setText(channelString);
        return packagePrice;
    }

    private void GetSubscriberData() throws ParseException {
        subscriber = new Subscriber(textFieldSubName.getText(), textFieldSubLastName.getText(),
                textFieldCity.getText(), Integer.parseInt(textFieldMobile.getText()));
        Date startCycle = dateFormat.parse(textFieldStartCycle.getText());
        Date endCycle = dateFormat.parse(textFieldEndCycle.getText());
        subscriptionCycle = new SubscriptionCycle(dateFormat.format(startCycle), dateFormat.format(endCycle));
        ShowPrices();
    }

    private void ShowPrices() {
        Date todayDate = new Date();
        if (jCheckBoxSports.isSelected())
            packagesSelectedPrice += DisplaySportsChannels();
        else if (jCheckBoxMovies.isSelected())
            packagesSelectedPrice += DisplayMoviesChannels();
        else if (jCheckBoxDocumentaries.isSelected())
            packagesSelectedPrice += DisplayDocumentariesChannels();
        subscription = new Subscription(Integer.parseInt(textFieldTvNumber.getText()), subscriber, subscriptionCycle,
                dateFormat.format(todayDate), packagesSelectedPrice);
        totalValue = subscription.getTotalPrice();
        labelPackagePrice.setText("Package(s) Price: $"+packagesSelectedPrice);
        labelInstall.setText("Installation Price: $"+subscription.getInstallPrice());
        labelTotal.setText("Total Value: $"+totalValue);
    }

    private void NewSubscription() {
        textFieldSubName.setText("");
        textFieldSubLastName.setText("");
        textFieldMobile.setText("");
        textFieldCity.setText("");
        textFieldStartCycle.setText("");
        textFieldEndCycle.setText("");
        textFieldTvNumber.setText("");
        labelInstall.setText("Installation Price:");
        labelPackagePrice.setText("Package(s) Price:");
        labelTotal.setText("Total Value:");
        jCheckBoxSports.setSelected(false);
        jCheckBoxMovies.setSelected(false);
        jCheckBoxDocumentaries.setSelected(false);
    }

    private void SaveSubscription() {
        ArrayList<Subscription> subscriptionToSave = new ArrayList<>();
        subscriptionToSave.add(subscription);
        fileSubscribers = new File("myData.dat");
        ArrayList<Subscription> readSubscriptions = new ArrayList<>();
        try {
            OutputStream outputStream = new FileOutputStream(fileSubscribers);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(subscriptionToSave);
            objectOutputStream.flush();
            objectOutputStream.close();
            outputStream.close();
            // Loading Data
            InputStream inputStream = new FileInputStream(fileSubscribers);
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            readSubscriptions = (ArrayList) objectInputStream.readObject();
            objectInputStream.close();
            inputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        // Display data into Table
        for (Subscription subscription: readSubscriptions) {
            tableModel.addRow(new Object[]{
                    subscription.getSubscriber().getFirstName(),
                    subscription.getSubscriber().getLastName(),
                    subscription.getSubscriber().getPhone(),
                    subscription.getSubscriptionCycle().getStartDate(),
                    subscription.getSubscriptionCycle().getEndDate(),
                    subscription.getTotalPrice()
            });
        }
    }

    public static void main(String[] args) {
        MainScreen mainScreen = new MainScreen();
        mainScreen.setVisible(true);
        mainScreen.setBounds(400, 200, 1200, 700);
    }

}
