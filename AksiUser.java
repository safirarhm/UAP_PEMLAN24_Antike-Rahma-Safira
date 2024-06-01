package UAP;

import java.util.Scanner;
import java.util.Map;

public class AksiUser extends Aksi {
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void tampilanAksi() {
        System.out.println("Aksi User:");
        System.out.println("1. Pesan Film");
        System.out.println("2. Lihat List Film");
        System.out.println("3. Lihat Pesanan");
        System.out.println("4. Lihat Saldo");
        System.out.println("5. Logout");
        System.out.println("6. Tutup Aplikasi");
    }

    @Override
    public void keluar() {
        Akun.logout();
        System.out.println("Anda telah logout.");
    }

    @Override
    public void tutupAplikasi() {
        System.out.println("Aplikasi ditutup.");
        System.exit(0);
    }

    @Override
    public void lihatListFilm() {
        Map<String, Film> films = Film.getFilms();
        for (Film film : films.values()) {
            System.out.println(film.getName() + " - " + film.getDescription() + " - Harga: " + film.getPrice() + " - Stok: " + film.getStock());
        }
    }

    public void lihatSaldo() {
        System.out.println("Saldo anda: " + Akun.getCurrentUser().getSaldo());
    }

    public void pesanFilm() {
        System.out.print("Nama Film yang ingin dipesan: ");
        String namaFilm = scanner.nextLine();
        Film film = Film.getFilms().get(namaFilm);

        if (film == null) {
            System.out.println("Film yang dicari tidak ditemukan.");
            return;
        }

        System.out.print("Jumlah tiket yang ingin dipesan: ");
        int jumlah = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        if (jumlah > film.getStock()) {
            System.out.println("Stok tiket tidak mencukupi.");
            return;
        }

        double totalHarga = jumlah * film.getPrice();
        System.out.println("Harga satuan tiket " + film.getPrice());
        System.out.println("Total harga " + totalHarga);
        
        if (totalHarga > Akun.getCurrentUser().getSaldo()) {
            System.out.println("Saldo tidak mencukupi, saldo yang dimiliki " + Akun.getCurrentUser().getSaldo());
            return;
        }

        film.setStock(film.getStock() - jumlah);
        Akun.getCurrentUser().setSaldo(Akun.getCurrentUser().getSaldo() - totalHarga);

        Akun.getCurrentUser().addPesanan(film, jumlah);
        System.out.println("Tiket berhasil dipesan.");
    }

    public void lihatPesanan() {
        Map<String, Pesanan> pesanan = Akun.getCurrentUser().getPesanan();
        if (pesanan.isEmpty()) {
            System.out.println("Kamu belum pernah melakukan pemesanan.");
        } else {
            for (Pesanan p : pesanan.values()) {
                System.out.println("Film: " + p.getFilm().getName() + " - Jumlah: " + p.getKuantitas() + " - Total Harga: " + (p.getKuantitas() * p.getFilm().getPrice()));
            }
        }
    }
}
