import javax.swing.*;
import java.io.*;
import java.math.BigDecimal;
import java.util.List;

import java.util.*;


public class Form extends JFrame{

    private List<Deskovky> deskovky = new ArrayList<>();

    private List<BigDecimal> Numberlist = new ArrayList<BigDecimal>();

    private JFileChooser chooser = new JFileChooser(".");

    private Deskovky current;
    private Deskovky deskovka;

    private ArrayList<String> seznamDeskovek = new ArrayList<>();





    private static final String ODDELOVAC = ";";
    private static final String SOUBOR = "vstup.txt";

    private static final String SOUBOROS = "vystup.txt";
    private  JTextArea textArea1;
    private JPanel panel1;
    private JButton button1;
    private JTextArea textArea2;
    private JTextArea textArea3;
    private JButton backButton;
    private JButton nextButton;


    public Form(){
        zapisDoSouboru();
        initComponents();
        button1.addActionListener(e-> zapisDoSouboru());
        nextButton.addActionListener(e-> next());
        backButton.addActionListener(e-> back());





    }
    public void zapisDoSouboru() {
        try(PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(SOUBOROS)))) {
            String radek;
            for (Deskovky deskovky : deskovky) {

                radek = deskovky.getJmeno() + ODDELOVAC + deskovky.getPocetHodu()  + ODDELOVAC + deskovky.getPocetKopu();

                writer.println(radek);


            }


        }


   catch (FileNotFoundException e) {
                throw new RuntimeException();

            }

        catch (IOException e) {
            throw new RuntimeException();
        }

        }


    public List<Deskovky> cteniSouboru(File file) {


try(Scanner scanner = new Scanner(new BufferedReader(new FileReader(file))))
{
    while(scanner.hasNextLine()){
        String radek = scanner.nextLine();
      String [] polozky  = radek.split(ODDELOVAC);
        BigDecimal pocetHodu = new BigDecimal(polozky[1].trim());
        int pocetKopu = Integer.parseInt(polozky[2].trim());
        deskovky.add(new Deskovky(polozky[0].trim(), pocetHodu, pocetKopu));
        Numberlist.add(pocetHodu);





    }


}

catch (FileNotFoundException e)
{
    throw new RuntimeException();
}

        return deskovky;
    }

    public List<Deskovky> pocetVSeznamu() {
        int size = deskovky.size();
        JOptionPane.showMessageDialog(null, size);





        return deskovky;
    }

    private List<Deskovky> loadData() {
        clear();
        int result = chooser.showOpenDialog(this);
        if (result == JFileChooser.CANCEL_OPTION) {
            JOptionPane.showMessageDialog(null, "špatný typ souboru!");
        }
        deskovky = cteniSouboru(chooser.getSelectedFile());
        Deskovky deskovka = deskovky.get(0);
        showData(deskovka);
        return deskovky;
    }


    private void back() {
        clear();
        if(deskovky.isEmpty())
        {

         JOptionPane.showMessageDialog(null, "není zde žádná položka");

        }
        else {
            try {
                current = deskovky.get(deskovky.indexOf(current) - 1);
                showData(current);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Není zde žádný předešlý návštěvník!");
            }
        }
    }
    private void next() {
        clear();
        if(deskovky.isEmpty())
        {

            JOptionPane.showMessageDialog(null, "není zde žádná položka");

        }
        else {
            try {
                current = deskovky.get(deskovky.indexOf(current) + 1);
                showData(current);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Není zde žádný další návštěvník!");
            }
        }
    }
    public void clear() {
        textArea3.setText(null);
        textArea1.setText(null);
        textArea2.setText(null);
    }






    public void initComponents(){
        JMenu menu = new JMenu("jakubik");
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenuItem item = new JMenuItem("negrubik");
        JMenuItem pocetNavstevniku = new JMenuItem("pocet návštěvníků");
        JMenuItem soucet = new JMenuItem("součet");
        JMenuItem item1 = new JMenuItem("clear");
        menuBar.add(menu);
        menu.add(item);
        item.addActionListener(e -> loadData());
        item1.addActionListener(e -> clearListu());
        pocetNavstevniku.addActionListener(e-> soucet());
        menu.add(item1);
        soucet.addActionListener(e-> pocetVSeznamu());
        menu.add(pocetNavstevniku);
        menu.add(soucet);
        setContentPane(panel1);



    }


    public void clearListu(){
        deskovky.clear();
    }



    public List<BigDecimal> soucet() {

        BigDecimal sum = BigDecimal.ZERO;
        for (BigDecimal amt : Numberlist) {
            sum = sum.add(amt);
        }
        JOptionPane.showMessageDialog(null, "Počet kopů dohromady je " + sum + " .");

        return Numberlist;
    }

    public void showData(Deskovky deskovka) {
        this.current = deskovka;
        textArea1.append(deskovka.getJmeno());
        textArea2.append(deskovka.getPocetHodu() + "toto je pocet hodu");
        textArea3.append(deskovka.getPocetKopu() + "toto je pocet kopu");






    }



    public static void main(String[] args) {
    Form h = new Form();
    h.setVisible(true);
    h.setDefaultCloseOperation(EXIT_ON_CLOSE);



    }


}
