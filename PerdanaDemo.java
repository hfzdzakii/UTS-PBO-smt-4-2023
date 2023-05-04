import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class PerdanaDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Holder> holder = new ArrayList<>();

        while (true) {
            Grosir grosir = new Grosir("Semarang");
            holder.add(new Holder(grosir.barCode,
                    grosir.hrgBeli, grosir.hrgJual, grosir.jmlJual,
                    grosir.totJual, grosir.jnsByr, grosir.noNota,
                    grosir.tempo, grosir.bunga, grosir.ppn, grosir.totByr,
                    grosir.nmKartu, grosir.ketByr, grosir.souvernir));

            System.out.print("Tambah Data Lagi y/n ? ");
            if (sc.next().equalsIgnoreCase("n")) {
                break;
            }
        }
        System.out.println("\n");
        Grosir.cetak(holder);
        sc.close();
    }
}

class Holder {
    int barCode, hrgBeli, hrgJual, jmlJual, totJual,
            jnsByr, noNota, tempo, bunga, ppn, totByr;
    String nmKartu, ketByr, souvernir;

    Holder(int barCode, int hrgBeli, int hrgJual, int jmlJual,
            int totJual, int jnsByr, int noNota, int tempo,
            int bunga, int ppn, int totByr, String nmKartu, String ketByr,
            String souvernir) {
        this.barCode = barCode;
        this.hrgBeli = hrgBeli;
        this.hrgJual = hrgJual;
        this.jmlJual = jmlJual;
        this.totJual = totJual;
        this.jnsByr = jnsByr;
        this.noNota = noNota;
        this.tempo = tempo;
        this.bunga = bunga;
        this.ppn = ppn;
        this.totByr = totByr;
        this.nmKartu = nmKartu;
        this.ketByr = ketByr;
        this.souvernir = souvernir;
    }
}

class Perdana {
    int barCode, hrgBeli, hrgJual;
    String nmKartu;
    Scanner sc = new Scanner(System.in);

    public void inputPerdana() {
        System.out.print("Masukkan Barcode\t: ");
        barCode = sc.nextInt();
        System.out.print("Masukkan Nama Produk\t: ");
        nmKartu = sc.next();
        System.out.print("Masukkan Harga Beli\t: ");
        hrgBeli = sc.nextInt();
    }
}

class Grosir extends Perdana {
    String ketByr, souvernir;
    static String cabang;
    int jmlJual, totJual, jnsByr, noNota, tempo, bunga, ppn, totByr;
    Scanner sc = new Scanner(System.in);

    Grosir(String cabangg) {
        cabang = cabangg;
        hitung();
    }

    public void setNota(int noNota) {
        this.noNota = noNota;
    }

    public void inputJumlah() {
        System.out.print("Masukkan Jumlah Jual\t: ");
        this.jmlJual = sc.nextInt();
    }

    public void inputJenis() {
        System.out.print("Masukkan Jenis Bayar\t: ");
        jnsByr = sc.nextInt();
        if (jnsByr == 1) {
            ketByr = "Cash";
        } else {
            ketByr = "Tempo";
        }
    }

    public void inputTempo() {
        System.out.print("Masukkan Waktu Tempo\t: ");
        tempo = sc.nextInt();
    }

    public void setTotJual() {
        totJual = hrgBeli * jmlJual;
    }

    public void setBunga() {
        if (tempo > 1) {
            this.bunga = 5 * getHargaJual() * jmlJual / 100;
        } else {
            this.bunga = 0;
        }
    }

    public void setPPN() {
        this.ppn = 11 * (getHargaJual() * jmlJual) / 100;
    }

    public int getHargaJual() {
        hrgJual = hitungJual();
        return hrgJual;
    }

    public int hitungJual() {
        if (hrgBeli > 200000) {
            hrgJual = hrgBeli + 50 * hrgBeli / 100;
        } else if (hrgBeli > 100000) {
            hrgJual = hrgBeli + 75 * hrgBeli / 100;
        } else if (hrgBeli > 50000) {
            hrgJual = hrgBeli + hrgBeli;
        } else if (hrgBeli > 25000) {
            hrgJual = hrgBeli + 150 * hrgBeli / 100;
        } else {
            hrgJual = hrgBeli + 200 * hrgBeli / 100;
        }
        return hrgJual;
    }

    public void setTotByr() {
        totByr = getHargaJual() * jmlJual + ppn + bunga;
    }

    public void setSouvernir() {
        if (jnsByr == 1) {
            if (totByr >= 100000 && totByr <= 500000) {
                souvernir = "Mug";
            } else if (totByr > 500000 && totByr <= 750000) {
                souvernir = "Payung";
            } else if (totByr > 750000 && totByr <= 1000000) {
                souvernir = "Tas";
            } else if (totByr > 1000000) {
                souvernir = "Voucher 100rb";
            }
        }
    }

    public void hitung() {

        Random rn = new Random();
        inputPerdana();
        inputJumlah();
        inputJenis();
        inputTempo();

        setNota(rn.nextInt(9999 - 1000) + 1000);
        setTotJual();
        setPPN();
        setBunga();
        setTotByr();
        setSouvernir();
    }

    public static void cetak(ArrayList<Holder> tes) {
        System.out.println("GROSIR KARTU PERDANA - " + cabang);
        System.out.println(
                "---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf(
                "%10s %10s %15s %15s %15s %15s %15s %15s %15s %15s %15s %15s %15s %15s",
                "No Nota", "Barcode", "Nama Produk",
                "Harga Beli", "Harga Jual", "Jml. Jual", "Total",
                "Jenis Bayar", "Ket. Bayar", "Waktu Tempo", "PPN",
                "Bunga", "Total Bayar", "Souvernir");
        System.out.println();
        System.out.println(
                "---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        for (int i = 0; i < tes.size(); i++) {
            Holder holder = tes.get(i);
            System.out.printf(
                    "%10s %10s %15s %15s %15s %15s %15s %15s %15s %9s Bulan %15s %15s %15s %15s\n",
                    holder.noNota, holder.barCode, holder.nmKartu,
                    holder.hrgBeli, holder.hrgJual, holder.jmlJual,
                    holder.totJual, holder.jnsByr, holder.ketByr,
                    holder.tempo, holder.ppn, holder.bunga,
                    holder.totByr, holder.souvernir);
        }

    }
}
