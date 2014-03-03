/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JKalkulator;

import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;

/**
 *
 * @author phpgeek
 */
public class JKalkulator extends JFrame implements ActionListener {

    static int LEBAR_APPS = 380;
    static int TINGGI_APPS = 250;

    /*
     * untuk frame ini kita akan memiliki lima baris komponen,
     * jadi saya akan membuat 5 panel, satu untuk tiap baris
     */
    JPanel[] row = new JPanel[5];
    /*
     * Kita akan membutuhkan 19 tombol
     */
    JButton[] button = new JButton[19];
    /*
     * Kita harus memberi nilai awal ke tombol segera. 
     * Setiap tombol akan membutuhkan string, 
     * tapi daripada mengetik 19 baris untuk menginisialisasi masing-masing tombol
     * kita akan membuat perulangan untuk itu. 
     * Jadi kita akan menempatkan nilai string untuk setiap tombol dalam array 
     * untuk digunakan dalam perulangan kita nanti. 
     * Perhatikan, hati-hati dengan urutannya, 
     * kita ingin mengatur button[0] sama dengan buttonString[0].
     */
    String[] buttonString = {
        "7", "8", "9", "+",
        "4", "5", "6", "-",
        "1", "2", "3", "*",
        ".", "/", "C", "√",
        "+/-", "=", "0"
    };
    /*
     * Sekarang mari kita buat array untuk dimensi lebar dan tinggi 
     * dari tombol yang akan kita buat.
     * lebarnya adalah 300, 45, 100, dan 90 untuk berbagai jenis komponen.
     */
    int[] dimW = {300, 45, 100, 90};
    /*
     * tingginya adalah 35 untuk display dan 40 untuk tombol.
     */
    int[] dimH = {35, 40};
    /*
     * Mari kita mendeklarasikan dan menginisialisasi dimensinya di sini.
     * Menggunakan bilangan bulat pertama pada lebar, dan bilangan bulat pertama pada tingginya.
     */
    Dimension displayDimension = new Dimension(dimW[0], dimH[0]);
    /*
     * Menggunakan bilangan bulat kedua pada lebar, dan bilangan bulat kedua pada tingginya.
     */
    Dimension regularDimension = new Dimension(dimW[1], dimH[1]);
    /*
     * Menggunakan integer ketiga pada lebar, dan integer kedua pada tingginya.
     */
    Dimension rColumnDimension = new Dimension(dimW[2], dimH[1]);
    /*
     * Menggunakan integer keempat pada lebar, dan integer kedua pada tingginya.
     */
    Dimension zeroButDimension = new Dimension(dimW[3], dimH[1]);
    /*
     * Sekarang kita perlu mendeklarasikan beberapa boolean untuk fungsi kita - 
     * menambah, mengurangi, mengalikan, dan membagi . 
     * Mari kita menggunakan sebuah array untuk itu.
     */
    boolean[] function = new boolean[4];
    /*
     * Kita akan membutuhkan beberapa nilai double yang ditampung di variabel sementara
     * untuk operasi perhitungan. inisiasi nilai awal dengan 0.
     */
    double[] temporary = {0, 0};
    /*
     * Mari kita membuat tampilan dengan JTextArea
     */
    JTextArea display = new JTextArea(1, 20);
    /*
     * dan untuk deklarasi yang terakhir, hanya untuk menambah efek yang lebih menarik, 
     * kita akan menggunakan font, gaya, dan pt yang berbeda.
     */
    Font font = new Font("Times new Roman", Font.BOLD, 14);

    /*
     * Buat Konstruktornya
     */
    public JKalkulator() throws HeadlessException {
        /*
         * Tentukan judul program
         */
        super("Kalkulator Sederhana");
        setDesign();
        setSize(LEBAR_APPS, TINGGI_APPS);

        /*
         * Frame tidak boleh diubah ukurannya, agar layout tidak rusak.
         */
        setResizable(false);

        /*
         * Tutup frame ketika aplikasi ingin ditutup
         */
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        /*
         * Oke, sekarang kita berpikir sejenak, bagaimana tata letak frame. 
         * Saya ingin menggunakan 5 baris, dan memiliki hingga 5 komponen untuk setiap baris.
         * 
         * Jadi saya akan menggunakan manajer GridLayout untuk ini.
         * dengan 5 yang pertama untuk jumlah baris, dan 5 yang kedua 
         * untuk berapa banyak komponen yang mungkin akan ditempatkan di tiap baris.
         */
        GridLayout grid = new GridLayout(5, 5);

        /*
         * Untuk mengatur layout kita akan menggunakan setLayout
         */
        setLayout(grid);

        /*
         * Mari kita menginisialisasi booleans yang kita buat diatas. 
         * Alih-alih menggunakan 4 baris kode untuk melakukan hal ini, 
         * kita dapat menggunakan perulangan.
         * 
         * Jadi mari kita memeriksa bagaimana loop bekerja. 
         * Kita menciptakan sebuah integer, i. jadi sementara i kurang dari 4, 
         * kita harus mengatur nilai function ke false, 
         * dengan menambahkan nilai i dengan satu setiap kali prose perulangan. 
         * Karena kita ingin function[0], function[1], function[2], dan function[3] 
         * harus ditetapkan sama dengan false, tidak ada yang lebih tinggi 
         * dan tidak lebih rendah. Sekarang pastikan kamu memahami konsep perulangan, 
         * karena kita akan menggunakan banyak dari mereka.
         */
        for (int i = 0; i < 4; i++) {
            function[i] = false;
        }

        /*
         * jika kita menggunakan manajer FlowLayout, 
         * komponen dijatuhkan di daerah dengan cara yang sama 
         * ketika kata-kata dibentuk pada halaman dalam bahasa Inggris. 
         * Dari kiri ke kanan, dan dari atas ke bawah.
         * 
         * Jadi saya pikir kita akan menggunakan FlowLayout untuk setiap barisnya 
         * sekarang. Ini akan menjadi cara kita mendirikan baris ke 1 
         * (yang akan kita sebut sebagai row[0] kemudian),
         */

        /*
         * Kita hanya akan menggunakan ini untuk baris ke 1
         */
        FlowLayout f1 = new FlowLayout(FlowLayout.CENTER);

        /*
         * Untuk sisa dari barisnya kita hanya perlu menyiapkan satu FlowLayout baru.         * 
         * 1 adalah bilangan bulat untuk gap horisontal dan gap vertikal.
         */
        FlowLayout f2 = new FlowLayout(FlowLayout.CENTER, 1, 1);

        /*
         * Mari sekarang kita menginisialisasi baris JPanel 
         * sehingga kita dapat menggunakannya. kita akan membuat perulangan untuk ini.
         */
        for (int i = 0; i < 5; i++) {
            row[i] = new JPanel();
        }

        /*
         * Sekarang kita dapat menggunakan barisnya!
         * 
         * Jadi mari kita mengatur layout yang kita dibuat ke baris ke 1. 
         * perintah dibawah ini akan membuat baris pertama menggunakan FlowLayout pertama.
         */
        row[0].setLayout(f1);

        /*
         * Sekarang karena kita menggunakan FlowLayout kedua untuk sisa dari barisnya 
         * kita dapat menggunakan perulangan.
         */
        for (int i = 1; i < 5; i++) {
            row[i].setLayout(f2);
        }

        /*
         * Karena ada beberapa hal yang sama harus kita lakukan untuk setiap tombol, 
         * mari kita membuat perulangan lainnya.
         * 
         * Oke sekarang mari kita lihat apa yang dilakukan kode dibawah ini. 
         * Kita sedang mengatur teks kita dalam tombol dengan teks yang sama 
         * dari buttonString. sehingga button[0] akan memiliki teks dari buttonString[0], 
         * button[1] akan memiliki teks dari buttonString[1] dan seterusnya. 
         * Di sini kita menyetting font kita untuk setiap tombol. 
         * .addActionListener(this); akan menjadi penting untuk nanti 
         * ketika kita perlu membuat tombol benar-benar bekerja.
         */
        for (int i = 0; i < 19; i++) {
            button[i] = new JButton();
            button[i].setText(buttonString[i]);
            button[i].setFont(font);
            button[i].addActionListener(this);
        }

        /*
         * Baik itu untuk tombol, tapi sekarang bagaimana dengan layar? 
         * Kita memiliki beberapa hal yang harus dilakukan dengan layar juga. 
         * Kita tidak perlu untuk menggunakan perulangan 
         * karena kita hanya berurusan dengan satu komponen sekarang.
         * 
         * Jadi di sini, kita menyetting font, 
         * membuat input tidak diperbolehkan melalui keyboard, 
         * dan pengaturannya sehingga input muncul dari kanan ke kiri pada layar.
         */
        display.setFont(font);
        display.setEditable(false);
        display.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        /*
         * Sekarang kita dapat bekerja pada pengaturan ukuran untuk semua komponennya. 
         * Jika kamu ingat benar kita menciptakan sebuah dimensi untuk tampilan,
         * tombol biasa, tombol-tombol pada kolom kanan, dan tombol nol. 
         * Untuk mengatur ukuran kita akan menggunakan component.setPreferredSize
         * 
         * Untuk pengaturan ukuran display
         */
        display.setPreferredSize(displayDimension);

        /*
         * Untuk tombol reguler dan tombol kolom kanan kita akan menggunakan loop.
         */
        for (int i = 0; i < 14; i++) {
            button[i].setPreferredSize(regularDimension);
        }
        for (int i = 14; i < 18; i++) {
            button[i].setPreferredSize(rColumnDimension);
        }

        /*
         * dan untuk nol, kita hanya perlu menggunakan ini
         */
        button[18].setPreferredSize(zeroButDimension);

        /*
         * Di sinilah kita menambahkan komponen ke panel, dan panel ke frame.
         * 
         * Untuk menambahkan komponen ke panel menggunakan panel.add(component);
         * Aku akan menunjukkan kepada kamu apa yang kita butuhkan,
         * 
         * Ini akan menambahkan display kita ke baris ke 1
         */
        row[0].add(display);

        /*
         * dan menambahkan baris ke 1 ke panel 
         */
        add(row[0]);

        /*
         * Baris lain akan berbeda karena kita akan menggunakan loop
         */
        for (int i = 0; i < 4; i++) {
            row[1].add(button[i]);
        }
        row[1].add(button[14]);
        add(row[1]);

        /*
         * Kami melakukan perulangan melalui 4 tombol pertama dan menambahkan mereka, 
         * kemudian kita tambahkan di tombol 15. Berikut adalah baris yang tersisa:
         */
        for (int i = 4; i < 8; i++) {
            row[2].add(button[i]);
        }
        row[2].add(button[15]);
        add(row[2]);

        for (int i = 8; i < 12; i++) {
            row[3].add(button[i]);
        }
        row[3].add(button[16]);
        add(row[3]);

        row[4].add(button[18]);
        for (int i = 12; i < 14; i++) {
            row[4].add(button[i]);
        }
        row[4].add(button[17]);
        add(row[4]);

        /*
         * tampilkan aplikasi, sebagai akhir kode untuk konstuktornya.
         */
        setVisible(true);


    }

    /*
     * Untuk menemukan sumber tombol yang ditekan kita menggunakan ActionEvent.getSource() == button 
     * karena kita menyebut ActionEvent dengan ae kita dapat menggunakan ae.getSource() 
     * sebagai gantinya. Jadi button[0] adalah 7. 
     * dengan JTextArea kita menggunakan append(string) untuk menambahkan teks kedalamnya.
     * Jadi jika sumber tombol kita adalah button[0] (yang merupakan tombol angka 7) 
     * display.append("7") ; 
     * Ini akan dilakukan untuk semua tombol yang menghasilkan teks dalam tampilan.
     * 
     * Sekarang pindah ke tombol fungsi kita (mengalikan, membagi, menambah, mengurangi). 
     * untuk sumber tombol yang ditekan kita perlu mengatur angka sementara kita pertama kali 
     * dari string pada layar. yaitu temporary[0] = Double.parseDouble(display.getText());
     * sekarang untuk fungsi yang kita lakukan kita perlu mengatur fungsi yang menjadi true.
     * sejak button[3] adalah fungsi add kita mengatur function[0] sama dengan true,
     * karena function[0] adalah untuk operasi menambahkan kita. 
     * sekarang kita harus mengatur ulang teks di layar untuk mendapatkan nilai
     * sementara kedua sebelum tombol sama dengan ditekan, 
     * dengan display.setText(""); Jadi sekarang melakukan hal ini dengan tombol lain 
     * yang memiliki fungsi. 
     * ingat bahwa (function[0] adalah menambahkan , function[1] adalah mengurangi, 
     * function[2] adalah mengalikan , dan function[3] adalah membagi) .
     * 
     * Sekarang cukup sederhana kita hanya perlu menggunakan metode-metode 
     * yang kita buat untuk tombol-tombol tersebut.
     * Tombol yang clear akan memanggil method clear(); , 
     * tombol plus minus akan memanggil getPosNeg(); , 
     * tombol akar kuadrat akan memanggil getSqrt(); , 
     * dan tombol sama dengan akan memanggil getResult();
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == button[0]) {
            display.append("7");
        }
        if (ae.getSource() == button[1]) {
            display.append("8");
        }
        if (ae.getSource() == button[2]) {
            display.append("9");
        }
        if (ae.getSource() == button[3]) {
            //add function[0]
            temporary[0] = Double.parseDouble(display.getText());
            function[0] = true;
            display.setText("");
        }
        if (ae.getSource() == button[4]) {
            display.append("4");
        }
        if (ae.getSource() == button[5]) {
            display.append("5");
        }
        if (ae.getSource() == button[6]) {
            display.append("6");
        }
        if (ae.getSource() == button[7]) {
            //subtract function[1]
            temporary[0] = Double.parseDouble(display.getText());
            function[1] = true;
            display.setText("");
        }
        if (ae.getSource() == button[8]) {
            display.append("1");
        }
        if (ae.getSource() == button[9]) {
            display.append("2");
        }
        if (ae.getSource() == button[10]) {
            display.append("3");
        }
        if (ae.getSource() == button[11]) {
            //multiply function[2]
            temporary[0] = Double.parseDouble(display.getText());
            function[2] = true;
            display.setText("");
        }
        if (ae.getSource() == button[12]) {
            display.append(".");
        }
        if (ae.getSource() == button[13]) {
            //divide function[3]
            temporary[0] = Double.parseDouble(display.getText());
            function[3] = true;
            display.setText("");
        }
        if (ae.getSource() == button[14]) {
            clear();
        }
        if (ae.getSource() == button[15]) {
            getSqrt();
        }
        if (ae.getSource() == button[16]) {
            getPosNeg();
        }
        if (ae.getSource() == button[17]) {
            getResult();
        }
        if (ae.getSource() == button[18]) {
            display.append("0");
        }
    }

    public static void main(String[] arguments) {
        JKalkulator k = new JKalkulator();

    }

    /*
     * Ini adalah metode setDesign, untuk info lebih lanjut tentang tampilan dan nuansa 
     * lihat ke Java LookAndFeel.
     * Karena menggunakan NimbusLookAndFeel kita membutuhkan Java 7.
     */
    public final void setDesign() {
        try {
            UIManager.setLookAndFeel(
                    "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            // kode untuk eksepsi
        }
    }

    /*
     * Kita memiliki beberapa metode yang harus dibuat sekarang. 
     * Salah satu metode untuk tombol clear, 
     * salah satu metode untuk tombol plus minus, 
     * salah satu metode untuk akar kuadrat, 
     * dan satu metode untuk mendapatkan hasilnya dari fungsi. 
     */
    /*
     * method untuk tombol clear
     */
    public void clear() {
        try {
            display.setText(""); // Sets the display blank
            for (int i = 0; i < 4; i++) {
                function[i] = false; // Sets the functions back to false
            }
            for (int i = 0; i < 2; i++) {
                temporary[i] = 0; // Sets our temporary variables back to 0
            }
        } catch (NullPointerException e) {
        }
    }

    /*
     * Method untuk tombol akar kuadrat
     */
    public void getSqrt() {
        try {
            double value = Math.sqrt(Double.parseDouble(display.getText())); // Create a variable for value, and use Math's square root to find value
            display.setText(Double.toString(value)); // Sets display to new value
        } catch (NumberFormatException e) {
        }
    }

    /*
     * Method untuk tombol plus minus
     */
    public void getPosNeg() {
        try {
            double value = Double.parseDouble(display.getText()); // again we create a variable for our current value
            if (value != 0) { // if the value isn't 0
                value = value * (-1); // we multiply it by -1 to get it's opposite value
                display.setText(Double.toString(value)); // set the text to the new value.
            } else {
            }
        } catch (NumberFormatException e) {
        }
    }

    /*
     * method untuk mendapatkah hasil perhitungan
     */
    public void getResult() {
        double result = 0;  // variable for result
        temporary[1] = Double.parseDouble(display.getText()); //our second temporary number from display
        String temp0 = Double.toString(temporary[0]); //necessary string for text of first temp
        String temp1 = Double.toString(temporary[1]); //necessary string for text of second temp
        try {
            if (temp0.contains("-")) { //if first string contains -
                String[] temp00 = temp0.split("-", 2); //split into two strings at -
                temporary[0] = (Double.parseDouble(temp00[1]) * -1); //puts string back in double with the real value.
            }
            if (temp1.contains("-")) { // same as above with second temporary
                String[] temp11 = temp1.split("-", 2);
                temporary[1] = (Double.parseDouble(temp11[1]) * -1);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        try {
            if (function[2] == true) //we start off with multiplication obviously
            {
                result = temporary[0] * temporary[1]; //sets result to multiplication of function
            } else if (function[3] == true) //now division
            {
                result = temporary[0] / temporary[1];
            } else if (function[0] == true) //now addition
            {
                result = temporary[0] + temporary[1];
            } else if (function[1] == true) //now subtraction
            {
                result = temporary[0] - temporary[1];
            }
            display.setText(Double.toString(result)); //display now has result
            for (int i = 0; i < 4; i++) {
                function[i] = false; //set all the functions back to false
            }
        } catch (NumberFormatException e) {
            // kode eksepsi untuk hasil perhitungan
        }
    }
}
